package net.tardis.mod.common.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;


public class EntityWatcher extends EntityMob {

    public EntityWatcher(World worldIn) {
        super(worldIn);
        setSize(0.6F, 2F);
    }

    @Override
    protected void initEntityAI() {
        setPathPriority(PathNodeType.WATER, -1.0F);
        setMoveForward(0);
        setAIMoveSpeed(0F);

        tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 200.0F));
        tasks.addTask(0, new EntityAISwimming(this));

    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        final PathNavigateGround navigator = new PathNavigateGround(this, worldIn);
        navigator.setCanSwim(true);
        return navigator;
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    }

}