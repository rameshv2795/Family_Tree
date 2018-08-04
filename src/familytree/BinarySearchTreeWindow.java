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
    private JButton button,button2,saveButton,loadButton,quitButton,editButton;
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
        x=new Person("dsdfdd","sdf",2,s,2);
        y=new Person("dsdfdd","sdf",2,s,2);
        z=new Person("dsdfdd","sdf",2,s,2);
        
        //draw.drawString("Not Complete Tree", 0, 100);
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
        //saveAction();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Binary Search Tree");
        getContentPane().add(label, BorderLayout.CENTER);
        setSize(800, 800);
        setLocationRelativeTo(null);
        addMouseListener(new MouseClick());
    }
   
    private void createButton(){ //GROUPLAYOUT MANAGER
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout); //NEED THIS
        layout.setAutoCreateContainerGaps(true); //Dont have to specify gaps
        layout.setAutoCreateGaps(true);
        
        layout.setVerticalGroup(//setHorizontalGroup(Group group)
            layout.createSequentialGroup().addComponent(button)
                .addComponent(editButton) .addComponent(button2)
                .addComponent(saveButton) 
                .addComponent(loadButton) .addComponent(quitButton)
        );
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(button) .addComponent(editButton) .addComponent(button2) 
                    .addComponent(saveButton).addComponent(loadButton)
                    .addComponent(quitButton))
        );
       repaint();
    }
    
    private void buttonAction(){
        
        button.addActionListener(new ActionListener(){ //Action listener for button
            @Override //
            public void actionPerformed(ActionEvent event){ //Want own action preformed

                if(whichButton == 1){
                    //JOptionPane.showMessageDialog(null, "Button Already Activated. Click On A Node To Add A New Node", "Button Already Active", INFORMATION_MESSAGE);
                }
                resetButtons();
                activateButton(1);
                if(t.getRoot() == null){
                    AddPopUp pop = new AddPopUp();
                    JOptionPane.showConfirmDialog(null, pop.numbers, "Adding First Node", JOptionPane.OK_CANCEL_OPTION);

                    in_val = Integer.parseInt(pop.input_val.getText());
                    t.addNode(t.getRoot(), in_val, null);  
                    repaint();
                    t.printTree(t.getRoot());
                    return;
                }
                else{
                    AddPopUp pop = new AddPopUp();
                    JOptionPane.showConfirmDialog(null, pop.numbers, "Adding Node", JOptionPane.OK_CANCEL_OPTION);

                    in_val = Integer.parseInt(pop.input_val.getText());
                    t.addNode(t.getRoot(), in_val, null);  
                    repaint();
                    t.printTree(t.getRoot());
                    return;                    
                }
            }     
        }); 
    }
    private void button2Action(){
        
        button2.addActionListener(new ActionListener() {
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   activateButton(2);
             }
        });
    }
    
    private void editAction(){
        
        editButton.addActionListener(new ActionListener() {            
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   activateButton(6);
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
        
        loadButton.addActionListener(new ActionListener() {
            
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
    
    public void DrawChild(String parFir,String parLas){
        System.out.println("DRAWING CHILD");
      //  System.out.println("HERE");
      /*  Person parent = t.findNode(t.getRoot(),parFir,parLas);
        AddPopUp pop = new AddPopUp();
                     //JOptionPane.showInputDialog(pop.names);
                    // add(pop.names);
        JOptionPane.showConfirmDialog(null, pop.names, "Adding Child", JOptionPane.OK_CANCEL_OPTION);

        fname = pop.firstName.getText();
        lname = pop.lastName.getText();
        
        if(t.findPerson(t.getRoot(), fname, lname) != null){ //Check if Person already exists
            JOptionPane.showMessageDialog(null, "Person Already Exists", "Duplicate Person", ERROR_MESSAGE);
            return;
        }
        
        t.addChild(parent,new Person(fname,lname,parent.getMaxDepth(),parent,1));
        repaint();
        */
        return;       
    }
    
    public void DrawChildDelete(String parFir,String parLas){

      /*  Person p = t.findPerson(t.getRoot(),parFir,parLas);
        AddPopUp pop = new AddPopUp();

        if(t.deleteChild(p) == 0){
            JOptionPane.showMessageDialog(null, "Cannot delete a node with dependencies", "Person has children", ERROR_MESSAGE);
            return;
        }
        repaint();*/
        return;         
    }
    
    public void DrawChildEdit(String parFir,String parLas){
/*
        Person p = t.findPerson(t.getRoot(),parFir,parLas);
        AddPopUp pop = new AddPopUp(parFir,parLas);

       JOptionPane.showConfirmDialog(null, pop.names, "Edit Node", JOptionPane.OK_CANCEL_OPTION);

        fname = pop.firstName.getText();
        lname = pop.lastName.getText();
        
        if(t.findPerson(t.getRoot(), fname, lname) != null){ //Check if Person already exists
            JOptionPane.showMessageDialog(null, "Person Already Exists", "Duplicate Person", ERROR_MESSAGE);
            return;
        }       
        
        t.editPerson(p, fname, lname);
        
        repaint();*/
        return;       
    }    

    private class Painting extends JPanel {

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
            
            if(!t.isComplete()){
                l.setColor(Color.red);
                l.drawString("Not Complete Tree",20,300);    
            }
            else{
                l.setColor(Color.green);     
                l.drawString("Is Complete Tree",20,300); 
            }
            l.drawString("Not Balanced Tree",20,330);
            l.drawString("Not Perfect Tree",20,360);
            l.drawString("Not Degenerate Tree",20,390);
            
            System.out.println("iter depth: "+ iter.getDepth() + " max depth: "+ t.getMaxDepth());
            if(iter.getLow() == null && iter.getHigh() == null && direction != "inviz"){ //BASE 1
                g.setColor(yellow);
                g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth())) +180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80); // The 620 and 180 are related to the JFrame
                g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth())) +180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80);
                l.setColor(Color.black);
                l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(maxDepth+2))*(iter.getDepth()+1))+5+35);
                //l.drawString(iter.getLast(),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5+48);
                coord.add(new XY()); //Store location of oval for mouse
                coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180; //All this to keep track if node is clicked 
                coord.get(coord.size()-1).yClick = ((785/(maxDepth+2))*(iter.getDepth()+1))+5;
                coord.get(coord.size()-1).first = Integer.toString(iter.getVal());
                //coord.get(coord.size()-1).last = iter.getLast();

                if(iter.getParent() != null){
                    if(direction == "right"){
                        l.setColor(Color.red);
                    }
                    else{
                        l.setColor(Color.blue);
                    }
                    l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+40, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*((depthCopy.get(iter.getDepth()-1))-1))+180+40, ((785/(maxDepth+2))*(iter.getParent().getDepth()+1))+86.5));    
                }  
                depthCopy.set(iter.getDepth(),depthCopy.get(iter.getDepth()) + 1);
                depthCopy.add(0);
                if(iter.getDepth() < t.getMaxDepth()){
                    PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                    PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                }
                //PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz");
                //PaintTree(g,l,new Node(-1,iter),maxDepth,"inviz"); 
                //return;
            }    
                                                                                
            else if(direction != "inviz"){
                for(int i = 0; i < 2; i++){
                                                                                
                    if(i == 0){
                        g.setColor(yellow);
                        if(direction == "left"){
                            g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80); //max y is 785 + 5
                            g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80);
                            l.setColor(Color.black);
                            l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(maxDepth+2))*(iter.getDepth()+1))+5+35);                            
                        }
                        else if(direction == "right"){
                            g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80); //max y is 785 + 5
                            g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, 80, 80);
                            l.setColor(Color.black);
                            l.drawString(Integer.toString(iter.getVal()),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(maxDepth+2))*(iter.getDepth()+1))+5+35);                            
                        }                     
                        if(iter.getParent() != null){                      
                            if(direction == "right"){
                                l.setColor(Color.red);
                            }
                            else{
                                l.setColor(Color.blue);
                            }
                            l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+40, ((785/(maxDepth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*((depthCopy.get(iter.getDepth()-1))-1))+180+40, ((785/(maxDepth+2))*(iter.getParent().getDepth()+1))+86.5));                                                         
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
                System.out.println("Depth: "+ t.getDepthTracker().get(iter.getDepth()));
                System.out.println("Depth Copy: " + depthCopy.get(iter.getDepth()));
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
            
            if(t.getRoot() != null)
                PaintTree(oval,line,t.getRoot(),t.getMaxDepth(), "right"); //direction parameter doesn't matter ("right")
        }   
    }

    public class MouseClick extends MouseAdapter{
        
        int x,y;
        
        @Override
        public void mouseClicked(MouseEvent me){
            x = me.getX();
            y = me.getY();
            
            if(buttonPressed || button2Pressed || editPressed){ //so you can't add while another button active
                clickPerson();
            }
        }
        
        public void clickPerson(){
            Boolean next = false;
            
            for(int i = 0; i < coord.size(); i++){
                if(coord.get(i).xClick == x && coord.get(i).yClick == x ){
                    if(buttonPressed){
                        //DrawChild(coord.get(i).first,coord.get(i).last);
                    }    
                    else if(button2Pressed)
                        DrawChildDelete(coord.get(i).first,coord.get(i).last);
                    else if(editPressed)
                        DrawChildEdit(coord.get(i).first,coord.get(i).last);
                    return;
                }
                
                for(int j = 0; j < 30; j++){
                    
                    if(coord.get(i).xClick  +47 + j == x || coord.get(i).xClick + 47 - j == x  ){
                        next = true;
                    }
                    
                    if(next == true){
                        for(int k = 0; k < 30; k++){
                            if(coord.get(i).yClick  +69+ k == y || coord.get(i).yClick  +69 - k == y  ){
                                if(buttonPressed)
                                    DrawChild(coord.get(i).first,coord.get(i).last);
                                else if(button2Pressed)
                                    DrawChildDelete(coord.get(i).first,coord.get(i).last);   
                                else if(editPressed)
                                    DrawChildEdit(coord.get(i).first,coord.get(i).last);    
                                return;
                            }
                        }
                        next = false;
                    }   
                }
            }
                for (int z =0; z < coord.size(); z++){
               // System.out.println("<"+(coord.get(z).xClick+47) + "," + (coord.get(z).yClick+69)+">");
                }    
        }     
    }
    public class XY{
        int xClick, yClick;
        String first,last;
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
       public class FindPersonPop{
        
        JPanel names = new JPanel();
        JTextField parentfName = new JTextField(10);
        JTextField parentlName = new JTextField(10);
        JTextField firstName = new JTextField(10);
        JTextField lastName = new JTextField(10);
        JLabel parentfLabel = new JLabel("Parent First Name");
        JLabel parentlLabel = new JLabel("Parent Last Name");
        JLabel firstLabel = new JLabel("First Name");
        JLabel lastLabel = new JLabel("Last Name");
        
        FindPersonPop(){
            names.add(parentfLabel);
            names.add(parentfName);
            names.add(parentlLabel);
            names.add(parentlName);
            names.add(firstLabel);
            names.add(firstName);
            names.add(lastLabel);
            names.add(lastName);
        }       
    }        
}    