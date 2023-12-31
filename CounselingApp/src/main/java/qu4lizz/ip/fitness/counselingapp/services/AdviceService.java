package qu4lizz.ip.fitness.counselingapp.services;

import qu4lizz.ip.fitness.counselingapp.dao.AdviceDAO;
import qu4lizz.ip.fitness.counselingapp.models.Advice;

import java.util.List;

public class AdviceService {
    private final static AdviceDAO dao = new AdviceDAO();

    public List<Advice> findAllUnread() {
        return dao.findAllUnreadWithUsers();
    }

    public Advice findById(int id) {
        return dao.findById(id);
    }

    public void markAsRead(Advice advice) {
        dao.markAsRead(advice);
    }
}
