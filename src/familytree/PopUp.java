/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package familytree;

import javax.swing.JFrame;

/**
 *
 * @author Vinod
 */
public class PopUp extends JFrame {
    
    private JFrame popUp;
   
    PopUp(){
        createPopUp();
        getPopUp();
    }
    
    PopUp(String first, String last){ //Overloading for edit popup
        createPopUp();
        getPopUp();
    }
    
    private void createPopUp(){
       popUp = new JFrame(); 
       popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       popUp.setTitle("Pop");
       popUp.setSize(500, 600);
       popUp.setLocationRelativeTo(null);
       popUp.setVisible(false);
    }
    private void createPopUp(String first, String last){ //Overloading for edit popup
        
       popUp = new JFrame(); 
       popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       popUp.setTitle("Pop");
       popUp.setSize(500, 600);
       popUp.setLocationRelativeTo(null);
       popUp.setVisible(false);
             
    }
    
    public JFrame getPopUp(){
        return popUp;
    }
    
}
