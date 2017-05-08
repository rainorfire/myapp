package com.test.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Human {
	private String name;
	private String sex;
	private int age;
	public void say(){
		System.out.println("I'm Human!");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	private static final SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) {
		try {
			String tmp = null;//"2015-10-08 09:00:01";
			Date tmpDate = SDF_YMDHMS.parse(tmp);
			
			String format = SDF_YMD.format(tmpDate);
			
			Date nowDate12 = SDF_YMDHMS.parse(format + " 09:010:00");
			System.out.println(tmpDate.compareTo(nowDate12) < 0);
			
//			throw new ParseException("检验是否能获取异常信息", 1);
		} catch (Exception e) {
//			log.error("",e);
			System.out.println(e.toString());
			for(StackTraceElement elem : e.getStackTrace()) {
                System.out.println("\tat " + elem);
            }
		}
	}
}
