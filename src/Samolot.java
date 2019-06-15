
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
    }
}
