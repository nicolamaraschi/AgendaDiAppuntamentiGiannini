package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import codice.*;
public class InterafacciaUtente {
    public static void main(String[] args) {
        Agenda agenda=new Agenda("nicola");
        Scanner scanner = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        int parolaScelta = 10;

        do {
            System.out.println("1:CREA AGENDA MEDIANTE NOME");
            System.out.println("2.CANCELLA AGENDA");
            System.out.println("3.CREA AGENDA MEDIANTE FILE APPUNTAMENTI");
            System.out.println("4.SCRIVI AGENDA SU FILE");
            System.out.println("5.INSERISCI NUOVO APPUNTAMENTO IN AGENDA");
            System.out.println("6.MODIFICA I DATI DELL'APPUNTAMENTO");
            System.out.println("7.CERCA APPUNTAMENTO MEDIANTE DATA");
            System.out.println("8.CERCA APPUNTAMENTO MEDIANTE NOME PERSONA DI CUI SI HA L'APPUNTAMENTO");
            System.out.print("Enter Your Choice : ");
            parolaScelta = s.nextInt();

            switch (parolaScelta) {
                case 1: {
                    System.out.print("Inserisci il nome da dare all'agenda : ");
                    String nomePerAgenda = scanner.nextLine();
                    agenda.creaAgendaDalNome(nomePerAgenda);
                    break;
                }
                case 2: {
                    System.out.println("----------------------------");
                    System.out.print("Inserisci il nome dell'agenda da cancellare : ");
                    agenda.cancellaAgenda(agenda);
                    System.out.println("----------------------------");
                    break;
                }
                case 3: {
                    agenda.creaAgendaDaFile(agenda);
                    System.out.println("----------------------------");
                    break;
                }
                case 4: {
                    try {
                        agenda.ScritturaAgendaSulFile(agenda);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("----------------------------");
                    break;
                }
                case 5:
                    int i = 1;
                    System.out.print("Su quale agenda vuoi operare :");
                    for (Agenda iteratore : agenda.getListaAgende()) {
                        System.out.println(i + " " + iteratore.getNomeAgenda());
                        i++;
                    }
                    int numeroAgenda;
                    do {
                        System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                        numeroAgenda = scanner.nextInt();
                    } while (numeroAgenda < 1 || numeroAgenda > i);
                    Agenda agenda1 = agenda.getListaAgende().get(numeroAgenda);
                    System.out.print("Inserisci la data dell'appuntamento :");
                    Scanner scan = new Scanner(System.in);
                    LocalDate dataAppuntamento = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
                    DateTimeFormatter ld = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.print("Inserisci l'ora dell'appuntamento :");
                    String time = scan.next();  //default format: hh:mm:ss
                    LocalTime oraAppuntamento = LocalTime.parse(time);
                    System.out.print("Inserisci la dutara dell'appuntamento :");
                    int durataAppuntamento = scanner.nextInt();
                    System.out.print("inserisci il nome della persona da incontrare all'appuntamento :");
                    String nomeAppuntamento = scanner.nextLine();
                    System.out.print("Inserisci luogo dell'appuntamento :");
                    String luogoAppuntamento = scanner.nextLine();

                    agenda.inserisciAppuntantoAllAgenda(agenda1, LocalDate.parse(dataAppuntamento.format(ld)), oraAppuntamento, durataAppuntamento, nomeAppuntamento, luogoAppuntamento);
                    break;
                case 6:
                    i = 1;
                    System.out.print("Su quale agenda vuoi operare :");
                    for (Agenda iteratore : agenda.getListaAgende()) {
                        System.out.println(i + " " + iteratore.getNomeAgenda());
                        i++;
                    }
                    do {
                        System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                        numeroAgenda = scanner.nextInt();
                    } while (numeroAgenda < 1 || numeroAgenda > i);
                    agenda1 = agenda.getListaAgende().get(numeroAgenda);

                    i = 1;
                    System.out.print("Su quale appuntamento vuoi operare :");
                    for (Appuntamento iteratore : agenda.getListaAppuntamentiDiUnAgenda()) {
                        System.out.println(i + " " + iteratore.toString());
                        i++;
                    }
                    int numeroAppello;
                    do {
                        System.out.print("inserisci il numero dell'appuntamento di cui vuoi operare :");
                        numeroAppello = scanner.nextInt();
                    } while (numeroAppello < 1 || numeroAppello > i);

                    System.out.print("reinserici la data dell' appuntamento:");
                    Scanner scan2 = new Scanner(System.in);
                    LocalDate dataAppuntamento2 = LocalDate.of(scan2.nextInt(), scan2.nextInt(), scan2.nextInt());
                    DateTimeFormatter ld2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.print("reinserici l'orario dell' appuntamento:");
                    String time2 = scan2.next();  //default format: hh:mm:ss
                    LocalTime oraAppuntamento2 = LocalTime.parse(time2);
                    System.out.print("reinserici la durata dell' appuntamento:");
                    int durataAppuntamento2 = scanner.nextInt();
                    System.out.print("reinserici il nome della persona dell' appuntamento:");
                    String nomeAppuntamento2 = scanner.nextLine();
                    System.out.print("reinserici il luogo dell' appuntamento:");
                    String luogoAppuntamento2 = scanner.nextLine();
                    Appuntamento appuntamento = new Appuntamento(LocalDate.parse(dataAppuntamento2.format(ld2)), oraAppuntamento2, durataAppuntamento2, nomeAppuntamento2, luogoAppuntamento2);
                    agenda.modificaAppuntamento(agenda1, appuntamento);
                    // qua vado ad togliere l'appuntamento che sono andato a modificare, faccio i-1 perche i parte da 1 e ArrayList da 0
                    agenda.getListaAppuntamentiDiUnAgenda().remove(i-1);
                case 7:
                    i = 1;
                    System.out.print("Su quale agenda vuoi operare :");
                    for (Agenda iteratore : agenda.getListaAgende()) {
                        System.out.println(i + " " + iteratore.getNomeAgenda());
                        i++;
                    }
                    do {
                        System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                        numeroAgenda = scanner.nextInt();
                    } while (numeroAgenda < 1 || numeroAgenda > i);
                    agenda1 = agenda.getListaAgende().get(numeroAgenda);
                    System.out.print("Inserisci la data dell'appuntamento :");
                    scan = new Scanner(System.in);
                    LocalDate dataAppuntamento3 = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
                    DateTimeFormatter ld3 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    Appuntamento appuntamento4 = agenda.cercaAppuntamentoPerData(agenda1, LocalDate.parse(dataAppuntamento3.format(ld3)));
                    System.out.print(appuntamento4.toString());

                case 8:
                    i = 1;
                    System.out.print("Su quale agenda vuoi operare :");
                    for (Agenda iteratore : agenda.getListaAgende()) {
                        System.out.println(i + " " + iteratore.getNomeAgenda());
                        i++;
                    }
                    do {
                        System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                        numeroAgenda = scanner.nextInt();
                    } while (numeroAgenda < 1 || numeroAgenda > i);

                    agenda1 = agenda.getListaAgende().get(numeroAgenda);
                    System.out.print("Inserisci il nome della persona con cui hai l'appuntamento :");
                    String nomeAppuntamento3 = scanner.nextLine();
                    Appuntamento appuntamento5 = agenda.cercaAppuntamentoPerNome(agenda1, nomeAppuntamento3);
                    System.out.print(appuntamento5.toString());
                    break;

            }
        }
        while (parolaScelta > 0);

    }

}
