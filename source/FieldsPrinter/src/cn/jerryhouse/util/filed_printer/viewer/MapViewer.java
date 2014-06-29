package cn.jerryhouse.util.filed_printer.viewer;

import java.util.Iterator;
import java.util.Map;

public class MapViewer extends CollectionViewer {
	private Map map;
	private Iterator iter;
	private String itemName;

	@Override
	protected void castObjectType() {
		map = (Map) obj;
		iter = map.keySet().iterator();
	}

	@Override
	protected String getItemName() {
		return itemName;
	}

	@Override
	protected Object getObj() {
		return map.get(itemName);
	}

	@Override
	protected long getSize() {
		return map.size();
	}

	@Override
	protected void movePosToNextItem() {
		itemName = (String)iter.next();
		super.movePosToNextItem();
	}
}
