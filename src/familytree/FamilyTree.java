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
public class FamilyTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        
        Tree tree = new Tree();
        
       // jim.addSpouse(sally);
        //sally.addSpouse(jim);
        //Ui Window = new Ui(),win2 = new Ui();
        //Window.setVisible(true);
        //win2.setVisible(true);
        Window frame = new Window(tree);
       // frame.setVisible(true);
       // jim.addChild(sally);
        treePrompts(tree,frame);

        
    }
    
    public static void treePrompts(Tree tree,Window frame){
        
        
        Person alpha,beta,bar,bas,ce,cea,ca,da;
        //tree.printTree(tree.getRoot());
        //System.out.println(frame.getButtonPressed());
       
     /*  alpha = new Person("Alpha", "Al", -1,null,1);
        tree.addChild(null,alpha);
        beta = new Person("Beta", "Al", alpha.getDepth(),alpha,2);
        tree.addChild(alpha,beta);
        bar = new Person("Bar", "Al", alpha.getDepth(),alpha,2);
        tree.addChild(alpha,bar);
        Person iter = tree.getRoot(); //CHANGE LATER
        tree.printTree(iter);
        
        if(tree.findPerson(tree.getRoot(), "1Bar", "Al") != null){
            
            System.out.println("FOUND!!");
            
        }
       
        else
            System.out.println("NOT found!!");
       */ 
    }
    

    
   
    
}
