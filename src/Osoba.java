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
<<<<<<< HEAD
    public void kup_bilet(Trasa t, Samolot s)
    {

=======
    public String typ(){
        return "osoba fizyczna";
>>>>>>> aba650f9a81dd420c98f7a39da106c4a4d100dff
    }



}
