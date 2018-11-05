package kg.gov.mf.loan.task.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskObject {

    private String table;
    private Map<String, List<ObjectData>> properties = new HashMap<>(0);

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Map<String, List<ObjectData>> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, List<ObjectData>> properties) {
        this.properties = properties;
    }
}
