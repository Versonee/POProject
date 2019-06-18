import java.awt.image.SampleModel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Trasa implements Serializable {
    private Lotnisko start;
    private Lotnisko koniec;
    private double odleglosc;
    private LocalDate data_utworzenia;
    private Samolot samolot;
    private int Co_ile;

    public Trasa(Lotnisko start, Lotnisko koniec, double odleglosc,Samolot samolot, int co_ile, LocalDate data){
        this.start = start;
        this.koniec = koniec;
        this.samolot = samolot;
        this.odleglosc = odleglosc;
        this.Co_ile = co_ile;
        this.data_utworzenia = data;
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

    public LocalDate DataUtworzenia() { return data_utworzenia;}

    //funkcja zwracajaca nowo utworzony lot
    //dokoncze
    public Lot NowyLot(LocalDate data, int numer)
    {

        Lot lot = new Lot(this, data, numer);
        return lot;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
