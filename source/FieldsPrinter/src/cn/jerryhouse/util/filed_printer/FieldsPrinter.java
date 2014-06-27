package cn.jerryhouse.util.filed_printer;

/**
 * 以xml格式打印对象内部的所有属性值.
 */
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.jerryhouse.util.filed_printer.objs.ArrayObjImpl;
import cn.jerryhouse.util.filed_printer.objs.CollectionItem;
import cn.jerryhouse.util.filed_printer.objs.CollectionObj;

public abstract class FieldsPrinter {
	protected Map<String, String> fieldsToFilterMap = new HashMap<String, String>();
	protected Map<Class, CollectionObj> collectionTypeMap = new HashMap<Class, CollectionObj>();

	public FieldsPrinter() {
		collectionTypeMap.put(Object[].class, new ArrayObjImpl());
//		collectionTypeMap.put(Map.class, "");
//		collectionTypeMap.put(Set.class, "");
//		collectionTypeMap.put(List.class, "");
//		collectionTypeMap.put(Vector.class, "");
	}

	public String toString(Object obj) {
		String str = formatOuput(listFields(obj));
		return str;
	}

	protected String formatOuput(String str) {
		String formattedStr = "<root>" + str + "</root>";
		return XMLFormat.beautifyXML(formattedStr);
	}

	protected String wrapFieldValuePair(String fieldName, Object value) {
		return "<" + fieldName + ">" + value + "</" + fieldName + ">";
	}

	private String listFields(Object obj) {
		StringBuffer stringBuffer = new StringBuffer();
		if (obj == null)
			return "null";
		if (isCollection(obj)) {
			processCollection(stringBuffer,obj);
		} else {
			if (isJavaClass(obj)) {
				stringBuffer.append(obj.toString());
			} else {
				Class<?> thisClass = obj.getClass();
				Field[] fields = thisClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					try {
						String fieldName = fields[i].getName();
						if (isFieldToPrint(fieldName)) {
							if (!fields[i].isAccessible())
								fields[i].setAccessible(true);
							stringBuffer.append(wrapFieldValuePair(fieldName,
									listFields(fields[i].get(obj))));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return stringBuffer.toString();
	}

	private boolean isFieldToPrint(String fieldName) {
		if (fieldsToFilterMap.containsKey(fieldName))
			return false;
		else
			return true;
	}

	private void processCollection(StringBuffer stringBuffer,Object obj)
	{
		CollectionObj collectionObj = getCollectionType(obj);
		if(collectionObj!=null)
		{
			collectionObj.setCollectionObj(obj);
			while(collectionObj.hasNext())
			{
				CollectionItem collectionItem = collectionObj.getNextItem();
				Object object = collectionItem.getObj();
				String itemName = collectionItem.getItemName();
				stringBuffer.append(wrapFieldValuePair(itemName,
						listFields(object)));
			}
		}
	}
	public boolean isList(Object obj) {
		if (obj instanceof List<?>) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMap(Object obj) {
		if (obj instanceof Map<?, ?>) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isArray(Object obj) {
		if (obj instanceof Object[]) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isJavaClass(Object obj) {
		return obj.getClass() != null
				&& obj.getClass().getClassLoader() == null;
	}
	private CollectionObj getCollectionType(Object obj) {
		CollectionObj collectionObj = null;
		for (Entry<Class, CollectionObj> entry : collectionTypeMap.entrySet()) {
			Class clazz = entry.getKey();
			if (clazz.isInstance(obj)) {
				collectionObj = entry.getValue();
				break;
			}
		}
		return collectionObj;
	}
	
	private boolean isCollection(Object obj) {
		boolean result = false;
		for (Entry<Class, CollectionObj> entry : collectionTypeMap.entrySet()) {
			Class clazz = entry.getKey();
			if (clazz.isInstance(obj)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
