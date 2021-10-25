package com.example.geektrust;

import java.util.HashMap;

public class Dynasty {

    HashMap<String, Node> dynastyTree;
    Node queenNode;
    Node kingNode;

    public Dynasty(HashMap<String, Node> dynastyTree, Node queenNode, Node kingNode) {
        this.dynastyTree = new HashMap<>();
        this.queenNode = queenNode;
        this.kingNode = kingNode;

        dynastyTree.put(queenNode.getName(),queenNode);
        dynastyTree.put(kingNode.getName(),kingNode);


    }


}
