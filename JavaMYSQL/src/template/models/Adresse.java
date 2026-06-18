package template.models;

import java.util.HashMap;
import java.util.Map;

public class Adresse {
    public static final Map<Integer, Adresse> adresse = new HashMap<>();

    private final int id;
    private String strassenr;
    private String plz;
    private String ort;
    private final Kunde kunde;

    public Adresse(int nummer, String strassenr, String plz, String ort, Kunde kunde){
        this.id = nummer;
        this.strassenr = strassenr;
        this.plz = plz;
        this.ort = ort;
        this.kunde = kunde;

        adresse.put(nummer, this);
    }

    public int getId() {
        return id;
    }

    public String getStrassenr() {
        return strassenr;
    }

    public void setStrassenr(String strassenr) {
        this.strassenr = strassenr;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Kunde getKunde() {
        return kunde;
    }


    @Override
    public String toString() {
        return "Adresse{" +
                "nummer=" + id +
                ", strassenr='" + strassenr + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", kunde=" + kunde +
                '}';
    }
}
