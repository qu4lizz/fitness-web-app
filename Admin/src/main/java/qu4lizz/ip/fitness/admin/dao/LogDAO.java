package qu4lizz.ip.fitness.admin.dao;

import qu4lizz.ip.fitness.admin.models.AdminEntity;
import qu4lizz.ip.fitness.admin.models.LogEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    public List<LogEntity> findAllAndSort() {
        List<LogEntity> retVal = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM log ORDER BY datetime DESC");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
                retVal.add(new LogEntity(rs.getLong("id"),
                        rs.getString("text"),
                        rs.getTimestamp("datetime")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }
}
