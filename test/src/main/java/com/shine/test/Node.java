package com.shine.test;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Integer> list;
    public NodeRole role;
    public Node(){
        list = new ArrayList<>();
        role = new NodeRole(this);
    }
}
