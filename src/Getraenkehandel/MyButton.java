package Getraenkehandel;

import javax.swing.JButton;
import java.awt.Color;

public class MyButton extends JButton{
    public MyButton(Color buttoncolour, Color schriftcolour, String label, int x, int y, int width, int height){
        this.setBackground(buttoncolour);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBounds(x, y, width, height);
        this.add(new MyLabel(schriftcolour, label));
    }
}
