package Persistence;

public class TableHeaderColumn {
    private String name;
    private DataType type;
    private int size;

    public TableHeaderColumn(final String name, final DataType type) {
        this(name, type, 255);
    }

    public TableHeaderColumn(final String name, final DataType type, final int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public DataType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
