package cn.jerryhouse.util.filed_printer;

/**
 * ��xml��ʽ��ӡ�����ڲ�����������ֵ.
 */
import cn.jerryhouse.util.filed_printer.test.Stu;

public class XMLFieldsPrinter extends FieldsPrinter{
	public XMLFieldsPrinter()
	{	
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
