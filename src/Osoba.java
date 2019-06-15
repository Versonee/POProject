import java.util.LinkedList;
import java.util.List;

public class Osoba extends Pasazer{
    public List<Bilet> bilety;
    public String imie;
    public String nazwisko;
    public Osoba(String imie, String nazwisko){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.bilety = new LinkedList<>();
    }

    public List<Bilet> zwroc_bilety(){
        return bilety;
    }
    public void kup_bilet(Trasa t, Samolot s)
    {

    }
}
