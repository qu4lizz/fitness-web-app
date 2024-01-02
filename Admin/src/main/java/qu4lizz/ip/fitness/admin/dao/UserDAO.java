package qu4lizz.ip.fitness.admin.dao;

import qu4lizz.ip.fitness.admin.models.AdminEntity;
import qu4lizz.ip.fitness.admin.models.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<UserEntity> findAll() {
        List<UserEntity> retVal = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM public.user WHERE active = true");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
                retVal.add(new UserEntity(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("mail"),
                        rs.getString("city"),
                        rs.getBytes("image"),
                        rs.getBoolean("verified")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

    public void createUser(UserEntity user) {
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            String query = "INSERT INTO public.user (name, surname, username, password, mail, city, image, verified) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getMail());
            stmt.setString(6, user.getCity());
            stmt.setBytes(7, user.getImage());
            stmt.setBoolean(8, user.getVerified());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(UserEntity user) {
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            String query = "UPDATE public.user SET name=?, surname=?, username=?, password=?, " +
                    "mail=?, city=?, image=?, verified=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getMail());
            stmt.setString(6, user.getCity());
            stmt.setBytes(7, user.getImage());
            stmt.setBoolean(8, user.getVerified());
            stmt.setInt(9, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int userId) {
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            String query = "UPDATE public.user SET active=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setBoolean(1, false);
            stmt.setInt(2, userId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity findById(int id) {
        UserEntity retVal = null;
        try {
            Connection conn = DatabaseConnection.createDataSource().getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM public.user WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
                retVal = new UserEntity(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("mail"),
                        rs.getString("city"),
                        rs.getBytes("image"),
                        rs.getBoolean("verified"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }
}
