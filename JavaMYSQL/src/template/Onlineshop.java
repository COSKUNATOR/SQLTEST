package template;

import template.models.*;
import template.models.services.*;

public class Onlineshop {
        public static void main(String[] args) {
            HerstellerService.selectHersteller();
            ArtikelService.selectArtikel();
            KundeService.selectKunde();
            AdresseService.selectAdresse();
            BestellungService.selectBestellung();
            BestellpositionService.selectBestellposition();



            for(Artikel a : Artikel.artikel.values()){
                System.out.println(a);
            }

            for(Kunde k : Kunde.kunde.values()){
                System.out.println(k);
            }

            for(Adresse a : Adresse.adresse.values()){
                System.out.println(a);
            }

            for(Bestellung b : Bestellung.bestellung.values()){
                System.out.println(b);
            }

            for (Bestellposition b : Bestellposition.bestellpositionen){
                System.out.println(b);
            }

            Hersteller hersteller = null;
            // HerstellerService.createHersteller("IHK");
            for (Hersteller h : Hersteller.hersteller.values()){
                if(h.getNummer() == 4){
                    hersteller = h;
                }
            }

            if(HerstellerService.updateHersteller(hersteller, "name", "bambule")){
                System.out.println("Name wurde aktualisiert ");
                for (Hersteller h : Hersteller.hersteller.values()){
                    System.out.println(h);
                }
            }

        }

        // letzter Block in MySQL.java und wie der Link funktioniert fragen
        // Reihenfolge egal, wie was abgefragt wird?
        // warum wird der Konstruktor Tooltip nicht angezeigt?
}
