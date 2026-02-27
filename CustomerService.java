package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerService {

    public static int addCustomer(String name, String phone) {
        int customerId = 0;

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "INSERT INTO customers(name, phone) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                customerId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerId;
    }
}


