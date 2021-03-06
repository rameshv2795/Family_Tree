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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Vinod
 */
public class FamilyTreeWindow extends JFrame{
    
    private JPanel design;
    private Painting paint;
    private FamilyTree t;
    private JLabel label;
    private Graphics2D draw;
    private JFrame window,popUp;
    private JTextArea search_in;
    private JButton button,button2,saveButton,loadButton,quitButton,editButton, searchButton;
    private Boolean buttonPressed,button2Pressed,savePressed,loadPressed,quitPressed,editPressed;
    private JOptionPane j;
    private String fname,lname,pf,pl,search_input = "";
    private int maxDepth,spot,people,whichButton;
    private Person p,q,r,s,sonS,sonS2,sonR,sson,x,y,z;
    private Color lightgreen,yellow,lightOrange,grey, darkGreen, brown, lightBlue;
    private ArrayList<Integer> depthCopy,parentCopy;
    private ArrayList<XY> coord;
    private Font border;
    private Dimension screenSize;
    
    FamilyTreeWindow(BaseTree tree){ 
        t = (FamilyTree) tree;
        initial();          
        createWindow();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setBounds(0,0,screenSize.width/2, screenSize.height);
        this.add(search_in);
        this.add(searchButton);
        setVisible(true);
    }
    
    private void initial(){
        // http://paletton.com/#uid=12C0u0kllllaFw0g0qFqFg0w0aF
        depthCopy = new ArrayList<Integer> (0);
        parentCopy = new ArrayList<Integer> (0);
        lightgreen = Color.decode("#B5E196");
        yellow = Color.decode("#85BC5E");
        lightOrange = Color.decode("#85BC5E");
        grey = new Color(224,224,224);
        darkGreen =  Color.decode("#468615");
        brown = Color.decode("#784212");
        lightBlue = Color.decode("#85C1E9");
        people = 1;
        button = new JButton("Add Person");
        button2 = new JButton("Delete Person");
        editButton = new JButton("Edit Person");
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
        
        /*search bar*/
        search_in = new JTextArea("search here");
        Dimension search_d = new Dimension(140,30);
        Dimension search_button_d = new Dimension(60,30);
        search_in.setSize(search_d);
        search_in.setLocation(5, 300);
        searchButton = new JButton("Find");
        searchButton.setSize(search_button_d);
        searchButton.setLocation(150, 300);
        
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
        searchAction();
        //saveAction();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Family Tree");
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
            layout.createSequentialGroup()
                .addComponent(button)
                .addComponent(editButton) 
                .addComponent(button2).addComponent(saveButton) 
                .addComponent(loadButton).addComponent(quitButton)
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
        
        button.addActionListener(new ActionListener() { //Action listener for button
            @Override //
            public void actionPerformed(ActionEvent event){ //Want own action preformed
 
                label.getRootPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon(FamilyTree.class.getResource("img/add_icon.png")).getImage(),
                    new Point(0,0),"add icon"));
               
                if(whichButton == 1){
                    JOptionPane.showMessageDialog(null, "Button already activated. Click on a node to add a new person", "Button Already Active", INFORMATION_MESSAGE);
                }
                resetButtons();
                activateButton(1);
                
                if(t.getRoot() == null){
                    AddPopUp pop = new AddPopUp();
                    JOptionPane.showConfirmDialog(null, pop.names, "Adding First Person", JOptionPane.OK_CANCEL_OPTION);

                    fname = pop.firstName.getText();
                    lname = pop.lastName.getText();
                    t.addChild(null,new Person(fname,lname,-1,null,0));
                    repaint();
                    return;
              }
            }     
        }); 
    }
    private void button2Action(){
        
        button2.addActionListener(new ActionListener() {
            @Override //
             public void actionPerformed(ActionEvent event){
                label.getRootPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon(FamilyTree.class.getResource("img/trash_icon.png")).getImage(),
                    new Point(0,0),"delete icon"));                 
                   resetButtons();
                   activateButton(2);
             }
        });
    }
    
    private void editAction(){
        
        editButton.addActionListener(new ActionListener() {            
            @Override //
             public void actionPerformed(ActionEvent event){
                label.getRootPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon(FamilyTree.class.getResource("img/edit_icon.png")).getImage(),
                    new Point(0,0),"edit icon"));                 
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
                           Logger.getLogger(FamilyTreeWindow.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   
             repaint();
             return;
             }           
        });
    }        
    
    private void searchAction(){
        
        searchButton.addActionListener(new ActionListener(){
            
            @Override //
             public void actionPerformed(ActionEvent event){
                   resetButtons();
                   
                   search_input = search_in.getText().trim();
                   System.out.println(search_input);
                   repaint();
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
            JOptionPane.showMessageDialog(null, "Person already exists", "Duplicate Person", ERROR_MESSAGE);
            return;
        }
        
        t.addChild(parent,new Person(fname.trim(),lname.trim(),parent.getMaxDepth(),parent,1));
        repaint();
        
        return;       
    }
    
    public void DrawChildDelete(String parFir,String parLas){

        Person p = t.findPerson(t.getRoot(),parFir,parLas);
        AddPopUp pop = new AddPopUp();

        if(t.deleteChild(p) == 0){
            JOptionPane.showMessageDialog(null, "Cannot delete a node with dependencies", "Person has children", ERROR_MESSAGE);
            return;
        }
        repaint();
        return;         
    }
    
    public void DrawChildEdit(String parFir,String parLas){

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
        
        repaint();
        return;       
    }    

    private class Painting extends JPanel {

        Painting(){
            setBackground(lightgreen);
           // repaint();
        }

        /*
        OVALS (adding 1 to maxDepth because lowest debth is 0)
        X Axis: (X JFrame length) / (# of nodes at certain debth + 1 * by counter for # of nodes in certain maxDepth) ... Counter increments
        Y Axis: (Y JFrame length / Total Tree maxDepth + 2) * (maxDepth at position + 1)
       
        LINES TO PARENT (When access parent maxDepth counter(in ArrayList) subtract 1 because it incremented before)
        X Axis: (X JFrame length) / (# of nodes at parent debth + 1 * by counter for # of nodes in parent maxDepth) 
        Y Axis: (Y JFrame length / Total Tree maxDepth + 2) * (maxDepth at Parent Position plus 1)
        */
        private void PaintTree(Graphics2D g, Graphics2D l, Person iter, int maxDepth,int iterCount){

            if(iter.getChildren().isEmpty()){ //BASE 1
                System.out.println(search_input + "==" +iter.getFirst() );
                if(search_input.equals(iter.getFirst()) || search_input.equals(iter.getLast())
                   || search_input.equals(iter.getFirst() + " " + iter.getLast())){
                    g.setColor(lightBlue);
                }
                else{
                    g.setColor(yellow);
                }
                g.drawOval(((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180, ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5, 80, 80); // The 620 and 180 are related to the JFrame
                g.fillOval(((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180, ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5, 80, 80);
                l.setColor(Color.black);
                l.drawString(iter.getFirst(),((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180+18,((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5+35);
                l.drawString(iter.getLast(),((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180+18,((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5+48);
                coord.add(new XY()); //Store location of oval for mouse
                coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180; //All this to keep track if node is clicked 
                coord.get(coord.size()-1).yClick = ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5;
                coord.get(coord.size()-1).first = iter.getFirst();
                coord.get(coord.size()-1).last = iter.getLast();

                if(iter.getParent() != null){
                    l.setColor(brown);
                    l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180+40, ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getMaxDepth())+1))*((depthCopy.get(iter.getMaxDepth()-1))-1))+180+40, ((785/(maxDepth+2))*(iter.getParent().getMaxDepth()+1))+86.5));    
                }  
                depthCopy.set(iter.getMaxDepth(),depthCopy.get(iter.getMaxDepth()) + 1);

                return;
            }    
            
            else{
                for(int i = 0; i<iter.getChildren().size(); i++){

                    if(i == 0){
                        if(search_input.equals(iter.getFirst()) || search_input.equals(iter.getLast()) 
                          || search_input.equals(iter.getFirst() + " " + iter.getLast())){
                            g.setColor(lightBlue);
                        }
                        else{
                            g.setColor(yellow);
                        }
                        g.drawOval(((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180, ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5, 80, 80); //max y is 785 + 5
                        g.fillOval(((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180, ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5, 80, 80);
                        l.setColor(Color.black);
                        l.drawString(iter.getFirst(),((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180+18,((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5+35);
                        l.drawString(iter.getLast(),((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180+18,((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5+48);
                        coord.add(new XY());
                        coord.get(coord.size()-1).xClick = ((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180;
                        coord.get(coord.size()-1).yClick = ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5;
                        coord.get(coord.size()-1).first = iter.getFirst();
                        coord.get(coord.size()-1).last = iter.getLast();

                        if(iter.getParent() != null){                      
                            l.setColor(brown);
                            l.draw(new Line2D.Double(((620/(t.getDepthTracker().get(iter.getMaxDepth())+1))*depthCopy.get(iter.getMaxDepth()))+180+40, ((785/(maxDepth+2))*(iter.getMaxDepth()+1))+5, ((620/(t.getDepthTracker().get(iter.getParent().getMaxDepth())+1))*((depthCopy.get(iter.getMaxDepth()-1))-1))+180+40, ((785/(maxDepth+2))*(iter.getParent().getMaxDepth()+1))+86.5));                                                         
                        }
                        depthCopy.set(iter.getMaxDepth(),depthCopy.get(iter.getMaxDepth()) + 1);
                    }
                    PaintTree(g,l,iter.getChildren().get(i),maxDepth,iterCount++); 
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
                PaintTree(oval,line,t.getRoot(),t.getMaxDepth(),0);
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
                    if(buttonPressed)
                        DrawChild(coord.get(i).first,coord.get(i).last);
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
        
        AddPopUp(String f, String l){ //Overload for edit popup
            names.add(firstLabel);
            names.add(firstName);
            this.firstName.setText(f);
            names.add(lastLabel);
            names.add(lastName);
            this.lastName.setText(l);
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