/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package familytree;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;

/**
 *
 * @author Vinod
 */
public class Tree extends BaseTree {
    
    private int peopleNumber,marriedNumber;
    private Person root;
    private Person iterator;
    private ArrayList<Integer> depthTracker;
    
    
    Tree(){
         treeInit(); //final method called
         tempStorage = new File("C:/Users/Public/Documents/testtree.txt");
    }
    
    public final void treeInit(){ //could move this to constructor
        
        root = null;
        peopleNumber = 0;
        maxDepth = -1;
        depthTracker = new ArrayList<Integer> (0);
    }
    
   
    public void printTree(Person iter){
        
        if(iter.getChildren().isEmpty()){ //BASE 1
            
            System.out.println(iter.getFirst() + " " + iter.getLast()+ " (maxDepth: " + iter.getMaxDepth() + ")");
            //System.out.println("EXIT");
            return;
        }    
        
        else{
            
            System.out.println("HERE");
            for(int i = 0; i<iter.getChildren().size(); i++){
                
                if(i == 0){
                    System.out.println(iter.getFirst() + " " + iter.getLast() + " (maxDepth: " + iter.getMaxDepth() + ")");
                    
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
    
    public int getMaxDepth(){
        return maxDepth;
    }
    
    
    public void addChild(Person parent,Person child){
        
        int sizeArray; //Not needed
       /* if(children.get(0) == null){
        
            System.out.println("IT IS NULL");
        
        }*/
        
        peopleNumber++;
        
        if(root == null){ //adding very first node
            root = child;
            maxDepth++; //-1 + 1 = 0
            depthTracker.add(1);
        }    
        
        else{
            
            //sizeArray = parent.getChildren().size(); //Not needed
            parent.getChildren().add(child); //adds child under parent
            if(maxDepth < child.getMaxDepth()){
                maxDepth++;
                depthTracker.add(1); //Adds new debth counter
            }//System.out.println(parent.getChildren().size());
            else
                depthTracker.set(child.getMaxDepth(),depthTracker.get(child.getMaxDepth())+1); //Increments certain debth
            
             
        }
        System.out.println("maxDepth!!!! IS: " + maxDepth);    
        System.out.println(Arrays.toString(depthTracker.toArray()));
    }
    
        
    public int deleteChild(Person current){
        
        if(maxDepth == 0){ //only root to delete
            maxDepth--;
            depthTracker.remove(0);
            root = null;
        }
        
        
        else if(current.getChildren().isEmpty()){ //check if no children
            for(int i = 0; i < current.getParent().getChildren().size(); i++){ //find person to delete
                if(current.getParent().getChildren().get(i).getFirst() == current.getFirst() && 
                   current.getParent().getChildren().get(i).getLast() == current.getLast()){ //check if right child
                    
                    if(current.getParent().getChildren().size() > 1){ //lower depthTracker
                        depthTracker.set(current.getMaxDepth(),depthTracker.get(current.getMaxDepth())-1);
                    }
                    else{ //lower maxDepth and remove depthTracker
                        depthTracker.remove(current.getMaxDepth());
                        maxDepth--;
                    }
                    current.getParent().getChildren().remove(i);
                    
                }
            }
            
        }
        else if(!current.getChildren().isEmpty()){ //ERROR: cannot delete node with children 
            return 0;
        }
        return 1;
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
    
    void editPerson(Person iter,String first, String last){  
       iter.editName(first, last);
    }    
    
    @Override
    public String loadTree(String fileName) throws Exception{
        System.out.println("File chosen: " + fileName);
        tempStorage = new File(fileName);
        BufferedReader in = new BufferedReader(new FileReader(tempStorage));
        
        ArrayList<String> holder = new ArrayList<String> (0);
        String st;
        /*
        Source:
        https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        */
        Boolean isRoot = true, isFirstGroup = true;
        String parentHolderFirst = "",parentHolderLast = "";
        
        while ((st = in.readLine()) != null){ 
           //System.out.println("AM I HERE???");
            if(st.trim().isEmpty()){ //trim removes leading and trailing space
               System.out.println("BLANK?");
                isFirstGroup = true;
            }
            else{
                //holder.add(st.split(" "));
                String firstNameHolder = "", lastNameHolder = "", tempNameHolder = "";
                for(int i = 0; i < st.length(); i++){ //read string char by char
                    if(st.charAt(i) == '|'){
                        firstNameHolder = tempNameHolder; 
                        tempNameHolder = "";
                    }
                    tempNameHolder = tempNameHolder + st.charAt(i);
                }
                lastNameHolder = tempNameHolder;
                if(isRoot){
                    addChild(null,new Person(firstNameHolder,lastNameHolder,0,null,1));
                    isRoot = false;
                    parentHolderFirst = firstNameHolder;
                    parentHolderLast = lastNameHolder;

                   // System.out.println("ROOT WORKED");
                }
                else if(!isFirstGroup){
                    Person pHolder = findPerson(root,parentHolderFirst,parentHolderLast);
                    if(pHolder == null){
                        System.out.println("THIS IS NULL");
                        System.out.println(parentHolderFirst);
                        System.out.println(parentHolderLast);

                    }    
                    addChild(pHolder,
                            new Person(firstNameHolder,lastNameHolder,pHolder.getMaxDepth(),pHolder,1));
                }
                else if (isFirstGroup){

                    parentHolderFirst = firstNameHolder;
                    parentHolderLast = lastNameHolder;                
                }
                isFirstGroup = false;
                        System.out.println(firstNameHolder);
                        System.out.println(lastNameHolder);
                        
            }        
        }    
      // printTree(root);
        return fileName;
    }
    @Override
    public void saveTree(){
        
    }
    
}
