/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary.search.tree;

/**
 *
 * @author Mr.AB
 */
public class Node {
  String data;
    Node parent, left, right;

    public Node(String data, Node parent, Node left, Node right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
