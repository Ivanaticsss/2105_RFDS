
package resortmanagementsystem;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener{
    
    Dashboard(){
        setTitle("Serenity Cove Resort Front Desk");
        
        setBounds(0, 0, 1550, 1000);
        
        setLayout(null);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/thirdbg3.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 1000);
        add(image);
        
        /*JLabel text = new JLabel("THE SERENITY GROUP WELCOMES YOU");
        text.setBounds(400, 80, 1000, 50);
        text.setFont(new Font("Tahoma", Font.PLAIN, 46));
        text.setForeground(Color.WHITE);
        image.add(text);
        */
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1550, 50);
        mb.setBackground(Color.decode("#64331f"));
        image.add(mb);
        
        JMenu resort = new JMenu("RESORT MANAGEMENT");
        resort.setForeground(Color.decode("#e9d29f"));
        mb.add(resort);
        
        JMenuItem reception = new JMenuItem("RECEPTION");
        reception.addActionListener(this);
        resort.add(reception);
        
        //admin menu bar
        JMenu admin = new JMenu("ADMIN");
        admin.setForeground(Color.WHITE);
        mb.add(admin);
        
        JMenuItem addRooms = new JMenuItem("ADD ROOMS");
        addRooms.addActionListener(this);
        admin.add(addRooms);
        
        JMenuItem addCottage = new JMenuItem("ADD COTTAGES");
        addCottage.addActionListener(this);
        admin.add(addCottage);
        
        JMenuItem addPool = new JMenuItem("ADD POOLS");
        addPool.addActionListener(this);
        admin.add(addPool);
        
        JMenuItem addService = new JMenuItem("ADD SERVICES");
        addService.addActionListener(this);
        admin.add(addService);
        

        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("ADD ROOMS")){
            new AddRooms();
        }
        else if (ae.getActionCommand().equals("ADD COTTAGES")){
            new AddCottages();
        }
        else if (ae.getActionCommand().equals("ADD POOLS")){
            new AddPools();
        }
        else if (ae.getActionCommand().equals("ADD SERVICES")){
            new AddServices();
        }
        else if (ae.getActionCommand().equals("RECEPTION")){
            new Reception();
        }
        
        
        
    
    }
    public static void main(String[] args){
        new Dashboard();
    }
    
}
