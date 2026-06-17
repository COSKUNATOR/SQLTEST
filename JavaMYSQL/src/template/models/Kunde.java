package template.models;

import java.util.HashMap;
import java.util.Map;

public class Kunde {
    public static final Map<Integer, Kunde> kunde = new HashMap<>();

    private final int nummer;
    private String name;

    public Kunde(int nummer, String name) {
        this.nummer = nummer;
        this.name = name;

        kunde.put(nummer, this);
    }

    public int getNummer() {
        return nummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "nummer=" + nummer +
                ", name='" + name + '\'' +
                '}';
    }
}
