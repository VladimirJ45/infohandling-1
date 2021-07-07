package com.epam.infohandling.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

	public int calculate(String expression, Map<String, Integer> variables) {
		Context contex = new Context();
		List<Expression> expressions = parse(expression, variables);
		for (Expression terminal : expressions) {
			terminal.interpret(contex);
		}
		return contex.popValue();
	}

	private List<Expression> parse(String expression, Map<String, Integer> variables) {
		expression = expression.replace("[", "");
		expression = expression.replace("]", "");
		List<Expression> expressions = new ArrayList<>();
		for (String lexeme : expression.split("\\s")) {
			if (lexeme.isEmpty()) {
				continue;
			}
			switch (lexeme) {
			case "+":
				expressions.add((Expression) new TerminalPlusExpression());
				break;
			case "-":
				expressions.add((Expression) new TerminalMinusExpression());
				break;
			case "*":
				expressions.add((Expression) new TerminalMultiplyExpression());
				break;
			case "/":
				expressions.add(new TerminalDivideExpression());
				break;
			default:
				if (variables.containsKey(lexeme)) {
					expressions.add(new NonterminalExpression(variables.get(lexeme)));
				} else {
					Scanner scan = new Scanner(lexeme);
					if (scan.hasNextInt()) {
						expressions.add(new NonterminalExpression(scan.nextInt()));
					}
					scan.close();
				}
			}
		}
		return expressions;
	}

}
