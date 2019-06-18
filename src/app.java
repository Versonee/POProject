import java.io.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

//co trzeba zrobic:

// - !!!!!!!!!ZROBIONE!!!!!!!!!!!! dodac zapis i odzcyt lotow z pliku
// - trzeba jakas zmienna co przechowuje date i ja modyfkikowac to bede mogl dokonczy generowanie lotow
// - !!!!!!!!!ZROBIONE!!!!!!!!!!!!trzeba jakas zmienna co perzchowuje ilosc lotow, dzieki temu automatycznie bedzie ustalac nowe nr lotu a nie prosic  urzytkownika
// - !!!!!!!!!ZROBIONE!!!!!!!!!!!!funkjce do kupowania i zwracania biloetow, jak beda problemy to rzaem cos ogarniemy

public class app {
    //Funkcja wczytująca z pliku wszystkie dane do systemu : pasazerow, lotniska, samoloty, dostepne trasy, aktywne loty.
    public static void wczytajZPliku(List<Samolot> lista_s, List<Lotnisko> lista_l, List<Trasa> lista_t, List<Klient> lista_k, List<Lot> lista_lot) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.bin"));
            lista_s.addAll((List<Samolot>) inputStream.readObject());
            lista_t.addAll((List<Trasa>) inputStream.readObject());
            lista_l.addAll((List<Lotnisko>) inputStream.readObject());
            lista_k.addAll((List<Klient>) inputStream.readObject());
            lista_lot.addAll((List<Lot>) inputStream.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku, po prawidłowym zamknięciu programu plik zostanie utworzony automatycznie");
           // System.exit(-1);
        } catch (ClassNotFoundException e) {

            System.out.println("Nie znaleziono klasy");
            //System.exit(-1);
        } catch (IOException e) {
            System.out.println("IOException");
            //System.exit(-1);
        }
    }

    //Funkcja zapisująca do pliku wszystkie dane z systemu : pasazerow, lotniska, samoloty i dostepne trasy.
    public static void zapiszDoPliku(List<Samolot> lista_s, List<Lotnisko> lista_l, List<Trasa> lista_t, List<Klient> lista_k, List<Lot> lista_lot) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.bin"))) {
            outputStream.writeObject(lista_s);
            outputStream.writeObject(lista_t);
            outputStream.writeObject(lista_l);
            outputStream.writeObject(lista_k);
            outputStream.writeObject(lista_lot);

        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(-1);
        }
    }
    //funkcja zwracająca kolejne wolne id lotu, jako parametr przyjmuje listę lotów, gdy nie ma żadnych lotów zwróci początkowe id czyli 1.
    public static int nextId(List<Lot> lista){
        int id_max=0;
        for(Lot l : lista)
        {
            if (l.zwrocId()>id_max) id_max = l.zwrocId();
        }
        return id_max+1;
    }
    //Funkcja głowna. Odpowiedzialna jest za wiekszosc funkcjonalnosci systemu
    public static void main(String[] args) {
        List<Samolot> lista_s = new LinkedList<>();
        List<Trasa> lista_t = new LinkedList<>();
        List<Lotnisko> lista_l = new LinkedList<>();
        List<Klient> lista_k = new LinkedList<>();
        List<Lot> lista_lot = new LinkedList<>();
        wczytajZPliku(lista_s, lista_l, lista_t, lista_k, lista_lot);
        Scanner scan = new Scanner(System.in);
        String endl = System.getProperty("line.separator");
        int c;
        while (true) {
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Wyświetl");
            System.out.println("2. Dodaj");
            System.out.println("3. Usuń");
            System.out.println("4. System biletów"); //to zostawiam wam
            System.out.println("5. Update"); //po wybraniu tego program sprawdza które loty/ trasy sie juz odbyly i je usuwa
            System.out.println("0. Wyjdź");
            c = scan.nextInt();
            scan.nextLine();
            switch (c) {
                case 0: {
                    zapiszDoPliku(lista_s, lista_l, lista_t, lista_k, lista_lot);
                    System.exit(0);
                }
                case 2: {
                    int c_2 = -1;
                    while (c_2 != 0) {
                        System.out.println("DODAWANIE");
                        System.out.println("1. Samoloty");
                        System.out.println("2. Lotniska");
                        System.out.println("3. Trasy");
                        System.out.println("4. Klienci");
                        System.out.println("0. Wyjdź");
                        c_2 = scan.nextInt();
                        scan.nextLine();
                        switch (c_2) {
                            case 1: {
                                System.out.println("DODAWANIE SAMOLOTU DO SYSTEMU:");
                                System.out.println("Podaj nazwę samolotu.");
                                String nazwa = scan.nextLine();
                                System.out.println("Podaj zasięg samolotu.");
                                int zasieg = scan.nextInt();
                                scan.nextLine();
                                if (zasieg < 0) {
                                    System.out.println("BŁĄD. Zasięg nie może być wartością mniejszą od 0");
                                    break;
                                }
                                System.out.println("Podaj liczbę miejsc w samolocie.");
                                int l_miejsc = scan.nextInt();
                                scan.nextLine();
                                if (l_miejsc < 0) {
                                    System.out.println("BŁĄD. Liczba miejsc nie może być wartością mniejszą od 0");
                                    break;
                                }
                                Samolot s = new Samolot(nazwa, zasieg, l_miejsc);
                                lista_s.add(s);
                                System.out.println("POMYŚLNIE DODANO SAMOLOT DO SYSTEMU:");
                                c_2 = 0;
                                break;
                            }
                            case 2: {
                                System.out.println("DODAWANIE LOTNISKA DO SYSTEMU:");
                                System.out.println("Podaj nazwę lotniska.");
                                String nazwa = scan.nextLine();
                                System.out.println("Podaj szerokosc geograficzna lotniska(x).");
                                double x = 0;
                                while (x <= 0) //polozenie nie byc ujemnie
                                {
                                    x = scan.nextDouble();
                                }
                                System.out.println("Podaj wysokosc geograficzna lotniska(y).");
                                double y = 0;
                                while (y <= 0) //polozenie nie byc ujemnie
                                {
                                    y = scan.nextDouble();
                                }
                                Lotnisko l = new Lotnisko(nazwa, x, y);
                                lista_l.add(l);
                                System.out.println("POMYŚLNIE DODANO LOTNISKO DO SYSTEMU:");
                                c_2 = 0;
                                break;
                            }
                            case 3: {
                                System.out.println("DODAWANIE TRASY DO SYSTEMU:");
                                System.out.println("Wybierz lotnisko startowe:");
                                int i = 0;
                                for (Lotnisko l : lista_l) {
                                    i++;
                                    System.out.println(i + ". " + l.nazwa);

                                }
                                try {
                                    i = scan.nextInt();
                                }
                                catch (InputMismatchException e) {
                                    System.out.println("Błąd krytyczny!!!");
                                    scan.nextLine();
                                    break;
                                }
                                scan.nextLine();
                                try {
                                    lista_l.get(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }

                                Lotnisko start = lista_l.get(i - 1);
                                //lotnisko koncowe
                                System.out.println("Podaj lotnisko koncowe");
                                i = 0;
                                for (Lotnisko l : lista_l) {
                                    i++;
                                    System.out.println(i + ". " + l.nazwa);

                                }
                                i = scan.nextInt();
                                scan.nextLine();
                                try {
                                    lista_l.get(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }
                                Lotnisko kon = lista_l.get(i - 1);
                                double odl = Math.sqrt(Math.pow(start.X() - kon.X(), 2) + Math.pow(start.Y() - kon.Y(), 2));
                                //samolot
                                System.out.println("Wybierz samolot:");
                                i = 0;
                                List<Samolot> lista_s_pom = new LinkedList<>();
                                for (Samolot s : lista_s) {
                                    if (odl <= s.zasieg) {
                                        i++;
                                        lista_s_pom.add(s);
                                        System.out.println(i + ". " + s.nazwa);

                                    }
                                }
                                i = scan.nextInt();
                                scan.nextLine();
                                Samolot s = lista_s_pom.get(i - 1);

                                //co ile czasu ma byc powtarzany lot
                                System.out.println("Co ile dni ma powtarzac sie lot po danej trasie: ");
                                System.out.println("Podaj 0 jezeli nie ma sie powtarzac, max wartosc 30 dni");
                                int ile = -1;
                                while(ile <0 || ile > 30)
                                {
                                    ile = scan.nextInt();
                                    if(ile < 0)
                                        System.out.println("Wartosc nie moze byc ujemna");
                                    else if(ile > 30)
                                        System.out.println("Maksymalna wartosc to 30 dni");
                                }
                                Trasa t = new Trasa(start, kon, odl, s, ile);
                                lista_t.add(t);
                                c_2 = 0;
                                break;
                            }
                            case 4: {
                                System.out.println("DODAWANIE KLIENTÓW DO SYSTEMU:");
                                System.out.println("Podaj imię / nazwę firmy");
                                String imie = scan.nextLine();
                                System.out.println("Podaj nazwisko / ENTER w przypadku firmy");
                                String nazw = scan.nextLine();
                                if (nazw.equals("")) {
                                    Firma k = new Firma(imie);
                                    lista_k.add(k);
                                } else {
                                    Osoba k = new Osoba(imie, nazw);
                                    lista_k.add(k);
                                }
                                c_2 = 0;
                                break;

                            }
                            case 0: {
                                c_2 = 0;
                                break;
                            }
                            default: {
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
                        System.out.println("4. Klienci");
                        c_2 = scan.nextInt();
                        switch (c_2) {
                            case 1: {
                                int i = 1;
                                System.out.println(endl + "SAMOLOTY:");
                                for (Samolot s : lista_s) {
                                    System.out.println(i + ". " + s.nazwa);
                                    i++;
                                }
                                System.out.println(endl);
                                c_2 = 0;
                                break;
                            }
                            case 2: {
                                int i = 1;
                                System.out.println(endl + "LOTNISKA");
                                for (Lotnisko l : lista_l) {
                                    System.out.println(i + ". " + l.nazwa);
                                    i++;
                                }
                                System.out.println(endl);
                                c_2 = 0;
                                break;
                            }
                            case 3: {
                                int i = 1;
                                System.out.println(endl + "TRASY");
                                for (Trasa t : lista_t) {
                                    System.out.println(i + ". " + t.Start().toString() + " -> " + t.Koniec().toString() + "Co" + t.CoIle() + " dni");
                                    i++;
                                }
                                System.out.println(endl);
                                c_2 = 0;
                                break;
                            }
                            case 4: {
                                int i = 1;
                                System.out.println(endl + "KLIENCI");
                                for (Klient k : lista_k) {
                                    System.out.println(i + ". " + k.info() + " -> " + k.typ()+" liczba biletów: "+k.iloscBiletow());
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
                case 3: {
                    int c_2 = -1;
                    while (c_2 != 0) {
                        System.out.println("Usuwanie");
                        System.out.println("1. Samoloty");
                        System.out.println("2. Lotniska");
                        System.out.println("3. Trasy");
                        System.out.println("4. Klienci");
                        c_2 = scan.nextInt();
                        scan.nextLine();
                        switch (c_2) {
                            case 1: {
                                int i = 0;
                                for (Samolot s : lista_s) {
                                    i++;
                                    System.out.println(i + ". " + s.nazwa);
                                }
                                i = scan.nextInt();
                                scan.nextLine();
                                try {
                                    lista_s.remove(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }
                                c_2 = 0;
                                break;
                            }
                            case 2: {
                                int i = 0;
                                for (Lotnisko l : lista_l) {
                                    i++;
                                    System.out.println(i + ". " + l.nazwa);
                                }
                                i = scan.nextInt();
                                scan.nextLine();
                                try {
                                    lista_l.remove(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }
                                c_2 = 0;
                                break;
                            }
                            case 3: {
                                int i = 0;
                                for (Trasa t : lista_t) {
                                    i++;
                                    System.out.println(i + ". " + t.Start() + " -> " + t.Koniec() + "Co" + t.CoIle() + " dni");
                                }
                                i = scan.nextInt();
                                scan.nextLine();
                                try {
                                    lista_t.remove(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }
                                c_2 = 0;
                                break;
                            }
                            case 4: {
                                int i = 0;
                                for (Klient k : lista_k) {
                                    i++;
                                    System.out.println(i + ". " + k.info());
                                }
                                i = scan.nextInt();
                                scan.nextLine();
                                try {
                                    lista_k.remove(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }
                                c_2 = 0;
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Kim jesteś?");
                    int i=1;
                    for (Klient k : lista_k) {
                        System.out.println(i + ". " + k.info());
                        i++;
                    }
                    i = scan.nextInt();
                    scan.nextLine();

                    try {
                        lista_k.get(i-1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                        break;
                    }
                    int c_2 = 1;
                    Klient k = lista_k.get(i - 1);
                    while(c_2!=0) {

                        System.out.println(endl + "Jestem " + k.info());
                        System.out.println("1. Moje bilety");
                        System.out.println("2. Kup bilet");
                        c_2 = scan.nextInt();
                        switch (c_2) {
                            case 1: {
                                i=0;
                                System.out.println("1. Lista moich biletow:"+endl);
                                for(Bilet b : k.bilety()) {
                                    i++;
                                    System.out.println(i+". Numer Lotu: "+b.NumerLotu()+" Numer miejsca: "+b.NumerMiejsca());
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("KUPOWANIE BILETU:");
                                System.out.println("Wybierz lot:"+endl);
                                i=0;
                                if(lista_lot.size()==0){
                                    System.out.println("Przepraszamy. Nie ma dostępnych lotów");
                                    c_2 = 0;
                                    break;
                                }
                                for(Lot l : lista_lot){
                                    System.out.println(i+". "+l.info());
                                }
                                i=-1;
                                while(i <0 || i>lista_lot.size())
                                {
                                    try {
                                        i = scan.nextInt();
                                        scan.nextLine();
                                    }
                                    catch(InputMismatchException e){
                                        i=-1;
                                    }
                                }
                                Lot l = lista_lot.get(i-1);
                                System.out.println("Wybrano lot o id: "+l.zwrocId());
                                System.out.println("Dostępne miejsca w samolocie:");
                                String miejsca = "";
                                i=1;
                                for(int n : l.getMiejsca()){
                                    if(n==1) miejsca+=n+" ";
                                    i++;
                                }
                                System.out.println(miejsca+endl);
                                System.out.println("Wybierz miejsce:");
                                i = scan.nextInt();
                                scan.nextLine();
                                while(!l.KupBilet(k,i)){
                                    System.out.println("Cos poszło nie tak. Wybierz ponownie miejsce");
                                    i = scan.nextInt();
                                    scan.nextLine();
                                }
                                System.out.println("Sukces. Miejsce zostało kupione");
                                c_2 =0;
                                break;
                            }
                            case 0: {
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                    break;
                }
                case 5:
                {

                    break;
                }

                default:
                    break;
            }
        }
    }
}
