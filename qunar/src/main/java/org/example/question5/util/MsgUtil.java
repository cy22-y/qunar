package org.example.question5.util;

public class MsgUtil {

    public static boolean isURL(String str){
        //转换为小写
        str = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return  str.matches(regex);
    }


    //返回返回内容中，字符个数，标点符号个数，中文个数，
    public static String parseText(String text){
        long total = 0;
        long chinese = 0;
        long english = 0;
        long other = 0;//标点符号

        for (int i = 0; i < text.length(); i++) {
            if (isChinese(text.charAt(i))) {
                chinese++;
            } else if ((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
                    || text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                english++;
            }else if(!(text.charAt(i) >= '0' && text.charAt(i) <= '9') && text.charAt(i) != ' '){
                other++;
            }
        }
        total = chinese + english + other;
        return "总字符数：" + total + ",汉字数：" + chinese + ",英文数：" + english + ",标点符号数：" + other + " 备注:不包括数字和空格";
    }


    private static boolean isChinese(char ch) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

}
