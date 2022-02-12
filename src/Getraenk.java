import java.lang.String;

public class Getraenk extends Produkt{
    //Getraenke Klasse erbt von der Produkt klassen und hat dern Alkohol gehalt als extra attribut
    private double alkoholgehalt;

    public Getraenk(String name, double preis, int bestand, double alkoholgehalt){
        //Uebernimmt die werte name, preis, bestand von der vererbenden Klasse
        super(name, preis, bestand);

        this.alkoholgehalt = alkoholgehalt;

    }

    public double getalkohol(){
      return this.alkoholgehalt;
    }
}
