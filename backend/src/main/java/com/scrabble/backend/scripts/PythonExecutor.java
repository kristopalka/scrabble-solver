package com.scrabble.backend.scripts;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PythonExecutor {
    private static final String python = "scripts";
    private static final String src = "scripts/";


    public static String getPythonScriptPath(String file) throws IOException {
        Resource pythonFile = new ClassPathResource(src + file);
        return pythonFile.getURL().getPath();
    }

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
        if(matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String executeScript(String file, String... args) throws IOException {
        String scriptPath = getPythonScriptPath(file);
        String[] command = new String[]{python, scriptPath, String.join(" ", args)};

        ProcessBuilder processBuilder = new ProcessBuilder(command);

        Process process = processBuilder.start();

        String out = readStream(process.getInputStream());
        String err = readStream(process.getErrorStream());


        System.out.println("Python script output:\n" + out);
        if (err.contains("Traceback")) throw new RuntimeException(
                "Error while processing python script:\n", new Throwable(err));

        return getReturnedValue(out);
    }
}
