package com.example.dilkursu.repository;

import java.util.ArrayList;

public class TextProcessor {

    public static ArrayList<String> stringToArray(String string) {
        ArrayList<String> items = new ArrayList<>();
        string = string.substring(string.indexOf("{") + 1);
        string = string.substring(0, string.indexOf("}"));

        String[] temp = string.split(",");
        for (String s : temp) {
            s = s.substring(s.indexOf('"') + 1);
            s = s.substring(0, s.indexOf('"'));
            items.add(s);
        }

        return items;
    }

}
