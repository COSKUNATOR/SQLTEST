package template;

import java.math.BigDecimal;

public class Onlineshop {
    public static void main(String[] args) {
        String alles = Service.selectAlleBestellung();
        System.out.println(alles);
        String anzahl = Service.selectAnzahlGekaufterprodukte(123123123);
        System.out.println(anzahl);

//        int nummer = Service.insertArtikel("Hundefutter", BigDecimal.valueOf(1.99), 2);
//        System.out.println(nummer);

        int betroffen = Service.updateArtikelPreis(6,BigDecimal.valueOf(1.49));
        System.out.println(betroffen);
    }
}
