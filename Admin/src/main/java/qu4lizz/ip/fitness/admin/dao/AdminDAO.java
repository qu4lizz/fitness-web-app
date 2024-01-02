package qu4lizz.ip.fitness.admin.dao;

import qu4lizz.ip.fitness.admin.models.AdminEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public List<AdminEntity> findAll() {
        List<AdminEntity> retVal = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admin");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
                retVal.add(new AdminEntity(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }
}
