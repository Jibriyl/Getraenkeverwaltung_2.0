import javax.swing.JFrame;
import java.awt.Color;

public class Hauptfenster extends JFrame{
    
    Hauptfenster(){
        Color coolcolour = new Color(210,190,255); //Create new Colour to be used, RGB value
        //Color coolcolour2 = new Color(0x123456); //Create new Colour to be used, Hexadecimal value
            
        this.setSize(1280,720); //Sets Frame Size x-dimension y-dimension
        this.setTitle("Getränkeverwaltung"); //Sets Title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the application on close / Default is only hiding it
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(coolcolour); //Change Colour of background

        //this.setUndecorated(true);
        //this.setOpacity(0.95f);


        this.setVisible(true); //Makes the Window Visible
    }

}
