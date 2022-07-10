package org.example.question4.command.service.impl;

import com.google.common.collect.Maps;
import org.example.question4.command.pojo.CommandPojo;
import org.example.question4.command.service.CommandService;
import org.example.question4.command.util.FileUtils;

import java.util.List;
import java.util.Map;

public class CatCommand extends CommandService {

    @Override
    protected void runCommand(CommandPojo commandPojo) {
        try {
            List<String> result = FileUtils.readFile(commandPojo.getFilePath());
            Map<String, Object> resultContent = Maps.newHashMap();
            resultContent.put("content", result);
            commandPojo.setResult(resultContent);
            if (commandPojo.getNextCommand() != null) {
                commandPojo.getNextCommand().setResult(resultContent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
