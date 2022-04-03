package Getraenkehandel.guiklassen;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;

public class MyButton extends JButton{
    public MyButton(Color buttoncolour, Color schriftcolour, String labeltext){
        this.setBackground(buttoncolour);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        JLabel label = new MyLabel(schriftcolour, labeltext, 15);
        this.add(label);
    }
    
    public MyButton(Color buttoncolour, Color schriftcolour, String labeltext, int x, int y, int width, int height){
        this.setBackground(buttoncolour);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBounds(x, y, width, height);
        this.add(new MyLabel(schriftcolour, labeltext, 15));
    }
}
