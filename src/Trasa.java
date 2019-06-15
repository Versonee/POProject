import java.io.Serializable;
import java.util.List;


public class Trasa implements Serializable {
    public Lotnisko start;
    public Lotnisko koniec;
    public double odleglosc;
    public Samolot samolot;
    public String data;
    public int miejsca[];

    public Trasa(Lotnisko start, Lotnisko koniec, double odleglosc,Samolot samolot, String data){
        this.start = start;
        this.koniec = koniec;
        this.samolot = samolot;
        this.data = data;
        this.odleglosc = odleglosc;
        miejsca = new int[samolot.l_miejsc];
        for(int i = 0; i < miejsca.length; i++) //gdy miejsce od i ==0 znaczy że wolne jak 1 znaczy że zajęte
            miejsca[i] = 0;

    }

    //dokoncze
    public boolean RezerwojMiejsce(int nr)
    {
        if(miejsca[nr] == 0 )
        {
            miejsca[nr] = 1;
            return true;
        }
        else
        {
            return false;
        }
    }
    //dokonczycze
    public void ZwolnijMiejsce(int nr)
    {

    }



}
