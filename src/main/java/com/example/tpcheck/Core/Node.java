package com.example.tpcheck.Core;

import java.io.Serializable;

public class Node implements Serializable {
    private String name;
    private String value;

    public Node(String name, String value) {
        setName(name);
        setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}