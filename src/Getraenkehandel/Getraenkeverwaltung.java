package Getraenkehandel;

import java.util.HashMap;

import Getraenkehandel.guiklassen.Hauptfenster;

public class Getraenkeverwaltung{
    public static void main(String[] args) {

        Kasse kasse = new Kasse(100);

        // Erstelen der Map in der die Getraenke und Snack Objekte gespeichert werden.
        HashMap<String, Getraenk> getraenke = new HashMap<>();
        HashMap<String, Snack> snacks = new HashMap<>();

        // Hinzufügen der Getraenke mit Konstuktor
        getraenke.put("Fanta", new Getraenk("Fanta", 3, 30, 0));
        getraenke.put("Cola", new Getraenk("Cola", 2.99, 50, 0));
        getraenke.put("Cola Zero", new Getraenk("Cola Zero", 2.99, 20, 0));
        getraenke.put("Cola Light", new Getraenk("Cola Light", 2.99, 10, 0));
        getraenke.put("Vodka", new Getraenk("Vodka", 5, 30, 40));
        getraenke.put("Tomatensaft", new Getraenk("Tomatensaft", 1, 5, 0));
        getraenke.put("WhiteRussian", new Getraenk("WhiteRussian", 0.49, 40, 35));
        getraenke.put("Fritz", new Getraenk("Fritz", 2, 50, 0));
        getraenke.put("Effekt", new Getraenk("Effekt", 1, 10, 0));
        getraenke.put("Effekt Zero", new Getraenk("Effekt Zero", 1, 10, 0));



        // Hinzufügen der Snacks mit Konstruktor
        snacks.put("Chips", new Snack("Chips", 1.99, 100));
        snacks.put("Nuesse", new Snack("Nuesse", 2.49, 30));
        snacks.put("Pringels", new Snack("Pringels", 3.99, 50));
        snacks.put("Popkorn", new Snack("Popkorn", 3.49, 10));

        Hauptfenster hauptfenster = new Hauptfenster(getraenke, snacks, kasse);
        hauptfenster.setVisible(true); //Macht das Hauptfenster sichtbar
    }
}
