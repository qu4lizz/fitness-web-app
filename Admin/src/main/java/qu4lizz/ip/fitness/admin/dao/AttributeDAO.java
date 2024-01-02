package qu4lizz.ip.fitness.admin.dao;

import qu4lizz.ip.fitness.admin.models.AttributeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeDAO {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM attribute WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO attribute (name, id_category) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM attribute WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE attribute SET name = ?, id_category = ? WHERE id = ?";

    public AttributeEntity findById(int id) {
        AttributeEntity attribute = null;
        try (Connection conn = DatabaseConnection.createDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                attribute = new AttributeEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("id_category")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attribute;
    }

    public void create(AttributeEntity attribute) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(CREATE_QUERY)) {
            stmt.setString(1, attribute.getName());
            stmt.setInt(2, attribute.getIdCategory());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(AttributeEntity attribute) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, attribute.getName());
            stmt.setInt(2, attribute.getIdCategory());
            stmt.setInt(3, attribute.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
