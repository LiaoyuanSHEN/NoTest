package com.github.zszlly.recorder.asm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zszlly.io.CaseHolder;
import com.github.zszlly.model.Case;
import com.github.zszlly.util.SneakyThrow;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ASMCaseSaver {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final int MAX_CASES_COUNT = 10;
    private static final List<Case> LIST = new LinkedList<>();
    private static int count = 0;
    private static String STORAGE_FILE_PATH;

    static {
        MAPPER.writerFor(new TypeReference<List>(){});
    }

    public static void setStorageFilePath(String storageFilePath) {
        STORAGE_FILE_PATH = storageFilePath;
    }

    public static synchronized void saveCase(Case c) {
        LIST.add(c);
        if (++count == MAX_CASES_COUNT) {
            try (OutputStream out = new FileOutputStream(STORAGE_FILE_PATH)) {
                MAPPER.writeValue(out, new CaseHolder(LIST));
                System.out.println("Cases saved to " + STORAGE_FILE_PATH);
            } catch (IOException e) {
                SneakyThrow.sneakyThrow(e);
            }
        }
    }

    public static String toJsonString() {
        try {
            return MAPPER.writeValueAsString(new CaseHolder(LIST));
        } catch (IOException e) {
            SneakyThrow.sneakyThrow(e);
            return null;
        }
    }

}
