import java.util.LinkedList;
import java.util.List;

public class Firma extends Klient {
    private String nazwa;
    private List<Bilet> bilety;

    public Firma(String nazwa){

        this.nazwa = nazwa;
        this.bilety = new LinkedList<>();
    }
    public String info(){
        return nazwa;
    }
    public List<Bilet> bilety(){
        return bilety;
    }
    public String typ(){
        return "firma";
    }
    public int iloscBiletow(){
        return bilety.size();
    }
}
