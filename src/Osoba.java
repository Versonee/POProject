import java.util.LinkedList;
import java.util.List;

public class Osoba extends Klient {
    public List<Bilet> bilety;
    public String imie;
    public String nazwisko;
    public Osoba(String imie, String nazwisko){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.bilety = new LinkedList<>();
    }
    public String info(){
        return imie+" "+nazwisko;
    }

    public List<Bilet> bilety(){
        return bilety;
    }

    public void kup_bilet(Trasa t, Samolot s) {

    }

    public String typ(){
        return "osoba fizyczna";

    }
    public int iloscBiletow(){
        int i =0;
        for (Bilet b : this.bilety) i++;
        return i;
    }



}
