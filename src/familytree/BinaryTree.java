/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.util.ArrayList;

/**
 *
 * @author Vinod
 */
public class BinaryTree extends BaseTree{
    int nodesTotal, maxDepth;
    Node root;
    private ArrayList<Integer> depthTracker;
        
    BinaryTree(){
        root = null;
        depthTracker = new ArrayList<Integer> (0);
        nodesTotal = 0;
        maxDepth = -1;
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
    public ArrayList<Integer> getDepthTracker(){
        return depthTracker;
    } 
    public int getMaxDepth(){
        return maxDepth;
    }     
           
    public void addNode(Node r, int val, Node par){

        if(root == null){
            root = new Node(val, -1, null);
            nodesTotal++;
            maxDepth++; //-1 + 1 = 0
            depthTracker.add(1);         
        }
        else if(r == null){
            System.out.println("parentdep VALUE: " + par.getDepth());
            r = new Node(val, par.getDepth(), par); 
            if(maxDepth < r.getDepth()){
                maxDepth++;
                depthTracker.add(1); //Adds new debth counter
            }//System.out.println(parent.getChildren().size());   
            else{
                depthTracker.set(r.getDepth(),depthTracker.get(r.getDepth())+1); //Increments certain debth   
            }    
            nodesTotal++;
            return;
        }
        else if(r.getVal() >= val){
            addNode(r.getLow(), val, r);
        }
        else{
            addNode(r.getHigh(), val, r);
        }
        
        System.out.println("ROOT VALUE: " + root.getVal());
    }
    
    public Node findNode(){
        return root;
    }
    
}
