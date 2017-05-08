package com.design_pattern.strategy;

public class MinusCalculator implements ICalculator {

	public int calculate(String expression) {
		String[] strArray = expression.split("\\-");
		return Integer.valueOf(strArray[0]) - Integer.valueOf(strArray[1]);
	}

}
