package com.epam.infohandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.epam.infohandling.model.Component;
import com.epam.infohandling.parsing.ChainBuilder;
import com.epam.infohandling.parsing.Parser;

public class TextProcessor {

	public Component parseText(String text) throws IOException {

		Parser parser = new ChainBuilder().build();
		InputStream input = getClass().getClassLoader().getResourceAsStream("/" + text);

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input))) {
			StringBuilder stringBulider = new StringBuilder();
			String line = bufferedReader.readLine();

			while (line != null) {
				stringBulider.append(line);
				stringBulider.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			String result = stringBulider.toString();
			return parser.parse(result.trim());
		}

	}

}
