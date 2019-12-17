package com.github.zszlly.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NoTestProperties extends Properties {

    private static final String DEFAULT_PATH = "noTest.properties";

    public NoTestProperties() {
        this(System.getProperty("noTestPropertiesDir", DEFAULT_PATH));
    }

    public NoTestProperties(String filePath) {
        this(new File(filePath));
    }

    public NoTestProperties(File file) {
        super();
        try {
            super.load(new FileReader(file));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
