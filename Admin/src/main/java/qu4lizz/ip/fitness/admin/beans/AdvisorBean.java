package qu4lizz.ip.fitness.admin.beans;

import qu4lizz.ip.fitness.admin.dao.AdvisorDAO;
import qu4lizz.ip.fitness.admin.models.AdvisorEntity;

import java.io.Serializable;
import java.util.List;

public class AdvisorBean implements Serializable {
    private AdvisorEntity user;
    private AdvisorDAO dao = new AdvisorDAO();

    public AdvisorEntity getUser() {
        return user;
    }

    public void setUser(AdvisorEntity user) {
        this.user = user;
    }

    public List<AdvisorEntity> findAll() {
        return dao.findAll();
    }

    public AdvisorEntity findById(String username) {
        return dao.findById(username);
    }

    public void create(AdvisorEntity user) {
        dao.createUser(user);
    }

    public void delete(String username) {
        dao.deleteUser(username);
    }

    public void update(AdvisorEntity user) {
        dao.updateUser(user);
    }
}
