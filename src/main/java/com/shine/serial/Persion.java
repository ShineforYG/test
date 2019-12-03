package com.shine.serial;

import java.io.Serializable;

public class Persion implements Serializable {
    //    private static final long serialVersionUID = 4359709211352400087L;
    public Long id;
    public String name;

    public Persion(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id.toString() + "--" + name.toString();
    }
}
