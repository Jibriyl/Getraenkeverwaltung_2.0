package Getraenkehandel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.awt.BorderLayout;

public class Verkaufsfenster extends JFrame{

    private static final DecimalFormat runden = new DecimalFormat("0.00");

    private Verkaufsfenster(Color schriftcolour, Color coolcolour){
        this.setSize(240,360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Kassenzettel"); //Sets Title of Frame
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(schriftcolour); //Change Colour of background
        this.setLayout(new BorderLayout()); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms

        JButton bestaetigen = new MyButton(coolcolour, schriftcolour, "OK");
        bestaetigen.addActionListener(e -> this.dispose());
        this.add(bestaetigen, BorderLayout.PAGE_END);
    }

    public static void getreankverkaufen(Getraenk getraenk, int menge, Color schriftcolour, Color coolcolour, Kasse kasse){
        Verkaufsfenster kassenzettel = new Verkaufsfenster(schriftcolour, coolcolour);

        JPanel anzeige = new JPanel();
        anzeige.setBackground(coolcolour);
        anzeige.setLayout(null);
        anzeige.setPreferredSize(new Dimension(240, 330));
        kassenzettel.add(anzeige, BorderLayout.PAGE_START);

        if(getraenk.bestandabfrage(menge)){
            double gesamtpreis = getraenk.verkaufen(menge);
            kasse.verkauf(gesamtpreis);

            JLabel preis = new MyLabel(schriftcolour, "Name: " + getraenk.getName(), 20, 0, 50, 240, 50);
            JLabel verkaufsmenge = new MyLabel(schriftcolour, "Verkaufsmenge: " + menge, 20, 0, 110, 240, 50);
            JLabel verkaufspreis = new MyLabel(schriftcolour, "Verkaufspreis " + getraenk.getpreis() + "€", 20, 0, 170, 240, 50);
            JLabel gesamtpreisanzeige = new MyLabel(schriftcolour, "Gesamtpreis: " + runden.format(gesamtpreis) + "€", 20, 0, 230, 240, 50);
    
            anzeige.add(preis);
            anzeige.add(verkaufspreis);
            anzeige.add(gesamtpreisanzeige);
            anzeige.add(verkaufsmenge);
            
        }
        else{
            kassenzettel.setSize(600,120);
            anzeige.setPreferredSize(new Dimension(600, 120));
            JLabel nichtgenug = new MyLabel(schriftcolour, "Leider sind nicht genug " + getraenk.getName() + " im Bestand vorhanden!", 20, 0, 30, 600, 50);
            anzeige.add(nichtgenug);
        }
        kassenzettel.setVisible(true);
    }

    public static void snackverkaufen(Snack snack, int menge, Color schriftcolour, Color coolcolour, Kasse kasse){
        Verkaufsfenster kassenzettel = new Verkaufsfenster(schriftcolour, coolcolour);

        JPanel anzeige = new JPanel();
        anzeige.setBackground(coolcolour);
        anzeige.setLayout(null);
        anzeige.setPreferredSize(new Dimension(240, 330));
        kassenzettel.add(anzeige, BorderLayout.PAGE_START);

        if(snack.bestandabfrage(menge)){
            double gesamtpreis = snack.verkaufen(menge);
            kasse.verkauf(gesamtpreis);

            JLabel preis = new MyLabel(schriftcolour, "Name: " + snack.getName(), 20, 0, 50, 240, 50);
            JLabel verkaufsmenge = new MyLabel(schriftcolour, "Verkaufsmenge: " + menge, 20, 0, 110, 240, 50);
            JLabel verkaufspreis = new MyLabel(schriftcolour, "Verkaufspreis " + snack.getpreis() + "€", 20, 0, 170, 240, 50);
            JLabel gesamtpreisanzeige = new MyLabel(schriftcolour, "Gesamtpreis: " + runden.format(gesamtpreis) + "€", 20, 0, 230, 240, 50);
    
            anzeige.add(preis);
            anzeige.add(verkaufspreis);
            anzeige.add(gesamtpreisanzeige);
            anzeige.add(verkaufsmenge);
        }
        else{
            kassenzettel.setSize(600,120);
            anzeige.setPreferredSize(new Dimension(600, 120));
            JLabel nichtgenug = new MyLabel(schriftcolour, "Leider sind nicht genug " + snack.getName() + " im Bestand vorhanden!", 20, 0, 30, 600, 50);
            anzeige.add(nichtgenug);
        }
        kassenzettel.setVisible(true);
    }
}
