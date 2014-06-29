package cn.jerryhouse.util.filed_printer.viewer;

public abstract class CollectionViewer {
	protected Object obj;
	protected int currentIndex = -1;
	protected int level = -1;
	private boolean showLevelInItemName = true;
	public void setCollectionObj(Object obj,int level)
	{
		this.obj = obj;
		this.level = level;
		castObjectType();
	}
	public CollectionItem getNextItem()
	{
		CollectionItem collectionItem = null;
		if(hasNext())
		{
			movePosToNextItem();
			String itemName = getItemName();
			Object nextObj = null;
			nextObj = getObj();
			collectionItem = new CollectionItem(itemName,nextObj);
		}
		return collectionItem;
	}
	public boolean hasNext() {
		if(currentIndex<getSize()-1)
			return true;
		else
			return false;
	}
	
	protected void movePosToNextItem()
	{
		currentIndex++;
	}
	public boolean isShowLevelInItemName()
	{
		return showLevelInItemName;
	}
	public void setShowLevelInItemName(boolean showLevelInItemName)
	{
		this.showLevelInItemName = showLevelInItemName;
	}
	protected abstract void castObjectType();
	
	protected abstract String getItemName();
	
	protected abstract Object getObj();
	protected abstract long getSize();
}
