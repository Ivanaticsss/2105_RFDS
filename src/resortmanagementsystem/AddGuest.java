package resortmanagementsystem;

/**
 *
 * @author Valiente, Theresa
 */

import javax.swing.*;
import java.awt.*;

public class AddGuest extends JFrame {
    AddGuest(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        
        
        setBounds(350, 200, 800, 550);
        setVisible (true);
    }
    
    public static void main (String[] args) {
        new AddGuest();
        
    }
}
