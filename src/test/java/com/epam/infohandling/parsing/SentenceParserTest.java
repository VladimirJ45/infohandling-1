package com.epam.infohandling.parsing;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public class SentenceParserTest {

	@Test
	public void sentenceParserTest() {

		Parser successor = Mockito.mock(Parser.class);
		String text = "Hello world my name is [8 2 -] John Java";

		when(successor.parse(anyString())).thenAnswer(invocation -> {
			String argument = (String) invocation.getArguments()[0];
			return new Leaf(argument);
		});

		SentenceParser sentenceParser = new SentenceParser(successor);
		Composite composite = sentenceParser.parse(text);
		Assert.assertEquals(new Leaf("Hello"), composite.getChild(0));
		Assert.assertEquals(new Leaf("[8 2 -]"), composite.getChild(5));
		Assert.assertEquals(new Leaf("Java"), composite.getChild(7));
	}
}
