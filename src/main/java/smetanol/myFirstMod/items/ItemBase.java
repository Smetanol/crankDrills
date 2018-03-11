package smetanol.myFirstMod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import smetanol.myFirstMod.Main;
import smetanol.myFirstMod.init.ModItems;
import smetanol.myFirstMod.util.IHasModel;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.Items.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
}
