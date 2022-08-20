package com.scrabble.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PythonExecutor {
    private static final String python = "python";
    private static final String src = "python/src/";


    public static String getPythonScriptPath(String file) throws IOException {
        Resource pythonFile = new ClassPathResource(src + file);
        return pythonFile.getURL().getPath();
    }

    public static String readStream(InputStream stream) throws IOException {
        BufferedReader std = new BufferedReader(new InputStreamReader(stream));

        StringBuilder out = new StringBuilder();
        String s;
        while ((s = std.readLine()) != null) {
            out.append(s);
        }
        return out.toString();
    }

    public static String executeScript(String file, String ... args) throws IOException {
        String scriptPath = getPythonScriptPath(file);
        String[] command = new String[]{python, scriptPath, Arrays.toString(args)};

        ProcessBuilder processBuilder = new ProcessBuilder(command);

        Process process = processBuilder.start();

        String in = readStream(process.getInputStream());
        String err = readStream(process.getErrorStream());

        if(err.contains("Traceback")){
            throw new RuntimeException("Error while processing python script", new Throwable(err));
        }

        return in;
    }
}
