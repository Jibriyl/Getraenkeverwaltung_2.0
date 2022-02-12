import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Hauptfenster extends JFrame{

    Hauptfenster(){
        //Deklarieren der Farben und Importen der Bilder
        int colorpallet = 2;
        Color coolcolour1;
        Color coolcolour2;
        if(colorpallet == 1){
            coolcolour1 = new Color(210,190,255); //Create new Colour to be used, RGB value
            coolcolour2 = new Color(220,200,255); 
        }
        else if(colorpallet == 2){
            coolcolour1 = new Color(50,50,50);
            coolcolour2 = new Color(160,160,160); 
        }
        else{
            coolcolour1 = new Color(160,160,160); 
            coolcolour2 = new Color(150,150,150); 
        }
        Border border = BorderFactory.createLineBorder(Color.blue, 10); //Creates a border 


        //Frame Configureren
        this.setSize(1280,720); //Sets Frame Size x-dimension y-dimension
        this.setTitle("Getränkeverwaltung"); //Sets Title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the application on close / Default is only hiding it
        this.setResizable(false); //Prevents frame from being resized
        this.getContentPane().setBackground(coolcolour1); //Change Colour of background
        this.setLayout(null); //Sets the Layoutmanager to null
        this.setUndecorated(true);
        this.setLocationRelativeTo(null); //Setzt das Fenster in die mitte des Bildschirms
        this.setOpacity(0.98f);


        

        //Erstellen der Panel
        JPanel topbar = new JPanel(); //Creats a new Panel 
        topbar.setBounds(0, 0, 1280, 40); //Sets bounds like in the label axample
        topbar.setBackground(new Color(0,0,0,65));
        topbar.setLayout(null);

        JPanel getraenkeliste = new JPanel();
        getraenkeliste.setBounds(0, 40, 360, 680);
        getraenkeliste.setBackground(new Color(255,255,255,65));
        getraenkeliste.setLayout(null);

        //Erstellen der Label
        JLabel getraenkelistelabel = new JLabel(); //Creating a Label inside a frame 
        //Editing the label
        getraenkelistelabel.setText("Getränkeliste"); //Setting the text for a Label
        //Setting text position relative to image
        getraenkelistelabel.setForeground(Color.BLACK); //Changes the colour of the label
        getraenkelistelabel.setFont(new Font("", Font.PLAIN, 30)); //Set Font and Style of label text
        getraenkelistelabel.setVerticalAlignment(JLabel.CENTER); //Sets Text and Image vertical position in Label
        getraenkelistelabel.setHorizontalAlignment(JLabel.CENTER); //Sets Text and Image horizontal position in Label
        getraenkelistelabel.setBounds(0, 0, 360, 80); //Sets dimentions and position of the label in the frame / x, y, width, height / in pixel / starts at top left of screen

        //Hinzufuegen der Button
        JButton getraenk = new JButton();
        getraenk.setBounds(60, 80, 240, 40);
        getraenk.addActionListener(e -> System.out.println("x"));

        JButton schließen = new JButton();
        JLabel schließenlabel = new JLabel("Schließen");
        schließenlabel.setFont(new Font("", Font.PLAIN, 15));
        schließen.setBounds(1175, 0, 105, 40);
        schließen.addActionListener(e -> System.exit(0));
        schließen.setBackground(coolcolour2);
        schließen.setBorderPainted(false);
        schließen.setFocusPainted(false);

        schließen.add(schließenlabel);

        //Hinzufuegen der Komponenten zum Frame
        this.add(topbar);
        topbar.add(schließen);
        this.add(getraenkeliste);
        getraenkeliste.add(getraenkelistelabel);
        getraenkeliste.add(getraenk);

        this.setVisible(true); //Makes the Window Visible
    }
}
