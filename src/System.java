import java.util.LinkedList;
import java.util.List;

public class System {
    public List<Samolot> lista_s;
    public List<Trasa> lista_t;
    public List<Lotnisko> lista_l;
    public List<Pasazer> lista_p;

    public System(){
        this.lista_l = new LinkedList<>();
        this.lista_s = new LinkedList<>();
        this.lista_t = new LinkedList<>();
        this.lista_p = new LinkedList<>();
    }
    public void dodaj_samolot(Samolot s){
        lista_s.add(s);
    }

}
