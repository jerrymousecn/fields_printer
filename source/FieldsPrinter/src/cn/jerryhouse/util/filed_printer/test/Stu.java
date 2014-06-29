package cn.jerryhouse.util.filed_printer.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Stu {
	int id = 100;
	Integer age = 18;
	String name = "jerry";
	Teacher teacher;
	Map bookMap;
	String[] friends;
	List nickNameList;
	List hobbyList;
	Set testSet;
	Vector testVec;
	public Stu()
	{
//		teacher = new Teacher();
		bookMap = new HashMap();
		bookMap.put("book1", "harry");
		bookMap.put("book2", "young");
		friends = new String[2];
		friends[0] = "linda";
		friends[1] = "jane";
		nickNameList = new LinkedList();
		nickNameList.add("jerry1");
		nickNameList.add("jerry2");
		hobbyList = new ArrayList();
		hobbyList.add(new Hobby("football"));
		hobbyList.add(new Hobby("baseball"));
		testSet = new HashSet();
		testSet.add("1");
		testSet.add("2");
		testVec = new Vector();
		testVec.add("1");
		testVec.add("2");
	}
}
