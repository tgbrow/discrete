package com.tgbrow.discrete;

public class DiscreteUnit {
    private int id;
    private int row;
    private int column;
    private char representation;
    private boolean isOn;

    public DiscreteUnit(int id, int row, int column, char representation, boolean isOn) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.representation = representation;
        this.isOn = isOn;
    }

    public DiscreteUnit(int id, int row, int column, char representation) {
        this(id, row, column, representation, false);
    }

    private DiscreteUnit(DiscreteUnit o) {
        this(o.id, o.row, o.column, o.representation, o.isOn);
    }

    public int getId() { return id; }
    public int getRow() { return row; }
    public int getColumn() { return column; }

    public char getRepresentation() { return representation; }

    public boolean isOn() { return isOn; }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public static DiscreteUnit copyOf(DiscreteUnit o) {
        return new DiscreteUnit(o);
    }
}
