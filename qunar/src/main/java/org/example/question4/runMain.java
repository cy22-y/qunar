package org.example.question4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class runMain {
    private static final List<ParentCommond> commonds = new ArrayList<>();


    //添加处理
    public static void addCommond(ParentCommond commond){
        commonds.add(commond);
    }

    public static void main(String[] args) throws IllegalAccessException, IOException {
        //读入处理命令
        addCommond(new CatCommond());
        addCommond(new GrepCommond());
        addCommond(new Wccommond());
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(!line.contains("|"))
        {
            String root = splitUtil.commond(line);
            String[] parses = splitUtil.parse(line);
            String filename = splitUtil.filenam(line);
            boolean flag = false;
            for (int i =0 ;i<commonds.size();i++)
            {

                ParentCommond commond = commonds.get(i);

                if (commond.support(root, parses))
                {
                    System.out.println("命令类型为："+ root);
                    commond.work(root, parses,filename);
                    flag = true;
                    break;
                }
                else {
                    System.out.println("不合适命令类型为："+ commond.toString());
                }
            }

        }
        else{
            String filename = " ";
            String[] all = line.trim().split("\\|");
            System.out.println(line);
            System.out.println(all[0]+" ====== ");
            System.out.println(all[1]+"=======");

            filename = splitUtil.filenam(all[0]);
            System.out.println(filename);

            for (int i = 0; i < all.length ; i++) {
                boolean flag = false;
                String[] parses = splitUtil.parse(all[i]);
                String root = splitUtil.commond(all[i]);


                for (int j =0 ;j < commonds.size();j++)
                {
                    ParentCommond commond = commonds.get(j);

                    if(i== all.length-1)
                    {
                        if (commond.support(root, parses))
                        {
                            System.out.println("命令类型为："+ root);
                            commond.work(root, parses,filename);
                            flag = true;
                            break;
                        }
                    }

                }

            }
            }
    }


    //轮询匹配处理命令
    public static void poll(String root,String[] parses,String filename) throws IOException, IllegalAccessException {
        //轮询处理
        boolean flag = false;

        for (ParentCommond commond : commonds)
        {

            if (commond.support(root, parses))
            {
                System.out.println("命令类型为："+ root);
                commond.work(root, parses,filename);
                flag = true;
                break;
            }
        }
        if (flag)
            throw new IllegalAccessException("输入无效命令");

    }







}
