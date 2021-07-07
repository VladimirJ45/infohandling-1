package com.epam.infohandling.parsing;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public class TextParserTest {
	@Test
	public void textParserTest() {

		Parser successor = Mockito.mock(Parser.class);

		when(successor.parse(anyString())).thenAnswer(invocation -> {
			String argument = (String) invocation.getArguments()[0];
			return new Leaf(argument);
		});

		TextParser textParser = new TextParser(successor);
		Composite composite = textParser.parse("1\n2");
		Assert.assertEquals(new Leaf("1"), composite.getChild(0));
		Assert.assertEquals(new Leaf("2"), composite.getChild(1));
	}
}
