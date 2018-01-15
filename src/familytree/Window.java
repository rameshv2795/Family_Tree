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
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import javax.swing.Box;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JTextField;

/**
 *
 * @author Vinod
 */
public class Window extends JFrame{
    
    private JPanel design;
    private Painting paint;
    private Tree t;
    private JLabel label;
    private Graphics2D draw;
    //private Container win;
    private JFrame window,popUp;
    private JButton button,button2,saveButton,loadButton,quitButton,editButton;
    private Boolean buttonPressed,button2Pressed,savePressed,loadPressed,quitPressed,editPressed;
    private JOptionPane j;
    private String fname,lname,pf,pl;
    private int depth,spot,people,whichButton;
    private Person p,q,r,s,sonS,sonS2,sonR,sson,x,y,z;
    private Color lightgreen,yellow,lightOrange,grey, darkGreen;
    private ArrayList<Integer> depthCopy,parentCopy;
    private ArrayList<XY> coord;
    
    private Font border;
  //  MouseClick e;
    Window(Tree tree){ //Constructor
        t = tree;
        initial();      
       
        createWindow();

        setVisible(true);
        
         //combinePanel();
        
        
      //  setVisible(true);
       // createButton();
        //actionAddButton();
 
  
        
        
    }
    
    private void initial(){
        
         //window = new JFrame("Family Tree");
         // http://paletton.com/#uid=12C0u0kllllaFw0g0qFqFg0w0aF
         depthCopy = new ArrayList<Integer> (0);
         parentCopy = new ArrayList<Integer> (0);
       //  coord = new ArrayList<Float> (0);
         lightgreen = Color.decode("#B5E196");
         yellow = Color.decode("#85BC5E");
         lightOrange = Color.decode("#85BC5E");
         grey = new Color(224,224,224);
         darkGreen =  Color.decode("#468615");
         people = 1;
         button = new JButton("Add Person");
         button2 = new JButton("Delete Person");
         editButton = new JButton("Edit Person");
         saveButton = new JButton("Save");
         loadButton = new JButton("Load");
         quitButton = new JButton("Quit");
         resetButtons();
         coord = new ArrayList<XY> (0);
         //XY c = new XY();
         
         j = new JOptionPane();
         
         //win = getContentPane();
         label = new JLabel();
         design = new JPanel();
         paint = new Painting();
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Family Tree");
        
        
        getContentPane().add(label, BorderLayout.CENTER);
        setSize(800, 800);
        setLocationRelativeTo(null);
        addMouseListener(new MouseClick());
        //
                
        
    
       // win.setLayout(new GridLayout(1,1));
       
         //Adds paint to container
    }
   
    private void createButton(){ //GROUPLAYOUT MANAGER
        
       // Container panel = getContentPane(); // content panel area where child components placed
        //panel.add(button,BorderLayout.LINE_START); 
        /*getContentPane returns contentPane object*/
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout); //NEED THIS
       // GroupLayout layout = new GroupLayout(window.getContentPane());
        layout.setAutoCreateContainerGaps(true); //Dont have to specify gaps
        layout.setAutoCreateGaps(true);
        
        
        layout.setVerticalGroup(  //setHorizontalGroup(Group group)
        
                layout.createSequentialGroup().addComponent(button)
                    .addComponent(editButton) .addComponent(button2)
                    .addComponent(saveButton) .addComponent(quitButton)
                    .addComponent(loadButton) 
        
        
        );
        
        layout.setHorizontalGroup(
                /*createParallelGroup() returns new parallel group*/
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(button) .addComponent(editButton) .addComponent(button2) 
                    .addComponent(saveButton).addComponent(loadButton)
                    .addComponent(quitButton))
                   
        
        
        );
       repaint();
    }
    
    private void buttonAction(){
        
        
        button.addActionListener(new ActionListener() { //Action listener for button
             @Override //
             public void actionPerformed(ActionEvent event){ //Want own action preformed
                   
                   if(whichButton == 1){
                       JOptionPane.showMessageDialog(null, "Button Already Activated. Click On A Node To Add A New Person", "Button Already Active", INFORMATION_MESSAGE);
                   }
                   
                   resetButtons();
                   activateButton(1);
                 //System.out.println(fname);
                 //setContentPane(new addPopUp().names);
// buttonPressed = true;
                // System.out.println(t.getDepth());
               //  button.setBackground(lightOrange);
                 if(t.getRoot() == null){
                 /*
                     t.addChild(null,p);*/
                     AddPopUp pop = new AddPopUp();
                     //JOptionPane.showInputDialog(pop.names);
                    // add(pop.names);
                    JOptionPane.showConfirmDialog(null, pop.names, "Adding First Person", JOptionPane.OK_CANCEL_OPTION);

                     fname = pop.firstName.getText();
                     lname = pop.lastName.getText();
                     t.addChild(null,new Person(fname,lname,-1,null,0));
                     repaint();
                     return;
                    // System.out.println("here");
                 }
                 
                    //System.out.println("NOT HERE PLEASEEEEE");  

                 
                 
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
    
    

    public void DrawChild(String parFir,String parLas){
      //  System.out.println("HERE");

        Person parent = t.findPerson(t.getRoot(),parFir,parLas);
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
        
        t.addChild(parent,new Person(fname,lname,parent.getDepth(),parent,1));
        repaint();
        return;       
        
        
    }
    
        public void DrawChildDelete(String parFir,String parLas){

        Person p = t.findPerson(t.getRoot(),parFir,parLas);
        AddPopUp pop = new AddPopUp();
                     //JOptionPane.showInputDialog(pop.names);
                    // add(pop.names);
      //  JOptionPane.showConfirmDialog(null, pop.names, "Deleting Child", JOptionPane.OK_CANCEL_OPTION);

        
      //  fname = pop.firstName.getText();
     //   lname = pop.lastName.getText();
        
        
        if(t.deleteChild(p) == 0){
            JOptionPane.showMessageDialog(null, "Cannot delete a node with dependencies", "Person has children", ERROR_MESSAGE);
            return;
        }
        repaint();
        return;       
        
        
    }
    
    
    //@Override
   /* public void paintComponent (Graphics g){ //Override paint method
         //g = window.getGraphics();
        super.paint(g);
       
        Graphics2D divider = (Graphics2D) g; //casting object
        
        //Point2D.Double point = new Point2D.Double(1.3,2.4);
       // point.setLocation(2, 3);
        
        divider.draw(new Line2D.Double(180, 0, 180, 650));
        divider.draw(new Ellipse2D.Double(300,40,150,150));
        
        
    }
    
    */
    
                                                     /*CLASSES*/
    

    private class Painting extends JPanel {
    
        

        Painting(){
            setBackground(lightgreen);
           // repaint();
        }

        
        /*
        OVALS (adding 1 to depth because lowest debth is 0)
        X Axis: (X JFrame length) / (# of nodes at certain debth + 1 * by counter for # of nodes in certain depth) ... Counter increments
        Y Axis: (Y JFrame length / Total Tree Depth + 2) * (Depth at position + 1)
       
        LINES TO PARENT (When access parent depth counter(in ArrayList) subtract 1 because it incremented before)
        X Axis: (X JFrame length) / (# of nodes at parent debth + 1 * by counter for # of nodes in parent depth) 
        Y Axis: (Y JFrame length / Total Tree Depth + 2) * (Depth at Parent Position plus 1)
        
        
        
        */
        private void PaintTree(Graphics2D g, Graphics2D l, Person iter, int depth,int iterCount){
        
            //g.draw(new Line2D.Double(280, 0, 280, 1000));
            //g.draw(new Line2D.Double(290, 0, 280, 1000));
            if(iter.getChildren().isEmpty()){ //BASE 1
                g.setColor(yellow);
                g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(depth+2))*(iter.getDepth()+1))+5, 80, 80); // The 620 and 180 are related to the JFrame
                g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(depth+2))*(iter.getDepth()+1))+5, 80, 80);
                l.setColor(Color.black);
                l.drawString(iter.getFirst(),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(depth+2))*(iter.getDepth()+1))+5+35);
                l.drawString(iter.getLast(),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(depth+2))*(iter.getDepth()+1))+5+48);
                coord.add(new XY()); //Store location of oval for mouse
               
                coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180; //All this to keep track if node is clicked 
                coord.get(coord.size()-1).yClick = ((785/(depth+2))*(iter.getDepth()+1))+5;
                coord.get(coord.size()-1).first = iter.getFirst();
                coord.get(coord.size()-1).last = iter.getLast();
               // System.out.println(coord.get(coord.size()-1).xClick + " " + coord.get(coord.size()-1).first); 
                //System.out.println(coord.get(coord.size()-1).xClick);
                
                if(iter.getParent() != null){
//                  g.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+11, ((785/(depth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*parentCopy.get(iter.getParent().getDepth()))+180+11, ((785/(depth+2))*(iter.getParent().getDepth()+1))+5));
                    l.setColor(Color.black);
                    l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+40, ((785/(depth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*((depthCopy.get(iter.getDepth()-1))-1))+180+40, ((785/(depth+2))*(iter.getParent().getDepth()+1))+86.5));    
                    //parentCopy.set(iter.getParent().getDepth(),parentCopy.get(iter.getParent().getDepth()) + 1);
                   
                }  
                
                //System.out.println(iter.getFirst() + " " + iter.getLast()+ " (Depth: " + iter.getDepth() + ")");
                //System.out.println("EXIT");
                depthCopy.set(iter.getDepth(),depthCopy.get(iter.getDepth()) + 1);
                //System.out.println(depthCopy);
                //System.out.println(depthCopy); 
                return;
            }    
            
            else{

                //System.out.println("HERE");
                for(int i = 0; i<iter.getChildren().size(); i++){

                    if(i == 0){
                        //System.out.println(iter.getFirst() + " " + iter.getLast() + " (Depth: " + iter.getDepth() + ")");
                        g.setColor(yellow);
                        g.drawOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(depth+2))*(iter.getDepth()+1))+5, 80, 80); //max y is 785 + 5
                        g.fillOval(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(depth+2))*(iter.getDepth()+1))+5, 80, 80);
                        l.setColor(Color.black);
                        l.drawString(iter.getFirst(),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(depth+2))*(iter.getDepth()+1))+5+35);
                        l.drawString(iter.getLast(),((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+18,((785/(depth+2))*(iter.getDepth()+1))+5+48);
                        coord.add(new XY());
                        coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180;
                        coord.get(coord.size()-1).yClick = ((785/(depth+2))*(iter.getDepth()+1))+5;
                        coord.get(coord.size()-1).first = iter.getFirst();
                        coord.get(coord.size()-1).last = iter.getLast();
                        
                  //      System.out.println(coord.get(coord.size()-1).xClick + " " + coord.get(coord.size()-1).first);        

                        if(iter.getParent() != null){
                           // g.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+11, ((785/(depth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*parentCopy.get(iter.getParent().getDepth()))+180+11, ((785/(depth+2))*(iter.getParent().getDepth()+1))+5));
                            l.setColor(Color.black);
                            l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180+40, ((785/(depth+2))*(iter.getDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getDepth())+1))*((depthCopy.get(iter.getDepth()-1))-1))+180+40, ((785/(depth+2))*(iter.getParent().getDepth()+1))+86.5));                            
                                
                        }
                        depthCopy.set(iter.getDepth(),depthCopy.get(iter.getDepth()) + 1);
                    }
                    
//g.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getDepth())+1))*depthCopy.get(iter.getDepth()))+180, ((785/(depth+2))*(iter.getDepth()+1)), ((620/(t.getDepthTracker().get(iter.getDepth())+2))*depthCopy.get(i+1))+180,  ((785/(depth+3))*(i+1))+5));
                //  System.out.println(t.getDepthTracker());      
                    PaintTree(g,l,iter.getChildren().get(i),depth,iterCount++); //Recursion
                    
//parentCopy.set(iter.getParent().getDepth(),parentCopy.get(iter.getParent().getDepth()) + 1);
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
            //divider.setStroke(new BasicStroke(5));
            divider.draw(new Line2D.Double(180, 0, 180, 1000));
          //  drawPerson();
            
           // oval.setColor(yellow);
            
           // line.setColor(Color.black);
           // divider.setFont(border);
            depthCopy = new ArrayList<Integer> (0);
            parentCopy = new ArrayList<Integer> (0);
            coord = new ArrayList<XY> (0);
            //coord = new ArrayList<Float> (0);
            
            for(int i = 0; i < t.getDepthTracker().size(); i++){
                
                depthCopy.add(1);
                parentCopy.add(1);
            }
            
            if(t.getRoot() != null)
                PaintTree(oval,line,t.getRoot(),t.getDepth(),0);
            
            
           /*     
             if(t.getDepth() == 0)
               oval.drawOval(380, 5, 25, 25);
            
             if(t.getDepth() == 1)
               oval.drawOval(580, 5, 25, 25);
            */

        }
        




    
    }
    

    
    public class MouseClick extends MouseAdapter{
        
        int x,y;
        
        @Override
        public void mouseClicked(MouseEvent me){
            
            x = me.getX();
            y = me.getY();
            
            if(buttonPressed || button2Pressed){ //so you can't add while another button active
                clickPerson();
            }

            
           // System.out.println("screen(X,Y) = " + x + "," + y);
        }
        
        public void clickPerson(){
            Boolean next = false;
            for(int i = 0; i < coord.size(); i++){
              //  System.out.println(coord.get(i).xClick + ","+ coord.get(i).yClick  + "==" + t +"," +y);  
       //         DrawChild(coord.get(i).first,coord.get(i).last);
                //System.out.println(coord.get(i).xClick + "=" + "x");
                if(coord.get(i).xClick == x && coord.get(i).yClick == x ){
                   if(buttonPressed)
                       DrawChild(coord.get(i).first,coord.get(i).last);
                   else if(button2Pressed)
                       DrawChildDelete(coord.get(i).first,coord.get(i).last);
                    return;
                }

                for(int j = 0; j < 30; j++){
                    //System.out.println("HERE");
                    
                  //  System.out.println((coord.get(i).xClick + 47 + j) + " == " + (x));
                    if(coord.get(i).xClick  +47 + j == x || coord.get(i).xClick + 47 - j == x  ){
                        //DrawChild(coord.get(i).first,coord.get(i).last);
                        next = true;
                     //   System.out.println("HERE");
                    }
                    
                    if(next == true){
                        
                      // System.out.println(coord.get(i).yClick + "=" + "y");
                        for(int k = 0; k < 30; k++){
//System.out.println((coord.get(i).yClick +69+ k) + " == " + (y));
                            if(coord.get(i).yClick  +69+ k == y || coord.get(i).yClick  +69 - k == y  ){
                                if(buttonPressed)
                                    DrawChild(coord.get(i).first,coord.get(i).last);
                                 else if(button2Pressed)
                                    DrawChildDelete(coord.get(i).first,coord.get(i).last);                                
                                return;
                            }
                               // return;
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
        
        JPanel names = new JPanel();
        JTextField firstName = new JTextField(10);
        JTextField lastName = new JTextField(10);
        JLabel firstLabel = new JLabel("First Name");
        JLabel lastLabel = new JLabel("Last Name");
        
        AddPopUp(){
            names.add(firstLabel);
            names.add(firstName);
            
            names.add(lastLabel);
            names.add(lastName);
        
            //.showConfirmDialog(null, p, "Family and first name : ", JOptionPane.OK_CANCEL_OPTION);


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
        
            //JOptionPane.showConfirmDialog(null, p, "Family and first name : ", JOptionPane.OK_CANCEL_OPTION);


        }
        
        
    }     
        
        
        
        
        
        
    
}    
    

