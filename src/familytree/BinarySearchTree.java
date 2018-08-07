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
public class BinarySearchTree extends BaseTree{
    int nodesTotal, maxDepth;
    Node root;
    private ArrayList<Integer> depthTracker;
        
    BinarySearchTree(){
        root = null;
        depthTracker = new ArrayList<Integer> (0);
        nodesTotal = 0;
        maxDepth = -1;
    }
    
    /*ALL THESE FUNCTIONS ARE INCOMPLETE*/
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
        System.out.println("Adding: " + val);
        int wide_count = 1;
        if(root == null){
            System.out.println("IS ROOT");
            root = new Node(val, null);
            nodesTotal++;
            maxDepth++; //-1 + 1 = 0
            depthTracker.add(1);     
            
            return;
        }
        else if(r.getVal() >= val){
            
            if(r.getLow() != null){
                System.out.println("Lower");
                addNode(r.getLow(), val, r);
            }
            else{         
                nodesTotal++;
                r.setLow(val);
                if(r.getHigh() == null){
                    wide_count = r.getLow().getDepth() * 2;
                }       
                
                if(maxDepth < r.getLow().getDepth()){
                    maxDepth++;
                    depthTracker.add(wide_count); //Adds new debth counter
                }     
                else{
                    //depthTracker.set(r.getLow().getDepth(),depthTracker.get(r.getLow().getDepth()) + wide_count);
                }
            }
        }
        else{
            if(r.getHigh() != null){
                System.out.println("HIGHER");
                addNode(r.getHigh(), val, r);
            }
            else{
                nodesTotal++;
                r.setHigh(val);
                if(r.getLow() == null){
                    wide_count = r.getHigh().getDepth() * 2;
                }
                
                if(maxDepth < r.getHigh().getDepth()){
                    maxDepth++;

                    depthTracker.add(wide_count); //Adds new debth counter
                }    
                else{
                    //depthTracker.set(r.getHigh().getDepth(),depthTracker.get(r.getHigh().getDepth()) + wide_count);
                }
            }            
        }
    }
    
    public Node findNode(){
        return root;
    }
    
    public void printTree(Node n){
        if(n == null){
            return;
        }
        else{
            printTree(n.getLow());
            System.out.println(n.getVal());
            printTree(n.getHigh());
        }
    }
    public int height(Node n){
        
        if(n == null){
            return 0;
        }
        
        return 1 + Integer.max(height(n.getLow()),height(n.getHigh()));
    }    
    public Boolean isComplete(Node n, int index){
        System.out.println("Total Nodes: " + nodesTotal);    
        if(n == null){
            return true;
        }
        else if(index >= nodesTotal){
            System.out.println("Index: " + index);
            return false;
        }
        else{
            return (isComplete(n.getLow(), (2 * index) + 1)
                    && isComplete(n.getHigh(), (2 * index) + 2));
        }
    }
    public Boolean isBalanced(Node n){
        int r_height = 0, l_height = 0;
        if(n == null){
            return true;
        }
        
        r_height = height(n.getHigh());
        l_height = height(n.getLow());
        
        if(Math.abs(r_height - l_height) <= 1
                && isBalanced(n.getLow())
                && isBalanced(n.getHigh())){
            return true;
        }
        
        return false;
    }    
    public Boolean isPerfect(Node n, int index){
        return false;
    }    
    public Boolean isDegenerate(Node n, int index){
        return false;
    } 
    
}

