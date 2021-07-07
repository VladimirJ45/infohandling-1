package com.epam.infohandling.parsing;

public class ChainBuilder {

	public Parser build() {
		return new TextParser(new ParagraphParser(new SentenceParser(null)));
	}
}
