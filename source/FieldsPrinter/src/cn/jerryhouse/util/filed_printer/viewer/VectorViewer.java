package cn.jerryhouse.util.filed_printer.viewer;

import java.util.Vector;

public class VectorViewer extends CollectionViewer {
	private Vector vector;

	@Override
	protected void castObjectType() {
		vector = (Vector) obj;
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
		return vector.get(currentIndex);
	}

	@Override
	protected long getSize() {
		return vector.size();
	}
}
