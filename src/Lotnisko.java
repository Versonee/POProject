import java.io.Serializable;

public class Lotnisko implements Serializable {
    public String nazwa;
    public Lotnisko(String nazwa){
        this.nazwa = nazwa;
    }
    public String toString()
    {
        return this.nazwa;
    }
}
