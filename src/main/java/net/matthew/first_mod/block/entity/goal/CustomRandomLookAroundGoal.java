package net.matthew.first_mod.block.entity.goal;

import net.matthew.first_mod.block.entity.custom.CatEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class CustomRandomLookAroundGoal extends RandomLookAroundGoal {

    private final Mob mob;

    private final CatEntity cat;

    private double relX;

    private double relZ;

    private int lookTime;

    public CustomRandomLookAroundGoal(Mob pMob, CatEntity cat) {
        super(pMob);
        this.mob = pMob;
        this.cat = cat;
    }

    @Override
    public boolean canUse() {
        if(this.cat.focus() == false){
            return this.mob.getRandom().nextFloat() < 0.02F;
        }else{
            return false;
        }
    }
}
