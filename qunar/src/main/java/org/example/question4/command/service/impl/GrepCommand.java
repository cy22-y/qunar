package org.example.question4.command.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.example.question4.command.pojo.CommandPojo;
import org.example.question4.command.service.CommandService;
import org.example.question4.command.util.FileUtils;
import org.thymeleaf.util.MapUtils;

import java.util.List;
import java.util.Map;

public class GrepCommand extends CommandService {

    @Override
    protected void runCommand(CommandPojo commandPojo) {
        List<String> newContent = Lists.newArrayList();
        Map<String, Object> result;
        List<String> list;
        if (!MapUtils.isEmpty(commandPojo.getResult())) {
            result = commandPojo.getResult();
            list  = (List<String>) result.get("content");
        }else{
            list = FileUtils.readFile(commandPojo.getFilePath());
            result = Maps.newHashMap();
        }
        list.forEach(item -> {
            if (item.contains(commandPojo.getKeyWord())) {
                newContent.add(item);
            }
        });
        result.put("content", newContent);
        commandPojo.setResult(result);
        if (commandPojo.getNextCommand() != null) {
            commandPojo.getNextCommand().setResult(result);
        }
    }
}
