package gmb;

import java.util.LinkedList;
import java.util.List;

public class Customer {

    int id;
    List<String> latlng;
    LinkedList<String> ids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<String> latlng) {
        this.latlng = latlng;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(LinkedList<String> ids) {
        this.ids = ids;
    }

    /**
     * 
     * @return
     */

    public boolean notEmpty() {
        // TODO Auto-generated method stub
        return !this.ids.isEmpty();
    }

    public long pop() {
        if (!this.ids.isEmpty()) {
            return Long.valueOf(this.ids.pop());
        }
        
        return -1;
    }

    public void push(long prodid) {
        // TODO Auto-generated method stub
        this.ids.add("" + prodid);

    }

}
