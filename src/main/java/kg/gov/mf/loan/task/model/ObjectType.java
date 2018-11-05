package kg.gov.mf.loan.task.model;

public class ObjectType {

    private String object;
    private String className;
    //private Map<String, Object> fields;

    //region GET-SET
    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    /*
    public Map<String, Object> getFields() {
        return fields;
    }
    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
    */
    //endregion
}
