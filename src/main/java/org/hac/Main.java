package org.hac;

import com.beust.jcommander.JCommander;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hac.util.StringUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("start haclang");
        Command command = new Command();
        JCommander jc = JCommander.newBuilder().addObject(command).build();
        jc.parse(args);
        if (command.help) {
            jc.usage();
            return;
        }
        if(StringUtil.isEmpty(command.filename)){
            logger.error("filename is null");
            return;
        }
        String extName = StringUtil.getExtName(command.filename);
        if(StringUtil.isEmpty(extName)){
            logger.error("extension name is null");
            return;
        }else{
            if(!extName.equalsIgnoreCase("hac")){
                logger.error("error extension name");
                return;
            }
        }
        Path path = Paths.get(command.filename);
        if(!Files.exists(path)){
            logger.error("file is not exist");
            return;
        }
        try {
            byte[] input = Files.readAllBytes(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
