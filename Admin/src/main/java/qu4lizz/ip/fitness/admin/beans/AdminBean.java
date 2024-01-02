package qu4lizz.ip.fitness.admin.beans;

import qu4lizz.ip.fitness.admin.dao.AdminDAO;
import qu4lizz.ip.fitness.admin.models.AdminEntity;

import java.io.Serializable;
import java.util.List;

public class AdminBean implements Serializable {
    private final AdminDAO dao = new AdminDAO();
    private boolean isLogged;

    public AdminEntity checkLogin(String username, String password) {
        List<AdminEntity> accounts = dao.findAll();

        for (var acc : accounts)
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                isLogged = true;
                return acc;
            }

        return null;
    }

    public boolean isLogged() {
        return isLogged;
    }
}
