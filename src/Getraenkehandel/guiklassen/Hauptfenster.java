package Getraenkehandel.guiklassen;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import Getraenkehandel.Getraenk;
import Getraenkehandel.Kasse;
import Getraenkehandel.Produkt;
import Getraenkehandel.Snack;

public class Hauptfenster extends JFrame implements ActionListener{

    private int y_position;
    private int y_laenge_waren;
    private Getraenk aktuellesgetraenk;
    private Snack aktuellersnack;
    private JPanel getraenkeliste;
    private JPanel getraenkeanzeige;
    private JPanel snackliste;
    private Color coolcolour1;
    private Color coolcolour2;
    private Color coolcolour3;
    private Color coolcolour4;
    private Color coolcolour5;
    private String typ;
    private Kasse kasse;
    private HashMap<Produkt, Integer> warenkorb;

    private JLabel getraenkename;
    private JLabel getraenkebestand;
    private JLabel getraenkepreis;
    private JLabel getraenkealkohol;
    private JPanel snackanzeige;
    private MyLabel snackname;
    private MyLabel snackbestand;
    private MyLabel snackpreis;
    private JPanel warenkorbJPanel;
    private int getraenkeanzahl;
    private JFormattedTextField anzahlgetraenke;
    private MyButton getraenkwarenkorb;
    private MyButton snackwarenkorb;
    private JFormattedTextField anzahlsnacks;
    private int snackanzahl;
    private MyPanel warenkorbPanelaussen;

    private int xx,xy;
    private MyPanel topbar;
    private MyLabel kassenstand;

    private static final DecimalFormat runden = new DecimalFormat("0.00");

    public Hauptfenster(HashMap<String, Getraenk> getraenkemap, HashMap<String, Snack> snackmap, Kasse kasse){
        //Deklarieren der Farben und Importen der Bilder
        this.kasse = kasse;
        //Bestimmt die position der Button f??r getr??nke und snack liste
        y_position = 80;
        y_laenge_waren = 20;
        //Legt fest welches colourpallet genutzt wird
        int colorpallet = 2;
        //Platzthalter variable f??r auswahl was angezeigt werden soll
        typ = "getraenk";
        //Nimmt den Ersten eintrag aus den Maps und setzt in als Startwert f??r die Anzeige
        HashMap.Entry<String,Getraenk> entry1 = getraenkemap.entrySet().iterator().next();
        aktuellesgetraenk = entry1.getValue();

        HashMap.Entry<String,Snack> entry2 = snackmap.entrySet().iterator().next();
        aktuellersnack = entry2.getValue();

        //Erstellen des Warenkorbs
        warenkorb = new HashMap<>();

        //Festlegen der Colourpallets
        if(colorpallet == 1){
            coolcolour1 = new Color(210,190,255); 
            coolcolour2 = new Color(220,200,255);
            coolcolour3 = new Color(0, 0, 0);
            coolcolour4 = new Color(230,190,255); 
            coolcolour5 = new Color(120,80,155);
        }
        else if(colorpallet == 2){
            coolcolour1 = new Color(50,50,50);
            coolcolour2 = new Color(80,80,80); 
            coolcolour3 = new Color(255, 255, 255);
            coolcolour4 = new Color(70, 70, 70);
            coolcolour5 = new Color(60, 60, 60);
        }
        else{
            coolcolour1 = new Color(160,160,160); 
            coolcolour2 = new Color(150,150,150); 
            coolcolour3 = new Color(0, 0, 0);
            coolcolour4 = new Color(200, 200, 200);
            coolcolour5 = new Color(130, 130, 130);
        }

        //Bestimmt den h??heren Wert f??r die Scrollpane
        int laenge = Math.max(getraenkemap.size(), snackmap.size());

        //Frame Configureren
        this.setSize(1280,720);
        this.setTitle("Getr??nkeverwaltung");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(coolcolour1);
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setOpacity(0.98f);
        this.setLocationRelativeTo(null);

        //Erstellen der Panel
        topbar = new MyPanel(coolcolour1, 1280, 40);

        //Funktion um das Fenster during Runtime zu bewegen/ Sets die Mouse Location als neue Fenster Location
        topbar.addMouseListener(new MouseAdapter() {
            @Override
            //Getted die Mouselocation und speichert sie, wenn die maus geklickt wird
            public void mousePressed(MouseEvent e) {
                xx = e.getX();
                xy = e.getY();
            }
        });
        topbar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                //Setzt die Fensterlocation als die mouse location, wenn das fenster gezogen wird 
                int x = arg0.getXOnScreen();
                int y = arg0.getYOnScreen();
                Hauptfenster.this.setLocation(x - xx, y - xy);
            }
        });
        this.add(topbar ,BorderLayout.PAGE_START);        

        //Erstellend des Scrollpanels was in die Scroll hinzugef??gt werden soll, mit berechnung der ben??tigten l??nge anhand der Anzahl an Getr??nken/Snacks
        JPanel scrollpanel = new MyPanel(coolcolour1, 360, Math.max(100 + (laenge * 80), 680));

        //Erstellen der Scrollbar f??r die getr??nke und snacks
        JScrollPane scrollbar = new MyScrollPane(scrollpanel);
        this.add(scrollbar, BorderLayout.WEST); 

        //Panel f??r die liste aller getr??nke Buttons
        getraenkeliste = new MyPanel(coolcolour2, 0, 0, 360, Math.max(100 + (laenge * 80), 680));
        scrollpanel.add(getraenkeliste);

        //Panel f??r die liste aller snack buttons
        snackliste = new MyPanel(coolcolour5, 0, 0, 360, Math.max(100 + (laenge * 80), 680));
        snackliste.setVisible(false);
        scrollpanel.add(snackliste);

        //Anzeige panel, getr??nk und snack anzeige werden hier rein getan
        JPanel anzeige = new MyPanel(coolcolour4, 560, 680);
        this.add(anzeige, BorderLayout.CENTER);

        //Warenkorb panel aussen, wird in die scrollpane getan
        warenkorbPanelaussen = new MyPanel(coolcolour5, 360, 640);
        warenkorbPanelaussen.setLayout(null);

        //Warenkorb panel, wird in das warenkorbpanel aussen getan und die gr????e wird dynamisch anhand der anzahl an dingen im warenkorb bestimmt
        JPanel waren = new MyPanel(coolcolour5, 360, 680);
        waren.setLayout(new BorderLayout());
        this.add(waren, BorderLayout.EAST);  
        
        //Scrollpane f??r den warenkorb
        JScrollPane warenscroll = new MyScrollPane(warenkorbPanelaussen);
        warenscroll.setPreferredSize(new Dimension(360, 640));
        waren.add(warenscroll, BorderLayout.CENTER);

        //Panel in dem alle sachen im Warenkorb sein werden, gr????e wird angepasst ja anchdem wie viele artikel im warenkorb sind
        addanzeige();

        //Anzeige der Daten des Getraenkes
        anzeige.add(getraenkeanzeige = new MyPanel(coolcolour4, 0, 0, 920, 680));

        //Erstellen der Label f??r die Getr??nke, werden direkt in der getr??nkeanzeige add methode erstellt
        getraenkeanzeige.add(getraenkename = new MyLabel(coolcolour3, "Name: " + aktuellesgetraenk.getName(), 20, 0, 70, 560, 60));
        getraenkeanzeige.add(getraenkebestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden", 20, 0, 210, 560, 60));
        getraenkeanzeige.add(getraenkepreis = new MyLabel(coolcolour3, "Preis: " + aktuellesgetraenk.getpreis() + "???", 20, 0, 350, 560, 60));
        getraenkeanzeige.add(getraenkealkohol = new MyLabel(coolcolour3, "Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%", 20, 0, 490, 560, 60));

        //Snackanzeige
        snackanzeige = new MyPanel(coolcolour4, 0, 0, 920, 680);
        snackanzeige.setVisible(false);
        anzeige.add(snackanzeige);
        
        //Erstellen der Snack Label
        snackanzeige.add(snackname = new MyLabel(coolcolour3, "Name: " + aktuellersnack.getName(), 20, 0, 100, 560, 60));
        snackanzeige.add(snackbestand = new MyLabel(coolcolour3, "Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden", 20, 0, 300, 560, 60));
        snackanzeige.add(snackpreis = new MyLabel(coolcolour3, "Preis: " + aktuellersnack.getpreis() + "???", 20, 0, 500, 560, 60));

        
        //Erstellen der Bottumleiste f??r die Getr??nke, in dem das verkaufsfeld ist. Die Buttomleiste wird f??r getr??nke und snack getrennt da je nachdem ob
        //Getr??nke oder Snacks aufgerufen werdem m??ssen verschiedene Methoden f??r den verkauf benutzt werden m??ssen. 
        JPanel buttombargetraenke = new MyPanel(coolcolour1, 0, 640, 560, 40);
        getraenkeanzeige.add(buttombargetraenke);   

        //Erstellen der Buttomleiste f??r die Snacks
        JPanel buttombarsnack = new MyPanel(coolcolour1, 0, 640, 560, 40);
        snackanzeige.add(buttombarsnack);   

        //Input Felder f??r den Warenkorb
        anzahlgetraenke = new JFormattedTextField(NumberFormat.getNumberInstance());
        anzahlgetraenke.setText("0");
        anzahlgetraenke.setColumns(2);
        anzahlgetraenke.setBounds(0, 0, 280, 40);       
        buttombargetraenke.add(anzahlgetraenke);

        //Getr??nke Warenkorbbutton
        getraenkwarenkorb = new MyButton(coolcolour2, coolcolour3, "        Zum Warenkorb hinzuf??gen", 280, 0, 280, 40);
        getraenkwarenkorb.addActionListener(this);
        buttombargetraenke.add(getraenkwarenkorb);

        //Formated Text Feld was nur nummern an nimmt
        anzahlsnacks = new JFormattedTextField(NumberFormat.getNumberInstance());
        anzahlsnacks.setText("0");
        anzahlsnacks.setColumns(2);
        anzahlsnacks.setBounds(0, 0, 280, 40);       
        buttombarsnack.add(anzahlsnacks);

        //Snack Warenkorbbutton
        snackwarenkorb = new MyButton(coolcolour2, coolcolour3, "        Zum Warenkorb hinzuf??gen", 280, 0, 280, 40);
        snackwarenkorb.addActionListener(this);
        buttombarsnack.add(snackwarenkorb);

        //Hinzufuegen des Button zum schlie??en des Programms
        JButton schlie??en = new MyButton(coolcolour2, coolcolour3, "Schlie??en", 1160, 0, 120, 40);
        schlie??en.addActionListener(e -> System.exit(0));
        topbar.add(schlie??en);

        //Hinzufuegen des Button zum ausw??hlen des Getr??nkeliste
        JButton getraenkeButton = new MyButton(coolcolour2, coolcolour3, "Getr??nke", 0, 0, 180, 40);
        getraenkeButton.addActionListener(e -> changegetraenk());
        topbar.add(getraenkeButton);

        //Hinzufuegen des Button zum ausw??hlen der Snackliste
        JButton snackButton = new MyButton(coolcolour5, coolcolour3, "Snacks", 180, 0, 180, 40);
        snackButton.addActionListener(e -> changesnack());
        topbar.add(snackButton);

        //Button um den Bestand zu erh??hen
        JButton erhoeheBestand = new MyButton(coolcolour5, coolcolour3, "Bestand erh??hen", 640, 0, 280, 40);
        erhoeheBestand.addActionListener(e ->{
            //Bei dr??cken des Buttons, wird neues bestanderh??hen fenster ge??ffnet und die erh??hen methode ausgef??hrt
            Bestanderhoehen bestandfenster = new Bestanderhoehen(getraenkemap, snackmap, coolcolour3, coolcolour5, typ, this);
        });
        topbar.add(erhoeheBestand);

        //Zeigt kassenstand am oberen Bildschirmrand an
        kassenstand = new MyLabel(coolcolour3,"Kassenstand: " + runden.format(kasse.getkassenstand()) + "???", 20, 360, 0, 280, 40);
        kassenstand.setHorizontalAlignment(JLabel.CENTER);
        topbar.add(kassenstand);

        //Verkaufsbutton, ??bergibt warenkorb liste an die verkaufsfunktion
        JButton verkaufen = new MyButton(coolcolour4, coolcolour3, "Verkaufen");
        verkaufen.setPreferredSize(new Dimension(360, 40));
        verkaufen.add(new MyLabel(coolcolour3, "Verkaufen", 15));
        verkaufen.addActionListener(e -> Verkaufsfenster.verkaufen(warenkorb, coolcolour3, coolcolour1, kasse, this));
        waren.add(verkaufen, BorderLayout.PAGE_END);

        //Erstellen der Getr??nkeliste indem zuerst die ??berschrift und anschlie??end die Buttons f??r die Getr??nke eingef??gt werden
        getraenkeliste.add(new MyLabel(coolcolour3, "Getr??nkeliste", 30, 0, 0, 360, 80));
        //Position des Buttons wir f??r jeden button nach unten verschoben
        getraenkemap.forEach((k,v) -> newgetraenk(y_position, k, v));

        //Zur??cksetzen der position an der, der erste button positioniert wird
        y_position = 80;
        //Erstellen der Snackliste indem zuerst die ??berschrift und anschlie??end die Buttons f??r die Snacks eingef??gt werden
        snackliste.add(new MyLabel(coolcolour3, "Snackliste", 30, 0, 0, 360, 80));
        snackmap.forEach((k,v) -> newsnack(y_position, k, v));
    }

    //Erstellt neuen Button f??r die Getr??nke Liste
    private void newgetraenk(int y, String name, Getraenk getraenk){
        JButton button = new MyButton(coolcolour1, coolcolour3, name, 60, y, 240, 50);
        button.addActionListener(e -> changeanzeigegetraenk(getraenk));
        y_position = y_position + 80;
        getraenkeliste.add(button);
    }

    //Erstellt neuen Button f??r die Sn??ck Liste
    private void newsnack(int y, String name, Snack snack){
        JButton button = new MyButton(coolcolour1, coolcolour3, name, 60, y, 240, 50);
        button.addActionListener(e -> changeanzeigesnack(snack));
        y_position = y_position + 80;
        snackliste.add(button);
    }

    //Wechselt Anzeige von Snack auf Getr??nk
    private void changegetraenk(){
        //??ndert typ auf getr??nk wenn auf getr??nk gewechselt wird
        typ = "getraenk";
        //Setzt vivibility allee snack panels auf false aund aller getraeke panel auf true
        snackliste.setVisible(false);
        getraenkeliste.setVisible(true);
        snackanzeige.setVisible(false);
        getraenkeanzeige.setVisible(true);
    }

    //Wechselt Anzeige von Getr??nk auf Snack
    private void changesnack(){
        typ = "snack";
        snackliste.setVisible(true);
        getraenkeliste.setVisible(false);
        snackanzeige.setVisible(true);
        getraenkeanzeige.setVisible(false);
    }

    //Aktuallisiert die Anzeige der Getr??nke Daten
    public void changeanzeigegetraenk(Getraenk getraenk){
        aktuellesgetraenk = getraenk;
        getraenkename.setText("Name: " + aktuellesgetraenk.getName());
        getraenkebestand.setText("Es sind noch: " + aktuellesgetraenk.getbestand() + " im Bestand vorhanden");
        getraenkepreis.setText("Preis: " + aktuellesgetraenk.getpreis() + "???");
        getraenkealkohol.setText("Hat einen Alkoholgehalt von: " + aktuellesgetraenk.getalkohol() + "%");
    }

    //Aktuallisiert die Anzeige der Sn??ck Daten
    public void changeanzeigesnack(Snack snack){
        aktuellersnack = snack;
        snackname.setText("Name: " + aktuellersnack.getName());
        snackbestand.setText("Es sind noch: " + aktuellersnack.getbestand() + " im Bestand vorhanden");
        snackpreis.setText("Preis: " + aktuellersnack.getpreis() + "???");
    }

    //Funktion wenn etwas zum warenkorb hinzugef??gt wird
    private void addWarenkorb(Produkt produkt, int anzahl){

        //Check ob genug bestand vorhanden ist
        if (produkt.getbestand() >= anzahl){
            if (anzahl > 0){
                //Checkt ob produkt schon im warenkorb ist, wenn ja wird die menge erh??ht, wenn nein wir es hinzugef??gt
                if(warenkorb.containsKey(produkt)){
                    //Check ob genug im bestand sind
                    if (produkt.getbestand() >= anzahl + warenkorb.get(produkt)){
                        warenkorb.put(produkt, warenkorb.get(produkt) + anzahl);
                    }
                    else {
                        openError(produkt);
                    }
                }
                else{
                    warenkorb.put(produkt, anzahl);
                }
                //Legt die l??nge des warenkorbspanels fest anhand daran wie viele dinge im warenkorb sind
                y_laenge_waren = 20 + (warenkorb.size()*20);
                warenkorbJPanel = new MyPanel(coolcolour5, 0, 10, 360, y_laenge_waren);
                GridLayout grid = new GridLayout(1 + warenkorb.size(), 4);
                warenkorbJPanel.setLayout(grid);
                //Label f??r die Kategorien
                JLabel anzeigelabel1 = new MyLabel(coolcolour3,"Name:",15);
                anzeigelabel1.setHorizontalAlignment(JLabel.CENTER);
                warenkorbJPanel.add(anzeigelabel1);
                JLabel anzeigelabel2 = new MyLabel(coolcolour3,"Anzahl:", 15);
                anzeigelabel2.setHorizontalAlignment(JLabel.CENTER);
                warenkorbJPanel.add(anzeigelabel2);
                JLabel anzeigelabel3 = new MyLabel(coolcolour3,"Preis:", 15);
                anzeigelabel3.setHorizontalAlignment(JLabel.CENTER);
                warenkorbJPanel.add(anzeigelabel3);
                JLabel anzeigelabel4 = new MyLabel(coolcolour3,"Gesamtpreis:", 15);
                anzeigelabel4.setHorizontalAlignment(JLabel.CENTER);
                warenkorbJPanel.add(anzeigelabel4);
                //Schleife die alle artikel im warenkorb durch geht
                warenkorb.forEach((k,v) -> {
                    //Erstellt f??r jeden Pordukt im Warenkorb 4 Label zur anzeige, muss das warenkorbJpanel jedesmal aktuallisieren  
                    double produktpreis = warenkorb.get(k) * k.getpreis();
                    JLabel label1 = new MyLabel(coolcolour3, k.getName(), 15);
                    label1.setHorizontalAlignment(JLabel.CENTER);
                    warenkorbJPanel.add(label1);
                    JLabel label2 = new MyLabel(coolcolour3, ""+ warenkorb.get(k), 15);
                    label2.setHorizontalAlignment(JLabel.CENTER);
                    warenkorbJPanel.add(label2);
                    JLabel label3 = new MyLabel(coolcolour3, k.getpreis() + " ???", 15);
                    label3.setHorizontalAlignment(JLabel.CENTER);
                    warenkorbJPanel.add(label3);
                    JLabel label4 = new MyLabel(coolcolour3, runden.format(produktpreis) + " ???", 15);
                    label4.setHorizontalAlignment(JLabel.CENTER);
                    warenkorbJPanel.add(label4);
                });
                //Revalidate das WarenkorbJPanel damit die ??nderungen angezeigt werden
                warenkorbPanelaussen.add(warenkorbJPanel);
                warenkorbJPanel.revalidate();
            }
        }
        else {
            openError(produkt);
        }
    }

    //Erstellt das Error Fenster was bei zu wenig bestand ge??ffnet wird
    private void openError (Produkt produkt){
        JOptionPane error = new JOptionPane();
        JOptionPane.showMessageDialog(error, "Es sind nicht genug " + produkt.getName() + " im Bestand.");
    }

    public void clearwarenkorb(){
        //Leert Warenkorb Hashmap
        this.warenkorb = new HashMap<>();
        //Warenkorb Panel leeren aber l??scht teil der anzeige mit, konnte das problem nicht finden
        y_laenge_waren = 20;
        warenkorbJPanel.removeAll();
        warenkorbJPanel.repaint();
        warenkorbJPanel.revalidate();
        addanzeige();
    }

    //Added die anzeige des Warenkorbs bei erstellen und leeren des Warenkorbs
    private void addanzeige(){
        warenkorbJPanel = new MyPanel(coolcolour5, 0, 10, 360, y_laenge_waren);
        GridLayout grid = new GridLayout(1, 4);
        warenkorbJPanel.setLayout(grid);
        JLabel anzeigelabel1 = new MyLabel(coolcolour3,"Name:",15);
        anzeigelabel1.setHorizontalAlignment(JLabel.CENTER);
        warenkorbJPanel.add(anzeigelabel1);
        JLabel anzeigelabel2 = new MyLabel(coolcolour3,"Anzahl:", 15);
        anzeigelabel2.setHorizontalAlignment(JLabel.CENTER);
        warenkorbJPanel.add(anzeigelabel2);
        JLabel anzeigelabel3 = new MyLabel(coolcolour3,"Preis:", 15);
        anzeigelabel3.setHorizontalAlignment(JLabel.CENTER);
        warenkorbJPanel.add(anzeigelabel3);
        JLabel anzeigelabel4 = new MyLabel(coolcolour3,"Gesamtpreis:", 15);
        anzeigelabel4.setHorizontalAlignment(JLabel.CENTER);
        warenkorbJPanel.add(anzeigelabel4);
        warenkorbPanelaussen.add(warenkorbJPanel);
    }

    //Damit andere Klassen die anzeige das aktuellen getr??nks/snacks aktuallisieren k??nnen, f??r bestandanzeigen genutzt
    public void refresh(){
        if (typ == "getraenk"){
            changeanzeigegetraenk(aktuellesgetraenk);
        }
        else{
            changeanzeigesnack(aktuellersnack);
        }
    }

    //Genutzt damit andere Klassen den Kassenstand aktuallisieren k??nnen
    public void kassenstandrefresh(){
        kassenstand.setText("Kassenstand: " + runden.format(kasse.getkassenstand()) + "???");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //??berpr??ft welcher Button gedr??ckt wurde
        if (e.getSource() == getraenkwarenkorb){
            //Pr??ft ob ein wert eingetragen wurde
            if (anzahlgetraenke.getValue() != null){
                //Nimmt den Value von dem inputfeld f??r den warenkorb
                getraenkeanzahl = ((Number) anzahlgetraenke.getValue()).intValue();
                addWarenkorb(aktuellesgetraenk, getraenkeanzahl);}
            }
        if (e.getSource() == snackwarenkorb){
            if (anzahlsnacks.getValue() != null){
                snackanzahl = ((Number) anzahlsnacks.getValue()).intValue();
                addWarenkorb(aktuellersnack, snackanzahl);
            }
        }
    }
}


