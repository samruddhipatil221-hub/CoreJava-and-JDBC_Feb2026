package hotel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- HOTEL RESERVATION SYSTEM ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    RoomService.viewAvailableRooms();
                    break;
                case 2:
                    BookingService.bookRoom(sc);
                    break;
                case 3:
                    BookingService.cancelBooking(sc);
                    break;
                case 4:
                    BookingService.viewBookings();
                    break;
                case 0:
                    System.out.println("Thank You 🙏");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 0);
    }
}
