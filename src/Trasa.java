import java.awt.image.SampleModel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Trasa implements Serializable {
    private Lotnisko start;
    private Lotnisko koniec;
    private double odleglosc;
    private Samolot samolot;
    private int Co_ile;

    public Trasa(Lotnisko start, Lotnisko koniec, double odleglosc,Samolot samolot, int co_ile){
        this.start = start;
        this.koniec = koniec;
        this.samolot = samolot;
        this.odleglosc = odleglosc;
        this.Co_ile = co_ile;
    }
    //gettery
    public Lotnisko Start() { return start; }

    public Lotnisko Koniec()
    {
        return koniec;
    }

    public Samolot Samolot()
    {
        return samolot;
    }

    public int CoIle() { return Co_ile; }

    //funkcja zwracajaca nowo utworzony lot
    //dokoncze
    public Lot NowyLot(String data, int numer)
    {

        Lot lot = new Lot(this, data, numer);
        return lot;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
