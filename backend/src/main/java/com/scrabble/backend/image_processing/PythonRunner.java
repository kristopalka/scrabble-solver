package com.scrabble.backend.image_processing;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class PythonRunner {

    public static String readStream(InputStream stream) throws IOException {
        BufferedReader std = new BufferedReader(new InputStreamReader(stream));

        StringBuilder out = new StringBuilder();
        String s;
        while ((s = std.readLine()) != null) {
            out.append(s).append("\n");
        }
        return out.toString();
    }

    private static String getReturnedValue(String output) {
        Matcher matcher = Pattern.compile(".*\\{\"output\": (.*)}$").matcher(output);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String executeScript(String pythonExec, String absoluteFilePath, String... args) throws IOException {
        List<String> command = new ArrayList<>();
        command.add(pythonExec);
        command.add(absoluteFilePath);
        command.addAll(List.of(args));

        ProcessBuilder processBuilder = new ProcessBuilder(command);

        Process process = processBuilder.start();

        String out = readStream(process.getInputStream());
        String err = readStream(process.getErrorStream());


        log.warn("Python script output:\n" + out);
        if (err.contains("Traceback")) throw new RuntimeException("Error while processing python script:\n", new Throwable(err));

        return getReturnedValue(out);
    }
}
