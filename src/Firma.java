import java.util.List;

public class Firma extends Klient {
    public String nazwa;
    public List<Bilet> bilety;

    public Firma(String nazwa){
        this.nazwa = nazwa;
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
        int i =0;
        for (Bilet b : this.bilety) i++;
        return i;
    }
}
