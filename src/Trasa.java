import java.io.Serializable;
import java.util.List;

public class Trasa implements Serializable {
    public Lotnisko start;
    public Lotnisko koniec;
    public int odleglosc;
    public Samolot samolot;
    public String data;
    public Trasa(Lotnisko start, Lotnisko koniec, int odleglosc, Samolot samolot, String data){
        this.start = start;
        this.koniec = koniec;
        this.odleglosc = odleglosc;
        this.samolot = samolot;
        this.data = data;
    }
}
