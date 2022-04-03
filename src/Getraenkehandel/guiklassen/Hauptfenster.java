package Getraenkehandel.guiklassen;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import Getraenkehandel.Getraenk;
import Getraenkehandel.Kasse;
import Getraenkehandel.Produkt;
import Getraenkehandel.Snack;

public class Hauptfenster extends JFrame implements ActionListener{

    int y_position;
    Getraenk aktuellesgetraenk;
    Snack aktuellersnack;
    JPanel getraenkeliste;
    JPanel getraenkeanzeige;
    JPanel snackliste;
    Color coolcolour1;
    Color coolcolour2;
    Color coolcolour3;
    Color coolcolour4;
    Color coolcolour5;
    String typ;
    Kasse kasse;
    HashMap<Produkt, Integer> warenkorb;

    private JLabel getraenkename;
    private JLabel getraenkebestand;
    private JLabel getraenkepreis;
    private JLabel getraenkealkohol;
    private JPanel snackanzeige;
    private MyLabel snackname;
    private MyLabel snackbestand;
    private MyLabel snackpreis;
    JPanel warenkorbJPanel;
    private int getraenkeanzahl;
    private JFormattedTextField anzahlgetraenke;
    private MyButton getraenkwarenkorb;
    private MyButton snackwarenkorb;
    private JFormattedTextField anzahlsnacks;
    private int snackanzahl;

    public Hauptfenster(HashMap<String, Getraenk> getraenkemap, HashMap<String, Snack> snackmap, Kasse kasse){
        //Deklarieren der Farben und Importen der Bilder
        this.kasse = kasse;
        //Bestimmt die position der Button für getränke und snack liste
        y_position = 80;
        //Legt fest welches colourpallet genutzt wird
        int colorpallet = 2;
        //Platzthalter variable für auswahl was angezeigt werden soll
        typ = "getraenk";
        //Nimmt den Ersten eintrag aus den Maps und setzt in als Startwert für die Anzeige
        HashMap.Entry<String,Getraenk> entry1 = getraenkemap.entrySet().iterator().next();
        aktuellesgetraenk = entry1.getValue();

        HashMap.Entry<String,Snack> entry2 = snackmap.entrySet().iterator().next();
        aktuellersnack = entry2.getValue();

        //Erstellen des Warenkorbs
        warenkorb = new HashMap<>();

        //Festlegen der Colourpallets
        if(colorpallet == 1){
            coolcolour1 = new Color(210,190,255); 
            coolcolour2 = new Color(220,200,255);
            coolcolour3 = new Color(0, 0, 0);
            coolcolour4 = new Color(230,190,255); 
            coolcolour5 = new Color(120,80,155);
        }
        else if(colorpallet == 2){
            coolcolour1 = new Color(50,50,50);
            coolcolour2 = new Color(80,80,80); 
            coolcolour3 = new Color(255, 255, 255);
            coolcolour4 = new Color(70, 70, 70);
            coolcolour5 = new Color(60, 60, 60);
        }
        else{
            coolcolour1 = new Color(160,160,160); 
            coolcolour2 = new Color(150,150,150); 
            coolcolour3 = new Color(0, 0, 0);
            coolcolour4 = new Color(200, 200, 200);
            coolcolour5 = new Color(130, 130, 130);
        }

        //Bestimmt den höheren Wert für die Scrollpane
        int laenge = Math.max(getraenkemap.size(), snackmap.size());

        //Frame Configureren
        this.setSize(1280,720);
        this.setTitle("Getränkeverwaltung");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(coolcolour1);
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setOpacity(0.98f);
        this.setLocationRelativeTo(null);

        //Erstellen der Panel
        JPanel topbar = new MyPanel(coolcolour1, 1280, 40);
        this.add(topbar ,BorderLayout.PAGE_START);        

        //Erstellend des Scrollpanels was in die Scroll hinzugefügt werden soll, mit berechnung der benötigten länge anhand der Anzahl an Getränken/Snacks
        JPanel scrollpanel = new MyPanel(coolcolour1, 360, Math.max(100 + (laenge * 80), 680));

        //Erstellen der Scrollbar für die getränke und snacks
        JScrollPane scrollbar = new MyScrollPane(scrollpanel);
        this.add(scrollbar, BorderLayout.WEST); 

        getraenkeliste = new MyPanel(coolcolour2, 0, 0, 360, Math.max(100 + (laenge * 80), 680));
        scrollpanel.add(getraenkeliste);

        snackliste = new MyPanel(coolcolour5, 0, 0, 360, Math.max(100 + (laenge * 80), 680));
        snackliste.setVisible(false);
        scrollpanel.add(snackliste);

        JPanel anzeige = new MyPanel(coolcolour4, 560, 680);
        this.add(anzeige, BorderLayout.CENTER);

        warenkorbJPanel = new MyPanel(coolcolour5, 360, 680);
        warenkorbJPanel.setLayout(null);

        JScrollPane warenscroll = new MyScrollPane(warenkorbJPanel);
        this.add(warenscroll, BorderLayout.EAST);  

        //Anzeige der Daten des Getraenkes
        anzeige.add(getraenkeanzeige = new MyPanel(coolcolour4, 0, 0, 920, 680));

        //Erstellen der Label für die Getränke, werden direkt in der getränkeanzeige add methode erstellt
        getraenkeanzeige.add(getraenkename = new MyLabel(coolcolour3, "Name: " + aktuellesgetraenk.getName(), 20, 0, 70, 560, 60));
        getraenkeanzeige.add(getraenkebestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden", 20, 0, 210, 560, 60));
        getraenkeanzeige.add(getraenkepreis = new MyLabel(coolcolour3, "Preis: " + aktuellesgetraenk.getpreis() + "€", 20, 0, 350, 560, 60));
        getraenkeanzeige.add(getraenkealkohol = new MyLabel(coolcolour3, "Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%", 20, 0, 490, 560, 60));

        //Snackanzeige
        snackanzeige = new MyPanel(coolcolour4, 0, 0, 920, 680);
        snackanzeige.setVisible(false);
        anzeige.add(snackanzeige);
        
        //Erstellen der Snack Label
        snackanzeige.add(snackname = new MyLabel(coolcolour3, "Name: " + aktuellersnack.getName(), 20, 0, 100, 560, 60));
        snackanzeige.add(snackbestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden", 20, 0, 300, 560, 60));
        snackanzeige.add(snackpreis = new MyLabel(coolcolour3, "Preis: " + aktuellersnack.getpreis() + "€", 20, 0, 500, 560, 60));

        
        //Erstellen der Bottumleiste für die Getränke, in dem das verkaufsfeld ist. Die Buttomleiste wird für getränke und snack getrennt da je nachdem ob
        //Getränke oder Snacks aufgerufen werdem müssen verschiedene Methoden für den verkauf benutzt werden müssen. 
        JPanel buttombargetraenke = new MyPanel(coolcolour1, 0, 640, 560, 40);
        getraenkeanzeige.add(buttombargetraenke);   

        //Erstellen der Buttomleiste für die Snacks
        JPanel buttombarsnack = new MyPanel(coolcolour1, 0, 640, 560, 40);
        snackanzeige.add(buttombarsnack);   

        //Input Felder für den Warenkorb
        anzahlgetraenke = new JFormattedTextField(NumberFormat.getNumberInstance());
        anzahlgetraenke.setText("0");
        anzahlgetraenke.setColumns(2);
        anzahlgetraenke.setBounds(0, 0, 280, 40);       
        buttombargetraenke.add(anzahlgetraenke);

        //Getränke Warenkorbbutton
        getraenkwarenkorb = new MyButton(coolcolour2, coolcolour3, "        Zum Warenkorb hinzufügen", 280, 0, 280, 40);
        getraenkwarenkorb.addActionListener(this);
        buttombargetraenke.add(getraenkwarenkorb);

        anzahlsnacks = new JFormattedTextField(NumberFormat.getNumberInstance());
        anzahlsnacks.setText("0");
        anzahlsnacks.setColumns(2);
        anzahlsnacks.setBounds(0, 0, 280, 40);       
        buttombarsnack.add(anzahlsnacks);

        //Snack Warenkorbbutton
        snackwarenkorb = new MyButton(coolcolour2, coolcolour3, "        Zum Warenkorb hinzufügen", 280, 0, 280, 40);
        snackwarenkorb.addActionListener(this);
        buttombarsnack.add(snackwarenkorb);

        //Hinzufuegen des Button zum schließen des Programms
        JButton schließen = new MyButton(coolcolour2, coolcolour3, "Schließen", 1160, 0, 120, 40);
        schließen.addActionListener(e -> System.exit(0));
        topbar.add(schließen);

        //Hinzufuegen des Button zum auswählen des Getränkeliste
        JButton getraenkeButton = new MyButton(coolcolour2, coolcolour3, "Getränke", 0, 0, 180, 40);
        getraenkeButton.addActionListener(e -> changegetraenk());
        topbar.add(getraenkeButton);

        //Hinzufuegen des Button zum auswählen der Snackliste
        JButton snackButton = new MyButton(coolcolour5, coolcolour3, "Snacks", 180, 0, 180, 40);
        snackButton.addActionListener(e -> changesnack());
        topbar.add(snackButton);
        
        //Erstellen der Getränkeliste indem zuerst die überschrift und anschließend die Buttons für die Getränke eingefügt werden
        getraenkeliste.add(new MyLabel(coolcolour3, "Getränkeliste", 30, 0, 0, 360, 80));
        getraenkemap.forEach((k,v) -> newgetraenk(y_position, k, v));

        //Zurücksetzen der position an der, der erste button positioniert wird
        y_position = 80;
        //Erstellen der Snackliste indem zuerst die überschrift und anschließend die Buttons für die Snacks eingefügt werden
        snackliste.add(new MyLabel(coolcolour3, "Snackliste", 30, 0, 0, 360, 80));
        snackmap.forEach((k,v) -> newsnack(y_position, k, v));
    }

    //Erstellt neuen Button für die Getränke Liste
    private void newgetraenk(int y, String name, Getraenk getraenk){
        JButton button = new MyButton(coolcolour1, coolcolour3, name, 60, y, 240, 50);
        button.addActionListener(e -> changeanzeigegetraenk(getraenk));
        y_position = y_position + 80;
        getraenkeliste.add(button);
    }

    //Erstellt neuen Button für die Snäck Liste
    private void newsnack(int y, String name, Snack snack){
        JButton button = new MyButton(coolcolour1, coolcolour3, name, 60, y, 240, 50);
        button.addActionListener(e -> changeanzeigesnack(snack));
        y_position = y_position + 80;
        snackliste.add(button);
    }

    //Wechselt Anzeige von Snack auf Getränk
    private void changegetraenk(){
        typ = "getraenk";
        snackliste.setVisible(false);
        getraenkeliste.setVisible(true);
        snackanzeige.setVisible(false);
        getraenkeanzeige.setVisible(true);
        System.out.println(kasse.getkassenstand());
    }

    //Wechselt Anzeige von Getränk auf Snack
    private void changesnack(){
        typ = "snack";
        snackliste.setVisible(true);
        getraenkeliste.setVisible(false);
        snackanzeige.setVisible(true);
        getraenkeanzeige.setVisible(false);
    }

    //Aktuallisiert die Anzeige der Getränke Daten
    public void changeanzeigegetraenk(Getraenk getraenk){
        aktuellesgetraenk = getraenk;
        getraenkename.setText("Name: " + aktuellesgetraenk.getName());
        getraenkebestand.setText("Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden");
        getraenkepreis.setText("Preis: " + aktuellesgetraenk.getpreis() + "€");
        getraenkealkohol.setText("Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%");
    }

    //Aktuallisiert die Anzeige der Snäck Daten
    public void changeanzeigesnack(Snack snack){
        aktuellersnack = snack;
        snackname.setText("Name: " + aktuellersnack.getName());
        snackbestand.setText("Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden");
        snackpreis.setText("Preis: " + aktuellersnack.getpreis() + "€");
    }

    //Funktion wenn etwas zum warenkorb hinzugefügt wird
    private void addWarenkorb(Produkt produkt, int anzahl){
        SpringLayout spring = new SpringLayout();
        warenkorbJPanel = new MyPanel(coolcolour5, 360, 680);
        warenkorbJPanel.setLayout(spring);
        if (anzahl != 0){
            //Checkt ob produkt schon im warenkorb ist, wenn ja wird die menge erhöht, wenn nein wir es hinzugefügt
            if(warenkorb.containsKey(produkt)){
                //Check ob genug im bestand sind
                if (produkt.getbestand() >= anzahl + warenkorb.get(produkt)){
                    warenkorb.put(produkt, warenkorb.get(produkt) + anzahl);
                }
                else {
                    openError(produkt);
                }
            }
            else{
                //Check ob genug im bestand sind
                if (produkt.getbestand() >= anzahl){
                    warenkorb.put(produkt, anzahl);
                }
                else {
                    openError(produkt);
                }
            }
        }
        warenkorb.forEach((k,v) -> {
            //Erstellt für jeden Pordukt im Warenkorb ein Laben zur anzeige, muss das warenkorbJpanel jedesmal aktuallisieren  
            JLabel label = new MyLabel(coolcolour3,"Name: " + k.getName() + "    |   Anzahl: " + warenkorb.get(k), 20);
            label.setBackground(coolcolour1);
            warenkorbJPanel.add(label);
        });
        //Revalidate das WarenkorbJPanel damit die änderungen angezeigt werden
        this.add(warenkorbJPanel, BorderLayout.EAST);
        warenkorbJPanel.revalidate();
    }

    //Erstellt das Error Fenster was bei zu wenig bestand geöffnet wird
    private void openError (Produkt produkt){
        JOptionPane error = new JOptionPane();
        JOptionPane.showMessageDialog(error, "Es sind nicht genug " + produkt.getName() + " im Bestand.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getraenkwarenkorb){
            if (anzahlgetraenke.getValue() != null){
                getraenkeanzahl = ((Number) anzahlgetraenke.getValue()).intValue();
                addWarenkorb(aktuellesgetraenk, getraenkeanzahl);}
            }
        if (e.getSource() == snackwarenkorb){
            if (anzahlsnacks.getValue() != null){
                snackanzahl = ((Number) anzahlsnacks.getValue()).intValue();
                addWarenkorb(aktuellersnack, snackanzahl);
            }
        }
    }
}


