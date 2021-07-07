package com.epam.infohandling.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public class SentenceParser extends AbstractParser {

	private static final String SPLITTER = "\\s";
	private static final String regex = "\\[(.*?)\\]";

	public SentenceParser(Parser successor) {
		super(successor);
	}

	@Override
	public Composite parse(String sentence) {
		sentence = sentence.trim();
		Composite composite = new Composite(sentence);

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sentence);

		List<String> parts = new ArrayList<String>();
		List<Integer> index = new ArrayList<Integer>();

		while (matcher.find()) {
			index.add(matcher.start());
			index.add(matcher.end());
		}
		
		parts.add(sentence.substring(0, index.get(0)));

		for (int i = 0; i < index.size() - 1; i++) {
			parts.add(sentence.substring(index.get(i), index.get(i + 1)));
		}

		if (index.get(index.size() - 1) != sentence.length()) {
			parts.add(sentence.substring(index.get(index.size() - 1), sentence.length()));
		}

		for (String part : parts) {
			if (Pattern.matches(regex, part)) {
				Leaf leaf = new Leaf(part);
				composite.add(leaf);
			} else {
				String[] words = part.split(SPLITTER);
				for (String word : words) {
					if (!word.isEmpty()) {
						Leaf leaf = new Leaf(word);
						composite.add(leaf);
					}
				}
			}
		}
		return composite;
	}
}
