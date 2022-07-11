package org.example.question2;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Question2 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader(Question2.class.getResource("StringUtils.txt").getFile())) {
            BufferedReader bf = new BufferedReader(fr);
            boolean flag = false;
            int count = 0;
            while (true) {
                String s = bf.readLine();
                if (s == null) {
                    break;
                }
                s = s.trim();
                if (s.startsWith("/*")) {
                    flag = true;
                }
                if (flag) {
                    if (s.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    if (!s.startsWith("//") && !s.equals("")) {
                        count++;
                    }
                }
            }

            String filePath = Question2.class.getResource("").getFile() + "\\validLineCount.txt";
            File file = new File(filePath);
            if(!file.exists()) {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(String.valueOf(count).getBytes(StandardCharsets.UTF_8));
            }
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
