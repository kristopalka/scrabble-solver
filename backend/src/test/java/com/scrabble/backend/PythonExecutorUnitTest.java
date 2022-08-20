package com.scrabble.backend;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.scrabble.backend.util.PythonExecutor.executeScript;
import static org.assertj.core.api.Assertions.assertThat;


class PythonExecutorUnitTest {

	@Test
	void executeTestScript() throws IOException {
		String out = executeScript("test.py", "world");
		assertThat(out).isEqualTo("Hello world!");
	}

}
