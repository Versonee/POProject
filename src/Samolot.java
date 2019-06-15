<<<<<<< HEAD
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
=======
import java.io.Serializable;

public class Samolot implements Serializable {
    public String nazwa;
    public int zasieg;
    public int l_miejsc;
    public Samolot(){};
    public Samolot(String nazwa, int zasieg, int l_miejsc){
        this.nazwa = nazwa;
        this.zasieg = zasieg;
        this.l_miejsc = l_miejsc;
    }
    public String toString(){
        return this.nazwa;
>>>>>>> aba650f9a81dd420c98f7a39da106c4a4d100dff
    }
}
