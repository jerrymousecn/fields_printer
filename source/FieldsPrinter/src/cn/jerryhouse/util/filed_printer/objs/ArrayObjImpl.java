package cn.jerryhouse.util.filed_printer.objs;

public class ArrayObjImpl extends CollectionObj {
	@Override
	public CollectionItem getNextItem()
	{
		String itemName = "item_"+currentIndex;
		Object nextObj = null;
		Object[] objArr = (Object[]) obj;
		if(currentIndex<objArr.length)
			nextObj = objArr[currentIndex];
		currentIndex++;
		CollectionItem collectionItem = new CollectionItem(itemName,nextObj);
		return collectionItem;
	}

	@Override
	public boolean hasNext() {
		Object[] objArr = (Object[]) obj;
		if(currentIndex<objArr.length)
			return true;
		else
			return false;
	}
}
