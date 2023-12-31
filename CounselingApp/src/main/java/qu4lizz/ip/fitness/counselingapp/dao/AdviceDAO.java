package qu4lizz.ip.fitness.counselingapp.dao;

import qu4lizz.ip.fitness.counselingapp.models.Advice;
import qu4lizz.ip.fitness.counselingapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdviceDAO {
    public List<Advice> findAllUnreadWithUsers() {
        List<Advice> retVal = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            String query = "SELECT a.id, a.message, a.timestamp, a.is_read, a.id_user, "
                    + "u.name, u.surname, u.username, u.mail "
                    + "FROM advice a "
                    + "JOIN \"user\" u ON a.id_user = u.id "
                    + "WHERE a.is_read = false";

            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("mail")
                );

                Advice advice = new Advice(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getTimestamp("timestamp"),
                        rs.getBoolean("is_read"),
                        user
                );

                retVal.add(advice);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

    public Advice findById(int id) {
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            String query = "SELECT a.id, a.message, a.timestamp, a.is_read, a.id_user, "
                    + "u.name, u.surname, u.username, u.mail "
                    + "FROM advice a "
                    + "JOIN \"user\" u ON a.id_user = u.id "
                    + "WHERE a.id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("mail")
                );

                Advice advice = new Advice(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getTimestamp("timestamp"),
                        rs.getBoolean("is_read"),
                        user
                );

                return advice;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void markAsRead(Advice advice) {
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            String query = "UPDATE advice SET is_read = true WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, advice.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
