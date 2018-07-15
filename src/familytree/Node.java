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

    Node(int v, int p, Node par){
        val = v;
        low = null;
        high = null;
        parentDepth = p;
        nodeDepth = parentDepth + 1;
        parent = par;
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
    Node getParent(){
        return parent;
    }
}
