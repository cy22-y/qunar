package org.example.question4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class runMain {
    private static final List<ParentCommand> commands = new ArrayList<>();


    //添加处理
    public static void addCommand(ParentCommand command) {
        commands.add(command);
    }

    public static void main(String[] args) throws IllegalAccessException, IOException {
        //读入处理命令
        addCommand(new CatCommand());
        addCommand(new GrepCommand());
        addCommand(new Wccommand());
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (!line.contains("|")) {
            String root = splitUtil.command(line);
            String[] parses = splitUtil.parse(line);
            String filename = splitUtil.filename(line);
            boolean flag = false;
            for (int i = 0; i < commands.size(); i++) {

                ParentCommand command = commands.get(i);

                if (command.support(root, parses)) {
                    System.out.println("命令类型为：" + root);
                    command.work(root, parses, filename);
                    flag = true;
                    break;
                } else {
                    System.out.println("不合适命令类型为：" + command.toString());
                }
            }

        } else {
            String filename = " ";
            String[] all = line.trim().split("\\|");
            System.out.println(line);
            System.out.println(all[0] + " ====== ");
            System.out.println(all[1] + "=======");

            filename = splitUtil.filename(all[0]);
            System.out.println(filename);

            for (int i = 0; i < all.length; i++) {
                boolean flag = false;
                String[] parses = splitUtil.parse(all[i]);
                String root = splitUtil.command(all[i]);


                for (int j = 0; j < commands.size(); j++) {
                    ParentCommand command = commands.get(j);

                    if (i == all.length - 1) {
                        if (command.support(root, parses)) {
                            System.out.println("命令类型为：" + root);
                            command.work(root, parses, filename);
                            flag = true;
                            break;
                        }
                    }

                }

            }
        }
    }

    //轮询匹配处理命令
    public static void poll(String root, String[] parses, String filename) throws IOException, IllegalAccessException {
        //轮询处理
        boolean flag = false;

        for (ParentCommand command : commands) {

            if (command.support(root, parses)) {
                System.out.println("命令类型为：" + root);
                command.work(root, parses, filename);
                flag = true;
                break;
            }
        }
        if (flag)
            throw new IllegalAccessException("输入无效命令");
    }
}
