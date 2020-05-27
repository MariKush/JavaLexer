package com.lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Lexer {

    private String wholeFile;

    private static final Logger log = Logger.getLogger(Lexer.class.getName());

    public Lexer(String filePath) throws IOException {
        readWholeFile(filePath);
    }

    private void readWholeFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        if(fileInputStream.read(data) != -1) {
            log.info("File " + filePath + " read correctly");
        }
        else{
            log.severe("File " + filePath + "  read incorrectly");
        }
        fileInputStream.close();
        wholeFile = new String(data, StandardCharsets.UTF_8);
        System.out.println(wholeFile);
    }


}
