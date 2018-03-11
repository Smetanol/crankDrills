package smetanol.myFirstMod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import smetanol.myFirstMod.blocks.BlockBase;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block DRILL_STATION_BLOCK = new BlockBase("drill_station", Material.IRON);
}
