package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.model.GenericModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="task_action")
public class TaskAction extends GenericModel {

    private String name;
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
    //endregion
}
