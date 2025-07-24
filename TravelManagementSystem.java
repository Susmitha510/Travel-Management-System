package travelmanagementsystem;

import java.util.*;

public class TravelManagementSystem {
    private static List<Admin> admins = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<Trip> trips = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();

        while (true) {
            System.out.println("\n=== Travel Management System ===");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    handleAdminMenu();
                    break;
                case 2:
                    handleUserMenu();
                    break;
                case 3:
                    saveData();
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        if (admins.isEmpty()) {
            System.out.println("No admin registered. Register an admin.");
            registerAdmin();
        } else {
            loginAdmin();
        }
    }

    private static void registerAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        admins.add(new Admin(username, password));
        saveData();
        System.out.println("Admin registered successfully!");
    }

    private static void loginAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Login successful!");
                manageTrips(admin);
                return;
            }
        }
        System.out.println("Invalid admin credentials.");
    }

    private static void manageTrips(Admin admin) {
        while (true) {
            System.out.println("\n=== Manage Trips ===");
            System.out.println("1. Add Trip");
            System.out.println("2. View Trips");
            System.out.println("3. View Bookings");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addTrip();
                    break;
                case 2:
                    viewTrips();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addTrip() {
        System.out.print("Enter trip code (integer): ");
        int code = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter price: ₹");
        double price = Double.parseDouble(scanner.nextLine());
        trips.add(new Trip(code, destination, price));
        System.out.println("Trip added successfully!");
    }

    private static void viewTrips() {
        System.out.println("\n=== Available Trips ===");
        System.out.println("Code       | Destination                          | Price");
        System.out.println("--------------------------------------------------------------");
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    private static void viewBookings() {
        System.out.println("\n=== Bookings ===");
        System.out.println("Username       | Trip Code | Destination           | Total Price  | Status");
        System.out.println("---------------------------------------------------------------------------");
        for (Booking booking : bookings) {
            System.out.printf("%-15s | %-9d | %-20s | ₹%-11.2f | %-7s\n",
                    booking.getUsername(),
                    booking.getTrip().getCode(),
                    booking.getTrip().getDestination(),
                    booking.getTotalPrice(),
                    booking.isPaid() ? "Paid" : "Pending");
        }
    }

    private static void handleUserMenu() {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        saveData();
        System.out.println("User registered successfully!");
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                manageUserTrips(user);
                return;
            }
        }
        System.out.println("Invalid user credentials.");
    }

    private static void manageUserTrips(User user) {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Trips");
            System.out.println("2. Book Trip");
            System.out.println("3. View Bookings");
            System.out.println("4. Make Payment");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewTrips();
                    break;
                case 2:
                    bookTrip(user);
                    break;
                case 3:
                    viewUserBookings(user);
                    break;
                case 4:
                    makePayment(user);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void bookTrip(User user) {
        viewTrips();
        System.out.print("Enter trip code to book: ");
        int code = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of days: ");
        int days = Integer.parseInt(scanner.nextLine());

        for (Trip trip : trips) {
            if (trip.getCode() == code) {
                Booking booking = new Booking(user.getUsername(), trip, days);
                bookings.add(booking);
                System.out.println("Trip booked successfully!");
                System.out.println("Total price: ₹" + booking.getTotalPrice());
                return;
            }
        }
        System.out.println("Trip not found. Booking failed.");
    }

    private static void viewUserBookings(User user) {
        System.out.println("\n=== Your Bookings ===");
        for (Booking booking : bookings) {
            if (booking.getUsername().equals(user.getUsername())) {
                System.out.println(booking);
            }
        }
    }

    private static void makePayment(User user) {
        System.out.println("\n=== Pending Payments ===");
        for (Booking booking : bookings) {
            if (booking.getUsername().equals(user.getUsername()) && !booking.isPaid()) {
                System.out.println(booking);
            }
        }

        System.out.print("Enter trip code to pay: ");
        int code = Integer.parseInt(scanner.nextLine());

        for (Booking booking : bookings) {
            if (booking.getUsername().equals(user.getUsername()) && booking.getTrip().getCode() == code && !booking.isPaid()) {
                booking.markPaid();
                System.out.println("Payment successful for trip code " + code);
                return;
            }
        }
        System.out.println("Invalid trip code or already paid.");
    }

    private static void loadData() {
        FileHandler.ensureFileExists(FileHandler.ADMIN_FILE, "Username,Password");
        FileHandler.ensureFileExists(FileHandler.USER_FILE, "Username,Password");
        FileHandler.ensureFileExists(FileHandler.TRIP_FILE, "Code,Destination,Price");
        FileHandler.ensureFileExists(FileHandler.BOOKING_FILE, "Username,TripCode,Days,TotalPrice,IsPaid");

        List<String[]> adminData = FileHandler.readFile(FileHandler.ADMIN_FILE);
        for (String[] row : adminData) {
            admins.add(new Admin(row[0], row[1]));
        }

        List<String[]> userData = FileHandler.readFile(FileHandler.USER_FILE);
        for (String[] row : userData) {
            users.add(new User(row[0], row[1]));
        }

        List<String[]> tripData = FileHandler.readFile(FileHandler.TRIP_FILE);
        for (String[] row : tripData) {
            trips.add(new Trip(Integer.parseInt(row[0]), row[1], Double.parseDouble(row[2])));
        }

        List<String[]> bookingData = FileHandler.readFile(FileHandler.BOOKING_FILE);
        for (String[] row : bookingData) {
            for (Trip trip : trips) {
                if (trip.getCode() == Integer.parseInt(row[1])) {
                    Booking booking = new Booking(row[0], trip, Integer.parseInt(row[2]));
                    if (Boolean.parseBoolean(row[4])) {
                        booking.markPaid();
                    }
                    bookings.add(booking);
                }
            }
        }
    }

    private static void saveData() {
        List<String[]> adminData = new ArrayList<>();
        for (Admin admin : admins) {
            adminData.add(new String[]{admin.getUsername(), admin.getPassword()});
        }
        FileHandler.writeFile(FileHandler.ADMIN_FILE, adminData, "Username,Password");

        List<String[]> userData = new ArrayList<>();
        for (User user : users) {
            userData.add(new String[]{user.getUsername(), user.getPassword()});
        }
        FileHandler.writeFile(FileHandler.USER_FILE, userData, "Username,Password");

        List<String[]> tripData = new ArrayList<>();
        for (Trip trip : trips) {
            tripData.add(new String[]{String.valueOf(trip.getCode()), trip.getDestination(), String.valueOf(trip.getPrice())});
        }
        FileHandler.writeFile(FileHandler.TRIP_FILE, tripData, "Code,Destination,Price");

        List<String[]> bookingData = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingData.add(new String[]{booking.getUsername(), String.valueOf(booking.getTrip().getCode()),
                    String.valueOf(booking.getDays()), String.valueOf(booking.getTotalPrice()), String.valueOf(booking.isPaid())});
        }
        FileHandler.writeFile(FileHandler.BOOKING_FILE, bookingData, "Username,TripCode,Days,TotalPrice,IsPaid");
    }
}
