package net.matthew.first_mod.block.entity.custom;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.block.custom.Cat_Bowl_Block;
import net.matthew.first_mod.block.entity.ModEntities;
import net.matthew.first_mod.block.entity.goal.*;
import net.matthew.first_mod.datagen.ModBlockStateProvider;
import net.matthew.first_mod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.BlockState;
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
import java.util.function.Predicate;

public class CatEntity extends TamableAnimal implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final EntityDataAccessor<BlockPos> targetPosSharpen = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BLOCK_POS);

    private static final EntityDataAccessor<BlockPos> targetPosFood = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BLOCK_POS);

    private static final EntityDataAccessor<Long> lastSharpenTime = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.LONG);

    private static final EntityDataAccessor<Boolean> following = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> interactSetting = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> finishedEating = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> isSitting = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> isSleeping = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> focus = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> isKneading = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Long> sittingTime = SynchedEntityData.defineId(CatEntity.class, EntityDataSerializers.LONG);

    private long eatingTime = System.currentTimeMillis();

    private long scratchingTime = System.currentTimeMillis();

    private long lastMovedTime = System.currentTimeMillis();

    private long kneadingTime = System.currentTimeMillis();

    public BlockPos targetPos;

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
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new WalkToFoodGoal(this, 0.8F, ModBlocks.CAT_BOWL.get(), this));
        this.goalSelector.addGoal(4, new CustomFollowOwnerGoal(this, 0.5F, 10.0F, 5.0F, false, this));
        this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(6, new OcelotAttackGoal(this));
        this.goalSelector.addGoal(7, new WalkToBlockGoal(this, 0.5F, Blocks.RED_BED, this));
        this.goalSelector.addGoal(8, new WalkToBlockSharpenGoal(this, 0.5F, Blocks.OAK_LOG, this));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 0.5f));
        this.goalSelector.addGoal(10, new CustomRandomLookAroundGoal(this, this));
        this.goalSelector.addGoal(11, new CustomLookAtPlayerGoal(this, Player.class, 10.0F, this));

        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Rabbit.class, false, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));

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

            if(this.isSitting() == true){
                if(this.isSleeping() == true){
                    tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.sleep", Animation.LoopType.LOOP));
                }else if(this.isKneading() == true){
                    tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.knead", Animation.LoopType.PLAY_ONCE));
                    return PlayState.CONTINUE;
                }else{
                    tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.sit", Animation.LoopType.LOOP));
                }
                return PlayState.CONTINUE;
            }

            if(this.isKneading() == true){
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.knead", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;
            }


            if (!tAnimationState.isMoving()) {
                //System.out.println(isSitting);

                if((System.currentTimeMillis() - lastMovedTime >= 2500 && this.isSitting() == false)){
                    //System.out.println("PLAY LAYING DOWN");
                    tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.layingDown", Animation.LoopType.PLAY_ONCE).then("animation.cat.layingDownLoop", Animation.LoopType.LOOP));
                    return PlayState.CONTINUE;
                }

                if(this.targetPosSharpen() != null) {
                    if (this.blockPosition().closerThan(this.targetPosSharpen(), 1.5)) {

                        //System.out.println("SHAPREN");


                        lastMovedTime = System.currentTimeMillis();  // Reset the timer when the entity moves

                        this.getLookControl().setLookAt(this.targetPosSharpen().getX(), this.targetPosSharpen().getY() + 1.0, this.targetPosSharpen().getZ());

                        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.sharpen", Animation.LoopType.PLAY_ONCE));

                        return PlayState.CONTINUE;

                    }
                }


                if(this.targetPosFood() != null) {
                    if (this.blockPosition().closerThan(this.targetPosFood(), 1.5) && (this.level().getBlockState(this.targetPosFood()).getValue(ModBlockStateProvider.STATES_TWO) == 1)) {

                        //System.out.println("FOOD");


                        lastMovedTime = System.currentTimeMillis();  // Reset the timer when the entity moves

                        this.getLookControl().setLookAt(this.targetPosFood().getX(), this.targetPosFood().getY() + 1.0, this.targetPosFood().getZ());

                        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cat.eat", Animation.LoopType.PLAY_ONCE));

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

                try{
                    if(!(this.blockPosition().closerThan(this.targetPosFood(), 1.5)) && !(this.level().getBlockState(this.targetPosFood()).getValue(ModBlockStateProvider.STATES_TWO) == 1)){
                        //System.out.println("RESET");
                        this.eatingTime = System.currentTimeMillis();
                    }else{
                        if(System.currentTimeMillis() - this.eatingTime >= 7000) {
                            //System.out.println("DONE");
                            this.eatingTime = System.currentTimeMillis();
                            this.setEating(true);
                        }
                    }
                }catch(NullPointerException e){
                    this.eatingTime = System.currentTimeMillis();
                }catch(IllegalArgumentException e){
                    this.eatingTime = System.currentTimeMillis();
                }

                try{
                    if(!(this.blockPosition().closerThan(this.targetPosSharpen(), 1.5))){
                        this.scratchingTime = System.currentTimeMillis();
                    }else{
                        if(System.currentTimeMillis() - this.scratchingTime >= 5000){
                            this.scratchingTime = System.currentTimeMillis();
                            this.setLastSharpenTime(System.currentTimeMillis());
                        }
                    }
                }catch(NullPointerException e){
                    this.scratchingTime = System.currentTimeMillis();
                }

                if(this.targetPosFood() != null && this.targetPosFood() != BlockPos.ZERO){
                    //System.out.println((this.finishedEating() == true));
                    if(this.finishedEating() == true){
                        //System.out.println("DONE");
                        this.eatingTime = System.currentTimeMillis();
                        this.level().setBlock(this.targetPosFood(), this.level().getBlockState(this.targetPosFood()).setValue(ModBlockStateProvider.STATES_TWO, 0), 3);
                        this.setEating(false);
                    }
                }

                if(this.isSitting() == false){
                    this.setSleeping(false);
                    this.setNoAi(false);
                    this.setSittingTime(System.currentTimeMillis());
                }else{
                    if(System.currentTimeMillis() - sittingTime() >= 17250){
                        this.setNoAi(true);
                        this.setSleeping(true);
                    }
                }

                //System.out.println(targetPos);
                if(targetPos != null) {
                    if (this.blockPosition().closerThan(targetPos,  1.0)) {
                        targetPos = null;
                        this.setInteractSetting(1);
                        this.setSitting(true);
                        this.setOrderedToSit(true);
                    }
                }

                if(this.isKneading() == true){
                    this.setNoAi(true);
                    //this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0F);
                    this.setSittingTime(System.currentTimeMillis());
                    if(System.currentTimeMillis() - kneadingTime >= 8500){
                        this.spawnAtLocation(ModItems.KNEADED_DOUGH.get());
                        this.setKneading(false);
                    }
                }else{
                    this.setNoAi(false);
                    //this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.5F);
                    kneadingTime = System.currentTimeMillis();
                }

            }
        }else{

            if(targetPos != null) {
                if (this.blockPosition().closerThan(targetPos,  1.0)) {
                    targetPos = null;
                    this.setInteractSetting(1);
                    this.setSitting(true);
                    this.setOrderedToSit(true);
                }
            }

        }

    }

    public boolean isSitting() {
        return this.entityData.get(isSitting);
    }

    // Setter for isSitting
    public void setSitting(boolean sitting) {
        this.entityData.set(isSitting, sitting);
    }

    public boolean isSleeping() {
        return this.entityData.get(isSleeping);
    }

    // Setter for isSitting
    public void setSleeping(boolean sleeping) {
        this.entityData.set(isSleeping, sleeping);
    }

    public boolean finishedEating() {
        return this.entityData.get(finishedEating);
    }

    // Setter for isSitting
    public void setEating(boolean eating) {
        this.entityData.set(finishedEating, eating);
    }

    public boolean following() {
        return this.entityData.get(following);
    }

    // Setter for isSitting
    public void setFollowing(boolean follow) {
        this.entityData.set(following, follow);
    }

    public boolean focus() {
        return this.entityData.get(focus);
    }

    // Setter for isSitting
    public void setFocus(boolean f) {
        this.entityData.set(focus, f);
    }

    public int interactSetting() {
        return this.entityData.get(interactSetting);
    }

    // Setter for isSitting
    public void setInteractSetting(int setting) {
        this.entityData.set(interactSetting, setting);
    }

    public BlockPos targetPosFood() {
        return this.entityData.get(targetPosFood);
    }

    // Setter for isSitting
    public void setTargetPosFood(BlockPos food) {
        this.entityData.set(targetPosFood, food);
    }

    public BlockPos targetPosSharpen() {
        return this.entityData.get(targetPosSharpen);
    }

    // Setter for isSitting
    public void setTargetPosSharpen(BlockPos food) {
        this.entityData.set(targetPosSharpen, food);
    }

    public long lastSharpenTime() {
        return this.entityData.get(lastSharpenTime);
    }

    // Setter for isSitting
    public void setLastSharpenTime(long time) {
        this.entityData.set(lastSharpenTime, time);
    }

    public boolean isKneading() {
        return this.entityData.get(isKneading);
    }

    // Setter for isSitting
    public void setKneading(boolean kneading) {
        this.entityData.set(isKneading, kneading);
    }

    public long sittingTime() {
        return this.entityData.get(sittingTime);
    }

    // Setter for isSitting
    public void setSittingTime(long time) {
        this.entityData.set(sittingTime, time);
    }



    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // Register the sitting flag, default to false
        this.entityData.define(isSitting, false);
        this.entityData.define(isSleeping, false);
        this.entityData.define(finishedEating, false);
        this.entityData.define(following, false);
        this.entityData.define(focus, false);
        this.entityData.define(interactSetting, 1);
        this.entityData.define(targetPosFood, BlockPos.ZERO);
        this.entityData.define(targetPosSharpen, BlockPos.ZERO);
        this.entityData.define(lastSharpenTime, System.currentTimeMillis());
        this.entityData.define(isKneading, false);
        this.entityData.define(sittingTime, System.currentTimeMillis());
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
                }else if (itemstack.getItem() ==  ModItems.DOUGH.get() && this.isTame()) {
                    if (!this.level().isClientSide) {
                        // Reduce the item count
                        itemstack.shrink(1);

                        this.setKneading(true);

                        return InteractionResult.SUCCESS;

                    }
                }else{
                    switch (interactSetting()){
                        case 1: //makes the entity follow
                            System.out.println("CASE 1" + interactSetting);
                            this.setSitting(false);
                            this.setFollowing(true);
                            this.setInteractSetting(2);
                            this.setOrderedToSit(false);
                            break;
                        case 2: //makes the entity sit
                            System.out.println("CASE 2" + interactSetting);
                            this.setOrderedToSit(true);
                            //this.setInSittingPose(true);
                            this.setSitting(true);
                            this.setInteractSetting(3);
                            break;
                        case 3: //makes the entity roam around
                            System.out.println("CASE 3" + interactSetting);
                            this.setOrderedToSit(false);
                            //this.setInSittingPose(false);
                            this.setSitting(false);
                            this.setFollowing(false);
                            this.setInteractSetting(1);
                            break;
                        default:
                            System.out.println("CASE 0" + interactSetting);
                            this.setFollowing(true);
                            this.setInteractSetting(1);
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
            compound.putInt("Interact", this.interactSetting());
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

        switch (this.interactSetting()){
            case 1: //makes the entity follow
                System.out.println("CASE 1" + this.interactSetting);
                this.setFollowing(true);
                this.setInteractSetting(2);
                break;
            case 2: //makes the entity sit
                System.out.println("CASE 2" + this.interactSetting);
                this.setOrderedToSit(true);
                //this.setInSittingPose(true);
                this.setSitting(true);
                this.setInteractSetting(3);
                break;
            case 3: //makes the entity roam around
                System.out.println("CASE 3" + this.interactSetting);
                this.setOrderedToSit(false);
                //this.setInSittingPose(false);
                this.setSitting(false);
                this.setFollowing(false);
                this.setInteractSetting(1);
                break;
            default:
                System.out.println("CASE 0" + this.interactSetting);
                this.setFollowing(true);
                this.setInteractSetting(1);
                break;
        }
    }

    public void setInteract(int i){
        this.setInteractSetting(i - 1);
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
            this.cat.setTargetPosFood(findNearestOakLog());

            if (this.cat.targetPosFood() != null && (mob.level().getBlockState(this.cat.targetPosFood()).getValue(ModBlockStateProvider.STATES_TWO) == 1)) {
                this.cat.setFocus(true);
                return true;
            }
            this.cat.setFocus(false);
            return false;
        }

        @Override
        public void start() {
            if (this.cat.targetPosFood() != null) {
                BlockPos adjacentPos = findAdjacentAirBlock(this.cat.targetPosFood());
                if (adjacentPos != null) {

                    // Move to the adjacent block
                    mob.getNavigation().moveTo(adjacentPos.getX(), adjacentPos.getY(), adjacentPos.getZ(), speed);

                    // Make the mob face the oak log when it arrives
                    mob.getLookControl().setLookAt(this.cat.targetPosFood().getX(), this.cat.targetPosFood().getY() + 1.0, this.cat.targetPosFood().getZ());

                } else {
                    // Fallback to moving to the log's position
                    mob.getNavigation().moveTo(this.cat.targetPosFood().getX(), this.cat.targetPosFood().getY(), this.cat.targetPosFood().getZ(), speed);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !mob.getNavigation().isDone() && this.cat.targetPosFood() != null && isOakLog(this.cat.targetPosFood()) && (mob.level().getBlockState(this.cat.targetPosFood()).getValue(ModBlockStateProvider.STATES_TWO) == 1);
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
            this.cat.setTargetPosSharpen(findNearestOakLog());

            if(this.cat.targetPosSharpen() != null){
                if(System.currentTimeMillis() - this.cat.lastSharpenTime() >= 20000) {
                    //System.out.println("SHARPEN");
                    this.cat.setFocus(true);
                    return true;
                }
            }
            this.cat.setFocus(false);
            return false;
        }

        @Override
        public void start() {
            if (this.cat.targetPosSharpen() != null) {
                if (this.cat.targetPosSharpen() != null) {

                    // Move to the adjacent block
                    mob.getNavigation().moveTo(this.cat.targetPosSharpen().getX(), this.cat.targetPosSharpen().getY(), this.cat.targetPosSharpen().getZ(), speed);

                    // Make the mob face the oak log when it arrives
                    mob.getLookControl().setLookAt(this.cat.targetPosSharpen().getX(), this.cat.targetPosSharpen().getY() + 1.0, this.cat.targetPosSharpen().getZ());

                } else {
                    // Fallback to moving to the log's position
                    mob.getNavigation().moveTo(this.cat.targetPosSharpen().getX(), this.cat.targetPosSharpen().getY(), this.cat.targetPosSharpen().getZ(), speed);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !mob.getNavigation().isDone() && this.cat.targetPosSharpen() != null && isOakLog(this.cat.targetPosSharpen());
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
