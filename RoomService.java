package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomService {

    public static void viewAvailableRooms() {
        String query = "SELECT room_id, room_type, price FROM rooms WHERE status = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, "Available");

            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("\nRoomID | Type | Price");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("room_id") + " | " +
                        rs.getString("room_type") + " | " +
                        rs.getDouble("price")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
