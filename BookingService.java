package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookingService {

    public static void bookRoom(Scanner sc) {

        try {
            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Phone: ");
            String phone = sc.next();

            System.out.print("Enter Room ID: ");
            int roomId = sc.nextInt();

            int customerId =
                    CustomerService.addCustomer(name, phone);

            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 =
                    con.prepareStatement(
                            "INSERT INTO bookings(room_id, customer_id, check_in, check_out) " +
                                    "VALUES (?, ?, CURDATE(), CURDATE()+1)");

            ps1.setInt(1, roomId);
            ps1.setInt(2, customerId);
            ps1.executeUpdate();

            PreparedStatement ps2 =
                    con.prepareStatement(
                            "UPDATE rooms SET status='Booked' WHERE room_id=?");

            ps2.setInt(1, roomId);
            ps2.executeUpdate();

            System.out.println("Room Booked Successfully ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelBooking(Scanner sc) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Booking ID: ");
            int bookingId = sc.nextInt();

            PreparedStatement ps =
                    con.prepareStatement(
                            "DELETE FROM bookings WHERE booking_id=?");

            ps.setInt(1, bookingId);
            ps.executeUpdate();

            System.out.println("Booking Cancelled ❌");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewBookings() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM bookings");

            System.out.println("\nBookingID | RoomID | CustomerID | CheckIn | CheckOut");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("booking_id") + " | " +
                        rs.getInt("room_id") + " | " +
                        rs.getInt("customer_id") + " | " +
                        rs.getDate("check_in") + " | " +
                        rs.getDate("check_out"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
