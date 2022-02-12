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



public class Hauptfenster extends JFrame{

    static int y_position;
    static Getraenk aktuellesgetraenk;
    static JPanel getraenkeliste;
    static Color coolcolour1;
    static Color coolcolour2;
    static Color coolcolour3;

    Hauptfenster(HashMap<String, Getraenk> getraenkemap){
        //Deklarieren der Farben und Importen der Bilder
        y_position = 100;
        int colorpallet = 2;

        if(colorpallet == 1){
            coolcolour1 = new Color(210,190,255); //Create new Colour to be used, RGB value
            coolcolour2 = new Color(220,200,255); 
            coolcolour3 = new Color(0, 0, 0);
        }
        else if(colorpallet == 2){
            coolcolour1 = new Color(50,50,50);
            coolcolour2 = new Color(70,70,70); 
            coolcolour3 = new Color(255, 255, 255);

        }
        else{
            coolcolour1 = new Color(160,160,160); 
            coolcolour2 = new Color(150,150,150); 
            coolcolour3 = new Color(255, 255, 255);
        }

        int laenge = getraenkemap.size();

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

        getraenkeliste = new JPanel();
        getraenkeliste.setPreferredSize(new Dimension(360,Math.max(100 + (laenge * 80), 680) ));
        getraenkeliste.setBackground(coolcolour2);
        getraenkeliste.setLayout(null);

        JPanel anzeige = new JPanel();
        anzeige.setPreferredSize(new Dimension(920, 680));
        anzeige.setSize(920, 680);
        anzeige.setBackground(Color.white);

        //Erstellen der Label
        JLabel getraenkelistelabel = new JLabel(); //Creating a Label inside a frame 
        getraenkelistelabel.setText("Getränkeliste"); //Setting the text for a Label
        getraenkelistelabel.setForeground(Color.BLACK); //Changes the colour of the label
        getraenkelistelabel.setFont(new Font("Arial", Font.PLAIN, 30)); //Set Font and Style of label text
        getraenkelistelabel.setBounds(0, 0, 360, 80);
        getraenkelistelabel.setHorizontalAlignment(JLabel.CENTER); //Sets Text and Image horizontal position in Label


        JScrollPane scrollbar = new JScrollPane(getraenkeliste);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setBorder(BorderFactory.createEmptyBorder());




        //Hinzufuegen der Button      
        JButton getraenk2 = new JButton();
        getraenk2.setBounds(80, 100, 200, 40);
        getraenk2.addActionListener(e -> System.out.println("x"));

        JLabel schließenlabel = new JLabel("Schließen");
        schließenlabel.setForeground(coolcolour3);
        schließenlabel.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton schließen = new JButton();
        schließen.setBounds(1175, 0, 105, 40);
        schließen.addActionListener(e -> System.exit(0));
        schließen.setBackground(coolcolour2);
        schließen.setBorderPainted(false);
        schließen.setFocusPainted(false);

        schließen.add(schließenlabel);
        
        //Hinzufuegen der Komponenten zum Frame
        this.add(topbar ,BorderLayout.PAGE_START);       
        this.add(anzeige, BorderLayout.EAST);
        topbar.add(schließen);
        this.add(scrollbar, BorderLayout.WEST); 
        getraenkeliste.add(getraenkelistelabel);
        getraenkemap.forEach((k,v) -> newbutton(y_position, k, v));

        this.setVisible(true); //Makes the Window Visible
    }

    private static void newbutton(int y, String name, Getraenk getraenk){
        JLabel buttonlabel = new JLabel(name);
        buttonlabel.setForeground(coolcolour3);
        buttonlabel.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton button = new JButton();
        button.setBounds(60, y, 240, 50);
        button.addActionListener(e -> aktuellesgetraenk = getraenk);
        button.setBackground(coolcolour1);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        y_position = y_position + 80;
        button.add(buttonlabel);
        getraenkeliste.add(button);
    }
}


