package Getraenkehandel.guiklassen;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Getraenkehandel.Kasse;
import Getraenkehandel.Produkt;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.GridLayout;


public class Verkaufsfenster extends JFrame{

    private static final DecimalFormat runden = new DecimalFormat("0.00");
    static double gesamtpreis;
    static HashMap<Produkt, Integer> warenkorb;

    private Verkaufsfenster(Color schriftcolour, Color coolcolour, int lenght){
        this.setSize(360, 60 + lenght);
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

    public static void verkaufen(HashMap<Produkt, Integer> warenkorb, Color schriftcolour, Color coolcolour, Kasse kasse, Hauptfenster hauptfenster){
        if (warenkorb.size() > 0){
            int laenge = 20 + warenkorb.size() * 30;
            gesamtpreis = 0;

            Verkaufsfenster kassenzettel = new Verkaufsfenster(schriftcolour, coolcolour, laenge);
    
            JPanel anzeige = new JPanel();
            anzeige.setBackground(coolcolour);
            GridLayout grid = new GridLayout(1 + warenkorb.size(), 4, 0, 0);
            anzeige.setLayout(grid);
            anzeige.setPreferredSize(new Dimension(240, laenge));
            kassenzettel.add(anzeige, BorderLayout.PAGE_START);

            //Label für die Kategorien
            JLabel anzeigelabel1 = new MyLabel(schriftcolour,"Name:",15);
            anzeigelabel1.setHorizontalAlignment(JLabel.CENTER);
            anzeige.add(anzeigelabel1);
            JLabel anzeigelabel2 = new MyLabel(schriftcolour,"Anzahl:", 15);
            anzeigelabel2.setHorizontalAlignment(JLabel.CENTER);
            anzeige.add(anzeigelabel2);
            JLabel anzeigelabel3 = new MyLabel(schriftcolour,"Preis:", 15);
            anzeigelabel3.setHorizontalAlignment(JLabel.CENTER);
            anzeige.add(anzeigelabel3);
            JLabel anzeigelabel4 = new MyLabel(schriftcolour,"Gesamtpreis:", 15);
            anzeigelabel4.setHorizontalAlignment(JLabel.CENTER);
            anzeige.add(anzeigelabel4);
            //Schleife die alle artikel im warenkorb durch geht
            warenkorb.forEach((k,v) -> {
                //Erstellt für jeden Pordukt im Warenkorb 4 Label zur anzeige, muss das warenkorbJpanel jedesmal aktuallisieren  
                //Verkauft jedes der Produkte
                double produktpreis = k.verkaufen(v);
                JLabel label1 = new MyLabel(schriftcolour, k.getName(), 15);
                label1.setHorizontalAlignment(JLabel.CENTER);
                anzeige.add(label1);
                JLabel label2 = new MyLabel(schriftcolour, ""+ warenkorb.get(k), 15);
                label2.setHorizontalAlignment(JLabel.CENTER);
                anzeige.add(label2);
                JLabel label3 = new MyLabel(schriftcolour, k.getpreis() + " €", 15);
                label3.setHorizontalAlignment(JLabel.CENTER);
                anzeige.add(label3);
                JLabel label4 = new MyLabel(schriftcolour, runden.format(produktpreis) + " €", 15);
                label4.setHorizontalAlignment(JLabel.CENTER);
                anzeige.add(label4);
                gesamtpreis += produktpreis;
            });
            JPanel preispanel = new MyPanel(coolcolour, 360, 30);
            preispanel.setLayout(new BorderLayout());
            JLabel gesamtpreislabel = new MyLabel(schriftcolour, "Gesamtpreis: " + runden.format(gesamtpreis), 20);
            gesamtpreislabel.setHorizontalAlignment(JLabel.CENTER);
            preispanel.add(gesamtpreislabel, BorderLayout.CENTER);
            kassenzettel.add(preispanel, BorderLayout.CENTER);
            kassenzettel.setVisible(true);
            hauptfenster.clearwarenkorb();
            hauptfenster.refresh();
            kasse.verkauf(gesamtpreis);
        }
    }
}
