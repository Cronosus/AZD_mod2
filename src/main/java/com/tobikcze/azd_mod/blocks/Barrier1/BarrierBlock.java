package com.tobikcze.azd_mod.blocks.Barrier1;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BarrierBlock extends BlockContainer {

    public BarrierBlock() {
        super(Material.cloth);
        this.setBlockName("BarrierBlock");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
        return new TileEntityBarrier();
    }

    // This method is called when the block is placed in the world
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int rotation = determineRotation(placer);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityBarrier) {
            ((TileEntityBarrier) tileEntity).setRotation(rotation);
        }
    }

    // Determine the rotation of the block based on the player's facing direction
    private int determineRotation(EntityLivingBase placer) {
        int direction = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        switch (direction) {
            case 0:
                return 180;
            case 1:
                return 90;
            case 2:
                return 0;
            case 3:
                return 270;
            default:
                return 0;
        }
    }
}