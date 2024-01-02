package qu4lizz.ip.fitness.admin.dao;

import qu4lizz.ip.fitness.admin.models.AttributeEntity;
import qu4lizz.ip.fitness.admin.models.CategoryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<CategoryEntity> findAll() {
        List<CategoryEntity> categories = new ArrayList<>();
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CategoryEntity category = new CategoryEntity(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public CategoryEntity findById(int id) {
        CategoryEntity category = null;
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmtCategory = conn.prepareStatement("SELECT * FROM category WHERE id = ?");
            stmtCategory.setInt(1, id);
            ResultSet rsCategory = stmtCategory.executeQuery();

            if (rsCategory.next()) {
                category = new CategoryEntity(
                        rsCategory.getInt("id"),
                        rsCategory.getString("name")
                );
            }

            PreparedStatement stmtAttributes = conn.prepareStatement("SELECT * FROM attribute WHERE id_category = ?");
            stmtAttributes.setInt(1, id);
            ResultSet rsAttributes = stmtAttributes.executeQuery();

            List<AttributeEntity> attributes = new ArrayList<>();
            while (rsAttributes.next()) {
                AttributeEntity attribute = new AttributeEntity(
                        rsAttributes.getInt("id"),
                        rsAttributes.getString("name"),
                        rsAttributes.getInt("id_category")
                );
                attributes.add(attribute);
            }

            if (category != null) {
                category.setAttributes(attributes);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }


    public void createCategory(CategoryEntity category) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO category (name) VALUES (?)");
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(int id) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM category WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(CategoryEntity category) {
        try (Connection conn = DatabaseConnection.createDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE category SET name = ? WHERE id = ?");
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
