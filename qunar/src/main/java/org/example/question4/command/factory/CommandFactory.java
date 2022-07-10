package org.example.question4.command.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.example.question4.command.pojo.CommandPojo;
import org.example.question4.command.service.CommandService;
import org.example.question4.command.service.impl.CatCommand;
import org.example.question4.command.service.impl.GrepCommand;
import org.example.question4.command.service.impl.WcCommand;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CommandFactory {


    private static volatile Map<String, CommandService> commandMap = Maps.newHashMap();

    static {
        commandMap.put("cat", new CatCommand());
        commandMap.put("grep", new GrepCommand());
        commandMap.put("wc", new WcCommand());
    }

    private static CommandService getCommandService(String cmd) {
        CommandService commandService = commandMap.get(cmd);
        if (commandService != null) {
            synchronized (commandService) {
                return commandService;
            }
        }
        return null;
    }


    public static CommandPojo getCommandPojo(String line) {
        try {
            String[] cmds = line.split("\\|");
            CommandPojo commandPojo = new CommandPojo();
            CommandPojo temp = commandPojo;
            String filePath = null;
            for (int i = 0; i < cmds.length; i++) {
                String[] params = cmds[i].split(" ");
                CommandService commandService = getCommandServiceByParams(params);
                if (commandService == null) {
                    throw new RuntimeException("null");
                }
                if (filePath == null) {
                    filePath = getFilePath(params);
                }
                String cmdKeyWord = getCmdKeyWord(params);
                temp.setKeyWord(cmdKeyWord);
                temp.setFilePath(filePath);
                temp.setCommandService(commandService);
                List<String> innerCmd = getInnerCmd(params);
                temp.setInnerCmd(innerCmd);
                if (i + 1 < cmds.length) {
                    temp.setNextCommand(new CommandPojo());
                    temp = temp.getNextCommand();
                }
            }

            return commandPojo;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取指令错误:" + e.getMessage());
            return null;
        }
    }


    //获取具体执行的那个指令
    private static CommandService getCommandServiceByParams(String[] params) {
        for (String param : params) {
            CommandService commandService = getCommandService(param);
            if (commandService != null) {
                return commandService;
            }
        }

        return null;
    }

    //获取操作的文件路径
    private static String getFilePath(String[] params) {
        for (String param : params) {
            File file = new File(param);
            if (file.exists()) {
                return param;
            }
        }
        throw new RuntimeException("请输入操作文件名");
    }

    //获取其他指令，比如cat -n abc.txt  这里获取的就是-n
    private static List<String> getInnerCmd(String[] params) {
        List<String> innerCmd = Lists.newArrayList();
        for (String param : params) {
            if (param.startsWith("-")) {
                innerCmd.add(param);
            }
        }
        return innerCmd;
    }

    private static String getCmdKeyWord(String[] params) {
        List<String> innerCmd = Lists.newArrayList();
        for (String param : params) {
            File file = new File(param);
            String[] ps = {param};
            if (!param.startsWith("-") && !file.exists() && getCommandServiceByParams(ps) == null) {
                return param;
            }
        }
        return "";
    }
}
