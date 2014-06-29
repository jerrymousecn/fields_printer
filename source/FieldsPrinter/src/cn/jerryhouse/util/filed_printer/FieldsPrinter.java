package cn.jerryhouse.util.filed_printer;

/**
 * 打印对象内部的所有属性值.
 */
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import cn.jerryhouse.util.filed_printer.viewer.ArrayViewer;
import cn.jerryhouse.util.filed_printer.viewer.CollectionItem;
import cn.jerryhouse.util.filed_printer.viewer.CollectionViewer;
import cn.jerryhouse.util.filed_printer.viewer.ListViewer;
import cn.jerryhouse.util.filed_printer.viewer.MapViewer;
import cn.jerryhouse.util.filed_printer.viewer.SetViewer;
import cn.jerryhouse.util.filed_printer.viewer.VectorViewer;

public abstract class FieldsPrinter {
	protected Map<String, String> fieldsToFilterMap = new HashMap<String, String>();
	protected Map<Class, Class> collectionTypeMap = new HashMap<Class, Class>();

	public FieldsPrinter() {
		collectionTypeMap.put(Object[].class, ArrayViewer.class);
		collectionTypeMap.put(Map.class, MapViewer.class);
		collectionTypeMap.put(Set.class, SetViewer.class);
		collectionTypeMap.put(List.class, ListViewer.class);
		collectionTypeMap.put(Vector.class, VectorViewer.class);
	}

	protected void addFieldFilter(String fieldName) {
		fieldsToFilterMap.put(fieldName, "");
	}

	protected void addCollectionViewer(Class typeClass, Class viewerClass) {
		collectionTypeMap.put(typeClass, viewerClass);
	}

	public String toString(Object obj) {
		String str = formatOuput(listFields(-1, obj));
		return str;
	}

	private String listFields(int level, Object obj) {
		level++;
		StringBuffer stringBuffer = new StringBuffer();
		if (obj == null)
			return "null";
		if (isCollection(obj)) {
			processCollection(level, stringBuffer, obj);
		} else if (isDirectPrintable(obj)) {
			stringBuffer.append(obj.toString());
		} else {
			processUserDefinedClass(level, obj, stringBuffer);
		}
		level--;
		return stringBuffer.toString();
	}

	private void processUserDefinedClass(int level, Object obj,
			StringBuffer stringBuffer) {
		Class<?> thisClass = obj.getClass();
		Field[] fields = thisClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				String fieldName = fields[i].getName();
				if (isFieldToPrint(fieldName)) {
					if (!fields[i].isAccessible())
						fields[i].setAccessible(true);
					stringBuffer.append(wrapFieldValuePair(fieldName,
							listFields(level, fields[i].get(obj))));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isFieldToPrint(String fieldName) {
		if (fieldsToFilterMap.containsKey(fieldName))
			return false;
		else
			return true;
	}

	private void processCollection(int level, StringBuffer stringBuffer,
			Object obj) {
		try {
			CollectionViewer collectionObj = null;
			collectionObj = getCollectionType(obj);

			if (collectionObj != null) {
				collectionObj.setCollectionObj(obj,level);
				while (collectionObj.hasNext()) {
					CollectionItem collectionItem = collectionObj.getNextItem();
					Object object = collectionItem.getObj();
					String itemName = collectionItem.getItemName();
					stringBuffer.append(wrapFieldValuePair(itemName,
							listFields(level, object)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDirectPrintable(Object obj) {
		return obj.getClass() != null
				&& obj.getClass().getClassLoader() == null;
	}

	private CollectionViewer getCollectionType(Object obj) throws Exception {
		CollectionViewer collectionViewer = null;
		for (Entry<Class, Class> entry : collectionTypeMap.entrySet()) {
			Class clazz = entry.getKey();
			if (clazz.isInstance(obj)) {
				collectionViewer = (CollectionViewer) entry.getValue()
						.newInstance();
				break;
			}
		}
		return collectionViewer;
	}

	private boolean isCollection(Object obj) {
		boolean result = false;
		for (Entry<Class, Class> entry : collectionTypeMap.entrySet()) {
			Class clazz = entry.getKey();
			if (clazz.isInstance(obj)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	protected abstract String formatOuput(String str);
	protected abstract String wrapFieldValuePair(String fieldName, Object value);
}
