package com.nullatom.cxapi.javabean;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.TreeMap;


/**
 * PullWord接口的GSON序列化对象
 * @author VioletTec
 * */
public class PullWordJson {

    LinkedList<Word> pullWords;

    public class Word{public Word(String t){this.t = t;} String t;}

    public PullWordJson(){
        pullWords = new LinkedList<Word>();
    }

    public void putWord(String word){
        pullWords.add(new Word(word));
    }
    public LinkedList<String> getWords(){
        LinkedList<String> words = new LinkedList<String>();
        for(Word w : pullWords)  words.add(w.t);//把word对象数组变成String数组
        return words;
    }
}
