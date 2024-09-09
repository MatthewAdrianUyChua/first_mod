package net.matthew.first_mod.block.entity.goal;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;

import java.util.EnumSet;

public class CustomSitWhenOrderedToGoal extends SitWhenOrderedToGoal {

    private final TamableAnimal mob;
    public CustomSitWhenOrderedToGoal(TamableAnimal pMob) {
        super(pMob);
        this.mob = pMob;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public void start() {
        this.mob.getNavigation().getPath();
    }


}
