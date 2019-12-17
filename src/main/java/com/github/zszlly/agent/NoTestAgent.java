package com.github.zszlly.agent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zszlly.recorder.asm.NoTestClassVisitor;
import com.github.zszlly.util.ClassUtils;
import com.github.zszlly.util.SneakyThrow;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.*;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;

public class NoTestAgent extends ClassLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String NO_TEST_RECORD_METHOD_JSON = "/tmp/noTestRecordMethod.json";
    private static Map<Class<?>, Map<String, Set<String>>> NO_TEST_RECORD_METHOD = new HashMap();

    // pattern [method full name][(argument type, split with ';')][return type]
    // e.g. com.github.zszlly.agent.NoTestAgent.example(int;java.lang.Object)java.lang.Object
    @SuppressWarnings("unchecked")
    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException, InterruptedException, IOException {
        ((List<String>) MAPPER.readValue(new FileInputStream(NO_TEST_RECORD_METHOD_JSON), List.class))
                .forEach(str -> {
                    int splitPoint = str.indexOf("(");
                    String classNameAndMethodName = str.substring(0, splitPoint);
                    String[] tmp = classNameAndMethodName.split("\\.");
                    Class<?> clazz = ClassUtils.forName(Type.getType(tmp[0]).getClassName());
                    NO_TEST_RECORD_METHOD.computeIfAbsent(clazz, key -> new HashMap<>());
                    Map<String, Set<String>> methods = NO_TEST_RECORD_METHOD.get(clazz);
                    String methodName = tmp[1];
                    methods.computeIfAbsent(methodName, key -> new HashSet<>());
                    Set<String> descriptions = methods.get(methodName);
                    String methodDescription = str.substring(splitPoint);
                    descriptions.add(methodDescription);
                });
        NO_TEST_RECORD_METHOD
                .forEach((clazz, methods) -> {
                    try {
                        ClassReader cr = new ClassReader(clazz.getName());
                        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                        NoTestClassVisitor pt = new NoTestClassVisitor(Opcodes.ASM5, cw, methods);
                        cr.accept(pt, EXPAND_FRAMES);
                        inst.redefineClasses(new ClassDefinition(clazz, cw.toByteArray()));
                    } catch (IOException | ClassNotFoundException | UnmodifiableClassException e) {
                        SneakyThrow.sneakyThrow(e);
                    }
                });
    }


}
