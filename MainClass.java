package c3654717;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import uk.ac.leedsbeckett.oop.LBUGraphics;

public class MainClass extends LBUGraphics
{
        public static void main(String[] args)
        {
                new MainClass(); 
                GraphicsSystem graphics = new GraphicsSystem();
        }

        public MainClass()
        {
                JFrame MainFrame = new JFrame();            
                MainFrame.setLayout(new FlowLayout());     
                MainFrame.add(this);                       
                MainFrame.setSize(850,450);                
                MainFrame.setVisible(true);                
                                                    
                                                                                       //call the LBUGraphics about method to display version information.
        }

        public void processCommand(String command)              
        {
              
        }
}
