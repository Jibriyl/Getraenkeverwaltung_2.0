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


public class Bestanderhoehen extends JFrame implements ActionListener{
    
    private JButton bestaetigen;
    private JFormattedTextField anzahlfeld;
    private String typ;
    private HashMap<String, Getraenk> getraenke;
    private HashMap<String, Snack> snacks;
    private JComboBox getraenkebox;
    private JComboBox snackbox;
    Hauptfenster haupt;

    public Bestanderhoehen(HashMap<String, Getraenk> getraenke, HashMap<String, Snack> snacks, Color schriftcolor, Color backgroundcolor, String typ, Hauptfenster haupt){
        
        this.setSize(420, 360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Kassenzettel"); //Sets Title of Frame
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(schriftcolor); //Change Colour of background
        this.setLayout(new BorderLayout()); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms

        bestaetigen = new MyButton(new Color(70, 70, 70), schriftcolor, "OK");
        bestaetigen.addActionListener(this);
        bestaetigen.setBackground(new Color(120, 120, 120));
        bestaetigen.setHorizontalTextPosition(JButton.CENTER);
        this.add(bestaetigen, BorderLayout.PAGE_END);

        this.haupt = haupt;
        this.typ = typ;
        this.getraenke = getraenke;
        this.snacks = snacks;
        if(typ == "getraenk"){
            //Erzeugen des Mainpanels
            JPanel mainpanel = new MyPanel(backgroundcolor, 420, 720);
            mainpanel.setLayout(null);

            this.add(mainpanel, BorderLayout.CENTER);
            getraenkebox = new JComboBox();
            getraenkebox.setBounds(160, 50, 100, 30);

            //Erstellen Inputfeld
            anzahlfeld = new JFormattedTextField(NumberFormat.getNumberInstance());
            anzahlfeld.setText("0");
            anzahlfeld.setColumns(2);
            anzahlfeld.setBounds(160, 150, 100, 30);

            //Füg jedes Getränk in die Kombobox hinzu
            getraenke.forEach((k,v) -> {
                getraenkebox.addItem(k);
            });
            mainpanel.add(getraenkebox);
            mainpanel.add(anzahlfeld);

        }
        else{
            //Panel als Hintergrund
            JPanel mainpanel = new MyPanel(new Color(200,200,200), 420, 720);
            mainpanel.setLayout(null);
            this.add(mainpanel, BorderLayout.CENTER);
            snackbox = new JComboBox();
            snackbox.setBounds(160, 50, 100, 30);

            anzahlfeld = new JFormattedTextField(NumberFormat.getNumberInstance());
            anzahlfeld.setText("0");
            anzahlfeld.setColumns(2);
            anzahlfeld.setBounds(160, 150, 100, 30);

            //Füg jeden Snack in die Kombobox hinzu
            snacks.forEach((k,v) -> {
                snackbox.addItem(k);
            });
            mainpanel.add(snackbox);
            mainpanel.add(anzahlfeld);
        }
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Wenn Button gedrückt wird
        if(e.getSource() == bestaetigen){
            if(anzahlfeld.getValue() != null){
                //Nimmt die eigegebene anzahl aus dem Eingabefeld
                int anzahl = ((Number) anzahlfeld.getValue()).intValue();
                if (anzahl >= 0){
                    //Check ob getränk oder snack
                    if (typ == "getraenk"){
                        //Nimmt aus der Combobox was ausgewählt ist
                        Getraenk selectedgetraenk = getraenke.get(getraenkebox.getSelectedItem());
                        selectedgetraenk.bestanderhoehen(anzahl);
                    }
                    else{
                        Snack selectedsnack = snacks.get(snackbox.getSelectedItem());
                        selectedsnack.bestanderhoehen(anzahl);
                    }
                }
                haupt.refresh();
                this.dispose();
            }
        }
    }
}
