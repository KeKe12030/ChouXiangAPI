package com.nullatom.cxapi.apis;

import com.google.gson.Gson;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * 抽象话API
 * @author VioletTec
 * */
public class CxAPI {
    public static String pinyinCikuPath = "";

    private static Gson gson = new Gson();

    private static Properties pro = new Properties();
    private static InputStream is = null;


    //自动分词接口地址
    private static final String PULL_WORD_URL = "http://api.pullword.com/get.php?source={1}&param1=0&param2=1&json=1";

    static{
        try {
            refreshCiku();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把正常人的话变成Emoji
     *
     * @param msg 需要转换的字符串
     * */
    public static String toCxEmoji(String msg){

//    msg = encode(msg,"utf-8");//以UTF-8形式编码
//        /*http://api.pullword.com/get.php?source=&param1=1&param2=0&json=1*/
//        String html = "";
//        System.out.println(PULL_WORD_URL.replace("{1}",msg));
//        try {
//            URL url = new URL(PULL_WORD_URL.replace("{1}",msg));
//            URLConnection con = url.openConnection();
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            html = br.readLine();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Document doc = Jsoup.parse(html);;
//        String json = doc.text();
//
//        json = "{\"pullWords\":"+json+"}";
//
//        System.out.println(json);
//
//
//        PullWordJson pjw = gson.fromJson(json,PullWordJson.class);
//
//
//        LinkedList<String> words = pjw.getWords();//所有处理好的分词后的数据
//

        char[] msgs = msg.trim().toCharArray();
        LinkedList<String> pinyins = getPinyins(msgs);

        StringBuilder result = new StringBuilder();
        int i =0;
        for(String pinyin : pinyins) {
            Object emoji = getEmojiByPinyin(pinyin);
            if(emoji==null){//如果在词库中没找到emoji
                result.append(msgs[i]);//把对应的原文放入结果
            }else{//如果在词库中找到了emoji
                result.append(emoji);
            }
            i++;
        }

        /*msgs 和 pinyins 的下标对应的字符是一样的*/

        System.out.println("========================");
        System.out.println("输入字符："+msg+"\n转换成功，内容为：\n"+"输出字符"+result);
        return result.toString();
//        return "宁🐎的这是人👄🐎？👴翻译⑧来！";
    }

    /**
     * 把通过GET方式传输的数据字符串进行浏览器编码
     *
     * @param value 需要编码的字符串
     * @param enc 需要编码的编码方式
     * @return 转换好的文字
     */
    private static String encode(String value,String enc) {
        try {
            return java.net.URLEncoder.encode(value, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过传入的char数组，转换为拼音List
     * @param msgs 字符串toCharArray得到的数组
     * @return 转换好的拼音
     * */
    private static LinkedList<String> getPinyins(char[] msgs){
        LinkedList<String> pinyins = new LinkedList<String>();

        HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();

        //设置拼音小写
        pinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        //不设置音调
        pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        //设置输出格式有U和COLON
        pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);


        try {
            for (int i = 0; i < msgs.length; i++) {
                //判断是否是一个汉子字符
                if (java.lang.Character.toString(msgs[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    //如果是汉字
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(msgs[i], pinyinFormat);
                    //转换为拼音
                    pinyins.add(temp[0]);
                } else {
                    // 如果不是汉字字符，直接拼接
                    pinyins.add(java.lang.Character.toString(msgs[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination  e) {
            e.printStackTrace();
        }

        return pinyins;


    }

    /**
     * 通过拼音从词库中匹配emoji
     *
     * @param pinyin 需要到词库中去匹配的拼音
     * @return 匹配到的emoji
     * */
    public static Object getEmojiByPinyin(String pinyin){
        return  pro.getProperty(pinyin);
    }
    /**
     * 刷新词库
     * */
    public static void refreshCiku(){
        try {
            if(is!=null) {//如果不是空，则关闭
                is.close();
            }
            is = new FileInputStream(pinyinCikuPath);//拼音词库
            pro.load(new BufferedReader(new InputStreamReader(is)));//使用Reader为了避免emoji读取错误
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}