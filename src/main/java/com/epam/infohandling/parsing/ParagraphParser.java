package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Component;
import com.epam.infohandling.model.Composite;

public class ParagraphParser extends AbstractParser {

	private static final String SPLITTER = "\\.";

	public ParagraphParser(Parser successor) {
		super(successor);
	}

	@Override
	public Composite parse(String paragraph) {
		paragraph = paragraph.trim();
		Composite composite = new Composite(paragraph);
		String[] parts = paragraph.split(SPLITTER);
		for (int i = 0; i < parts.length; i++) {
			String value = parts[i] + ".";
			Component inner = getSuccessor().parse(value.trim());
			composite.add(inner);
		}
		return composite;
	}
}
