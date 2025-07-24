package travelmanagementsystem;

public class Trip {
    private int code;
    private String destination;
    private double price;

    public Trip(int code, String destination, double price) {
        this.code = code;
        this.destination = destination;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-10d | %-30s | ₹%-10.2f", code, destination, price);
    }
}
