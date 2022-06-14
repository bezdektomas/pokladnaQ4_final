package model;

import java.io.Serializable;

public abstract class Item implements Serializable {
    protected int id;

    public Item() {
        super();
    }
    public Item(int id) {
        super();
        this.id = id;
        this.load();
    }

    public int getId() {
        return id;
    }

    public abstract boolean save();
    public abstract void load();
    public abstract void delete();
}
