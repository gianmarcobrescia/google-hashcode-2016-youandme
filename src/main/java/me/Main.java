package me;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import gmb.Customer;
import gmb.Warehouse;

public class Main {

    public static Map<Long, Long> PESI = new HashMap<Long, Long>();

    public static void main(String[] args) throws Exception {

        FileReader input = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/pesi.json");
        BufferedReader bufRead = new BufferedReader(input);
        String json = "";
        String myLine = null;

        while ((myLine = bufRead.readLine()) != null) {
            json += myLine;
        }

        JsonParser parser = new JsonParser();
        JsonObject array = parser.parse(json).getAsJsonObject();

        Set<Entry<String, JsonElement>> entrySet = array.entrySet();
        for (Entry<String, JsonElement> entry : entrySet) {
            PESI.put(Long.valueOf(entry.getKey()), entry.getValue().getAsLong());
        }
        
        FileReader input1 = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/w.json");
        bufRead = new BufferedReader(input1);
        json = "";
        myLine = null;

        while ((myLine = bufRead.readLine()) != null) {
            json += myLine;
        }

        parser = new JsonParser();
        JsonArray ar = parser.parse(json).getAsJsonArray();

        List<Warehouse> warehouses = new ArrayList<Warehouse>();
        for (final JsonElement j : ar) {
            Warehouse entity = new Gson().fromJson(j.getAsJsonObject(), Warehouse.class);
            warehouses.add(entity);
        };


        Orders orders = new Orders();

        DistanceMap distanceMap = new DistanceMap(warehouses, orders.getAll());

        Long dronetotal = 30l;

        for (Warehouse w : warehouses) {
            int widex = warehouses.indexOf(w);

            long dww = 100000000000000l;
            if (widex < warehouses.size() - 2) {
                dww = distance(w.getLatlng(), warehouses.get(widex + 1).getLatlng());
            }

            List<gmb.Distance> allDistances = distanceMap.get(w.getId());
            int droneid = 0;
            for (gmb.Distance distance : allDistances) {
                if (distance.getDistance() < dww) {

                    Customer ordine = orders.get(distance.getOrdineId());

                    if (w.soddisfabile(ordine)) {
                        
                        while (dronetotal > 0 && ordine.notEmpty()) {
                            dronetotal--;

                            Drone drone = new Drone(droneid);

                            boolean pieno = false;
                            while (!pieno) {

                                long prodid = ordine.pop();
                                
                                if (!drone.load(prodid)) {
                                    if (prodid != -1 ) {
                                        ordine.push(prodid);
                                    }
                                    pieno = true;
                                } else {
                                    w.remove(prodid);
                                }
                            }
                            
                            drone.deliver(w.getId(), distance.getOrdineId());

                            droneid++;
                        }
                    }
                }
            }
        }
    }

    public static long distance(List<String> from, List<String> to) {
        
        double fromLat = Double.valueOf(from.get(0));
        double fromLng = Double.valueOf(from.get(1));
        double toLat = Double.valueOf(to.get(0));
        double toLng = Double.valueOf(to.get(1));

        double distanza = Math.sqrt(Math.pow(Math.abs(fromLat - fromLng), 2) + Math.pow(Math.abs(toLat - toLng), 2));
        int rounded = (int) Math.round(distanza);
        
        return rounded;

    }

}
