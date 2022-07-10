package org.example.question4.command.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.question4.command.service.CommandService;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandPojo {
    private String keyWord;
    private String filePath;
    private List<String> innerCmd;
    private CommandPojo nextCommand;
    private Map<String,Object> result;
    private CommandService commandService;

}
