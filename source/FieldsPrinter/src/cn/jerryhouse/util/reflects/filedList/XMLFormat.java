package cn.jerryhouse.util.reflects.filedList;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XMLFormat {
    /**
     * 将符合xml的字符串进行美化，美化后的字符串输出后与xml文件中的效果一样
     * @param xmlStr
     * @return
     */
    public static String beautifyXML(String xmlStr) {
       org.dom4j.Document document = null;
       try {
           document = DocumentHelper.parseText(xmlStr);
       } catch (DocumentException documentexception) {
           documentexception.printStackTrace();
       }
       OutputFormat outputformat = OutputFormat.createPrettyPrint();
       // 这里用于控制xml输出的头信息(如：<?xml version="1.0" encoding="UTF-8"?>)，true 表示不输出； false 表示输出
       outputformat.setSuppressDeclaration(true);
       StringWriter stringwriter = new StringWriter();
       XMLWriter xmlwriter = new XMLWriter(stringwriter, outputformat); 
       try {
           xmlwriter.write(document);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return stringwriter.toString().trim();
    }
   
    public static void main(String[] args)
    {
    	String str = "<root><bookMap><book1>harry</book1><book2>young</book2></bookMap><hobbyList><item_0><name>football</name></item_0><item_1><name>baseball</name></item_1></hobbyList></root>";
    	System.out.println(beautifyXML(str));
    }
}
