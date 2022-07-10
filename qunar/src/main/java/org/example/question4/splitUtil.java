package org.example.question4;

import java.util.ArrayList;

public class splitUtil {

    //返回主命令
    public static String commond(String msg){
        msg = msg.trim();
        String[] res = msg.split(" ");
        return res[0];
    }

    //返回参数文件名，
    public static String pare(String msg) throws IllegalAccessException {
        msg = msg.trim();
        String[] res = msg.split(" ");
        if(res.length > 2){
            throw new IllegalAccessException("您的命令不合法或存在未知命令");
        }
        return res[1];

    }

    //返回参数集合,命令最长为带两个参数
    public static String[] parse(String msg) throws IllegalAccessException {
        msg = msg.trim();
        String[] res = msg.split(" ");
        String[] temp = new String[res.length-1];
        for (int i = 1; i < res.length; i++) {
            temp[i-1] = res[i];
        }
        return temp;

    }
    //分割得到filename
    public static String filenam(String msg){
        msg = msg.trim();

        String[] res = msg.split(" ");
        if(res[0].equals(Commonds.CAT_COMMAND))
        {
            System.out.println(res[1]);
            return res[1];
        }
        else if(res[0].equals(Commonds.GREP_COMMAND)){
            return res[2];
        }else if(res[0].equals(Commonds.WC_COMMAND)){
            System.out.println(res[2]);
            return res[2];
        }else {
            return null;
        }
    }
    //得到相应的管道


}
