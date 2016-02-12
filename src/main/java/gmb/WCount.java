package gmb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sound.midi.Synthesizer;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.apache.poi.ss.formula.functions.T;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.noovle.gawl.util.GSONDefault;

public class WCount {

    private static final Gson GSON = GSONDefault.getDefault();

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 4));
    }

    public static void main1(String[] args) throws Exception {
        FileReader input = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/w.json");
        BufferedReader bufRead = new BufferedReader(input);
        String json = "";
        String myLine = null;

        while ((myLine = bufRead.readLine()) != null) {
            json += myLine;
        }

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();

        List<Warehouse> listWarehouse = new ArrayList<Warehouse>();
        for (final JsonElement j : array) {
            Warehouse entity = GSON.fromJson(j.getAsJsonObject(), Warehouse.class);
            listWarehouse.add(entity);
        };

        /**
         * 
         */

        FileReader input2 = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/C[ids].json");
        bufRead = new BufferedReader(input2);
        json = "";
        myLine = null;

        while ((myLine = bufRead.readLine()) != null) {
            json += myLine;
        }

        parser = new JsonParser();
        array = parser.parse(json).getAsJsonArray();

        List<Customer> listCustomer = new ArrayList<Customer>();
        for (final JsonElement j : array) {
            Customer entity = GSON.fromJson(j.getAsJsonObject(), Customer.class);
            listCustomer.add(entity);
        };

        /**
         * Qui ho listCustomer e listWarehouse
         * 
         */

        LinkedHashMap<Integer, List<Distance>> distanceMap = new LinkedHashMap<>();

        for (Warehouse w : listWarehouse) {
            double wLat = Double.valueOf(w.getLatlng().get(0));
            double wLng = Double.valueOf(w.getLatlng().get(1));
            
            ArrayList<Distance> list = new ArrayList<Distance>();

            for (Customer c : listCustomer) {
                double cLat = Double.valueOf(c.getLatlng().get(0));
                double cLng = Double.valueOf(c.getLatlng().get(1));

                double distanza = Math.sqrt(Math.pow(Math.abs(wLat - wLng), 2) + Math.pow(Math.abs(cLat - cLng), 2));
                int rounded = (int) Math.round(distanza);
                
                list.add(new Distance(c.getId(), rounded));
            }
            
            distanceMap.put(w.getId(), list);
        }

    }

}
