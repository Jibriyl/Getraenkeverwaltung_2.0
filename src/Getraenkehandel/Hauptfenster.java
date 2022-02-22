package Getraenkehandel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
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
    private Verkaufsfenster kassenzettel;

    Hauptfenster(HashMap<String, Getraenk> getraenkemap, HashMap<String, Snack> snackmap, Kasse kasse){
        //Deklarieren der Farben und Importen der Bilder
        this.kasse = kasse;
        y_position = 80;
        int colorpallet = 2;
        typ = "getraenk";
        //Nimmt den Ersten eintrag aus dem am und setzt in als Startwert für die Anzeige
        HashMap.Entry<String,Getraenk> entry1 = getraenkemap.entrySet().iterator().next();
        Getraenk aktuellesgetraenk = entry1.getValue();

        HashMap.Entry<String,Snack> entry2 = snackmap.entrySet().iterator().next();
        Snack aktuellersnack = entry2.getValue();

        if(colorpallet == 1){
            coolcolour1 = new Color(210,190,255); //Create new Colour to be used, RGB value
            coolcolour2 = new Color(220,200,255); //Getränkeiste Colour
            coolcolour3 = new Color(0, 0, 0); //Schriftfarbe
            coolcolour4 = new Color(230,190,255); //Background Anzeige
            coolcolour5 = new Color(120,80,155); //Snackliste Colour
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
        this.setSize(1280,720); //Sets Frame Size x-dimension y-dimension
        this.setTitle("Getränkeverwaltung"); //Sets Title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the application on close / Default is only hiding it
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(coolcolour1); //Change Colour of background
        this.setLayout(new BorderLayout()); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setOpacity(0.98f);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms

        //Erstellen der Panel
        JPanel topbar = new JPanel(); //Creats a new Panel 
        topbar.setPreferredSize(new Dimension(1280, 40));
        topbar.setBackground(coolcolour1);
        topbar.setLayout(null);
        this.add(topbar ,BorderLayout.PAGE_START);        

        JPanel scrollpanel = new JPanel();
        scrollpanel.setPreferredSize(new Dimension(360,Math.max(100 + (laenge * 80), 680) ));
        scrollpanel.setLayout(null);

        getraenkeliste = new JPanel();
        getraenkeliste.setBounds(0, 0, 360, Math.max(100 + (laenge * 80), 680));
        getraenkeliste.setBackground(coolcolour2);
        getraenkeliste.setLayout(null);
        scrollpanel.add(getraenkeliste);

        snackliste = new JPanel();
        snackliste.setBounds(0, 0, 360, Math.max(100 + (laenge * 80), 680));
        snackliste.setBackground(coolcolour5);
        snackliste.setLayout(null);
        scrollpanel.add(snackliste);
        snackliste.setVisible(false);

        JPanel anzeige = new JPanel();
        anzeige.setPreferredSize(new Dimension(920, 680));
        anzeige.setLayout(null);
        anzeige.setBackground(coolcolour4);
        this.add(anzeige, BorderLayout.EAST);

        //Anzeige der Daten des Getraenkes
        getraenkeanzeige = new JPanel();
        getraenkeanzeige.setBounds(0, 0, 920, 640);
        getraenkeanzeige.setLayout(null);
        getraenkeanzeige.setBackground(coolcolour4);
        anzeige.add(getraenkeanzeige);
        
        getraenkename = new MyLabel(coolcolour3, "Name: " + aktuellesgetraenk.getName(), 0, 70, 920, 60);
        getraenkeanzeige.add(getraenkename);

        getraenkebestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden", 0, 210, 920, 60);
        getraenkeanzeige.add(getraenkebestand);

        getraenkepreis = new MyLabel(coolcolour3, "Preis: " + aktuellesgetraenk.getpreis() + "€", 0, 350, 920, 60);
        getraenkeanzeige.add(getraenkepreis);

        getraenkealkohol = new MyLabel(coolcolour3, "Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%", 0, 490, 920, 60);
        getraenkeanzeige.add(getraenkealkohol);

        //Snackanzeige
        snackanzeige = new JPanel();
        snackanzeige.setBounds(0, 0, 920, 640);
        snackanzeige.setLayout(null);
        snackanzeige.setBackground(coolcolour4);
        anzeige.add(snackanzeige);
        snackanzeige.setVisible(false);

        snackname = new MyLabel(coolcolour3, "Name: " + aktuellersnack.getName(), 0, 100, 920, 60);
        snackanzeige.add(snackname);

        snackbestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden", 0, 300, 920, 60);
        snackanzeige.add(snackbestand);

        snackpreis = new MyLabel(coolcolour3, "Preis: " + aktuellersnack.getpreis() + "€", 0, 500, 920, 60);
        snackanzeige.add(snackpreis);

        //Erstellen der Bottumleiste in dem das verkaufsfeld ist
        JPanel buttombar = new JPanel();
        buttombar.setBounds(0, 640, 920, 40);
        buttombar.setBackground(coolcolour1);
        buttombar.setLayout(null);
        anzeige.add(buttombar);   

        JButton verkaufen = new MyButton(coolcolour2, coolcolour3, "Verkaufen", 800, 0, 120, 40);
        verkaufen.addActionListener(e -> Verkaufsfenster.getreankverkaufen(aktuellesgetraenk));
        buttombar.add(verkaufen);

        JScrollPane scrollbar = new JScrollPane(scrollpanel);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setBorder(BorderFactory.createEmptyBorder());
        scrollbar.getVerticalScrollBar().setOpaque(false);
        scrollbar.getVerticalScrollBar().setUnitIncrement(4);
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

        //Hinzufuegen der Button

        JButton schließen = new MyButton(coolcolour2, coolcolour3, "Schließen", 1160, 0, 120, 40);
        schließen.addActionListener(e -> System.exit(0));

        JButton getraenkeButton = new MyButton(coolcolour2, coolcolour3, "Getränke", 0, 0, 180, 40);
        getraenkeButton.addActionListener(e -> changegetraenk());

        JButton snackButton = new MyButton(coolcolour5, coolcolour3, "Snacks", 180, 0, 180, 40);
        snackButton.addActionListener(e -> changesnack());
        
        //Hinzufuegen der Komponenten zur Topbar
        topbar.add(schließen);
        topbar.add(getraenkeButton);
        topbar.add(snackButton);
        
        getraenkeliste.add(new MyLabel(coolcolour3, "Getränkeliste", 0, 0, 360, 80));
        getraenkemap.forEach((k,v) -> newgetraenk(y_position, k, v));

        y_position = 80;
        snackliste.add(new MyLabel(coolcolour3, "Snackliste", 0, 0, 360, 80));
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


