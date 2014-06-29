package cn.jerryhouse.util.filed_printer.viewer;

import java.util.List;

public class ListViewer extends CollectionViewer {
	private List list;

	@Override
	protected void castObjectType() {
		list = (List) obj;
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
		return list.get(currentIndex);
	}

	@Override
	protected long getSize() {
		return list.size();
	}
}
