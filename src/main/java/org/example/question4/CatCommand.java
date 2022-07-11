package org.example.question4;

import java.io.*;

public class CatCommand implements ParentCommand {

    @Override
    public void work(String root, String[] parses,String filename) throws IllegalAccessException, IOException {
        if(!support(root,parses)){
            throw new IllegalAccessException("指令不存在且指令不合法");
        }else{

           BufferedReader br = new BufferedReader(new FileReader(filename));
           StringBuffer sb = new StringBuffer();
           String line = "";
           while ((line = br.readLine()) != null){
               System.out.println(line);
           }
            System.out.println(sb.toString());
        }
    }



    @Override
    public boolean support(String root, String[] parse) {
        if(root.equals(Commands.CAT_COMMAND) && parse.length == 1 )
            return true;
        else
            return false;

    }



}
