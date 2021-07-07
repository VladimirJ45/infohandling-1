package com.epam.infohandling.interpeter;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.epam.infohandling.interpreter.ExpressionCalculator;

public class ExpressionCalculatorTest {
	private Map<String, Integer> variables = new HashMap<String, Integer>();

	public ExpressionCalculatorTest() {
		variables.put("x", 5);
	}

	@Test
	public void calculatorPlusWithPositiveInput() {
		final String expression = "[3 x +]";

		ExpressionCalculator calculator = new ExpressionCalculator();
		int result = calculator.calculate(expression, variables);

		Assert.assertEquals(8, result);
	}

	@Test
	public void calculatorMinusWithNegativeInput() {
		final String expression = "[-3 x +]";

		ExpressionCalculator calculator = new ExpressionCalculator();
		int result = calculator.calculate(expression, variables);

		Assert.assertEquals(2, result);
	}
	
	@Test
	public void calculatorMinusWithPositiveInput() {
		final String expression = "[8 x -]";

		ExpressionCalculator calculator = new ExpressionCalculator();
		int result = calculator.calculate(expression, variables);

		Assert.assertEquals(3, result);
	}
	
	@Test
	public void calculatorDivideWithPositiveInput() {
		final String expression = "[25 x /]";

		ExpressionCalculator calculator = new ExpressionCalculator();
		int result = calculator.calculate(expression, variables);

		Assert.assertEquals(5, result);
	}
	
	@Test
	public void calculatorMultiplyWithPositiveInput() {
		final String expression = "[5 x *]";

		ExpressionCalculator calculator = new ExpressionCalculator();
		int result = calculator.calculate(expression, variables);

		Assert.assertEquals(25, result);
	}
}
