package com.epam.infohandling.interpreter;

public class TerminalDivideExpression implements Expression {

	@Override
	public void interpret(Context context) {
		Integer last = context.popValue();
		Integer previous = context.popValue();
		context.pushValue(previous / last);
	}
	
}