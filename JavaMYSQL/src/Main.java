import template.models.Artikel;
import template.models.Hersteller;
import template.models.services.ArtikelService;
import template.models.services.HerstellerService;

public class Main {
    public static void main(String[] args) {
        HerstellerService.selectHersteller();
        ArtikelService.selectArtikel();

        for (Hersteller h : Hersteller.hersteller.values()){
            System.out.println(h);
        }

        for(Artikel a : Artikel.artikel.values()) {
            System.out.println(a);
        }
    }
}