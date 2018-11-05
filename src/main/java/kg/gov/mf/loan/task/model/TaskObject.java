package kg.gov.mf.loan.task.model;

import java.util.ArrayList;
import java.util.List;

public class TaskObject {

    private String table;
    private List<ObjectData> properties = new ArrayList<>();

    //region GET-SET
    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }

    public List<ObjectData> getProperties() {
        return properties;
    }
    public void setProperties(List<ObjectData> properties) {
        this.properties = properties;
    }
    //endregion
}
