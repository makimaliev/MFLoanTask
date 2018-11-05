package kg.gov.mf.loan.task.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name="cat_task_template")
public class TaskTemplate implements Serializable {

    private static final long serialVersionUID = -3307436748176180347L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String object;

    @ElementCollection
    private Map<String, String> properties;

    private String taskType;

    //region GET-SET
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    //endregion
}
