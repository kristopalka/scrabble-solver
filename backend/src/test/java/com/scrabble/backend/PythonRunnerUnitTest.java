package com.scrabble.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.scrabble.backend.image_processing.PythonRunner.executeScript;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PythonRunnerUnitTest {
	@Value("${config.python-exec}")
	private String exec;
	@Value("${config.python-scripts}")
	private String scripts;

	@Test
	void executeTestScript() throws IOException {
		String out = executeScript(exec, scripts + "test.py", "world");
		assertThat(out).isEqualTo("{\"hello\": \"world\"}");
	}

}
