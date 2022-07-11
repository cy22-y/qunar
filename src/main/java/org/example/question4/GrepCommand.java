package org.example.question4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GrepCommand implements ParentCommand {
    @Override
    public void work(String root, String[] parses,String filename) throws IllegalAccessException, IOException {

        if(!support(root,parses)){
            throw new IllegalAccessException("指令不存在且指令不合法");
        }else{
            String keyword = parses[0];
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null){
                if(line.contains(keyword))
                    System.out.println(line);
            }

        }
    }



    @Override
    public boolean support(String root, String[] parse) {
        if(root.equals(Commands.GREP_COMMAND) && (parse.length == 1 || parse.length == 2))
            return true;
        else
            return false;
    }


}
