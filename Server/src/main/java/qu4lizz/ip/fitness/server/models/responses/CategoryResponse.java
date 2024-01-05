package qu4lizz.ip.fitness.server.models.responses;

import qu4lizz.ip.fitness.server.models.entities.CategoryEntity;

public class CategoryResponse {
    private Integer id;
    private String name;

    public CategoryResponse() {}

    public CategoryResponse(CategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
