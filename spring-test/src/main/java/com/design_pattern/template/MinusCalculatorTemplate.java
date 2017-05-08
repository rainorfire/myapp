package com.design_pattern.template;

public class MinusCalculatorTemplate extends AbstractCalculatorTemplate {

	@Override
	public int doCalculate(int num1, int num2) {
		return num1 - num2;
	}
}
