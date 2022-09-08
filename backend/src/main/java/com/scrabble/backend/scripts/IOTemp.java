package com.scrabble.backend.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOTemp {
    private static final String imagePref = "file";
    private static final String imageExt = ".jpg";
    private final File tempDir = new File(".temp");
    private final File imageFile;


    public IOTemp(String path) throws IOException {
        createTempDirIfNotExist();
        imageFile = new File(path);
    }

    public IOTemp(byte[] image) throws IOException {
        createTempDirIfNotExist();
        imageFile = new File(generateUniquePath(image));
        FileOutputStream out = new FileOutputStream(imageFile);
        out.write(image);
        out.close();
    }

    public byte[] readTemp() throws IOException {
        FileInputStream in = new FileInputStream(imageFile);
        byte[] image = in.readAllBytes();
        in.close();
        return image;
    }

    private String generateUniquePath(byte[] image) {
        return tempDir.getName() + "/" + imagePref + Arrays.hashCode(image) + imageExt;
    }

    private void createTempDirIfNotExist() {
        if (!tempDir.exists()) {
            boolean is = tempDir.mkdir();
        }
    }


    public String getPath() {
        return imageFile.getAbsolutePath();
    }

    public void delete() {
        if (!imageFile.delete()) System.out.println(imageFile.getAbsolutePath() + " dont exist");
    }

}
