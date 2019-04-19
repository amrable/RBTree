package com.company;

public class Node {

//  Red -> 1        Black -> 0
    int color;
    Node left,right,parent;
    String content;

    public Node(String value)
    {
        this(value,null,null);
    }

    public Node ( String value , Node l , Node r )
    {
        content=value;
        left=l;
        right=r;
        color=1;
    }
}
