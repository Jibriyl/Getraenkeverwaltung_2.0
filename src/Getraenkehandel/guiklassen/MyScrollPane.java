package Getraenkehandel.guiklassen;

import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import java.awt.Container;

//Klasse zum erzeugen von JScrollpane, dient nur um code in den Fensterklassen zu reduzeiren.
//Ein Konstruktor mit setbounds und einen ohne 
public class MyScrollPane extends JScrollPane{
    
    public MyScrollPane(JPanel panel) {
        super(panel);
    
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getVerticalScrollBar().setOpaque(false);
        this.getVerticalScrollBar().setUnitIncrement(4);
        //Unsichtbar machen der Scrollbar ohne die Funktion zu beeinflussen
        this.setLayout(new ScrollPaneLayout(){
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane) parent;
                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;
                viewport.setBounds(availR);
            }
        });
    }
}
