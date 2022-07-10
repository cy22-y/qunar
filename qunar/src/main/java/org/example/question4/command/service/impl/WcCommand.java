package org.example.question4.command.service.impl;

import com.google.common.collect.Maps;
import org.example.question4.command.pojo.CommandPojo;
import org.example.question4.command.service.CommandService;
import org.example.question4.command.util.FileUtils;
import org.thymeleaf.util.MapUtils;

import java.util.List;
import java.util.Map;

public class WcCommand extends CommandService {

    @Override
    protected void runCommand(CommandPojo commandPojo) {
        Map<String, Object> result;
        List<String> list;
        if (!MapUtils.isEmpty(commandPojo.getResult())) {
            result = commandPojo.getResult();
            list = (List<String>) result.get("content");
        } else {
            list = FileUtils.readFile(commandPojo.getFilePath());
            result = Maps.newHashMap();
            result.put("content", list);
            commandPojo.setResult(result);
        }

        commandPojo.setResult(result);
        if (commandPojo.getNextCommand() != null) {
            commandPojo.getNextCommand().setResult(result);
        }

        System.out.println("总行数：" + list.size());
    }
}
