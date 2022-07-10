package org.example.question4;

import org.example.question4.command.factory.CommandFactory;
import org.example.question4.command.pojo.CommandPojo;
import java.util.Scanner;

public class Question4Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            CommandPojo commandPojo = CommandFactory.getCommandPojo(line);
            execute(commandPojo);
        }
    }

    public static void execute(CommandPojo commandPojo) {
        commandPojo.getCommandService().execute(commandPojo);
    }

}
