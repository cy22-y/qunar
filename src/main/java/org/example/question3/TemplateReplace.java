package org.example.question3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateReplace {
    //定义替换类别
    public static final String NATURE_ORDER = "natureOrder";
    public static final String INDEX_ORDER = "indexOrder";
    public static final String CHAR_ORDER = "charOrder";
    public static final String CHAR_ORDER_DESC = "charOrderDESC";
    //定义标志位
    public static final char FUNCTION_MARKET = '$';
    public static final char PREFIX_MARKET = '(';
    public static final char SUFFIX_MARKET = ')';

    //定义正则
    public static final String nature_regex = "\\$natureOrder\\([0-9]+\\)";
    public static final String index_regex = "\\$indexOrder\\([0-9]+\\)";
    public static final String char_regex = "\\$charOrder\\([0-9]+\\)";
    public static final String charDesc_regex = "\\$charOrderDESC\\([0-9]+\\)";
    public static final String prefix_suffix_regex = "([0-9]+)";
    //非贪婪匹配
    public static final String normal_regex = "\\$.*?\\([0-9]+\\)";
    public static String parse_replace(String target){
        Pattern regex_pattern = Pattern.compile(normal_regex);
        Matcher regex_matcher = regex_pattern.matcher(target);
        while (regex_matcher.find()){
            //找到一个plate
            String plate  = regex_matcher.group();
            //寻找规则类型
            String type = getOrderType(plate,FUNCTION_MARKET,PREFIX_MARKET);
            //提取key序列
            int index = getIndex(plate);
            switch (type){
                case NATURE_ORDER:
                    target = target.replaceAll(nature_regex, Question3.nature_list.get(index).getValue().context);
                    break;
                case INDEX_ORDER:
                    target = target.replaceAll(index_regex, Question3.index_list.get(index).getValue().context);
                    break;
                case CHAR_ORDER:
                    target = target.replaceAll(char_regex, Question3.char_list.get(index).getValue().context);
                    break;
                case CHAR_ORDER_DESC:
                    target = target.replaceAll(charDesc_regex, Question3.charDesc_list.get(index).getValue().context);
                    break;
            }
        }
        return target;
    }
    public static int getIndex(String target){
        Pattern indexRegex = Pattern.compile(prefix_suffix_regex);
        Matcher indexMatcher = indexRegex.matcher(target);
        int res = -1;
        while(indexMatcher.find()){
            res = Integer.parseInt(indexMatcher.group());
        }
        return res;
    }

    public static String getOrderType(String context,char start,char end){
        return context.substring(context.indexOf(start)+1,context.indexOf(end));
    }

    public static void main(String[] args) {
    }

}
