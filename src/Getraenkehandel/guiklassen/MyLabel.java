package Getraenkehandel.guiklassen;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

//Klasse zum erzeugen von JLabel, dient nur um code in den Fensterklassen zu reduzeiren.
//Ein Konstruktor mit setbounds und einen ohne 
public class MyLabel extends JLabel{
    public MyLabel(Color schriftcolour, String label, int schriftgroesse){
        this.setText(label); //Setting the text for a Label
        this.setForeground(schriftcolour);
        this.setFont(new Font("Arial", Font.PLAIN, schriftgroesse));
    } 

    public MyLabel(Color schriftcolour, String label,int schriftgroesse, int x, int y, int width, int height){
        this.setText(label); //Setting the text for a Label
        this.setForeground(schriftcolour); //Changes the colour of the label
        this.setFont(new Font("Arial", Font.PLAIN, schriftgroesse)); //Set Font and Style of label text
        this.setBounds(x, y, width, height);
        this.setHorizontalAlignment(JLabel.CENTER); //Sets Text and Image horizontal position in Label
    } 
}
