package org.hac.core;

import com.beust.jcommander.JCommander;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hac.Command;
import org.hac.ast.ASTree;
import org.hac.lexer.Lexer;
import org.hac.token.Token;
import org.hac.util.StringUtil;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Core {
    private static final Logger logger = LogManager.getLogger(Core.class);

    public static void start(String[] args) {
        logger.info("start haclang");
        Command command = new Command();
        JCommander jc = JCommander.newBuilder().addObject(command).build();
        jc.parse(args);
        if (command.help) {
            jc.usage();
            return;
        }
        if (StringUtil.isEmpty(command.filename)) {
            logger.error("filename is null");
            return;
        }
        String extName = StringUtil.getExtName(command.filename);
        if (StringUtil.isEmpty(extName)) {
            logger.error("extension name is null");
            return;
        } else {
            if (!extName.equalsIgnoreCase("hac")) {
                logger.error("error extension name");
                return;
            }
        }
        Path path = Paths.get(command.filename);
        if (!Files.exists(path)) {
            logger.error("file is not exist");
            return;
        }
        try {
            FileReader reader = new FileReader(path.toFile());
            Lexer lexer = new Lexer(reader);
            BasicParser bp = new BasicParser();
            while (lexer.peek(0) != Token.EOF) {
                ASTree ast = bp.parse(lexer);
                System.out.println("=> " + ast.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
