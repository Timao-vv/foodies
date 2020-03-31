package com.timao.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String s = "asdqweasd";
        String[] split = s.split("");
        List<String> list = new ArrayList<>();
        list.add(split[0]);
        for(int i=1; i<split.length; i++){
            if(!list.contains(split[i])) {
                list.add(split[i]);
            }
        }
        System.out.println(list);
    }

}
