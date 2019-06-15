import java.io.Serializable;

public class Lotnisko implements Serializable {
    public String nazwa;
    private double x;
    private double y;
    public Lotnisko(String nazwa, double x, double  y){

        this.nazwa = nazwa;
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return this.nazwa;
    }
    public double X(){
        return this.x;
    }
    public double Y(){
        return this.y;
    }
}
