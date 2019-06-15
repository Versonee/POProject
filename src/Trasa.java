import java.io.Serializable;
import java.util.List;

public class Trasa implements Serializable {
    public Lotnisko start;
    public Lotnisko koniec;
    public double odleglosc;
    public Samolot samolot;
    public String data;
    public int miejsca[];

    public Trasa(Lotnisko start, Lotnisko koniec, double odleglosc, Samolot samolot, String data){
        this.start = start;
        this.koniec = koniec;
        this.odleglosc = odleglosc;
        this.samolot = samolot;
        this.data = data;
        miejsca = new int[samolot.Pojemnosc()];
    }



}
