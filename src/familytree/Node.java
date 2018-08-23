/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package familytree;

/**
 *
 * @author Vinod Ramesh
 */
public class Node{
    private int val;
    private Node low;
    private Node high;
    private Node parent; //needed for GUI
    private int nodeDepth, parentDepth;

    Node(int v, Node par){
        val = v;
        low = null;
        high = null;
        parent = par;
        if(parent == null){
            nodeDepth = 0;
        }
        else{
            nodeDepth = parent.getDepth() + 1;
        }
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
    int getParentDepth(){
        return parentDepth;
    }
    int getDepth(){
        return nodeDepth;
    }    
    void setLow(int v){
        System.out.println(this);
        low = new Node(v, this);
    }
    void setHigh(int v){
        high = new Node(v, this);
    }
    void setParent(Node p){
        parent = p;
    }
    Node getParent(){
        return parent;
    }
}
