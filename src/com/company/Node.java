package com.company;

public class Node {

    //  Red -> 0        Black -> 1
    int color;
    Node left,right,parent;
    String content;

    public Node(String value) { this(value,null,null); }

    public Node ( String value , Node l , Node r )
    {
        content=value;
        left=l;
        right=r;
        color=1;
    }
}
