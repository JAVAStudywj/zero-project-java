package com.zero.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 获取汉字 拼音字符
 * **
 * @author  DILGUO
 * @date    2020-02-19
 */
public class PinyinUtils {

    /**
     * 获取字符串拼音的第一个字母
     * @param chinese
     * @return
     */
    public static String toFirstChar(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
                    Boolean bl10 = String.valueOf(newChar[i]).matches(regex);
                    pinyinStr += !bl10?newChar[i]:PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 汉字转为拼音
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
                    Boolean bl10 = String.valueOf(newChar[i]).matches(regex);
                    pinyinStr += !bl10?newChar[i]:PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
//
//    /**
//     * 测试main方法
//     * @param args
//     */
//    public static void main(String[] args) {
//        System.out.println(toFirstChar("汉S字转换为拼音qw12").toLowerCase()); //转为首字母大写
//        System.out.println(toPinyin("汉字dT转换为拼音123"));
//    }
}
