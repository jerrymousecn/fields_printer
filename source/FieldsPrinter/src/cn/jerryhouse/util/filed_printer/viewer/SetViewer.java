package cn.jerryhouse.util.filed_printer.viewer;

import java.util.Iterator;
import java.util.Set;

public class SetViewer extends CollectionViewer {
	private Set set;
	private Iterator iter;

	@Override
	protected void castObjectType() {
		set = (Set) obj;
		iter = set.iterator();
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
		return obj;
	}

	@Override
	protected long getSize() {
		return set.size();
	}

	@Override
	protected void movePosToNextItem() {
		obj = iter.next();
		super.movePosToNextItem();
	}
}
