public class Samolot {
    private int pojemnosc;
    private double zasieg;

    public Samolot(int pojemnosc, double zasieg)
    {
        this.pojemnosc = pojemnosc;
        this.zasieg = zasieg;
    }

    public double Zasieg()
    {
        return zasieg;
    }

    public int Pojemnosc()
    {
        return pojemnosc;
    }
}
