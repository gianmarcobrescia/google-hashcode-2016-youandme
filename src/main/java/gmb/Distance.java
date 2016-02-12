package gmb;

public class Distance {

    int customerId;
    int distance;

    public Distance() {
        // TODO Auto-generated constructor stub
    }

    public Distance(int customerId, int distance) {
        this.customerId = customerId;
        this.distance = distance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getOrdineId() {
        // TODO Auto-generated method stub
        return this.customerId;
    }

}
