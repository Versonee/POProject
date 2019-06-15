import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class app {
    //Funkcja wczytująca z pliku wszystkie dane do systemu : pasazerow, lotniska, samoloty i dostepne trasy.
    public static void wczytajZPliku(List<Samolot> lista_s, List<Lotnisko> lista_l, List<Trasa> lista_t, List<Klient> lista_k) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.bin"));
            lista_s.addAll((List<Samolot>) inputStream.readObject());
            lista_t.addAll((List<Trasa>) inputStream.readObject());
            lista_l.addAll((List<Lotnisko>) inputStream.readObject());
            lista_k.addAll((List<Klient>) inputStream.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound");
            System.exit(-1);
        } catch (ClassNotFoundException e) {

            System.out.println("ClassNotFound");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(-1);
        }
    }

    //Funkcja zapisująca do pliku wszystkie dane z systemu : pasazerow, lotniska, samoloty i dostepne trasy.
    public static void zapiszDoPliku(List<Samolot> lista_s, List<Lotnisko> lista_l, List<Trasa> lista_t, List<Klient> lista_k) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.bin"))) {
            outputStream.writeObject(lista_s);
            outputStream.writeObject(lista_t);
            outputStream.writeObject(lista_l);
            outputStream.writeObject(lista_k);

        } catch (IOException e) {
            System.exit(-1);
        }
    }

    //Funkcja głowna. Odpowiedzialna jest za wiekszosc funkcjonalnosci systemu
    public static void main(String[] args) {
        List<Samolot> lista_s = new LinkedList<>();
        List<Trasa> lista_t = new LinkedList<>();
        List<Lotnisko> lista_l = new LinkedList<>();
        List<Klient> lista_k = new LinkedList<>();
        wczytajZPliku(lista_s, lista_l, lista_t, lista_k);
        Scanner scan = new Scanner(System.in);
        String endl = System.getProperty("line.separator");
        int c;
        while (true) {
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Wyświetl");
            System.out.println("2. Dodaj");
            System.out.println("3. Usuń");
            System.out.println("0. Wyjdź");
            c = scan.nextInt();
            scan.nextLine();
            switch (c) {
                case 0: {
                    zapiszDoPliku(lista_s, lista_l, lista_t, lista_k);
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
                                i = scan.nextInt();
                                scan.nextLine();
                                try {
                                    lista_l.get(i - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("NIEDOZWOLONA OPERACJA. Wybrano element spoza listy!!!");
                                    break;
                                }
                                Lotnisko start = lista_l.get(i - 1);
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
                                LocalDate data = LocalDate.now();
                                Samolot s = lista_s_pom.get(i - 1);
                                Trasa t = new Trasa(start, kon, odl, s, data.toString());
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
                                    System.out.println(i + ". " + t.start.toString() + " -> " + t.koniec.toString() + "   data: " + t.data);
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
                                    System.out.println(i + ". " + k.info() + " -> " + k.typ());
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
                                    System.out.println(i + ". " + t.start + " -> " + t.koniec + "   data: " + t.data);
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
                default:
                    break;
            }
        }
    }
}
