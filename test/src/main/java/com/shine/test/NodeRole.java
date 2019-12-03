package com.shine.test;

import java.util.ArrayList;
import java.util.List;

public class NodeRole {
    static List<Integer> list_2 = new ArrayList<>();

    static {
        list_2.add(123);
        list_2.add(234);
        list_2.add(345);
        list_2.add(456);
    }

    Node node;

    public NodeRole(Node node) {
        this.node = node;
    }

    public void add() {
        this.node.list = list_2;
    }
}
