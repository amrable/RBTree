package com.company;

public class RBT {

    private Node current;
    private Node parent;
    private Node grand;
    private Node great;
    private Node header;
    private static Node nullNode;

    /* static initializer for nullNode */
    static
    {
        nullNode = new Node(null);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }
    /* Black - 1  RED - 0 */
    static final int BLACK = 1;
    static final int RED   = 0;

    /* Constructor */
    public RBT(String negInf)
    {
        header = new Node(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }


    public void rotateLeft(Node target)
    {
        Node y = target.right;
        target.right=y.left;
        if(y.left!=nullNode)
        {
            y.left.parent=target;
        }
        y.parent=target.parent;
        if(target.parent==nullNode) this.header=y;
        else if (target.parent.left==target) target.parent.left=y;
        else target.parent.right=y;
        y.left=target;
        target.parent=y;
    }

    public void rotateRight(Node target)
    {
        Node y=target.left;
        target.left= y.right;
        if (y.right != nullNode)    y.right.parent=target;
        y.parent=target.parent;
        if(target.parent==nullNode) this.header=y;
        else if (target.parent.right==target) target.parent.right=y;
        else target.parent.left=y;
        y.right=target;
        target.parent=y;
    }

    public void insert(String data)
    {
        data=data.toLowerCase();                //All data should be lower case, [ compareTo ] method is case sensitive
        Node newNode=new Node(data);

        if (header == nullNode) header=newNode;
        else
        {
            Node temp=header,prev=nullNode;
            while (temp!=nullNode)
            {
                prev=temp;                                                           //the prev node holds the future parent of the new node
                if (newNode.content.compareTo(temp.content)>0) temp=temp.right;      //TEMP < NEW NODE
                else if (newNode.content.compareTo(temp.content)<0)temp=temp.left;   //NEW NODE < TEMP
                else System.out.println("Failed to insert - Duplicate\n");
            }
            newNode.parent=prev;
            // NEW NODE > PREV
            if (newNode.content.compareTo(prev.content)>0) prev.right=newNode;
            // PREV > NEW NODE
            else prev.left=newNode;
        }
        newNode.left=newNode.right=nullNode;
        newNode.color=RED;

        insertFix(newNode);
    }

    public void insertFix(Node n)
    {

    }
}
