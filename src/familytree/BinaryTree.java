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
        private int val;
        private Node low;
        private Node high;
        
        Node(int v){
            val = v;
            low = null;
            high = null;
        }  
        
        int getVal(){
            return val;
        }
        Node getLow(){
            return low;
        }
        Node getHigh(){
            return high;
        }
    }
    int nodes;
    Node root;
    
    BinaryTree(){
        root = null;
    }
    
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
    
    public void addNode(Node r, int val){
        
        if(root == null){
            root = new Node(val);
            return;
        }
        else if(r == null){
            r = new Node(val);
            return;
        }
        else if(r.getVal() >= val){
            addNode(r.getLow(), val);
        }
        else{
            addNode(r.getHigh(), val);
        }
    }
    
    public Node findNode(){
        return root;
    }
}
