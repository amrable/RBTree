package com.company;

public class RBT {

    private Node header;
    private static Node nullNode;

    private int size;
    /* Black -> 1  RED -> 0 */
    static final int BLACK = 1;
    static final int RED   = 0;

    /* static initializer for nullNode */
    static
    {
        nullNode = new Node("AA");
        nullNode.left = nullNode;
        nullNode.right = nullNode;
        nullNode.color=BLACK;
    }

    /* Constructor */
    public RBT()
    {
        size=0;
        header = nullNode;
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
        else if (target.parent.left==target)
            target.parent.left=y;
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
        this.size++;
        data=data.toLowerCase();                //All data should be lower case, [ compareTo ] method is case sensitive
        Node newNode=new Node(data);

        if (header == nullNode)
        {
            header=newNode;
            header.parent=nullNode;
        }
        else
        {
            Node temp=header,prev=nullNode;
            while (temp!=nullNode)
            {
                prev=temp;                                                           //the prev node holds the future parent of the new node
                if (newNode.content.compareTo(temp.content)>0) temp=temp.right;      //TEMP < NEW NODE
                else if (newNode.content.compareTo(temp.content)<0)temp=temp.left;   //NEW NODE < TEMP
                else
                {
                    this.size--;
                    System.out.println("Failed to insert - Duplicate\n");
                    break;
                }
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

    private void insertFix(Node n)
    {
        Node uncle=nullNode;
        while (n.parent.color==RED)
        {
            if (n.parent==n.parent.parent.left)
            {
                uncle=n.parent.parent.right;
                if (uncle.color==RED)           // start of case 1
                {
                    n.parent.color=BLACK;
                    n.parent.parent.color=RED;
                    uncle.color=BLACK;          // end of case 1
                    n=n.parent.parent;          // pass the problem to the grandfather
                }
                //if the uncle is black
                else  // if the new node is right child
                {
                    if (n==n.parent.right) {    //start of case 2
                        n = n.parent;
                        rotateLeft(n);
                    }

                    n.parent.color=BLACK;       // start of case 3
                    n.parent.parent.color=RED;
                    rotateRight(n.parent.parent);
                }


            }
            else
            {
                uncle=n.parent.parent.left;
                if (uncle.color==RED)           // start of case 1
                {
                    n.parent.color=BLACK;
                    n.parent.parent.color=RED;
                    uncle.color=BLACK;          // end of case 1
                    n=n.parent.parent;          // pass the problem to the grandfather
                }
                //if the uncle is black
                else  // if the new node is right child
                {
                    if (n==n.parent.left) {    //start of case 2
                        n = n.parent;
                        rotateRight(n);
                    }

                    n.parent.color=BLACK;       // start of case 3
                    n.parent.parent.color=RED;
                    rotateLeft(n.parent.parent);
                }


            }
        }
        header.color=BLACK;

    }
    /* Function for inorder traversal */

    public void inorder(Node r)
    {
        if (r != nullNode)
        {
            inorder(r.left);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.content +" "+c+" ");
            inorder(r.right);
        }
    }

    public int getSize() {
        return size;
    }
    public Node getRoot()
    {
        return header;
    }

    public void find(String target)
    {
        Node n = header;
        int count=0;
        while (n != nullNode )
        {
            if (n.content.compareTo(target)<0)          //go right
            {
                n=n.right;
                count++;
            }else if (n.content.compareTo(target)>0)    //go left
            {
                n=n.left;
                count++;
            }else {                                     //found
                System.out.println("The target "+target+" is found at the level "+count+"\n");
                return;
            }
        }
        System.out.println("The target "+target+" is not found.");

    }

}
