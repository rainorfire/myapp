package com.design_pattern.template;

public abstract class AbstractCalculatorTemplate implements ICalculatorTemplate {
	
	public final int calculate(String expression, String token) {
		String[] strArray = parseExpression(expression, token);
		int num1 = Integer.valueOf(strArray[0]);
		int num2 = Integer.valueOf(strArray[1]);
		return doCalculate(num1,num2);
	}
	
	public abstract int doCalculate(int num1,int num2);
	
	protected String[] parseExpression(String expression,String token){
		String[] strArray = expression.split(token);
		return strArray;
	}
	
}
