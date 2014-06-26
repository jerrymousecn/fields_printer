package cn.jerryhouse.util.filed_printer;

/**
 * 以xml格式打印对象内部的所有属性值.
 */
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.jerryhouse.util.filed_printer.test.Stu;

public class XMLFieldsPrinter extends FieldsPrinter{
	public XMLFieldsPrinter()
	{
//		fieldsToFilter.put("teacher","");	
	}
	protected String formatOuput(String str)
	{
		String formattedStr = "<root>"+str+"</root>";
    	return XMLFormat.beautifyXML(formattedStr);
	}

	protected String wrapFieldValuePair(String fieldName, Object value) {
		return "<" + fieldName + ">" + value + "</" + fieldName + ">";
	}

	public static void main(String[] args) {
		Stu stu = new Stu();
		XMLFieldsPrinter fieldsLister = new XMLFieldsPrinter();
		System.out.println(fieldsLister.toString(stu));
	}
}
