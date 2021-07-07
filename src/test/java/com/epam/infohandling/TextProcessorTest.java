package com.epam.infohandling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.epam.infohandling.model.Composite;

public class TextProcessorTest {

	@Test
	public void textProcessorTest() throws IOException {
		TextProcessor textProcessor = new TextProcessor();
		Composite composite = (Composite) textProcessor.parseText("test.txt");

		assertTrue(composite.childCount() == 3);
		assertEquals("Integer et metus leo. Sed.", composite.getChild(0).toString());
		assertEquals("In tincidunt erat in odio.", composite.getChild(1).toString());
	}
}
