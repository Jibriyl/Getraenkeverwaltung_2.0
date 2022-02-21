package Getraenkehandel;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Verkaufsfenster extends JFrame{

    public Verkaufsfenster(){
        this.setSize(360,240);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Kassenzettel"); //Sets Title of Frame
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(Hauptfenster.coolcolour3); //Change Colour of background
        this.setLayout(new BorderLayout()); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setOpacity(0.98f);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms
    }

}
