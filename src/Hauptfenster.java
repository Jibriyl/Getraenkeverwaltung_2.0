import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

public class Hauptfenster extends JFrame{
    
    Hauptfenster(){
        //Deklarieren der Farben und Importen der Bilder
        Color coolcolour = new Color(210,190,255); //Create new Colour to be used, RGB value
        Border border = BorderFactory.createLineBorder(Color.blue, 10); //Creates a border 


        //Frame Configureren
        this.setSize(1280,720); //Sets Frame Size x-dimension y-dimension
        this.setTitle("Getränkeverwaltung"); //Sets Title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the application on close / Default is only hiding it
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(coolcolour); //Change Colour of background
        this.setLayout(null); //Sets the Layoutmanager to null
        //this.setUndecorated(true);
        //this.setOpacity(0.95f);

        //Erstellen der Panel
        JPanel topbar = new JPanel(); //Creats a new Panel 
        topbar.setBounds(0, 0, 1280, 40); //Sets bounds like in the label axample
        topbar.setBackground(new Color(0,0,0,65));

        JPanel getraenkeliste = new JPanel();
        getraenkeliste.setBounds(0, 40, 360, 680);
        getraenkeliste.setBackground(new Color(255,255,255,65));
        getraenkeliste.setLayout(null);

        //Erstellen der Label
        JLabel getraenkelistelabel = new JLabel(); //Creating a Label inside a frame 
        //Editing the label
        getraenkelistelabel.setText("Getränkeliste"); //Setting the text for a Label
        //Setting text position relative to image
        getraenkelistelabel.setHorizontalTextPosition(JLabel.CENTER); //Options / Set text LEFT, RIGHT or Center of imageicon
        getraenkelistelabel.setVerticalTextPosition(JLabel.TOP); //Set text TOP, CENTER or BOTTOM of imageicon
        getraenkelistelabel.setForeground(Color.BLACK); //Changes the colour of the label
        getraenkelistelabel.setFont(new Font("MV Boli", Font.PLAIN, 30)); //Set Font and Style of label text
        getraenkelistelabel.setIconTextGap(70); //Changes the Gap between icon and text of label
        getraenkelistelabel.setVerticalAlignment(JLabel.CENTER); //Sets Text and Image vertical position in Label
        getraenkelistelabel.setHorizontalAlignment(JLabel.CENTER); //Sets Text and Image horizontal position in Label
        getraenkelistelabel.setBounds(0, 0, 360, 80); //Sets dimentions and position of the label in the frame / x, y, width, height / in pixel / starts at top left of screen

        //Hinzufuegen der Button
        JButton getraenk = new JButton();
        getraenk.setBounds(60, 80, 240, 40);

        //Hinzufuegen der Komponenten zum Frame
        this.add(topbar);
        this.add(getraenkeliste);
        getraenkeliste.add(getraenkelistelabel);
        getraenkeliste.add(getraenk);

        this.setVisible(true); //Makes the Window Visible
    }

}
