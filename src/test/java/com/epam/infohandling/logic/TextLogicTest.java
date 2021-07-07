package com.epam.infohandling.logic;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.epam.infohandling.TextProcessor;
import com.epam.infohandling.model.Composite;

public class TextLogicTest {

	@Test
	public void calculatorTest() throws IOException {
		TextProcessor textProcessor = new TextProcessor();
		Composite composite = (Composite) textProcessor.parseText("text.txt");
		TextLogic logic = new TextLogic();
		Composite result = (Composite) logic.calculate(composite);

		Assert.assertEquals("[20, 8, -3, 19, 145, 840]", result.toString());
	}

	@Test
	public void restoreTest() throws IOException {
		TextProcessor textProcessor = new TextProcessor();
		Composite composite = (Composite) textProcessor.parseText("test.txt");
		TextLogic logic = new TextLogic();
		String result = logic.restore(composite);
		String expected = "Integer et metus leo. Sed.\n" + "In tincidunt erat in odio.\n"
				+ "Vivamus fringilla rutrum scelerisque. Nulla.";

		Assert.assertEquals(expected, result.toString().trim());
	}

	@Test
	public void removeWordByGivenLengthTest() throws IOException {
		TextProcessor textProcessor = new TextProcessor();
		Composite composite = (Composite) textProcessor.parseText("test.txt");
		TextLogic logic = new TextLogic();
		String result = logic.removeWordsByLength(composite, 2);
		String expected = "Integer metus leo. Sed.\n" + "tincidunt erat odio.\n"
				+ "Vivamus fringilla rutrum scelerisque. Nulla.";

		Assert.assertEquals(expected, result.toString().trim());
	}

	@Test
	public void removeWordsByGivenLengthTest() throws IOException {
		TextProcessor textProcessor = new TextProcessor();
		Composite composite = (Composite) textProcessor.parseText("test.txt");
		TextLogic logic = new TextLogic();
		String result = logic.removeWordsStartingWithGivenLetter(composite, "m");
		String expected = "Integer leo. Sed.\n" + "tincidunt erat odio.\n"
				+ "Vivamus fringilla rutrum scelerisque. Nulla.";

		Assert.assertEquals(expected, result.toString().trim());
	}

	@Test
	public void countSentencesWithEqualWordsTest() throws IOException {
		TextProcessor textProcessor = new TextProcessor();
		Composite composite = (Composite) textProcessor.parseText("text.txt");
		TextLogic logic = new TextLogic();
		int result = logic.countSentencesWithEqualWords(composite);

		Assert.assertEquals(4, result);
	}
}
