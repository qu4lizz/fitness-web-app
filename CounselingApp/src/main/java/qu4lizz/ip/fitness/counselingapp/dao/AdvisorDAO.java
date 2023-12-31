package qu4lizz.ip.fitness.counselingapp.dao;

import qu4lizz.ip.fitness.counselingapp.models.Advisor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvisorDAO {
    public List<Advisor> findAll() {
        List<Advisor> retVal = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM advisor");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
                retVal.add(new Advisor(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }
}
