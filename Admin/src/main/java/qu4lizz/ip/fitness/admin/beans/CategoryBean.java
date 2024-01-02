package qu4lizz.ip.fitness.admin.beans;

import qu4lizz.ip.fitness.admin.dao.AttributeDAO;
import qu4lizz.ip.fitness.admin.dao.CategoryDAO;
import qu4lizz.ip.fitness.admin.models.AdvisorEntity;
import qu4lizz.ip.fitness.admin.models.AttributeEntity;
import qu4lizz.ip.fitness.admin.models.CategoryEntity;

import java.io.Serializable;
import java.util.List;

public class CategoryBean implements Serializable {
    private final CategoryDAO dao = new CategoryDAO();
    private final AttributeDAO attributeDAO = new AttributeDAO();
    private CategoryEntity category;
    private AttributeEntity attribute;

    public AttributeEntity getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeEntity attribute) {
        this.attribute = attribute;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public List<CategoryEntity> findAll() {
        return dao.findAll();
    }

    public CategoryEntity findById(int id) {
        return dao.findById(id);
    }

    public void create(CategoryEntity cat) {
        dao.createCategory(cat);
    }

    public void delete(int id) {
        dao.deleteCategory(id);
    }

    public void update(CategoryEntity category) {
        dao.updateCategory(category);
    }

    public AttributeEntity getAttributeById(int id) { return attributeDAO.findById(id); }

    public void createAttribute(AttributeEntity attribute) { attributeDAO.create(attribute); }

    public void deleteAttribute(int id) { attributeDAO.delete(id); }

    public void updateAttribute(AttributeEntity attribute) { attributeDAO.update(attribute); }
}
