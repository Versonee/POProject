import java.io.Serializable;
import java.util.List;

public abstract class Klient implements Serializable {
    abstract String info();
    abstract List<Bilet> bilety();
    abstract String typ();
}
