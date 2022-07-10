package org.example.question4.command.service;


import org.example.question4.command.pojo.CommandPojo;
import java.util.List;
import java.util.Map;

public abstract class CommandService {

    protected abstract void runCommand(CommandPojo commandPojo);

    public void execute(CommandPojo commandPojo){
        CommandPojo hasNext = commandPojo;
        while(hasNext != null && hasNext.getNextCommand() != null){
            hasNext.getCommandService().runCommand(hasNext);
            hasNext = hasNext.getNextCommand();
        }

        if(hasNext != null){
            hasNext.getCommandService().runCommand(hasNext);
            Map<String, Object> result = hasNext.getResult();
            List<String> content = (List<String>)result.get("content");
            content.forEach(System.out::println);
        }
    }

}
