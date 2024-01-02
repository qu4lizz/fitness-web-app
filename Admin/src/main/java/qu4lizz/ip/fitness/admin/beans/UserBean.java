package qu4lizz.ip.fitness.admin.beans;

import qu4lizz.ip.fitness.admin.dao.UserDAO;
import qu4lizz.ip.fitness.admin.models.UserEntity;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {
    private UserEntity user;
    private final UserDAO dao = new UserDAO();

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<UserEntity> findAll() {
        return dao.findAll();
    }

    public UserEntity findById(int id) {
        return dao.findById(id);
    }

    public void create(UserEntity user) {
        dao.createUser(user);
    }

    public void delete(int id) {
        dao.deleteUser(id);
    }

    public void update(UserEntity user) {
        dao.updateUser(user);
    }

    public String getImageFormat(byte[] imageData) {
        if (imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8) {
            return "jpeg";
        } else if (imageData[0] == (byte) 0x89 && imageData[1] == (byte) 0x50 && imageData[2] == (byte) 0x4E && imageData[3] == (byte) 0x47) {
            return "png";
        }
        return "unknown";
    }
}
