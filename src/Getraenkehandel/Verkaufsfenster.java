package Getraenkehandel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import java.awt.BorderLayout;

public class Verkaufsfenster extends JFrame{

    private Verkaufsfenster(Color schriftcolour, Color coolcolour){
        this.setSize(240,360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Kassenzettel"); //Sets Title of Frame
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(schriftcolour); //Change Colour of background
        this.setLayout(new BorderLayout()); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setOpacity(0.98f);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms

        JButton bestaetigen = new MyButton(Hauptfenster.coolcolour5, Hauptfenster.coolcolour3, "OK");
        bestaetigen.addActionListener(e -> this.dispose());
        this.add(bestaetigen, BorderLayout.PAGE_END);

    }

    public static void getreankverkaufen(Getraenk getraenk, int menge, Color schriftcolour, Color coolcolour){
        Verkaufsfenster kassenzettel = new Verkaufsfenster(schriftcolour, coolcolour);

        JPanel anzeige = new JPanel();
        kassenzettel.add(anzeige, BorderLayout.PAGE_START);


        JLabel preis = new MyLabel(schriftcolour, "Name: " + getraenk.getName(), 20, 0, 30, 240, 50);
        anzeige.add(preis);

        kassenzettel.setVisible(true);

        

    }
}
