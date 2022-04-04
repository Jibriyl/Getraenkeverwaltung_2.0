package Getraenkehandel.guiklassen;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class MyPanel extends JPanel{

    //Klasse zum erzeugen von JPanel, dient nur um code in den Fensterklassen zu reduzeiren.
    //Ein Konstruktor mit setbounds und einen mit Preferrredsize
    public MyPanel(Color background, int width, int height){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(background);
    }
    public MyPanel(Color background, int x, int y, int width, int height){
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(background);
    }

    
}
