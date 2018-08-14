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
public class Person {
    
   private ArrayList<Person> children;
   private Person spouse;
   private String firstName,lastName;
   private char gender;
   private int birthYear;
   private Boolean maxKids; 
   private int personDepth,rowNumber;
   private Person parent;
   //private int id;
   
    
    Person(String first,String last,int parentDepth,Person p,int row){
        rowNumber = row;
        firstName = first.trim();
        lastName = last.trim();
        parent = p; 
        initPerson(parentDepth);
       // id = i;
        
    }
    
 
    public void initPerson(int parentDepth){
        children = null;
        spouse = null;
        maxKids = false;
        children = new ArrayList<Person> (0);
        personDepth = parentDepth + 1;
    }
    
    public int getRowNumber(){
        return rowNumber;
    }
    
    public Person getSpouse(){
        return spouse;
    }
    
    public Person getParent(){
        return parent;
    }
    
    public String getFirst(){
        return firstName;
    }    
    
    public String getLast(){
        return lastName;
    }    
    
    public int getMaxDepth(){
        return personDepth;
    }
    
    public ArrayList<Person> getChildren(){
        return children;
    }  
    
    public int getChildrenCount(){
        return children.size();
    }
    
    public void editName(String f, String l){
        firstName = f;
        lastName = l;
    }
    
    public void addSpouse(Person sp){
        
        if(spouse != null){   
           System.out.println("Error: Person Already Has Spouse");
           return;
        }
        spouse = sp;
    }
}
