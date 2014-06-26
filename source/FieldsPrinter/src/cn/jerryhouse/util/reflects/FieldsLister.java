package cn.jerryhouse.util.reflects;
/**
 * ʵ��toString����,���Ǽ̳и�����඼����ͨ������toString����,��ȡ�����������������ֵ
 * Ҳ���Խ�Ŀ�����ʵ������toString����,һ�����Ի�ȡ��Ŀ����ʵ�����������Ժ�ֵ.
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
    //���������ʽ
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
               //���������private�Ļ�,�����޸���accessible����
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
