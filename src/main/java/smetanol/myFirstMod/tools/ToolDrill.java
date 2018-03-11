package smetanol.myFirstMod.tools;



import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import smetanol.myFirstMod.Main;
import smetanol.myFirstMod.init.ModItems;
import smetanol.myFirstMod.util.IHasModel;
import smetanol.myFirstMod.tools.Tags;

public class ToolDrill extends ItemPickaxe implements IHasModel {

	public ToolDrill(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.TOOLS);

		ModItems.Items.add(this);
		
		//recipe to be able to repair drill with any damage using crank
		ResourceLocation recipeName = new ResourceLocation("drill_2");
		ResourceLocation groupName = new ResourceLocation("fm:drills");
		Ingredient[] ingredients = {Ingredient.fromStacks(new ItemStack(ModItems.CRANK, 1, 0)), Ingredient.fromStacks(new ItemStack(ModItems.DRILL_BASE, 1, OreDictionary.WILDCARD_VALUE))};
		GameRegistry.addShapelessRecipe(recipeName, groupName, new ItemStack(this), ingredients[0], ingredients[1]);
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	
	@Override
	  public void setDamage(ItemStack stack, int damage) 
	{
	    super.setDamage(stack, damage);

	    if(getDamage(stack) == getMaxDamage(stack)) 
	    {
	      breakTool(stack, null);
	    }
	}
	
	/**
	 * sets tools NBT data that it's broken
	 * @param stack
	 * broken tool
	 * @param entity
	 * player(we don't really need him there)
	 */
	public static void breakTool(ItemStack stack, EntityLivingBase entity) 
	{
	    NBTTagCompound tag = Tags.getToolTag(stack);
	    tag.setBoolean(Tags.BROKEN, true);
	    Tags.setToolTag(stack, tag);
	}
	
	/**
	 * returns if tool is broken or not
	 * 
	 * @param stack
	 * item in question
	 * 
	 * @return
	 * true if tool is broken
	 */
	public static boolean isBroken(ItemStack stack) 
	{
	    return Tags.getToolTag(stack).getBoolean(Tags.BROKEN);
	}
	
	/**
	 * calculates amount of uses left before breaking
	 */
	public static int getCurrentDurability(ItemStack stack) 
	{
	    return stack.getMaxDamage() - stack.getItemDamage();
	}

	/**
	 * Damages tool by amount given
	 * 
	 * @param stack	
	 * this is our tool item
	 * 
	 * @param amount
	 * 
	 * @param entity
	 * it's a player
	 */
	public static void damageTool(ItemStack stack, int amount, EntityLivingBase entity) 
	{
		  //drill doesn't get damaged when broken and still mining
		  if(amount == 0 || isBroken(stack)) {
			    return;
		  }
	
		  stack.setItemDamage(stack.getItemDamage() + amount);
	
		  if(getCurrentDurability(stack) == 0) {
			  breakTool(stack, entity);
		  }
	}
	
	/**
	 * Overriding default minecraft method after breaking block
	 * 
	 * Broken tool can not break blocks.
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) 
	{
	    if(stack != null && entityLiving != null && stack.hasTagCompound()) 
	    {
	    	NBTTagCompound tag = stack.getTagCompound();
	    	assert tag != null;
	    }
	    if(isBroken(stack)) 
	    {
	    	//
	    	return false;
	    }

	    damageTool(stack, 1, entityLiving);

	    return true;
	}
	
	/**
	 * if drill is broken, it's speed drops from 12.0f to 0.1f
	 */
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		float speed = isBroken(stack) ? 0.1f : super.getDestroySpeed(stack, state);
		return speed;
	}
	
	/**
	 * you cannot harvest blocks with broken drill
	 */
	@Override
	  public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
	    return !isBroken(stack);
	    // TODO: check why that doesn't work as intentional
	  }

}
