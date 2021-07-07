package com.epam.infohandling.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.infohandling.interpreter.ExpressionCalculator;
import com.epam.infohandling.model.Component;
import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public class TextLogic {

	private static final String regex = "\\[(.*?)\\]";
	ExpressionCalculator calculator = new ExpressionCalculator();
	Map<String, Integer> variables = new HashMap<String, Integer>();

	public TextLogic() {
		variables.put("x", 5);
		variables.put("y", 10);
	}

	public Composite calculate(Composite text) {
		List<String> expressions = new ArrayList<>();
		nodeInheritance(text, expressions);
		List<Integer> results = calculateExpressions(expressions);
		Composite root = new Composite(results.toString());
		for (Integer result : results) {
			Leaf leaf = new Leaf(String.valueOf(result));
			root.add(leaf);
		}
		return root;
	}

	private List<Integer> calculateExpressions(List<String> expressions) {
		List<Integer> result = null;
		if (expressions != null) {
			result = new ArrayList<Integer>();
			for (String strMathExpression : expressions) {
				Integer value = calculator.calculate(strMathExpression, variables);
				result.add(value);
			}
		}
		return result;
	}

	private void nodeInheritance(Composite text, List<String> expressions) {
		for (int i = 0; i < text.childCount(); i++) {
			Component child = (Component) text.getChild(i);
			if (child instanceof Leaf) {
				String value = (String) child.getValue();
				if (value.matches(regex)) {
					expressions.add(value);
				}
			} else {
				nodeInheritance((Composite) child, expressions);
			}
		}
	}

	public String restore(Composite text) {
		StringBuilder stringBuilder = new StringBuilder();
		restoreText(text, stringBuilder);
		String result = stringBuilder.toString();
		System.out.println(result);
		return result;
	}

	private void restoreText(Composite text, StringBuilder stringBuilder) {
		for (int i = 0; i < text.childCount(); i++) {
			Component child = (Component) text.getChild(i);
			if (child instanceof Leaf) {
				stringBuilder.append((String) child.getValue()).append(" ");
			} else {
				stringBuilder.append("\t");
				restoreText((Composite) text.getChild(i), stringBuilder);
				stringBuilder.append("\n");
			}
		}
	}

	public String removeWordsByLength(Composite tree, int length) {
		StringBuilder stringBuilder = new StringBuilder();
		removeWordWithGivenLength(tree, stringBuilder, length);
		String result = stringBuilder.toString();
		return result;
	}

	private void removeWordWithGivenLength(Composite tree, StringBuilder stringBuilder, int length) {
		for (int i = 0; i < tree.childCount(); i++) {
			Component child = (Component) tree.getChild(i);
			if (child instanceof Leaf) {
				String value = child.getValue();
				if (value.length() != length) {
					stringBuilder.append(child.getValue()).append(" ");
				}
			} else {
				stringBuilder.append("\t");
				removeWordWithGivenLength((Composite) tree.getChild(i), stringBuilder, length);
				stringBuilder.append("\n");
			}
		}
	}

	public String removeWordsStartingWithGivenLetter(Composite tree, String letter) {
		StringBuilder stringBuilder = new StringBuilder();
		removeWordsStartingWith(tree, stringBuilder, letter);
		String result = stringBuilder.toString();
		return result;
	}

	private void removeWordsStartingWith(Composite tree, StringBuilder sb, String letter) {
		for (int i = 0; i < tree.childCount(); i++) {
			Component child = (Component) tree.getChild(i);
			if (child instanceof Leaf) {
				String value = child.getValue();
				if (!value.toLowerCase().startsWith(letter)) {
					sb.append(child.getValue()).append(" ");
				}
			} else {
				sb.append("\t");
				removeWordsStartingWith((Composite) tree.getChild(i), sb, letter);
				sb.append("\n");
			}
		}
	}

	public int countSentencesWithEqualWords(Composite text) {
		int count = 0;
		for (Component component : text.getComponents()) {
			if (component.getChild(0) instanceof Leaf) {
				count += findEqualWords((Composite) component);
			} else {
				count += countSentencesWithEqualWords((Composite) component);
			}
		}
		return count;
	}

	public int findEqualWords(Composite composite) {
		if (composite.getChild(0) instanceof Composite) {
			return 0;
		}
		String saveValue;
		int counter = 0;
		for (int i = 0; i < composite.getComponents().size(); i++) {
			saveValue = ((Leaf) composite.getComponents().get(i)).getValue();
			counter++;
			for (int j = counter; j < composite.getComponents().size(); j++) {
				String word = ((Leaf) composite.getComponents().get(j)).getValue();
				if (saveValue.equals(word)) {
					return 1;
				}
			}
		}
		return 0;
	}

}
