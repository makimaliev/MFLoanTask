package kg.gov.mf.loan.task.model;

import java.util.Map;

public class ObjectType {

    private static Long id = 0L;
    private String object;
    private Map<String, Object> fields;

    //region GET-SET

    public static Long getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
        id++;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
    //endregion
}
