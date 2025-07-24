package travelmanagementsystem;

public class Booking {
    private String username;
    private Trip trip;
    private int days;
    private double totalPrice;
    private boolean isPaid;

    public Booking(String username, Trip trip, int days) {
        this.username = username;
        this.trip = trip;
        this.days = days;
        this.totalPrice = trip.getPrice() * days;
        this.isPaid = false;
    }

    public String getUsername() {
        return username;
    }

    public Trip getTrip() {
        return trip;
    }

    public int getDays() {
        return days;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markPaid() {
        isPaid = true;
    }

    @Override
    public String toString() {
        return trip.getCode() + " | " + trip.getDestination() + " | ₹" + totalPrice + " | " + (isPaid ? "Paid" : "Pending");
    }
}
