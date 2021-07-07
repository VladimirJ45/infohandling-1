package com.epam.infohandling.parsing;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public class ParagraphParserTest {

	@Test
	public void paragraphParserTest() {

		Parser successor = Mockito.mock(Parser.class);

		when(successor.parse(anyString())).thenAnswer(invocation -> {
			String argument = (String) invocation.getArguments()[0];
			return new Leaf(argument);
		});

		ParagraphParser paragraphParser = new ParagraphParser(successor);
		Composite composite = paragraphParser.parse("I like apples. My car is painted blue. John Java is my best friend");
		Assert.assertEquals(new Leaf("I like apples."), composite.getChild(0));
		Assert.assertEquals(new Leaf("John Java is my best friend."), composite.getChild(2));
	}
}
