public class Bilet {
    private int  numer_miejsca;
    private int numer_lotu;

    public Bilet(int nr_lotu, int nr_miejsca)
    {
        this.numer_lotu = nr_lotu;
        this.numer_miejsca = nr_miejsca;
    }

    public int NumerMiejsca()
    {
        return numer_miejsca;
    }

    public int NumerLotu(){
        return numer_lotu;
    }

}
