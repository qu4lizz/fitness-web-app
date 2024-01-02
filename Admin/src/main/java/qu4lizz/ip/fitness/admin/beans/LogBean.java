package qu4lizz.ip.fitness.admin.beans;

import qu4lizz.ip.fitness.admin.dao.LogDAO;
import qu4lizz.ip.fitness.admin.models.LogEntity;

import java.io.Serializable;
import java.util.List;

public class LogBean implements Serializable {
    private final LogDAO dao = new LogDAO();
    public List<LogEntity> findAllAndSort() {
        return dao.findAllAndSort();
    }
}
