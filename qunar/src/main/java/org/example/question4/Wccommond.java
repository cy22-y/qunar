package org.example.question4;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Wccommond implements ParentCommond {
    @Override
    public void work(String root, String[] parses,String filename) throws IllegalAccessException, IOException {

        if (!support(root, parses)) {
            throw new IllegalAccessException("指令不存在且指令不合法");
        } else {
            String keyword = parses[0];
            File file = new File(filename);
            List<String> list = Files.readLines(file, Charsets.UTF_8);
            System.out.println(list.size());


        }


    }

    @Override
    public boolean support(String root, String[] parse) {

        if(root.equals(Commonds.WC_COMMAND) && (parse.length == 1 || parse.length == 2))
            return true;
        else
            return false;
    }
}


