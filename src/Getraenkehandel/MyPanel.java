package Getraenkehandel;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class MyPanel extends JPanel{

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
