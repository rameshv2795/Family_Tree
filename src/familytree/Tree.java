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
public class Tree {
    
    private int depth,peopleNumber,marriedNumber;
    private Person root;
    private Person iterator;
    private ArrayList<Integer> depthTracker;
    
    
    Tree(){
         treeInit();
        
    }
    
    public void treeInit(){
        
        root = null;
        peopleNumber = 0;
        depth = -1;
        depthTracker = new ArrayList<Integer> (0);
    }
    

    public void printTree(Person iter){
        
        if(iter.getChildren().isEmpty()){ //BASE 1
            
            System.out.println(iter.getFirst() + " " + iter.getLast()+ " (Depth: " + iter.getDepth() + ")");
            //System.out.println("EXIT");
            return;
        }    
        
        else{
            
            System.out.println("HERE");
            for(int i = 0; i<iter.getChildren().size(); i++){
                
                if(i == 0){
                    System.out.println(iter.getFirst() + " " + iter.getLast() + " (Depth: " + iter.getDepth() + ")");
                    
                }
                
                printTree(iter.getChildren().get(i)); //Recursion
                
                
            }
            
        }
           
        
        
    }    
    
    public Person getRoot(){

        return root;
        
    }
    
    public ArrayList<Integer> getDepthTracker(){
        return depthTracker;
    }
    
    public ArrayList<Integer> getDepthCopy(){
        return depthTracker;
    }
    
    public int getPeopleNumber(){
        return peopleNumber;
        
    }
    
    public int getDepth(){
        return depth;
    }
    
    
    public void addChild(Person parent,Person child){
        
        int sizeArray; //Not needed
       /* if(children.get(0) == null){
        
            System.out.println("IT IS NULL");
        
        }*/
        
        peopleNumber++;
        
        if(root == null){ 
            root = child;
            depth++;
            depthTracker.add(1);
        }    
        
        else{
            
            //sizeArray = parent.getChildren().size(); //Not needed
            parent.getChildren().add(child); //adds child under parent
            if(depth < child.getDepth()){
                depth++;
                depthTracker.add(1); //Adds new debth counter
            }//System.out.println(parent.getChildren().size());
            else
                depthTracker.set(child.getDepth(),depthTracker.get(child.getDepth())+1); //Increments certain debth
            
             
        }
        System.out.println("DEPTH!!!! IS: " + depth);    
    }
    
        
    public void deleteChild(Person current){
        
        if(!current.getChildren().isEmpty()){ //check if no children
            for(int i = 0; i < current.getChildren().size(); i++){ //find person to delete
                if(current.getParent().getChildren().get(i).getFirst() == current.getFirst() && 
                   current.getParent().getChildren().get(i).getLast() == current.getLast()){ //check if right child
                    
                    current.getParent().getChildren().remove(i);
                }
            }
            
        }
        else{
            
        }
        
    }            
    
    public Person findPerson(Person iter,String first, String last){
        
        if(iter.getFirst().equals(first) && iter.getLast().equals(last)){
            return iter;
        }
        
        //ArrayList<Person> children = iter.getChildren();
        Person p = null;
        
        for(int i = 0; p == null && i < iter.getChildren().size(); i++ ){
            
            
            p = findPerson(iter.getChildren().get(i),first,last);

        }
        return p; //Return null if not found
    }
    
    
}
