package gmb;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    Integer id;
    List<String> latlng;
    List<String> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<String> latlng) {
        this.latlng = latlng;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public boolean soddisfabile(Customer ordine) {

        ArrayList<String> list = new ArrayList<>();
        list.addAll(products);

        for (String id : ordine.getIds()) {
            int indexOf = list.indexOf(id);
            if (indexOf != -1) {

                String pippo = list.remove(indexOf);
                if (pippo != null) {
                    return false;
                }
            }
        }

        this.products = list;

        return true;
        //
        // for (String string : ) {
        // if (ordine.getIds().contains(string)) {
        // found++;
        // ordine.getIds().remove(ordine.getIds().indexOf(string));
        // products.remove(index)
        // }
        // }
        //
        // return found == ordine.getIds().size();

        // list.retainAll(ordine.getIds());
        // return ((products.size() - list.size()) == ordine.getIds().size());
    }

    public void remove(long prodid) {
        // TODO Auto-generated method stub
        this.products.remove("" + prodid);
    }

}
