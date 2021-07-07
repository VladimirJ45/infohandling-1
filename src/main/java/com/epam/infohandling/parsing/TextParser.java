package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Component;
import com.epam.infohandling.model.Composite;

public class TextParser extends AbstractParser {

	private static final String SPLITTER = "\n";

	public TextParser(Parser successor) {
		super(successor);
	}

	@Override
	public Composite parse(String text) {
		text = text.trim();
		Composite composite = new Composite(text);
		String[] parts = text.split(SPLITTER);
		for (String part : parts) {
			Component component = getSuccessor().parse(part);
			composite.add(component);
		}
		return composite;
	}
}
