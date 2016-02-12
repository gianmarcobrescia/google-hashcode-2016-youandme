package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Id_Weigth {
    
    public static void main(String[] args) throws Exception {
        FileReader input = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/Input_data_set/busy_day(C-I).in");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        
        int indexP = 1;

        while ((myLine = bufRead.readLine()) != null) {
            String[] split = myLine.split(" ");
            for (String pWeigth : split) {
                linkedHashMap.put(indexP++, pWeigth);
            }

        }
        
        System.out.println(new Gson().toJson(linkedHashMap));
    }

}
