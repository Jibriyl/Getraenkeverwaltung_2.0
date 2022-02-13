import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    JPanel snackliste;
    Color coolcolour1;
    Color coolcolour2;
    Color coolcolour3;
    Color coolcolour4;
    Color coolcolour5;
    String typ;

    Hauptfenster(HashMap<String, Getraenk> getraenkemap, HashMap<String, Snack> snackmap){
        //Deklarieren der Farben und Importen der Bilder
        y_position = 100;
        int colorpallet = 2;
        typ = "getraenk";

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
            coolcolour4 = new Color(50, 50, 50);
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
        //topbar.setBounds(0, 0, 1280, 40); //Sets bounds like in the label axample
        topbar.setBackground(new Color(0,0,0,65));
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
        anzeige.setSize(920, 680);
        anzeige.setBackground(coolcolour4);
        this.add(anzeige, BorderLayout.EAST);

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
        JButton schließen = new JButton();
        schließen.setBounds(1160, 0, 120, 40);
        schließen.addActionListener(e -> System.exit(0));
        schließen.setBackground(coolcolour2);
        schließen.setBorderPainted(false);
        schließen.setFocusPainted(false);
        schließen.add(newlabel("Schließen"));

        JButton getraenkeButton = new JButton();
        getraenkeButton.setBounds(0, 0, 180, 40);
        getraenkeButton.addActionListener(e -> changegetraenk());
        getraenkeButton.setBackground(coolcolour2);
        getraenkeButton.setBorderPainted(false);
        getraenkeButton.setFocusPainted(false);
        getraenkeButton.add(newlabel("Getränke"));

        JButton snackButton = new JButton();
        snackButton.setBounds(180, 0, 180, 40);
        snackButton.addActionListener(e -> changesnack());
        snackButton.setBackground(coolcolour5);
        snackButton.setBorderPainted(false);
        snackButton.setFocusPainted(false);
        snackButton.add(newlabel("Snacks"));
        
        //Hinzufuegen der Komponenten zum Frame
        topbar.add(schließen);
        topbar.add(getraenkeButton);
        topbar.add(snackButton);
        
        getraenkeliste.add(titlelabel("Getränkeliste"));
        getraenkemap.forEach((k,v) -> newgetraenk(y_position, k, v));

        y_position = 100;
        snackliste.add(titlelabel("Snackliste"));
        snackmap.forEach((k,v) -> newsnack(y_position, k, v));

        this.setVisible(true); //Makes the Window Visible
    }

    private void newgetraenk(int y, String name, Getraenk getraenk){
        JButton button = new JButton();
        button.setBounds(60, y, 240, 50);
        button.addActionListener(e -> aktuellesgetraenk = getraenk);
        button.setBackground(coolcolour1);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        y_position = y_position + 80;
        button.add(newlabel(name));
        getraenkeliste.add(button);
    }

    private void newsnack(int y, String name, Snack snack){
        JButton button = new JButton();
        button.setBounds(60, y, 240, 50);
        button.addActionListener(e -> aktuellersnack = snack);
        button.setBackground(coolcolour1);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        y_position = y_position + 80;
        button.add(newlabel(name));
        snackliste.add(button);
    }

    private JLabel titlelabel(String text){
        JLabel label = new JLabel(); //Creating a Label inside a frame 
        label.setText(text); //Setting the text for a Label
        label.setForeground(coolcolour3); //Changes the colour of the label
        label.setFont(new Font("Arial", Font.PLAIN, 30)); //Set Font and Style of label text
        label.setBounds(0, 0, 360, 80);
        label.setHorizontalAlignment(JLabel.CENTER); //Sets Text and Image horizontal position in Label
        return label;
    }

    private JLabel newlabel(String text){
        JLabel label = new JLabel(text);
        label.setForeground(coolcolour3);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        return label;
    }

    private void changegetraenk(){
        typ = "getraenk";
        snackliste.setVisible(false);
        getraenkeliste.setVisible(true);
    }
    private void changesnack(){
        typ = "snack";
        snackliste.setVisible(true);
        getraenkeliste.setVisible(false);
    }

}


