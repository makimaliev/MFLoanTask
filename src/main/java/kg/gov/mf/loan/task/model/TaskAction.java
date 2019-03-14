package kg.gov.mf.loan.task.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="task_action")
public class TaskAction extends GenericModel {

    private String name;
    private String description;
    private String url;

    //region GET-SET
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//endregion
}
