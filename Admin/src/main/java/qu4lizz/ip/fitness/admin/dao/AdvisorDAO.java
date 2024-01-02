package qu4lizz.ip.fitness.admin.dao;

import qu4lizz.ip.fitness.admin.models.AdvisorEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvisorDAO {
    public List<AdvisorEntity> findAll() {
        List<AdvisorEntity> advisors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM advisor");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AdvisorEntity advisor = new AdvisorEntity(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                advisors.add(advisor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advisors;
    }

    public AdvisorEntity findById(String username) {
        AdvisorEntity advisor = null;
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM advisor WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                advisor = new AdvisorEntity(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advisor;
    }

    public void createUser(AdvisorEntity advisor) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO advisor (name, surname, username, password) VALUES (?, ?, ?, ?)");
            stmt.setString(1, advisor.getName());
            stmt.setString(2, advisor.getSurname());
            stmt.setString(3, advisor.getUsername());
            stmt.setString(4, advisor.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String username) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM advisor WHERE username = ?");
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(AdvisorEntity advisor) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE advisor SET name = ?, surname = ?, password = ? WHERE username = ?");
            stmt.setString(1, advisor.getName());
            stmt.setString(2, advisor.getSurname());
            stmt.setString(3, advisor.getPassword());
            stmt.setString(4, advisor.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
