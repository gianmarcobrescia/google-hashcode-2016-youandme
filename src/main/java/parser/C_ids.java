package parser;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class C_ids {

    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws Exception {
        FileReader input = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/Input_data_set/busy_day(C-I).in");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;

        JsonArray finalJson = new JsonArray();
        int indexOfCustomer = 0;
        int row = 1;

        JsonArray tempCoord = null;

        while ((myLine = bufRead.readLine()) != null) {

            if (row % 3 == 1) {
                tempCoord = new JsonArray();
                tempCoord.add(myLine.split(" ")[0]);
                tempCoord.add(myLine.split(" ")[1]);
            }

            if (row % 3 == 0) {
                String[] split = myLine.split(" ");

                JsonArray jsonArray = new JsonArray();
                for (String id : split) {
                    jsonArray.add(id);
                }

                JsonObject jsonObject = new JsonObject();
                jsonObject.add("id", GSON.toJsonTree(indexOfCustomer));
                jsonObject.add("latlng", tempCoord.getAsJsonArray());
                jsonObject.add("ids", jsonArray.getAsJsonArray());

                finalJson.add(jsonObject);
                indexOfCustomer++;
            }

            row++;

            // String[] array1 = myLine.split(":");
            // // check to make sure you have valid data
            // String[] array2 = array1[1].split(" ");
            // for (int i = 0; i < array2.length; i++)
        }

        System.out.println(finalJson);

    }

}
