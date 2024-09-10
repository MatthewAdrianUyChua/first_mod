package net.matthew.first_mod.block.entity.custom;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.block.custom.Cat_Bowl_Block;
import net.matthew.first_mod.block.entity.ModEntities;
import net.matthew.first_mod.block.entity.goal.*;
import net.matthew.first_mod.datagen.ModBlockStateProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class CatEntity extends TamableAnimal implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static Block blockTarget;

    public static Block blockTargetSharpen;

    public static BlockPos targetPosSharpen;

    public static BlockPos targetPos;

    public static Block blockTargetFood;

    public static BlockPos targetPosFood;

    private static long lastMovedTime = System.currentTimeMillis();

    public static long lastSharpenTime = System.currentTimeMillis();

    public boolean following = false; //if false mob wont follow player if true mob will follow player

    public int interactSetting;

    public static boolean finishedEating;

    public static boolean isSitting = false;

    private static long sittingTime = System.currentTimeMillis();

    public boolean isSleeping = false;

    public boolean focus = false;

    public CatEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.5f)
                .build();
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new WalkToFoodGoal(this, 0.8F, ModBlocks.CAT_BOWL.get(), this));
        this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new CustomFollowOwnerGoal(this, 0.5F, 10.0F, 5.0F, false, this));
        this.goalSelector.addGoal(5, new WalkToBlockGoal(this, 0.5F, Blocks.RED_BED, this));
        this.goalSelector.addGoal(6, new WalkToBlockSharpenGoal(this, 0.5F, Blocks.OAK_LOG, this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5f));
        this.goalSelector.addGoal(8, new CustomRandomLookAroundGoal(this, this));
        this.goalSelector.addGoal(9, new CustomLookAtPlayerGoal(this, Player.class, 10.0F, this));

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.CAT.get().create(pLevel);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isTame()) {
            if (this.isInLove()) {
                return SoundEvents.CAT_PURR;
            } else {
                return this.random.nextInt(4) == 0 ? SoundEvents.CAT_PURREOW : SoundEvents.CAT_AMBIENT;
            }
        } else {
            return SoundEvents.CAT_STRAY_AMBIENT;
        }
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {

        try {
            //System.out.println(isSleeping);
            //System.out.println("BLOCK SHARPEN TARGET" + (this.blockPosition().closerThan(targetPosSharpen, 1.5) && (blockTargetSharpen.equals(Blocks.OAK_LOG) == true)));
            //System.out.println("BLOCK TARGET" + (this.blockPosition().closerThan(targetPos, 1.5) && (blockTarget.equals(ModBlocks.CAT_BOWL.get()) == true)));
            if (!tAnimationState.isMoving()) {
                //System.out.println(isSitting);

                if((System.currentTimeMillis() - lastMovedTime >= 2500 && isSitting == false)){
                    //System.out.println("PLAY LAYING DOWN");
                    tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.layingDown", Animation.LoopType.PLAY_ONCE).then("animation.cat.layingDownLoop", Animation.LoopType.LOOP));
                    return PlayState.CONTINUE;
                }else if(isSitting == true){
                    if(this.isSleeping == true){
                        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.sleep", Animation.LoopType.LOOP));
                    }else{
                        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.sit", Animation.LoopType.LOOP));
                    }
                    return PlayState.CONTINUE;
                }

                if(targetPosSharpen != null && blockTargetSharpen != null) {
                    if (this.blockPosition().closerThan(targetPosSharpen, 1.5) && (blockTargetSharpen.equals(Blocks.OAK_LOG) == true)) {

                        //System.out.println("SHAPREN");


                        lastMovedTime = System.currentTimeMillis();  // Reset the timer when the entity moves

                        this.getLookControl().setLookAt(targetPosSharpen.getX(), targetPosSharpen.getY() + 1.0, targetPosSharpen.getZ());

                        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.sharpen", Animation.LoopType.PLAY_ONCE));

                        if (tAnimationState.getController().hasAnimationFinished()) {
                            targetPosSharpen = null;
                            blockTargetSharpen = null;
                            lastSharpenTime = System.currentTimeMillis();
                        }

                        return PlayState.CONTINUE;

                    }
                }

                if(targetPosFood != null && blockTargetFood != null) {
                    if (this.blockPosition().closerThan(targetPosFood, 1.5) && (blockTargetFood.equals(ModBlocks.CAT_BOWL.get()) == true) && (this.level().getBlockState(targetPosFood).getValue(ModBlockStateProvider.STATES_TWO) == 1)) {

                        //System.out.println("FOOD");


                        lastMovedTime = System.currentTimeMillis();  // Reset the timer when the entity moves

                        this.getLookControl().setLookAt(targetPosFood.getX(), targetPosFood.getY() + 1.0, targetPosFood.getZ());

                        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.eat", Animation.LoopType.PLAY_ONCE));

                        if (tAnimationState.getController().hasAnimationFinished()) {
                            targetPosFood = null;
                            blockTargetFood = null;
                            finishedEating = true;
                        }
                        return PlayState.CONTINUE;
                    }
                }

                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.idle", Animation.LoopType.LOOP));
                return PlayState.CONTINUE;
            }
        }catch(NullPointerException e){
            //System.out.println("ERROR");
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }catch (IllegalArgumentException e){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

            if (tAnimationState.isMoving()) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.walk", Animation.LoopType.LOOP));
                lastMovedTime = System.currentTimeMillis();;  // Reset the timer when the entity moves
                return PlayState.CONTINUE;
            }

            return PlayState.CONTINUE;
    }

    private long generateSharpenCooldown(){
        return random.nextInt(50000 - 20000 + 1) + 20000;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.tickLeash();
            if (this.tickCount % 5 == 0) {
                this.updateControlFlags();
                if(finishedEating == true && (targetPosFood != null && blockTargetFood != null)){
                    this.level().setBlock(targetPosFood, this.level().getBlockState(targetPosFood).setValue(ModBlockStateProvider.STATES_TWO, 0), 3);
                    finishedEating = false;
                }

                if(isSitting == false){
                    this.setNoAi(false);
                }else{
                    if(System.currentTimeMillis() - sittingTime >= 17250){
                        this.setNoAi(true);
                    }
                }

                System.out.println(targetPos);
                if(targetPos != null) {
                    if (this.blockPosition().closerThan(targetPos,  1.0)) {
                        targetPos = null;
                        this.interactSetting = 1;
                        isSitting = true;
                        this.setOrderedToSit(true);
                    }
                }
            }
        }else{
            if(isSitting == false){
                sittingTime = System.currentTimeMillis();
                this.isSleeping = false;
            }else{
                if(System.currentTimeMillis() - sittingTime >= 17250){
                    //System.out.println("SLEEP");
                    this.isSleeping = true;
                }
            }

            if(targetPos != null) {
                if (this.blockPosition().closerThan(targetPos,  1.0)) {
                    targetPos = null;
                    this.interactSetting = 1;
                    isSitting = true;
                    this.setOrderedToSit(true);
                }
            }
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if(!this.level().isClientSide) {
            if (hand == InteractionHand.MAIN_HAND) {
                // Check if the item is something you want to tame with (e.g., Bone for Wolves)
                if (itemstack.getItem() == Items.SALMON && !this.isTame()) {
                    if (!this.level().isClientSide) {
                        // Tame the entity
                        this.tame(player);

                        // Reduce the item count
                        itemstack.shrink(1);

                        // Optional: Make the entity love the player upon taming
                        this.level().broadcastEntityEvent(this, (byte) 7);  // 7 = hearts animation

                        return InteractionResult.SUCCESS;
                    }
                } else{
                    switch (interactSetting){
                        case 1: //makes the entity follow
                            System.out.println("CASE 1" + interactSetting);
                            isSitting = false;
                            following = true;
                            interactSetting++;
                            this.setOrderedToSit(false);
                            break;
                        case 2: //makes the entity sit
                            System.out.println("CASE 2" + interactSetting);
                            this.setOrderedToSit(true);
                            //this.setInSittingPose(true);
                            isSitting = true;
                            interactSetting++;
                            break;
                        case 3: //makes the entity roam around
                            System.out.println("CASE 3" + interactSetting);
                            this.setOrderedToSit(false);
                            //this.setInSittingPose(false);
                            isSitting = false;
                            following = false;
                            interactSetting = 1;
                            break;
                        default:
                            System.out.println("CASE 0" + interactSetting);
                            following = true;
                            interactSetting = 2;
                            break;
                    }
                }
            }
        }

        return super.mobInteract(player, hand);
    }

    // Save taming data
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Tamed", this.isTame());
        if (this.getOwnerUUID() != null) {
            compound.putUUID("Owner", this.getOwnerUUID());
            compound.putInt("Interact", this.interactSetting);
        }
    }

    // Load taming data
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Tamed")) {
            this.setTame(compound.getBoolean("Tamed"));
        }

        if (compound.contains("Owner")) {
            this.setOwnerUUID(compound.getUUID("Owner"));
            this.setInteract(compound.getInt("Interact"));
        }

        switch (this.interactSetting){
            case 1: //makes the entity follow
                System.out.println("CASE 1" + this.interactSetting);
                following = true;
                this.interactSetting++;
                break;
            case 2: //makes the entity sit
                System.out.println("CASE 2" + this.interactSetting);
                this.setOrderedToSit(true);
                //this.setInSittingPose(true);
                isSitting = true;
                this.interactSetting++;
                break;
            case 3: //makes the entity roam around
                System.out.println("CASE 3" + this.interactSetting);
                this.setOrderedToSit(false);
                //this.setInSittingPose(false);
                isSitting = false;
                following = false;
                this.interactSetting = 1;
                break;
            default:
                System.out.println("CASE 0" + this.interactSetting);
                following = true;
                this.interactSetting = 2;
                break;
        }
    }

    public void setInteract(int i){
        this.interactSetting = i - 1;
    }

    public class WalkToFoodGoal extends Goal {
        private final Mob mob;
        private final double speed;

        private final Block block;

        private final CatEntity cat;

        public WalkToFoodGoal(Mob mob, double speed, Block block, CatEntity cat) {
            this.mob = mob;
            this.speed = speed;
            this.block = block;
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.cat = cat;
        }

        @Override
        public boolean canUse() {
            targetPosFood = findNearestOakLog();

            if (targetPosFood != null && (mob.level().getBlockState(targetPosFood).getValue(ModBlockStateProvider.STATES_TWO) == 1)) {
                cat.focus = true;
                return true;
            }
            cat.focus = false;
            return false;
        }

        @Override
        public void start() {
            if (targetPosFood != null) {
                BlockPos adjacentPos = findAdjacentAirBlock(targetPosFood);
                if (adjacentPos != null) {

                    blockTargetFood = block;
                    // Move to the adjacent block
                    mob.getNavigation().moveTo(adjacentPos.getX(), adjacentPos.getY(), adjacentPos.getZ(), speed);

                    // Make the mob face the oak log when it arrives
                    mob.getLookControl().setLookAt(targetPosFood.getX(), targetPosFood.getY() + 1.0, targetPosFood.getZ());

                } else {
                    // Fallback to moving to the log's position
                    mob.getNavigation().moveTo(targetPosFood.getX(), targetPosFood.getY(), targetPosFood.getZ(), speed);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !mob.getNavigation().isDone() && targetPosFood != null && isOakLog(targetPosFood) && (mob.level().getBlockState(targetPosFood).getValue(ModBlockStateProvider.STATES_TWO) == 1);
        }

        private BlockPos findNearestOakLog() {
            Level world = mob.level();
            BlockPos mobPos = mob.blockPosition();
            int radius = 16;  // Horizontal search radius

            // Define vertical search limits
            int minY = mobPos.getY() - 0;  // 4 blocks below the mob
            int maxY = mobPos.getY() + 0;  // 4 blocks above the mob

            // Ensure minY and maxY are within world height limits
            minY = Math.max(world.getMinBuildHeight(), minY);
            maxY = Math.min(world.getMaxBuildHeight(), maxY);

            // Iterate over the specified range
            for (int x = mobPos.getX() - radius; x <= mobPos.getX() + radius; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = mobPos.getZ() - radius; z <= mobPos.getZ() + radius; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (isOakLog(pos)) {
                            //System.out.println("Found Oak Log at: " + pos);
                            return pos;
                        }
                    }
                }
            }
            return null;
        }

        private boolean isOakLog(BlockPos pos) {
            //System.out.println("Block at " + pos + ": " + mob.level().getBlockState(pos).getBlock());
            return ((mob.level().getBlockState(pos).getBlock() == block) && (mob.level().getBlockState(pos).getValue(ModBlockStateProvider.STATES_TWO) == 1)) ;
        }

        private BlockPos findAdjacentAirBlock(BlockPos logPos) {
            // List of adjacent offsets (4 horizontal directions)
            BlockPos[] adjacentOffsets = {
                    logPos.north(), logPos.south(), logPos.east(), logPos.west()
            };

            for (BlockPos adjacentPos : adjacentOffsets) {
                if (mob.level().isEmptyBlock(adjacentPos)) {
                    return adjacentPos;
                }
            }

            // No adjacent air block found
            return null;
        }

    }


    public class WalkToBlockSharpenGoal extends Goal {
        private final Mob mob;
        private final double speed;

        private final Block block;

        private final CatEntity cat;

        public WalkToBlockSharpenGoal(Mob mob, double speed, Block block, CatEntity cat) {
            this.mob = mob;
            this.speed = speed;
            this.block = block;
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.cat = cat;
        }

        @Override
        public boolean canUse() {
            targetPosSharpen = findNearestOakLog();

            if(targetPosSharpen != null){
                if(System.currentTimeMillis() - lastSharpenTime >= 20000) {
                    cat.focus = true;
                    return true;
                }
            }
            cat.focus = false;
            return false;
        }

        @Override
        public void start() {
            if (targetPosSharpen != null) {
                if (targetPosSharpen != null) {

                    blockTargetSharpen = block;
                    // Move to the adjacent block
                    mob.getNavigation().moveTo(targetPosSharpen.getX(), targetPosSharpen.getY(), targetPosSharpen.getZ(), speed);

                    // Make the mob face the oak log when it arrives
                    mob.getLookControl().setLookAt(targetPosSharpen.getX(), targetPosSharpen.getY() + 1.0, targetPosSharpen.getZ());

                } else {
                    // Fallback to moving to the log's position
                    mob.getNavigation().moveTo(targetPosSharpen.getX(), targetPosSharpen.getY(), targetPosSharpen.getZ(), speed);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !mob.getNavigation().isDone() && targetPosSharpen != null && isOakLog(targetPosSharpen);
        }

        private BlockPos findNearestOakLog() {
            Level world = mob.level();
            BlockPos mobPos = mob.blockPosition();
            int radius = 16;  // Horizontal search radius

            // Define vertical search limits
            int minY = mobPos.getY() - 0;  // 4 blocks below the mob
            int maxY = mobPos.getY() + 0;  // 4 blocks above the mob

            // Ensure minY and maxY are within world height limits
            minY = Math.max(world.getMinBuildHeight(), minY);
            maxY = Math.min(world.getMaxBuildHeight(), maxY);

            // Iterate over the specified range
            for (int x = mobPos.getX() - radius; x <= mobPos.getX() + radius; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = mobPos.getZ() - radius; z <= mobPos.getZ() + radius; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (isOakLog(pos)) {
                            //System.out.println("Found Oak Log at: " + pos);
                            return pos;
                        }
                    }
                }
            }
            return null;
        }

        private boolean isOakLog(BlockPos pos) {
            //System.out.println("Block at " + pos + ": " + mob.level().getBlockState(pos).getBlock());
            return mob.level().getBlockState(pos).getBlock() == block;
        }

    }

    public class WalkToBlockGoal extends Goal {
        private final Mob mob;
        private final double speed;

        private final Block block;

        private final CatEntity cat;

        private int tickCounter;  // Tracks ticks for next start

        private static final int TICK_THRESHOLD = 80;  // Delay between each goal usage

        public WalkToBlockGoal(Mob mob, double speed, Block block, CatEntity cat) {
            this.mob = mob;
            this.speed = speed;
            this.block = block;
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.cat = cat;
            this.tickCounter = 0;  // Initialize the tick counter
        }

        @Override
        public boolean canUse() {

            // Increment tick counter and only perform action every TICK_THRESHOLD ticks
            if (tickCounter++ < TICK_THRESHOLD) {
                return false;  // Skip the check if we're not at the tick threshold
            }
            // Reset the counter once we reach the threshold
            tickCounter = 0;

            try{
                if(!(mob.blockPosition().closerThan(findNearestOakLog(),  1.0))){
                    targetPos= findNearestOakLog();

                    if(targetPos != null){
                        return true;
                    }
                }
                return false;
            }catch(NullPointerException e){
                return false;
            }
        }

        @Override
        public void start() {
            if (targetPos != null) {
                if (targetPos != null) {

                    blockTarget = block;
                    // Move to the adjacent block
                    mob.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);

                    // Make the mob face the oak log when it arrives
                    mob.getLookControl().setLookAt(targetPos.getX(), targetPos.getY() + 1.0, targetPos.getZ());

                } else {
                    // Fallback to moving to the log's position
                    mob.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !mob.getNavigation().isDone() && targetPos != null && isOakLog(targetPos) && !(mob.blockPosition().closerThan(findNearestOakLog(),  1.0));
        }

        protected int nextStartTick(PathfinderMob pCreature) {
            return 40;
        }

        private BlockPos findNearestOakLog() {
            Level world = mob.level();
            BlockPos mobPos = mob.blockPosition();
            int radius = 8;  // Horizontal search radius

            // Define vertical search limits
            int minY = mobPos.getY() - 0;  // 4 blocks below the mob
            int maxY = mobPos.getY() + 0;  // 4 blocks above the mob

            // Ensure minY and maxY are within world height limits
            minY = Math.max(world.getMinBuildHeight(), minY);
            maxY = Math.min(world.getMaxBuildHeight(), maxY);

            // Iterate over the specified range
            for (int x = mobPos.getX() - radius; x <= mobPos.getX() + radius; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = mobPos.getZ() - radius; z <= mobPos.getZ() + radius; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (isOakLog(pos)) {
                            //System.out.println("Found Oak Log at: " + pos);
                            return pos;
                        }
                    }
                }
            }
            return null;
        }

        private boolean isOakLog(BlockPos pos) {
            //System.out.println("Block at " + pos + ": " + mob.level().getBlockState(pos).getBlock());
            return mob.level().getBlockState(pos).getBlock() == block;
        }

    }

}
