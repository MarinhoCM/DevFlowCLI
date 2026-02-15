package infra.database.sqlite.models;

public class Task {

    public Integer id;
    public String title;
    public String description;
    public Integer done;
    public String created_at;
    public String updated_at;

    public Task(
            Integer id,
            String title,
            String description,
            Integer done,
            String created_at,
            String updated_at
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

}
