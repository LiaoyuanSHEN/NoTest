package com.github.zszlly;

import com.sun.tools.attach.VirtualMachine;

import java.io.File;

public class NoTestAttachment {

    private static final String RECORD_METHOD_PARAMETER = "recordMethod";
    private static final String DEFAULT_NO_TEST_RECORD_METHOD_JSON_PATH = "noTestRecordMethod.json";
    private static final String STORAGE_FILE_PATH_KEY = "caseSavingPath";
    private static final String DEFAULT_STORAGE_FILE_PATH = "./noTestCases.json";

    public static void main(String[] args) throws Throwable {
        VirtualMachine vm = VirtualMachine.attach(args[0]);
        String recordMethodFilePath = new File(System.getProperty(RECORD_METHOD_PARAMETER, DEFAULT_NO_TEST_RECORD_METHOD_JSON_PATH)).getAbsolutePath();
        String storageFilePath = new File(System.getProperty(STORAGE_FILE_PATH_KEY, DEFAULT_STORAGE_FILE_PATH)).getAbsolutePath();
        String agentArgs = recordMethodFilePath + ";" + storageFilePath;
        vm.loadAgent(NoTestAttachment.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(),
                agentArgs);
        vm.detach();
        System.out.println("Done.");
    }

}
