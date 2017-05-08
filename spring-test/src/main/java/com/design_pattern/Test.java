package com.design_pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.design_pattern.strategy.ICalculator;
import com.design_pattern.strategy.MinusCalculator;
import com.design_pattern.strategy.PlusCalculator;
import com.design_pattern.template.ICalculatorTemplate;
import com.design_pattern.template.MinusCalculatorTemplate;
import com.design_pattern.template.PlusCalculatorTemplate;

public class Test {
	
//	public static void main(String[] args) {
//		//策略模式测试
//		ICalculator plusCalculator = new PlusCalculator();
//		System.out.println(plusCalculator.calculate("11+12"));
//		
//		ICalculator minusCalculator = new MinusCalculator();
//		System.out.println(minusCalculator.calculate("16-12"));
//		
//		//模板模式测试
//		ICalculatorTemplate plusCalculatorTep = new PlusCalculatorTemplate();
//		System.out.println(plusCalculatorTep.calculate("1+1", "\\+"));
//		ICalculatorTemplate minusCalculatorTep = new MinusCalculatorTemplate();
//		System.out.println(minusCalculatorTep.calculate("31-13", "\\-"));
//	}
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args)
    {
//        Calendar now = Calendar.getInstance();
//        now.set(0, 0, 0);
//        now.set(Calendar.HOUR, 0);
//        now.set(Calendar.MINUTE, 0);
//        now.set(Calendar.SECOND, 0);
//        System.out.println(sdf.format(now.getTime()));
//        now.set(Calendar.HOUR_OF_DAY, 0);
//        System.out.println(sdf.format(now.getTime()));
    	try {
			Date date = sdf.parse("0000-00-00 00:00:00");
			System.out.println(sdf.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
