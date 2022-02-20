import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class MyLabel extends JLabel{
    public MyLabel(Color schriftcolour, String label){
        this.setText(label); //Setting the text for a Label
        this.setForeground(schriftcolour);
        this.setFont(new Font("Arial", Font.PLAIN, 15));
    } 

    public MyLabel(Color schriftcolour, String label, int x, int y, int width, int height){
        this.setText(label); //Setting the text for a Label
        this.setForeground(schriftcolour); //Changes the colour of the label
        this.setFont(new Font("Arial", Font.PLAIN, 30)); //Set Font and Style of label text
        this.setBounds(x, y, width, height);
        this.setHorizontalAlignment(JLabel.CENTER); //Sets Text and Image horizontal position in Label
    } 
}
