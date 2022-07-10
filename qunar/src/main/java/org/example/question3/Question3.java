package org.example.question3;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Question3 {
    static HashMap<Integer,Prop_Entity> index_map = new HashMap<>();
    static List<Map.Entry<Integer, Prop_Entity>> nature_list;
    static List<Map.Entry<Integer, Prop_Entity>> index_list;
    static List<Map.Entry<Integer, Prop_Entity>> char_list;
    static List<Map.Entry<Integer, Prop_Entity>> charDesc_list;
    static int row = 1;
    public static void main(String[] args) {
        //将数据读入到内存中
        read_prop();
        //按照题目要求使用不同的方式对数据进行排序
        nature_sort();
        index_sort();
        char_sort();
        charDesc_sort();
        //将模板内容读入内存并进行替换
        try {
            read_template();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("已处理完成，内容已输出到sdxl.txt中");
    }

    //将prop文件的数据读入index_map中
    private static void read_prop() {
        final String file_path = Question3.class.getResource("sdxl_prop.txt").getFile();
        try {
            Files.asCharSource(new File(file_path), Charset.defaultCharset())
                    .readLines(new LineProcessor<String>() {
                        @Override
                        public boolean processLine(String s) throws IOException {
                            index_map.put(Integer.parseInt(s.split("\t")[0]),new Prop_Entity(row,s.split("\t")[1]));
                            row++;
                            return true;
                        }
                        @Override
                        public String getResult() {
                            return null;
                        }
                    });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //将模板文件的内容读入到内存中并按行处理
    private static void read_template() throws IOException {
        String path = Question3.class.getResource("").getFile() + "//sdxl.txt";
        File file = new File(path);
        if (file.exists())
            file.delete();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true)));
        final String file_path = Question3.class.getResource("sdxl_template.txt").getFile();
        try {
            Files.asCharSource(new File(file_path), Charset.defaultCharset())
                    .readLines(new LineProcessor<String>() {
                        @Override
                        public boolean processLine(String s) throws IOException {
                            //完成模板解析并且按照要求替换
                            s = TemplateReplace.parse_replace(s) + "\n";
                            out.write(s);
                            return true;
                        }
                        @Override
                        public String getResult() {
                            return null;
                        }
                    });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        out.close();

    }

    //按照题目要求进行nature排序  按row排序
    private static void nature_sort(){
        nature_list = new ArrayList<Map.Entry<Integer, Prop_Entity>>(index_map.entrySet());
        nature_list.sort(new Comparator<Map.Entry<Integer, Prop_Entity>>() {
            @Override
            public int compare(Map.Entry<Integer, Prop_Entity> o1, Map.Entry<Integer, Prop_Entity> o2) {
                return o1.getValue().row - o2.getValue().row;
            }
        });
    }
    //按照题目要求对进行index排序  按index排序
    private static void index_sort(){
        index_list = new ArrayList<Map.Entry<Integer, Prop_Entity>>(index_map.entrySet());
        index_list.sort(new Comparator<Map.Entry<Integer, Prop_Entity>>() {
            @Override
            public int compare(Map.Entry<Integer, Prop_Entity> o1, Map.Entry<Integer, Prop_Entity> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
    }
    //按照题目要求进行char排序   按字典序
    private static void char_sort(){
        char_list = new ArrayList<Map.Entry<Integer, Prop_Entity>>(index_map.entrySet());
        char_list.sort(new Comparator<Map.Entry<Integer, Prop_Entity>>() {
            @Override
            public int compare(Map.Entry<Integer, Prop_Entity> o1, Map.Entry<Integer, Prop_Entity> o2) {
                return o1.getValue().context.compareTo(o2.getValue().context);
            }
        });
    }
    //按照题目要求进行charDesc排序  按字典序逆序
    private static void charDesc_sort(){
        charDesc_list = new ArrayList<Map.Entry<Integer, Prop_Entity>>(index_map.entrySet());
        charDesc_list.sort(new Comparator<Map.Entry<Integer, Prop_Entity>>() {
            @Override
            public int compare(Map.Entry<Integer, Prop_Entity> o1, Map.Entry<Integer, Prop_Entity> o2) {
                return o2.getValue().context.compareTo(o1.getValue().context);
            }
        });
    }

}
