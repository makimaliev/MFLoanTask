package kg.gov.mf.loan.task.model;

public class ObjectData {

    private String property;
    private String operator;
    private String value;

    //region GET-SET
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    //endregion
}
