package net.tardis.mod.common.items.clothing;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelSpaceChest;
import net.tardis.mod.client.models.clothing.ModelSpaceHelm;

public class SpaceSuit extends ItemArmor {
	
	public static final ArmorMaterial material = EnumHelper.addArmorMaterial("space_suit", "spacesuit", 0,new int[] {0,0,0,0},0,SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,0F);
	
	public SpaceSuit(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setCreativeTab(Tardis.tab);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID+":textures/clothing/space_suit.png";
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if(armorSlot == EntityEquipmentSlot.CHEST) {
			return new ModelSpaceChest();
		}
		return new ModelSpaceHelm();
	}
	
}