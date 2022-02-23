package Getraenkehandel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.ScrollPaneLayout;

public class Hauptfenster extends JFrame{

    int y_position;
    Getraenk aktuellesgetraenk;
    Snack aktuellersnack;
    JPanel getraenkeliste;
    JPanel getraenkeanzeige;
    JPanel snackliste;
    static Color coolcolour1;
    static Color coolcolour2;
    static Color coolcolour3;
    static Color coolcolour4;
    static Color coolcolour5;
    String typ;
    Kasse kasse;

    private JLabel getraenkename;
    private JLabel getraenkebestand;
    private JLabel getraenkepreis;
    private JLabel getraenkealkohol;
    private JPanel snackanzeige;
    private MyLabel snackname;
    private MyLabel snackbestand;
    private MyLabel snackpreis;

    Hauptfenster(HashMap<String, Getraenk> getraenkemap, HashMap<String, Snack> snackmap, Kasse kasse){
        //Deklarieren der Farben und Importen der Bilder
        this.kasse = kasse;
        y_position = 80;
        int colorpallet = 2;
        typ = "getraenk";
        //Nimmt den Ersten eintrag aus dem am und setzt in als Startwert für die Anzeige
        HashMap.Entry<String,Getraenk> entry1 = getraenkemap.entrySet().iterator().next();
        aktuellesgetraenk = entry1.getValue();

        HashMap.Entry<String,Snack> entry2 = snackmap.entrySet().iterator().next();
        aktuellersnack = entry2.getValue();

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
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms

        //Erstellen der Panel
        JPanel topbar = new MyPanel(coolcolour1, 1280, 40);
        this.add(topbar ,BorderLayout.PAGE_START);        

        //Erstellend des Scrollpanels was in die Scroll hinzugefügt werden soll, mit berechnung der benötigten länge anhand der Anzahl an Getränken/Snacks
        JPanel scrollpanel = new MyPanel(coolcolour1, 360, Math.max(100 + (laenge * 80), 680));

        //Erstellen der Scrollbar für die getränke und snacks
        JScrollPane scrollbar = new JScrollPane(scrollpanel);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setBorder(BorderFactory.createEmptyBorder());
        scrollbar.getVerticalScrollBar().setOpaque(false);
        scrollbar.getVerticalScrollBar().setUnitIncrement(4);
        //Unsichtbar machen der Scrollbar ohne die Funktion zu beeinflussen
        scrollbar.setLayout(new ScrollPaneLayout(){
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane) parent;
        
                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;
        
                Rectangle vsbR = new Rectangle();
                vsbR.width = 12;
                vsbR.height = availR.height;
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;
        
                if (viewport != null) {
                viewport.setBounds(availR);
                }
            }
            });
        this.add(scrollbar, BorderLayout.WEST); 
        

        getraenkeliste = new MyPanel(coolcolour2, 0, 0, 360, Math.max(100 + (laenge * 80), 680));
        scrollpanel.add(getraenkeliste);

        snackliste = new MyPanel(coolcolour5, 0, 0, 360, Math.max(100 + (laenge * 80), 680));
        snackliste.setVisible(false);
        scrollpanel.add(snackliste);

        JPanel anzeige = new MyPanel(coolcolour4, 920, 680);
        this.add(anzeige, BorderLayout.EAST);

        //Anzeige der Daten des Getraenkes
        anzeige.add(getraenkeanzeige = new MyPanel(coolcolour4, 0, 0, 920, 680));
        //Erstellen der Label für die Getränke, werden direkt in der getränkeanzeige add methode erstellt
        getraenkeanzeige.add(getraenkename = new MyLabel(coolcolour3, "Name: " + aktuellesgetraenk.getName(), 30, 0, 70, 920, 60));
        getraenkeanzeige.add(getraenkebestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden", 30, 0, 210, 920, 60));
        getraenkeanzeige.add(getraenkepreis = new MyLabel(coolcolour3, "Preis: " + aktuellesgetraenk.getpreis() + "€", 30, 0, 350, 920, 60));
        getraenkeanzeige.add(getraenkealkohol = new MyLabel(coolcolour3, "Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%", 30, 0, 490, 920, 60));

        //Snackanzeige
        snackanzeige = new MyPanel(coolcolour4, 0, 0, 920, 680);
        snackanzeige.setVisible(false);
        anzeige.add(snackanzeige);
        
        //Erstellen der Snack Label
        snackanzeige.add(snackname = new MyLabel(coolcolour3, "Name: " + aktuellersnack.getName(), 30, 0, 100, 920, 60));
        snackanzeige.add(snackbestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden", 30, 0, 300, 920, 60));
        snackanzeige.add(snackpreis = new MyLabel(coolcolour3, "Preis: " + aktuellersnack.getpreis() + "€", 30, 0, 500, 920, 60));

        //Erstellen der Bottumleiste für die Getränke, in dem das verkaufsfeld ist. Die Buttomleiste wird für getränke und snack getrennt da je nachdem ob
        //Getränke oder Snacks aufgerufen werdem müssen verschiedene Methoden für den verkauf benutzt werden müssen. 
        JPanel buttombargetraenke = new MyPanel(coolcolour1, 0, 640, 920, 40);
        getraenkeanzeige.add(buttombargetraenke);   
        //Getränke Verkaufsbutton
        JButton getraenkverkaufen = new MyButton(coolcolour2, coolcolour3, "Verkaufen", 800, 0, 120, 40);
        getraenkverkaufen.addActionListener(e -> Verkaufsfenster.getreankverkaufen(aktuellesgetraenk, 10, coolcolour3, coolcolour5, kasse));
        buttombargetraenke.add(getraenkverkaufen);

        //Erstellen der Buttomleiste für die Snacks
        JPanel buttombarsnack = new MyPanel(coolcolour1, 0, 640, 920, 40);
        snackanzeige.add(buttombarsnack);   
        //Snack Verkaufsbutton
        JButton snackverkaufen = new MyButton(coolcolour2, coolcolour3, "Verkaufen", 800, 0, 120, 40);
        snackverkaufen.addActionListener(e -> Verkaufsfenster.snackverkaufen(aktuellersnack, 10, coolcolour3, coolcolour5, kasse));
        buttombarsnack.add(snackverkaufen);

        //Hinzufuegen des Button zum schließen des Programms
        JButton schließen = new MyButton(coolcolour2, coolcolour3, "Schließen", 1160, 0, 120, 40);
        schließen.addActionListener(e -> System.exit(0));
        topbar.add(schließen);

        JButton getraenkeButton = new MyButton(coolcolour2, coolcolour3, "Getränke", 0, 0, 180, 40);
        getraenkeButton.addActionListener(e -> changegetraenk());
        topbar.add(getraenkeButton);

        JButton snackButton = new MyButton(coolcolour5, coolcolour3, "Snacks", 180, 0, 180, 40);
        snackButton.addActionListener(e -> changesnack());
        topbar.add(snackButton);
        
        getraenkeliste.add(new MyLabel(coolcolour3, "Getränkeliste", 30, 0, 0, 360, 80));
        getraenkemap.forEach((k,v) -> newgetraenk(y_position, k, v));

        y_position = 80;
        snackliste.add(new MyLabel(coolcolour3, "Snackliste", 30, 0, 0, 360, 80));
        snackmap.forEach((k,v) -> newsnack(y_position, k, v));
    }

    private void newgetraenk(int y, String name, Getraenk getraenk){
        JButton button = new MyButton(coolcolour1, coolcolour3, name, 60, y, 240, 50);
        button.addActionListener(e -> changeanzeigegetraenk(getraenk));
        y_position = y_position + 80;
        getraenkeliste.add(button);
    }

    private void newsnack(int y, String name, Snack snack){
        JButton button = new MyButton(coolcolour1, coolcolour3, name, 60, y, 240, 50);
        button.addActionListener(e -> changeanzeigesnack(snack));
        y_position = y_position + 80;
        snackliste.add(button);
    }

    private void changegetraenk(){
        typ = "getraenk";
        snackliste.setVisible(false);
        getraenkeliste.setVisible(true);
        snackanzeige.setVisible(false);
        getraenkeanzeige.setVisible(true);
        System.out.println(kasse.getkassenstand());
    }

    private void changesnack(){
        typ = "snack";
        snackliste.setVisible(true);
        getraenkeliste.setVisible(false);
        snackanzeige.setVisible(true);
        getraenkeanzeige.setVisible(false);
    }

    private void changeanzeigegetraenk(Getraenk getraenk){
        aktuellesgetraenk = getraenk;
        getraenkename.setText("Name: " + aktuellesgetraenk.getName());
        getraenkebestand.setText("Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden");
        getraenkepreis.setText("Preis: " + aktuellesgetraenk.getpreis() + "€");
        getraenkealkohol.setText("Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%");
    }

    private void changeanzeigesnack(Snack snack){
        aktuellersnack = snack;
        snackname.setText("Name: " + aktuellersnack.getName());
        snackbestand.setText("Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden");
        snackpreis.setText("Preis: " + aktuellersnack.getpreis() + "€");
    }
}


