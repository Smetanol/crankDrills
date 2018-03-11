package smetanol.myFirstMod.tools;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Holds all the NBT Tag keys used by the drill.
 */
public final class Tags 
{

	public static final String TOOL_DATA = "Stats";
	public static final String BROKEN = "Broken";
	
	private Tags() {}

	public static void setToolTag(ItemStack stack, NBTTagCompound tag) 
	{
		NBTTagCompound root = getTagSafe(stack);
		setToolTag(root, tag);
	
		stack.setTagCompound(root);
	}
	
	public static void setToolTag(NBTTagCompound root, NBTTagCompound tag) 
	{
	    if(root != null) 
	    {
	      root.setTag(Tags.TOOL_DATA, tag);
	    }
	}
	  
	public static NBTTagCompound getToolTag(ItemStack stack) 
	{
		return getToolTag(getTagSafe(stack));
	}
	
	public static NBTTagCompound getToolTag(NBTTagCompound root) 
	{
		return getTagSafe(root, Tags.TOOL_DATA);
	}
  
	public static NBTTagCompound getTagSafe(ItemStack stack) 
	{
		if(stack == null || stack.getItem() == null || !stack.hasTagCompound()) 
		{
			return new NBTTagCompound();
		}
		return stack.getTagCompound();
	}

	public static NBTTagCompound getTagSafe(NBTTagCompound tag, String key) 
	{
		if(tag == null) 
	    {
	      return new NBTTagCompound();
	    }

	    return tag.getCompoundTag(key);
	  }
}