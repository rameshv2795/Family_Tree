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
    private int nodesTotal, maxDepth;
    private Node root;
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
    public int getNodesTotal(){
        return nodesTotal;
    }
           
    public void addNode(Node r, int val, Node par){
        //System.out.println("Adding: " + val);
        int wide_count = 1;
        if(root == null){
            //System.out.println("IS ROOT");
            root = new Node(val, null);
            nodesTotal++;
            maxDepth++; //-1 + 1 = 0
            depthTracker.add(1);                 
            return;
        }
        else if(r.getVal() >= val){
            
            if(r.getLow() != null){
                //System.out.println("Lower");
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
                //System.out.println("HIGHER");
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
    
    @Override
    public void clearTree(){
        root = null;
        depthTracker = new ArrayList<Integer> (0);
        nodesTotal = 0;
        maxDepth = -1;
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
    
    public void setRoot(Node r){
        root = r;
    }
    public Boolean isComplete(Node n, int index){
       // System.out.println("Total Nodes: " + nodesTotal);    
        if(n == null){
            return true;
        }
        else if(index >= nodesTotal){
            //System.out.println("Index: " + index);
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
    public Boolean isPerfect(Node n){
        if(n == null){
            return true;
        }
        else if(n.getHigh() != null && n.getLow() != null){
            return (isPerfect(n.getHigh()) && isPerfect(n.getLow()));
        }
        else if(n.getHigh() == null && n.getLow() == null){
            if(n.getDepth() != maxDepth){
                return false;
            }
            else{
                return true;
            }
        }
        return false; //only 1 child
    }    
    public Boolean isDegenerate(Node n){
        if(n == null){
            return true;
        }
        else if(n.getHigh() != null && n.getLow()!= null){
            return false;
        }
        else if (n.getHigh() != null){
            isDegenerate(n.getHigh());
        }
        return isDegenerate(n.getLow());
    } 
    public ArrayList<Node> getAsArrayList(Node n, ArrayList<Node> holder){
        if(n == null){
            return holder;
        }
        else{
            getAsArrayList(n.getLow(), holder);
            holder.add(n);
            getAsArrayList(n.getHigh(), holder);
            return holder;
        }
    }
    public Node balanceTree(ArrayList<Node> holder, int start, int end, Node parent, Boolean isRoot){
        
        int mid = 0;
        Node n;
        //printTree(root);
        //System.out.println("Holder Size: "+ holder.size());        
        if(start > end){
            return null;
        }
        
        mid = (start + end) / 2;
        n = holder.get(mid);
        
        if(n != null && !isRoot){
            //n.setParent(parent);
            addNode(n,n.getVal(),parent);
        }
        else if(n != null){
            holder = getAsArrayList(root, new ArrayList<Node>());
            //n.setParent(null);
            //n.;
            clearTree();
            addNode(n, n.getVal(), null);
            //depthTracker = new ArrayList<Integer>();
            //depthTracker.add(1);
            //maxDepth = 0;
            System.out.println("BALANCED ROOT: " + root.getVal());
        }
        
        //System.out.println(" Holder: " + holder + " Start: " +start+ " mid - 1: " + (mid-1) + " n: " + n);
        //System.out.println("----------------------------");
        balanceTree(holder, start, mid - 1, n, false);
        balanceTree(holder, mid+1, end, n, false);
        System.out.println("----------------------------");
        
        /*Test code to verify balance working*/
        try{
        System.out.println("Curr: "+n.getVal());
        }
        catch(NullPointerException e){
            System.out.println("Curr: "+"NULL");
        }
        try{
        System.out.println("Low: "+n.getLow().getVal());
        }
        catch(NullPointerException e){
            System.out.println("Low: "+"NULL");
        }        
        try{
        System.out.println("High: "+n.getHigh().getVal());
        }
        catch(NullPointerException e){
            System.out.println("High: "+"NULL");
        }        
        System.out.println("----------------------------");
        /*Test code done*/
        
        return n;
    }
}