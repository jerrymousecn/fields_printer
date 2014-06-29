package cn.jerryhouse.util.filed_printer.viewer;

public class CollectionItem {
	private String itemName;
	private Object obj;
	public CollectionItem(){}
	public CollectionItem(String itemName,Object obj)
	{
		this.itemName = itemName;
		this.obj = obj;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
