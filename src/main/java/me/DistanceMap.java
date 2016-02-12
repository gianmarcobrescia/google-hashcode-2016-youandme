package me;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import gmb.Customer;
import gmb.Distance;
import gmb.Warehouse;

public class DistanceMap {
    
    private LinkedHashMap<Integer, List<Distance>> distanceMap;

    public DistanceMap(List<Warehouse> listWarehouse, List<Customer> listCustomer) {
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
        
        this.distanceMap = distanceMap;
    }

	public List<Distance> get(Integer warehouseid) {
		return distanceMap.get(warehouseid);
	}
}
