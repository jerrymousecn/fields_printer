package cn.jerryhouse.util.filed_printer;

/**
 * 以xml格式打印对象内部的所有属性值.
 */
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class FieldsPrinter {
	protected Map<String,String> fieldsToFilter = new HashMap<String,String>();
	public FieldsPrinter()
	{
	}
	public String toString(Object obj) {
    	String str = formatOuput(listFields(obj));
    	return str;
	}
	protected String formatOuput(String str)
	{
		String formattedStr = "<root>"+str+"</root>";
    	return XMLFormat.beautifyXML(formattedStr);
	}

	protected String wrapFieldValuePair(String fieldName, Object value) {
		return "<" + fieldName + ">" + value + "</" + fieldName + ">";
	}

	private String listFields(Object obj) {
		StringBuffer stringBuffer = new StringBuffer();
		if (obj == null)
			return "null";
		if (isArray(obj)) {
			Object[] objArr = (Object[]) obj;
			for (int i = 0; i < objArr.length; i++) {
				Object currentObj = objArr[i];
				stringBuffer.append(wrapFieldValuePair(getItemNameForArrayOrList(
						currentObj, i), listFields(objArr[i])));
			}
		} else if (isMap(obj)) {
			Map<?,?> mapObj = (Map<?,?>) obj;
			Iterator<?> keyIter = mapObj.keySet().iterator();
			while (keyIter.hasNext()) {
				Object keyObj = keyIter.next();
				Object valueObj = mapObj.get(keyObj);
				stringBuffer.append(wrapFieldValuePair(keyObj.toString(),
						listFields(valueObj)));
			}
		} else if (isList(obj)) {
			List<?> listObj = (List<?>) obj;
			for (int i = 0; i < listObj.size(); i++) {
				Object currentObj = listObj.get(i);
				stringBuffer.append(wrapFieldValuePair(getItemNameForArrayOrList(
						currentObj, i), listFields(currentObj)));
			}
		} else {
			if (isJavaClass(obj)) {
				stringBuffer.append(obj.toString());
			} else {
				Class<?> thisClass = obj.getClass();
				Field[] fields = thisClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					try {
						String fieldName = fields[i].getName();
						if(isFieldToFilter(fieldName))
							continue;
						if (!fields[i].isAccessible())
							fields[i].setAccessible(true);
						stringBuffer.append(wrapFieldValuePair(fieldName,
								listFields(fields[i].get(obj))));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return stringBuffer.toString();
	}

	private boolean isFieldToFilter(String fieldName)
	{
		if(fieldsToFilter.containsKey(fieldName))
			return true;
		else
			return false;
	}
	private String getItemNameForArrayOrList(Object obj, int index) {
		return "item_" + index;
	}

	public boolean isList(Object obj) {
		if (obj instanceof List<?>) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMap(Object obj) {
		if (obj instanceof Map<?,?>) {
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
		return obj.getClass() != null && obj.getClass().getClassLoader() == null;
	}
}
