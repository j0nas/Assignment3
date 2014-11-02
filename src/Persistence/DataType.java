package Persistence;

public enum DataType {
    INTEGER("INTEGER"), VARCHAR("VARCHAR");
    private String value;

    private DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
