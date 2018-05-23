/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package familytree;



import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JComponent;



/**
 *
 * @author Vinod
 */
public class Ui extends JFrame {
    
    Ui(){    
        createWindow();
        createButton();
    }
    
    private void createWindow(){
        setTitle("Family Tree");
        setSize(500, 500);
        setLocationRelativeTo(null); //Centers Window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createButton(){
        JButton okayButton = new JButton("Okay");
        JButton d = new JButton("No");
        
        okayButton.addActionListener(new ActionListener() { //Action listener for button
             @Override //
             public void actionPerformed(ActionEvent event){         
                 System.exit(0);
             }            
        });   
        buttonLayout(okayButton);
        buttonLayout(d);
    }
    
    private void buttonLayout(JComponent ... arg){ //any number of JComponent  
        Container pane = getContentPane(); // content pane area where child components placed
        GroupLayout gl = new GroupLayout(pane); //Layout manager (inviz)
        
        pane.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));  
    }
}
