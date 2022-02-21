package Getraenkehandel;

import java.lang.String;
//Erbt genauso wie die Getraenke Klassen von der Produkt Klasse
public class Snack extends Produkt{

      public Snack(String name, double preis, int bestand){
        super(name, preis, bestand);
      }
}
