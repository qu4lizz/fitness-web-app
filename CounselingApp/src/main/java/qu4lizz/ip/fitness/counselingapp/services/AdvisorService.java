package qu4lizz.ip.fitness.counselingapp.services;

import qu4lizz.ip.fitness.counselingapp.dao.AdvisorDAO;
import qu4lizz.ip.fitness.counselingapp.models.Advisor;

public class AdvisorService {
    private final static AdvisorDAO dao = new AdvisorDAO();

    public Advisor checkLogin(String username, String password) {
        var advisors = dao.findAll();

        for (var advisor : advisors)
            if (advisor.getUsername().equals(username) && advisor.getPassword().equals(password))
                return advisor;

        return null;
    }
}
