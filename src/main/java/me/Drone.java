package me;

import java.util.HashMap;
import java.util.Map;

public class Drone {

	Map<Long, Integer> prodotti = new HashMap<Long, Integer>();

	long peso = 0;

	long pesomax = 100;

	private int droneid;

	public Drone(int droneid) {
		// TODO Auto-generated constructor stub
		this.droneid = droneid;
	}

	public boolean load(Long prodid) {
	    
	    if (prodid == -1) {
            return false;
        }

		long pesoprod = Main.PESI.get(prodid);

		if ((pesomax - peso) >= pesoprod) {

			this.peso += pesoprod;

			if (prodotti.containsKey(prodid)) {
				prodotti.put(prodid, prodotti.get(prodid) + 1);
			} else {
				prodotti.put(prodid, 1);
			}

			return true;
		} else {
			return false;
		}

	}

	public void deliver(int warehouseid, int ordineid) {
		for (Long prodid : prodotti.keySet()) {
			// TODO STAMPA IL COMANDO DI LOAD
			String command = this.droneid + " L " + warehouseid + " " + prodid
					+ " " + prodotti.get(prodid);
			System.out.println(command);
		}

		for (Long prodid : prodotti.keySet()) {
			// TODO STAMPA IL COMANDO DI DELIVER
			String command = this.droneid + " D " + ordineid + " " + prodid
					+ " " + prodotti.get(prodid);
			System.out.println(command);
		}
	}
}
