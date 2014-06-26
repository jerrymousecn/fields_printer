package cn.jerryhouse.util.reflects;
/**
 * 实现toString方法,凡是继承该类的类都可以通过调用toString方法,获取该类的所有属性名和值
 * 也可以将目标类的实例传给toString方法,一样可以获取该目标类实例的所有属性和值.
 * @author yinxing  email:yinxing1999@gmail.com
 * @date 2006-11-29
 */
import java.lang.reflect.Field;



public class FieldsLister {
    public String toString(Object obj)
    {
        return listFields(obj);
    }
    public String toString()
    {
        return listFields(this);
    }
    //定制输出格式
    private String formatOutput(String fieldName,Object value)
    {
        return fieldName+": "+value+"\n";
    }
    private String listFields(Object obj)
    {
        StringBuffer buffer = new StringBuffer();
        Class thisClass = obj.getClass();
        Field[] fields = thisClass.getDeclaredFields();
        for(int i=0;i<fields.length;i++)
        {
           try
           {
               //如果属性是private的话,必须修改其accessible属性
               if(!fields[i].isAccessible())
                   fields[i].setAccessible(true);
               buffer.append(formatOutput(fields[i].getName(),fields[i].get(obj)));
           }catch(Exception e)
           {
               e.printStackTrace();
           }
        }
        return buffer.toString();
    }
}
