/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package familytree;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Vinod
 */
public class BinarySearchTreeWindow extends JFrame{
    
    private JPanel design;
    private Painting paint;
    private BinarySearchTree t;
    private JLabel label;
    private Graphics2D draw;
    private JFrame window,popUp;
    private JButton button,button2,saveButton,loadButton,quitButton,editButton, balance, clearButton;
    private Boolean buttonPressed,button2Pressed,savePressed,loadPressed,quitPressed,editPressed;
    private JOptionPane j;
    private String fname,lname,pf,pl;
    private int in_val;
    private int maxDepth,spot,people,whichButton;
    private Person p,q,r,s,sonS,sonS2,sonR,sson,x,y,z;
    private Color lightgreen,yellow,lightOrange,grey, darkGreen;
    private ArrayList<Integer> depthCopy,parentCopy;
    private ArrayList<XY> coord;
    private Font border;
    private Dimension screenSize;
    
    BinarySearchTreeWindow(BaseTree tree){ 
        t = (BinarySearchTree) tree;
        initial();          
        createWindow();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setBounds(0,0,screenSize.width/2, screenSize.height);
        setVisible(true);
    }
    
    private void initial(){
        // http://paletton.com/#uid=12C0u0kllllaFw0g0qFqFg0w0aF
        depthCopy = new ArrayList<Integer> (0);
        parentCopy = new ArrayList<Integer> (0);
        lightgreen = Color.decode("#D7E4F5");
        yellow = Color.decode("#F1C40F");
        lightOrange = Color.decode("#85BC5E");
        grey = new Color(224,224,224);
        darkGreen =  Color.decode("#468615");
        people = 1;
        button = new JButton("Add Node");
        button2 = new JButton("Delete Node");
        editButton = new JButton("Edit Node");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");
        clearButton = new JButton("Clear");
        balance = new JButton("Balance");
        button.setPreferredSize(new Dimension(40, 40));
        resetButtons();
        coord = new ArrayList<XY> (0);
        j = new JOptionPane();
        label = new JLabel();
        design = new JPanel();
        paint = new Painting();
        
        /*Test data*/
        p = new Person("Jason","Kidd",-1,null,1);
        q = new Person("Jack","Kidd",0,p,1);
        r = new Person("Sarah","Kidd",1,q,1);
        s = new Person("Goro","Kidd",1,q,2);
        sonS = new Person("Kelly","Kidd",2,s,3);
        sonS2 = new Person("Su","Kidd",2,s,2);
        sonR = new Person("Larry","Richmen",2,r,1);
        sson = new Person("Andy","West",3,sonS,1);
        x = new Person("dsdfdd","sdf",2,s,2);
        y = new Person("dsdfdd","sdf",2,s,2);
        z = new Person("dsdfdd","sdf",2,s,2);
    }

    public JButton getButton(){
        return button;
    }
    
    public void resetButtons(){
        buttonPressed = false;
        button2Pressed = false;
        savePressed = false;
        loadPressed = false;
        quitPressed = false;
        editPressed = false;
        button.setBackground(grey);
        button2.setBackground(grey);
        saveButton.setBackground(grey);
        loadButton.setBackground(grey);
        quitButton.setBackground(grey);
        editButton.setBackground(grey);
        balance.setBackground(grey);
        clearButton.setBackground(grey);
        whichButton = 0;       
    }
    
    public void activateButton(int x){    
        switch(x){
            case 1: buttonPressed = true;
                    button.setBackground(lightOrange);
                    whichButton = 1;
                    break;
            case 2: button2Pressed = true;
                    button2.setBackground(lightOrange);
                    whichButton = 2;
                    break;
            case 3: savePressed = true;
                    saveButton.setBackground(lightOrange);
                    whichButton = 3;
                    break;
            case 4: loadPressed = true;
                    loadButton.setBackground(lightOrange);
                    whichButton = 4;
                    break;
            case 5: quitPressed = true;
                    quitButton.setBackground(lightOrange);
                    whichButton = 5;
                    break;
            case 6: editPressed = true;
                    editButton.setBackground(lightOrange);
                    whichButton = 6;
                    break;    
            default: break;
        }  
    }
    
    private void createWindow(){
        //super.add(new Painting());
        setContentPane(paint);
        createButton();
        buttonAction();
        button2Action();
        editAction();
        quitAction();
        loadAction();
        balanceAction();
        clearAction();
        //saveAction();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Binary Search Tree");
        getContentPane().add(label, BorderLayout.CENTER);
        setSize(800, 800);
        setLocationRelativeTo(null);
    }
   
    private void createButton(){ //GROUPLAYOUT MANAGER
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout); //NEED THIS
        layout.setAutoCreateContainerGaps(true); //Dont have to specify gaps
        layout.setAutoCreateGaps(true);
        
        layout.setVerticalGroup(//setHorizontalGroup(Group group)
            layout.createSequentialGroup().addComponent(button)
                .addComponent(balance) .addComponent(button2)
                .addComponent(clearButton) .addComponent(saveButton) 
                .addComponent(loadButton) .addComponent(quitButton)
                
        );
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(button) .addComponent(balance) .addComponent(button2) 
                    .addComponent(clearButton) .addComponent(saveButton)
                    .addComponent(quitButton) .addComponent(loadButton))
        );
       repaint();
    }
    
    private void buttonAction(){
        
        button.addActionListener(new ActionListener(){ //Action listener for button
            @Override //
            public void actionPerformed(ActionEvent event){ //Want own action preformed
                resetButtons();
                activateButton(1);
                AddPopUp pop = new AddPopUp();
                
                JOptionPane.showConfirmDialog(null, pop.numbers, "Adding Node", JOptionPane.OK_CANCEL_OPTION);
                try{
                    in_val = Integer.parseInt(pop.input_val.getText());
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Not a valid integer", "Must be 2 digit integer", ERROR_MESSAGE);
                    return;
                }
                if(in_val > 999 || in_val <-99){
                    JOptionPane.showMessageDialog(null, "Out of range", "Integer must be between -99 to 999", ERROR_MESSAGE);                        
                    return;
                }
                
                t.addNode(t.getRoot(), in_val, null);  
                repaint();
                //t.printTree(t.getRoot());
                return;                    
            }     
        }); 
    }
    private void button2Action(){
        
        button2.addActionListener(new ActionListener(){
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   activateButton(2);
             }
        });
    }
    
    private void editAction(){
        
        editButton.addActionListener(new ActionListener(){            
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   activateButton(6);
             }   
        });
    }    
    
    private void clearAction(){
        clearButton.addActionListener(new ActionListener(){

             @Override //
              public void actionPerformed(ActionEvent event){
                  //System.out.println("CLEARED");
                  depthCopy = new ArrayList<Integer> (0);
                  t.clearTree();
                  repaint();
              }
         }); 
    }
    
    private void quitAction(){
        
        quitButton.addActionListener(new ActionListener() {
            
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   activateButton(5);
                   System.exit(0);
             }
        });  
    }    
        
    private void loadAction(){
        
        loadButton.addActionListener(new ActionListener(){
            
            @Override //
             public void actionPerformed(ActionEvent event){
                   Component c = (Component) event.getSource();
                   resetButtons();
                   activateButton(4);
                   /*
                   Source for below code:
                   https://docs.oracle.com/javase/6/docs/api/javax/swing/JFileChooser.html
                   */
                   JFileChooser chooser = new JFileChooser();
                   int returnVal = chooser.showOpenDialog(c.getParent());
                   if(returnVal == JFileChooser.APPROVE_OPTION) {
                       try {
                           //  System.out.println("You chose to open this file: " +
                           //       chooser.getSelectedFile().getName());
                           t.loadTree(chooser.getSelectedFile().getAbsolutePath());
                       } catch (Exception ex) {
                           Logger.getLogger(BinarySearchTreeWindow.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   
             repaint();
             return;
             }           
        });
    }    
    
    private void balanceAction(){
        
        balance.addActionListener(new ActionListener(){
            
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   ArrayList<Node> inorder = new ArrayList<Node>();
                   t.balanceTree(t.getAsArrayList(t.getRoot(), inorder),0, t.getNodesTotal()-1, null, true);
                  // t.setRoot(inorder.get());
                   repaint();
             }
        });  
    }     
        
    public void DrawChildDelete(String parFir,String parLas){
        return;         
    }
    
    private class Painting extends JPanel{

        Painting(){
            setBackground(lightgreen);
           // repaint();
        }

        /*
        depthCopy = maximum amount of nodes possible at a certain depth
        OVALS (adding 1 to maxDepth because lowest debth is 0)
        X Axis: (X JFrame length) / (# of nodes at certain debth + 1 * by counter for # of nodes in certain maxDepth) ... Counter increments
        Y Axis: (Y JFrame length / Total Tree maxDepth + 2) * (maxDepth at position + 1)
       
        LINES TO PARENT (When access parent maxDepth counter(in ArrayList) subtract 1 because it incremented before)
        X Axis: (X JFrame length) / (# of nodes at parent debth + 1 * by counter for # of nodes in parent maxDepth) 
        Y Axis: (Y JFrame length / Total Tree maxDepth + 2) * (maxDepth at Parent Position plus 1)
        */
        private void PaintTree(Graphics2D g, Graphics2D l, Node iter, int maxDepth, String direction){
            
            if(!t.isComplete(t.getRoot(),0)){
                l.setColor(Color.red);
                l.drawString("Not Complete Tree",20,300);    
            }
            else{
                l.setColor(darkGreen);     
                l.drawString("Is Complete Tree",20,300); 
            }
            if(!t.isBalanced(t.getRoot())){
                l.setColor(Color.red);
                l.drawString("Not Balanced Tree",20,330);    
            }
            else{
                l.setColor(darkGreen);     
                l.drawString("Is Balanced Tree",20,330); 
            }          
            if(!t.isDegenerate(t.getRoot())){
                l.setColor(Color.red);
                l.drawString("Not Degenerate Tree",20,390);   
            }
            else{
                l.setColor(darkGreen);     
                l.drawString("Is Degenerate Tree",20,390); 
            }   
            if(!t.isPerfect(t.getRoot())){
                l.setColor(Color.red);
                l.drawString("Not Perfect Tree",20,360);   
            }
            else{
                l.setColor(darkGreen);     
                l.drawString("Is Perfect Tree",20,360);      
            }    
            l.setColor(Color.red);
            
            //System.out.println("iter depth: "+ iter.getDepth() + " max depth: "+ t.getMaxDepth());
            if(iter.getLow() == null && iter.getHigh() == null && direction != "inviz"){ //BASE 1
                g.setColor(yellow);
                g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth())) +180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 40, 40); // The 620 and 180 are related to the JFrame
                g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth())) +180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 40, 40);
                l.setColor(Color.black);
                l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+17,((785/(maxDepth+2))*(iter.getDepth()+1))+5+23);
                //l.drawString(iter.getLast(),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5+48);
                coord.add(new XY()); //Store location of oval for mouse
                coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth())) +180; //All this to keep track if node is clicked 
                coord.get(coord.size()-1).yClick = ((785/(maxDepth+2))*(iter.getDepth()+1))+5;
                coord.get(coord.size()-1).id = iter.getVal();
                //coord.get(coord.size()-1).last = iter.getLast();

                if(iter.getParent() != null){
                    if(direction == "right"){
                        l.setColor(Color.red);
                    }
                    else{
                        l.setColor(Color.blue);
                    }
                    l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+20, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*((depthCopy.get(iter.getDepth()-1))-1))+180+20, ((785/(maxDepth+2))*(iter.getParent().getDepth()+1))+45));    
                }  
                depthCopy.set(iter.getDepth(),depthCopy.get(iter.getDepth()) + 1);
                depthCopy.add(0);
                if(iter.getDepth() < t.getMaxDepth()){
                    PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                    PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                }
            }                                                                                  
            else if(direction != "inviz"){
                for(int i = 0; i < 2; i++){                                                      
                    if(i == 0){
                        g.setColor(yellow);
                        if(direction == "left"){
                            g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 40, 40); //max y is 785 + 5
                            g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 40, 40);
                            l.setColor(Color.black);
                            l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+17,((785/(maxDepth+2))*(iter.getDepth()+1))+5+21);                            
                        }
                        else if(direction == "right"){
                            g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 40, 40); //max y is 785 + 5
                            g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 40, 40);
                            l.setColor(Color.black);
                            l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+17,((785/(maxDepth+2))*(iter.getDepth()+1))+5+21);     
                            coord.add(new XY()); //Store location of oval for mouse
                            coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180; //All this to keep track if node is clicked 
                            coord.get(coord.size()-1).yClick = ((785/(maxDepth+2))*(iter.getDepth()+1))+5;
                            coord.get(coord.size()-1).id = iter.getVal();
                        }                     
                        if(iter.getParent() != null){                      
                            if(direction == "right"){
                                l.setColor(Color.red);
                            }
                            else{
                                l.setColor(Color.blue);
                            }
                            l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+20, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*((depthCopy.get(iter.getDepth()-1))-1))+180+20, ((785/(maxDepth+2))*(iter.getParent().getDepth()+1))+45));                                   
                        }
                        depthCopy.set(iter.getDepth(),depthCopy.get(iter.getDepth()) + 1);
                    }
                    if(iter.getLow() != null && i == 0){
                        PaintTree(g,l,iter.getLow(),maxDepth,"left"); 
                    }
                   /* else if(iter.getLow() == null && i == 0){ //draw invisible node for GUI scale
                        PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz"); 
                    }*/
                    else if(iter.getHigh() != null & i == 1){
                        PaintTree(g,l,iter.getHigh(),maxDepth,"right"); 
                    }  
                    /*else if(iter.getHigh() == null && i == 0){
                        PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz"); 
                    }*/
                    else if(iter.getDepth() < t.getMaxDepth()){
                        PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                    }
                }   
            }
            else if (direction == "inviz"){ //base case                      
                //g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80); //max y is 785 + 5
                //g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80);
                //l.setColor(Color.black);
                //l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(maxDepth+2))*(iter.getDepth()+1))+5+35);    
                depthCopy.set(iter.getDepth(),depthCopy.get(iter.getDepth()) + 1);
                //System.out.println("Depth: "+ t.getDepthTracker().get(iter.getDepth()));
                //System.out.println("Depth Copy: " + depthCopy.get(iter.getDepth()));
                if(iter.getDepth() < t.getMaxDepth() /*&& t.getDepthTracker().get(iter.getDepth() + 1) < iter.getDepth()*2*/){
                    PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                    PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                }                                
            }

        }       

        @Override
        public void paintComponent(Graphics g){
            border = new Font("Serif", Font.BOLD, 80);
            super.paintComponent(g);
            Graphics2D oval = (Graphics2D) g;
            Graphics2D divider = (Graphics2D) g; //casting object
            Graphics2D line = (Graphics2D) g;

            divider.draw(new Line2D.Double(screenSize.width/9, 0, screenSize.width/9, screenSize.height));
            depthCopy = new ArrayList<Integer> (0);
            parentCopy = new ArrayList<Integer> (0);
            coord = new ArrayList<XY> (0);
            
            for(int i = 0; i < t.getDepthTracker().size(); i++){
                depthCopy.add(1);
                parentCopy.add(1);
            }
            if(!t.isComplete(t.getRoot(),0)){
                line.setColor(Color.red);
                line.drawString("Not Complete Tree",20,300);    
            }
            else{
                line.setColor(darkGreen);     
                line.drawString("Is Complete Tree",20,300); 
            }
            if(!t.isBalanced(t.getRoot())){
                line.setColor(Color.red);
                line.drawString("Not Balanced Tree",20,330);    
            }
            else{
                line.setColor(darkGreen);     
                line.drawString("Is Balanced Tree",20,330); 
            }      
            if(!t.isDegenerate(t.getRoot())){
                line.setColor(Color.red);
                line.drawString("Not Degenerate Tree",20,390);   
            }
            else{
                line.setColor(darkGreen);     
                line.drawString("Is Degenerate Tree",20,390); 
            }      
            if(!t.isPerfect(t.getRoot())){
                line.setColor(Color.red);
                line.drawString("Not Perfect Tree",20,360);   
            }
            else{
                line.setColor(darkGreen);     
                line.drawString("Is Perfect Tree",20,360); 
            }               
            line.setColor(Color.red);
            //line.drawString("Not Balanced Tree",20,330);
           // line.drawString("Not Perfect Tree",20,360);
           // line.drawString("Not Degenerate Tree",20,390);            
            if(t.getRoot() != null)
                PaintTree(oval,line,t.getRoot(),t.getMaxDepth(), "right"); //direction parameter doesn't matter ("right")
        }   
    }

    public class XY{
        int xClick, yClick;
        int id;
        
        public int getId(){
            return id;
        }
    }    
    
    public class AddPopUp{
        JPanel numbers = new JPanel();
        JTextField input_val = new JTextField(10);
        JLabel firstLabel = new JLabel("Integer Value");
        
        AddPopUp(){
            numbers.add(firstLabel);
            numbers.add(input_val);
        }
        
        AddPopUp(String f, String l){ //Overload for edit popup
            numbers.add(firstLabel);
            numbers.add(input_val);
            this.input_val.setText(f);
        }        
    }      
}    