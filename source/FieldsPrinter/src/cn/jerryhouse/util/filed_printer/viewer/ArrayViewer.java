package cn.jerryhouse.util.filed_printer.viewer;

public class ArrayViewer extends CollectionViewer {
	private Object[] objArr;

	@Override
	protected void castObjectType() {
		objArr = (Object[]) obj;
	}

	@Override
	protected String getItemName() {
		String itemName = "item_"+currentIndex;
		if(isShowLevelInItemName())
		{
			itemName = "item_"+level+"_"+currentIndex;
		}
		return itemName;
	}

	@Override
	protected Object getObj() {
		return objArr[currentIndex];
	}

	@Override
	protected long getSize() {
		return objArr.length;
	}
}
