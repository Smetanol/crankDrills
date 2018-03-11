package smetanol.myFirstMod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockDoor.EnumHingePosition;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import smetanol.myFirstMod.items.ItemBase;
import smetanol.myFirstMod.tools.ToolDrill;

public class ModItems 
{
	public static final List<Item> Items = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial TOOL_DRILL = EnumHelper.addToolMaterial("tool_drill", 3, 8, 12.0F, 3.0F, 10);
	
	//Items
	public static final Item CRANK = new ItemBase("crank");
	
	//Tools
	public static final Item DRILL_BASE = new ToolDrill("drill_base", TOOL_DRILL);
}
