package com.github.zszlly;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zszlly.agent.GeneratedClassA;
import com.github.zszlly.annotation.NoTest;
import com.github.zszlly.io.CaseHolder;
import com.github.zszlly.io.CaseLoader;
import com.github.zszlly.io.CaseSaver;
import com.github.zszlly.mark.NoTestMark;
import com.github.zszlly.model.Case;
import com.github.zszlly.player.NoTestPlayer;
import com.github.zszlly.recorder.asm.ASMCaseSaver;
import com.github.zszlly.recorder.asm.NoTestClassVisitor;
import com.github.zszlly.util.FieldUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.ASMifier;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;

public class DummyMain extends ClassLoader implements NoTestMark {

    private int a;

    public static void main(String[] args) throws Throwable {
        test();
//        System.out.println(MethodUtils.toNoTestMethodDescription(DummyMain.class.getDeclaredMethod("add", int.class, int.class)));
//        DummyMain dummyMain = new DummyMain();
//        while (true) {
//            dummyMain.add(1, 2);
//            Thread.sleep(1000);
//        }
    }

    private static void printASM() throws IOException {
        ASMifier.main(new String[] {GeneratedClassA.class.getName()});
    }

    private static void test() throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        System.out.println("test record");

        ClassReader cr = new ClassReader(DummyMain.class.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        Map<String, Set<String>> methods = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add(Type.getMethodDescriptor(DummyMain.class.getDeclaredMethod("add", int.class, int.class)));
        methods.put("add", set);
        set = new HashSet<>();
        set.add(Type.getMethodDescriptor(DummyMain.class.getDeclaredMethod("addB", int.class, GetB.class)));
        methods.put("addB", set);
        set = new HashSet<>();
        set.add(Type.getMethodDescriptor(DummyMain.class.getDeclaredMethod("addArray", Integer[].class)));
        methods.put("addArray", set);
        set = new HashSet<>();
        set.add(Type.getMethodDescriptor(DummyMain.class.getDeclaredMethod("addAAndB", GetB.class)));
        methods.put("addAAndB", set);
        NoTestClassVisitor pt = new NoTestClassVisitor(Opcodes.ASM5, cw, methods);
        cr.accept(pt, EXPAND_FRAMES);
        byte[] bytes = cw.toByteArray();
//        FileOutputStream out = new FileOutputStream("D:\\Suit\\Documents\\Workspace\\Java\\no-test\\a.class");
//        out.write(bytes);
//        out.close();
//        CheckClassAdapter.verify(new ClassReader(bytes), true, new PrintWriter(System.out));

        Class<?> clazz = new DummyMain().defineClass(null, bytes, 0, bytes.length);
        Object obj = clazz.newInstance();
        Method add = clazz.getDeclaredMethod("add", int.class, int.class);
        add.invoke(obj, 1, 2);
        Method addB = clazz.getDeclaredMethod("addB", int.class, GetB.class);
        addB.invoke(obj, 2, (GetB) () -> 3);
        Method addArray = clazz.getDeclaredMethod("addArray", Integer[].class);
        addArray.invoke(obj, new Object[]{new Integer[]{1, 2, 3, 4, 5}});
        Method addAAndB = clazz.getDeclaredMethod("addAAndB", GetB.class);
        FieldUtils.setValue(obj, clazz.getDeclaredField("a"), 5);
        addAAndB.invoke(obj, (GetB) () -> 4);
//
//        CaseHolderImpl caseHolderImpl = new CaseHolderImpl();
//        DummyMain testDummyMain = NoTestRecorder.record(new DummyMain(), caseHolderImpl);
//        testDummyMain.add(1, 2);
//        testDummyMain.addB(2, () -> 3);
//        testDummyMain.a = 3;
//        testDummyMain.addAAndB(() -> 4);
        ObjectMapper mapper = new ObjectMapper();
        String caseJson = ASMCaseSaver.toJsonString();
        JsonNode node = mapper.readTree(caseJson);
        System.out.println(caseJson);
        System.out.println("test playback");
        Collection<Case> cases = mapper.readValue(caseJson, CaseHolder.class).getCases();
        NoTestPlayer player = new NoTestPlayer(cases, DummyMain.class);
        player.play();
    }

    @NoTest
    public int add(int a, int b) {
        int value = a + b;
        System.out.println(value);
        return value;
    }

    @NoTest
    public int addB(int a, GetB getB) {
        int value = a + getB.getBFn();
        System.out.println(value);
        return value;
    }

    @NoTest
    public int addArray(Integer[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        System.out.println(sum);
        return sum;
    }

    @NoTest
    public int addAAndB(GetB getB) {
        int value = a + getB.getBFn();
        System.out.println(value);
        return value;
    }

    public interface GetB {
        int getBFn();
    }

    private static class CaseHolderImpl implements CaseSaver, CaseLoader {

        private static final String CASE_FILE_PATH = "cases.json";
        private static final ObjectMapper MAPPER = new ObjectMapper();

        private List<Case> caseList = new LinkedList<>();

        @Override
        public void addCase(Case c) {
            caseList.add(c);
        }

        @Override
        public void addCases(Collection<Case> cases) {
            caseList.addAll(cases);
        }

        @Override
        public void save() {
            try {
                File file = new File(CASE_FILE_PATH);
                MAPPER.writeValue(file, caseList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void load() {

        }

        @Override
        public CaseHolder getCaseHolder() {
            return new CaseHolder(caseList);
        }
    }

}
