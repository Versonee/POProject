import com.sun.javafx.geom.Vec2d;

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

    public Vec2d Polozenie()
    {
        Vec2d vec = new Vec2d(x, y);
        return vec;
    }
}
