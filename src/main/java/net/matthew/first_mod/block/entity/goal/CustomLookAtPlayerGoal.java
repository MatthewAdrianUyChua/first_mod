package net.matthew.first_mod.block.entity.goal;

import net.matthew.first_mod.block.entity.custom.CatEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;

public class CustomLookAtPlayerGoal extends LookAtPlayerGoal {

    private final CatEntity cat;

    public CustomLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, CatEntity cat) {
        super(pMob, pLookAtType, pLookDistance);
        this.cat = cat;
    }

    @Override
    public boolean canUse() {

        if (this.mob.getRandom().nextFloat() >= this.probability || this.cat.focus() == true) {
            //System.out.println("FOCUSING");
            return false;
        } else {
            if (this.mob.getTarget() != null) {
                this.lookAt = this.mob.getTarget();
            }

            if (this.lookAtType == Player.class) {
                this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            } else {
                this.lookAt = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.lookAtType, this.mob.getBoundingBox().inflate((double)this.lookDistance, 3.0D, (double)this.lookDistance), (p_148124_) -> {
                    return true;
                }), this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            }

            return this.lookAt != null;
        }
    }
}
