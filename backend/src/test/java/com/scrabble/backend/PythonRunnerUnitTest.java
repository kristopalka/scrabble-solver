package com.scrabble.backend;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.scrabble.backend.image_processing.PythonRunner.executeScript;
import static org.assertj.core.api.Assertions.assertThat;


class PythonRunnerUnitTest {

	@Test
	void executeTestScript() throws IOException {
		String exec = "python";
		String scripts = "/home/krist/Projects/Scrabble-Solver/python_scripts/";

		String out = executeScript(exec, scripts + "test.py", "world");
		assertThat(out).isEqualTo("{\"hello\": \"world\"}");
	}

}
