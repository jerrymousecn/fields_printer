package cn.jerryhouse.util.filed_printer.objs;

public abstract class CollectionObj {
	protected Object obj;
	protected int currentIndex = 0;
	public void setCollectionObj(Object obj)
	{
		this.obj = obj;
	}
	public abstract CollectionItem getNextItem();
	public abstract boolean hasNext();
}
