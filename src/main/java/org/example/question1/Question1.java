package org.example.question1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Question1 {
    public static void main(String[] args) {
        int totalCount = 0;
        int postCount = 0;
        int getCount = 0;
        Map<String, Integer> requestCountMap = new HashMap<>();
        Map<String, HashSet<String>> URIMap = new HashMap<>();
        try (FileReader fr = new FileReader(Question1.class.getResource("access.log").getFile())) {
            BufferedReader bf = new BufferedReader(fr);
            while(true) {
                String s = bf.readLine();
                if(s == null) {
                    break;
                }
                s = s.trim();
                totalCount++;
                if(s.startsWith("GET")) {
                    getCount++;
                } else if(s.startsWith("POST")) {
                    postCount++;
                }
                String[] strings = s.split(" ");
                s = strings[1];
                strings = s.split("\\?");
                s = strings[0];
                //System.out.println(s);
                if(requestCountMap.containsKey(s)) {
                    requestCountMap.put(s, requestCountMap.get(s) + 1);
                } else {
                    requestCountMap.put(s, 1);
                }
                strings = s.split("/");
                if(strings.length == 2) {
                    s = "/" + strings[1];
                } else if (strings.length == 3) {
                    s =  "/" + strings[1] + "/" + strings[2];
                } else {
                    s =  "/" + strings[1] + "/" + strings[2] + "/" + strings[3];
                }

                if(URIMap.containsKey(strings[1])) {
                    URIMap.get(strings[1]).add(s);
                } else {
                    HashSet<String> set = new HashSet<>();
                    set.add(s);
                    URIMap.put(strings[1], set);
                }
            }

            System.out.println("总请求数为：" + totalCount);
            System.out.println("========================================");
            System.out.println("GET请求数为：" + getCount);
            System.out.println("========================================");
            System.out.println("POST请求数为：" + postCount);
            System.out.println("========================================");

            List<Map.Entry<String, Integer>> list = new ArrayList<>((requestCountMap.entrySet()));
            Collections.sort(list, (o1, o2) -> o2.getValue()- o1.getValue());
            for(int i = 0;i < 10;i++) {
                System.out.println("请求第" + (i+1) + "多的接口为：" + list.get(i).getKey() + "，请求数为" + list.get(i).getValue());
            }

            System.out.println("========================================");

            for(Map.Entry<String, HashSet<String>> entry : URIMap.entrySet()) {
                String s = entry.getKey();
                HashSet<String> set = entry.getValue();
                System.out.println(s + "分类下的URI有如下" + set.size() + "种：");
                for(String str : set) {
                    System.out.println("\t" + str);
                }
            }
        } catch (IOException e) {
        }
    }
}
