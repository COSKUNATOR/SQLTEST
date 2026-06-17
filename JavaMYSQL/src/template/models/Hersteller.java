package template.models;

import java.util.HashMap;
import java.util.Map;

public class Hersteller {
    public static final Map<Integer,Hersteller> hersteller = new HashMap<>();

    private final int nummer;
    private String name;

    public Hersteller(int nummer, String name){
        this.nummer = nummer;
        this.name = name;

        hersteller.put(nummer,this);
    }

    public int getNummer(){
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
        return "Hersteller{" +
                "nummer=" + nummer +
                ", name='" + name + '\'' +
                '}';
    }
}
