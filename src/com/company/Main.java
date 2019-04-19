package com.company;

public class Main {

    public static void main(String[] args) {

        RBT tree = new RBT();

        tree.insert("hassan");
        tree.insert("yassan");
        tree.insert("assan");

        tree.inorder(tree.getRoot());
        System.out.println();
        tree.find("assan");
    }
}
