package com.scrabble.backend.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageTemp {
    private static final String imagePref = "image";
    private static final String imageExt = ".jpg";
    private final File tempDir = new File(".temp");
    private final File imageFile;


    private String nameAsHash(byte[] image) {
        return tempDir.getName() + "/" + imagePref + Arrays.hashCode(image) + imageExt;
    }

    private void createTempDirIfNotExist() {
        if (!tempDir.exists()) {
            boolean is = tempDir.mkdir();
        }
    }

    public ImageTemp(byte[] image) throws IOException {
        createTempDirIfNotExist();

        imageFile = new File(nameAsHash(image));
        FileOutputStream out = new FileOutputStream(imageFile);
        out.write(image);
        out.close();
    }

    public String getPath() {
        return imageFile.getAbsolutePath();
    }

    public void delete() {
        if(!imageFile.delete()) System.out.println(imageFile.getAbsolutePath() + " dont exist");
        if(!tempDir.delete()) System.out.println(tempDir.getAbsolutePath() + " dont exist");
    }

}
