import java.io.Serializable;

public class Lot implements Serializable {

    private Trasa trasa;
    private String data;
    private int nrLotu;
    private int[] miejsca;
    public Lot(Trasa trasa, String data, int nr)
    {
        this.trasa = trasa;
        this.data = data;
        nrLotu = nr;
        miejsca = new int[trasa.Samolot().l_miejsc];
        for(int i = 0; i < miejsca.length; i++)
        miejsca[i] = 0;

    }
    //funkjca zwraca true jezeli uda sie zzarezerwowac bilet i dodaje go do listy dla danego klienta
    public boolean KupBilet(Klient k, int miejsce)
    {
        //sprawdza czy dane miejsce jest wolne
        if(miejsca[miejsce] != 0)
            return false;
        //jezeli jest wolne to przypisuje je do danej soby
        else {
            this.miejsca[miejsce] = 1;
            Bilet bilet = new Bilet(nrLotu, miejsce);
            k.bilety().add(bilet);
            return true;
        }
    }
    public int zwrocId(){
        return this.nrLotu;
    }
    //funkjcja usuwa bilet dla danego klienta i zwlanie miejsce
    public void ZwrocBilet(Klient k, Bilet b)
    {
        miejsca[b.NumerMiejsca()] = 0;
        k.bilety().remove(b);
    }
    public String info(){
        return this.trasa.Start()+" -> "+this.trasa.Koniec()+"   "+this.data;
    }
    public int[] getMiejsca(){
        return miejsca;
    }
}
