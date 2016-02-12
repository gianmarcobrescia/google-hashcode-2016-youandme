package me;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import gmb.Customer;

public class Orders {
    
    private List<Customer> ordini;

    public Orders() throws Exception {

        FileReader input2 = new FileReader("/Users/gianmarcobrescia/Desktop/HashCode 2016/C[ids].json");
        BufferedReader bufRead = new BufferedReader(input2);
        String json = "";
        String myLine = null;

        while ((myLine = bufRead.readLine()) != null) {
            json += myLine;
        }

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();

        List<Customer> listCustomer = new ArrayList<Customer>();
        for (final JsonElement j : array) {
            Customer entity = new Gson().fromJson(j.getAsJsonObject(), Customer.class);
            listCustomer.add(entity);
        };
        
        this.ordini = listCustomer;
    }

	public Customer get(int ordineId) {
		return ordini.get(ordineId);
	}

    public List<Customer> getAll() {
        return this.ordini;
    }

}
