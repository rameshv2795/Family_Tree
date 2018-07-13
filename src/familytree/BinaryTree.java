/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

/**
 *
 * @author Vinod
 */
public class BinaryTree extends BaseTree{
    
    private class Node{
        int val;
        Node(int v){
            val = v;
        }
    }
    
    int nodes;
    Node root;
    
    /*ALL THESE FUNCTIONS ARE UNDER INCOMPLETE*/
    @Override
    public String loadTree(String fileName){
        return "load";
    }
    @Override
    public void saveTree(){
        
    }
    @Override
    public int nodeCount(int count){
        return 0;
    }    
    //public W
    public Node getRoot(){
        return root;
    }
    
    public void addNode(int val){
        Node n = new Node(val);
    }
    
    public Node findNode(){
        return root;
    }
}
