import java.io.Serializable;
import java.util.List;


public class Trasa implements Serializable {
    private Lotnisko start;
    private Lotnisko koniec;
    private double odleglosc;
    private Samolot samolot;
    private String data;
    private int numer_lotu;
    private int miejsca[];
    private List<Klient> pasazerowie;

    public Trasa(Lotnisko start, Lotnisko koniec, double odleglosc,Samolot samolot, String data, int numer_lotu){
        this.start = start;
        this.koniec = koniec;
        this.samolot = samolot;
        this.data = data;
        this.odleglosc = odleglosc;
        this.numer_lotu = numer_lotu;
        miejsca = new int[samolot.l_miejsc];
        for(int i = 0; i < miejsca.length; i++) //gdy miejsce od i ==0 znaczy że wolne jak 1 znaczy że zajęte
            miejsca[i] = 0;

    }

    public String Data()
    {
        return data;
    }

    public int NumberLotu()
    {
        return numer_lotu;
    }

    //zwraca true jak sie udało zareerwowac wszystkie miejsca lub fail jak nie
    public boolean RezerwojMiejsceOsoba(Klient klient, int[]miejsca)
    {
        int tab[] = new int[10];
        for(int i = 0; i < miejsca.length; i++)
        {
            if(this.miejsca[miejsca[i]] != 0)
            {
                return false;
            }
        }

        for(int i = 0; i < miejsca.length; i++)
        {
            Bilet bilet = new Bilet(numer_lotu, miejsca[i]);
            this.miejsca[miejsca[i]] = 1;
            klient.bilety().add(bilet);
        }
        pasazerowie.add(klient);
        return true;
    }

    //dokoncze
    public void ZwolnijMiejsce(int nr)
    {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
