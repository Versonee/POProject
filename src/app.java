import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Sys sys = new Sys();
        Scanner scan = new Scanner(System.in);
        String endl = System.getProperty("line.separator");
        int c;
        while (true) {
            System.out.println("MUNU GŁÓWNE");
            System.out.println("1. Wyświetl");
            System.out.println("2. Dodaj");
            System.out.println("3. Usuń");
            System.out.println("0. Wyjdź");
            c = scan.nextInt();
            scan.nextLine();
            switch (c) {
                case 0: {
                    System.exit(0);
                }
                case 2: {
                    int c_2 =-1;
                    while(c_2!=0) {
                        System.out.println("DODAWANIE");
                        System.out.println("1. Samoloty");
                        System.out.println("2. Lotniska");
                        System.out.println("3. Trasy");
                        System.out.println("0. Wyjdź");
                        c_2 = scan.nextInt();
                        scan.nextLine();
                        switch(c_2){
                            case 1:
                                {
                                    System.out.println("DODAWANIE SAMOLOTU DO SYSTEMU:");
                                    System.out.println("Podaj nazwę samolotu.");
                                    String nazwa = scan.nextLine();
                                    System.out.println("Podaj zasięg samolotu.");
                                    int zasieg = scan.nextInt();
                                    scan.nextLine();
                                    if(zasieg<0){
                                        System.out.println("BŁĄD. Zasięg nie może być wartością mniejszą od 0");
                                        break;
                                    }
                                    System.out.println("Podaj liczbę miejsc w samolocie.");
                                    int l_miejsc = scan.nextInt();
                                    scan.nextLine();
                                    if(l_miejsc<0){
                                        System.out.println("BŁĄD. Liczba miejsc nie może być wartością mniejszą od 0");
                                        break;
                                    }
                                    Samolot s = new Samolot(nazwa, zasieg,l_miejsc);
                                    sys.dodaj_samolot(s);
                                    System.out.println("POMYŚLNIE DODANO SAMOLOT DO SYSTEMU:");
                                    c_2=0;
                                    break;
                                }
                            case 2:
                                {
                                    System.out.println("DODAWANIE LOTNISKA DO SYSTEMU:");
                                    System.out.println("Podaj nazwę lotniska.");
                                    String nazwa = scan.nextLine();
                                    Lotnisko l = new Lotnisko(nazwa);
                                    sys.lista_l.add(l);
                                    System.out.println("POMYŚLNIE DODANO LOTNISKO DO SYSTEMU:");
                                    c_2=0;
                                    break;
                                }
                            case 3:
                                {
                                    System.out.println("DODAWANIE TRASY DO SYSTEMU:");
                                    System.out.println("Wybierz lotnisko startowe:");
                                    int i = 0;
                                    for(Lotnisko l : sys.lista_l) {
                                        i++;
                                        System.out.println(i+". "+l.nazwa);

                                    }
                                    i = scan.nextInt();
                                    scan.nextLine();
                                    try {
                                        sys.lista_l.get(i - 1);
                                    }
                                    catch(IndexOutOfBoundsException e)
                                    {
                                        System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                        break;
                                    }
                                    Lotnisko start = sys.lista_l.get(i - 1);
                                            System.out.println("Podaj lotnisko koncowe");
                                    i = 0;
                                    for(Lotnisko l : sys.lista_l) {
                                        i++;
                                        System.out.println(i+". "+l.nazwa);

                                    }
                                    i = scan.nextInt();
                                    scan.nextLine();
                                    try {
                                        sys.lista_l.get(i - 1);
                                    }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                        break;
                                    }
                                    Lotnisko kon = sys.lista_l.get(i - 1);
                                    System.out.println("Podaj odleglość między lotniskami:");
                                    int odl = scan.nextInt();
                                    scan.nextLine();
                                    if(odl<0){
                                        System.out.println("BŁĄD. Odległość nie może być wartością mniejszą od 0");
                                        break;
                                    }
                                    System.out.println("Wybierz samolot:");
                                    i = 0;
                                    List<Samolot> lista_s_pom = new LinkedList<>();
                                    for(Samolot s : sys.lista_s) {
                                        if(odl<=s.zasieg) {
                                            i++;
                                            lista_s_pom.add(s);
                                            System.out.println(i + ". " + s.nazwa);

                                        }
                                    }
                                    i = scan.nextInt();
                                    scan.nextLine();
                                    LocalDate data = LocalDate.now();
                                    Samolot s = lista_s_pom.get(i-1);
                                    Trasa t = new Trasa(start, kon, odl, s, data.toString());
                                    sys.lista_t.add(t);
                                    c_2=0;
                                    break;
                                }
                            case 0:
                                {
                                    c_2=0;
                                    break;
                                }
                            default:
                                {
                                    break;
                                }
                        }
                    }
                    break;
                }

                case 1: {
                    int c_2 = -1;
                    while (c_2 != 0) {
                        System.out.println("WYŚWIETLANIE");
                        System.out.println("1. Samoloty");
                        System.out.println("2. Lotniska");
                        System.out.println("3. Trasy");
                        System.out.println("0. Wyjdź");
                        c_2 = scan.nextInt();
                        switch (c_2) {
                            case 0: {
                                c_2 = 0;
                                break;
                            }
                            case 1: {
                                int i = 1;
                                System.out.println(endl+"SAMOLOTY:");
                                for (Samolot s : sys.lista_s) {
                                    System.out.println(i + ". " + s.nazwa);
                                    i++;
                                }
                                System.out.println(endl);
                                c_2 = 0;
                                break;
                            }
                            case 2: {
                                int i = 1;
                                System.out.println(endl+"LOTNISKA");
                                for (Lotnisko l : sys.lista_l) {
                                    System.out.println(i + ". " + l.nazwa);
                                    i++;
                                }
                                System.out.println(endl);
                                c_2 = 0;
                                break;
                            }
                            case 3: {
                                int i = 1;
                                System.out.println(endl+"TRASY");
                                for (Trasa t : sys.lista_t) {
                                    System.out.println(i + ". " + t.start + " -> " + t.koniec + "   data: " + t.data);
                                    i++;
                                }
                                System.out.println(endl);
                                c_2 = 0;
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                    }
                    break;
                }
                case 3:
                {
                    int c_2 =-1;
                    while(c_2!=0) {
                        System.out.println("Usuwanie");
                        System.out.println("1. Samoloty");
                        System.out.println("2. Lotniska");
                        System.out.println("3. Trasy");
                        System.out.println("0. Wyjdź");
                        c_2 = scan.nextInt();
                        scan.nextLine();
                        switch(c_2) {
                            case 1:
                                {
                                    int i=0;
                                    for(Samolot s : sys.lista_s){
                                        i++;
                                        System.out.println(i+". "+s.nazwa);
                                    }
                                    i = scan.nextInt();
                                    scan.nextLine();
                                    try
                                    {
                                        sys.lista_s.remove(i-1);
                                    }
                                    catch(IndexOutOfBoundsException e)
                                    {
                                        System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                        break;
                                    }
                                    c_2=0;
                                    break;
                                }
                            case 2:
                                {
                                    int i=0;
                                    for(Lotnisko l : sys.lista_l){
                                        i++;
                                        System.out.println(i+". "+l.nazwa);
                                    }
                                    i = scan.nextInt();
                                    scan.nextLine();
                                    try
                                    {
                                        sys.lista_l.remove(i-1);
                                    }
                                    catch(IndexOutOfBoundsException e)
                                    {
                                        System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                        break;
                                    }
                                    c_2 = 0;
                                    break;
                                }
                            case 3:
                                {
                                    int i=0;
                                    for(Trasa t : sys.lista_t){
                                        i++;
                                        System.out.println(i + ". " + t.start + " -> " + t.koniec + "   data: " + t.data);
                                    }
                                    i = scan.nextInt();
                                    scan.nextLine();
                                    try
                                    {
                                        sys.lista_t.remove(i-1);
                                    }
                                    catch(IndexOutOfBoundsException e)
                                    {
                                        System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                        break;
                                    }
                                    c_2=0;
                                    break;
                                }
                            case 0:
                                {
                                    c_2 = 0;
                                    break;
                                }
                            default: break;
                        }
                        }
                    break;
                }
                default: break;
            }
        }
    }
}
