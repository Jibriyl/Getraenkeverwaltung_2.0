package Getraenkehandel.guiklassen;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Getraenkehandel.Getraenk;
import Getraenkehandel.Snack;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;


public class Bestangerhoehen extends JFrame implements ActionListener{
    
    private int i;
    private JButton bestaetigen;
    private JFormattedTextField anzahlfeld;
    private String typ;
    private HashMap<String, Getraenk> getraenke;
    private HashMap<String, Snack> snacks;
    private JComboBox getraenkebox;
    private JComboBox snackbox;


    public Bestangerhoehen(Color schriftcolor){
        
        this.setSize(420, 360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Kassenzettel"); //Sets Title of Frame
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(schriftcolor); //Change Colour of background
        this.setLayout(new BorderLayout()); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms

        bestaetigen = new MyButton(new Color(70, 70, 70), schriftcolor, "OK");
        bestaetigen.addActionListener(e -> this.dispose());
        this.add(bestaetigen, BorderLayout.PAGE_END);
    }

    public void erhoehen(HashMap<String, Getraenk> getraenke, HashMap<String, Snack> snacks, Color schriftcolor, Color backgroundcolor, String typ){
        Bestangerhoehen fenster = new Bestangerhoehen(schriftcolor);
        fenster.setVisible(true);
        i = 0;
        this.typ = typ;
        this.getraenke = getraenke;
        this.snacks = snacks;
        if(typ == "getraenk"){
            //Panel als Hintergrund
            JPanel mainpanel = new MyPanel(backgroundcolor, 420, 720);
            mainpanel.setLayout(null);

            fenster.add(mainpanel, BorderLayout.CENTER);
            getraenkebox = new JComboBox();
            getraenkebox.setBounds(160, 50, 100, 30);

            anzahlfeld = new JFormattedTextField(NumberFormat.getNumberInstance());
            anzahlfeld.setBounds(160, 150, 100, 30);

            //Füg jedes Getränk in die Kombobox hinzu
            getraenke.forEach((k,v) -> {
                getraenkebox.addItem(k);
                i += 1;
            });
            mainpanel.add(getraenkebox);
            mainpanel.add(anzahlfeld);

        }
        else{
            //Panel als Hintergrund
            JPanel mainpanel = new MyPanel(new Color(200,200,200), 420, 720);
            mainpanel.setLayout(null);
            fenster.add(mainpanel, BorderLayout.CENTER);
            snackbox = new JComboBox();
            snackbox.setBounds(160, 50, 100, 30);

            anzahlfeld = new JFormattedTextField(NumberFormat.getNumberInstance());
            anzahlfeld.setBounds(160, 150, 100, 30);

            //Füg jeden Snack in die Kombobox hinzu
            snacks.forEach((k,v) -> {
                snackbox.addItem(k);
                i += 1;
            });
            mainpanel.add(snackbox);
            mainpanel.add(anzahlfeld);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bestaetigen){
            int anzahl = ((Number) anzahlfeld.getValue()).intValue();
            if (typ == "getraenk"){
                Getraenk selectedgetraenk = getraenke.get(getraenkebox.getSelectedIndex());
                System.out.println(getraenkebox.getSelectedIndex());
                selectedgetraenk.bestanderhoehen(anzahl);
            }

        }
    }
}
