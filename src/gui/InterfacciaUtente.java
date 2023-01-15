package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import codice.*;
import eccezioni.AgendaInesistenteException;
import eccezioni.AppuntamentoInesistenteException;
import eccezioni.InputErratiException;
import eccezioni.SovrapposizioneAppuntamentiException;

/**
 *
 */
public class InterfacciaUtente {
    public static void main(String[] args) {
        Agenda agenda= new Agenda("");
        Scanner scanner = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        int parolaScelta = 0;
        while (true) {
            do {
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.println("- 1:CREA AGENDA MEDIANTE NOME                                              -");
                System.out.println("- 2.CANCELLA AGENDA                                                        -");
                System.out.println("- 3.CREA AGENDA MEDIANTE FILE APPUNTAMENTI                                 -");
                System.out.println("- 4.SCRIVI AGENDA SU FILE                                                  -");
                System.out.println("- 5.INSERISCI NUOVO APPUNTAMENTO IN AGENDA                                 -");
                System.out.println("- 6.MODIFICA I DATI DELL'APPUNTAMENTO                                      -");
                System.out.println("- 7.CERCA APPUNTAMENTO MEDIANTE DATA                                       -");
                System.out.println("- 8.CERCA APPUNTAMENTO MEDIANTE NOME PERSONA DI CUI SI HA L'APPUNTAMENTO   -");
                System.out.println("- 9.STAMPA APPUNTAMENTI ORDINATI PER DATA                                  -");
                System.out.println("--------------------------------------------------------------------------");

                try {
                    System.out.print("Enter Your Choice : ");
                    parolaScelta = s.nextInt();


                    switch (parolaScelta) {
                        case 1: {
                            try {
                                System.out.print("Inserisci il nome da dare all'agenda : ");
                                String nomePerAgenda = scanner.nextLine();
                                agenda.creaAgendaDalNome(nomePerAgenda);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            } catch (AgendaInesistenteException e) {
                                System.out.println("\nInserisci un agenda esistente\n");
                            } catch (InputErratiException e) {
                                System.out.println("\ninserisci i dati corretti\n");
                            }
                        }
                        case 2: {
                            try {
                                System.out.println("--------------------------------------------------------------------------");
                                try {
                                    if (agenda.getListaAgende().size() == 0)
                                        throw new AgendaInesistenteException("");
                                } catch (AgendaInesistenteException ex) {
                                    System.out.println("errore:non hai agende disponibili");
                                    break;
                                }
                                System.out.print("lista delle agende disponibili :\n");

                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(iteratore.getNomeAgenda());
                                }
                                System.out.print("Inserisci il nome dell'agenda da cancellare :");
                                String nomePerAgenda = scanner.nextLine();
                                Agenda agenda1 = new Agenda(nomePerAgenda);
                                agenda.cancellaAgenda(agenda1);
                                System.out.println(agenda.getNomeAgenda() + " è stata cancellata correttamente");
                                System.out.println("le agende ancora disponibili sono:\n");

                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(iteratore.getNomeAgenda());
                                }

                                System.out.println("--------------------------------------------------------------------------");
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            } catch (InputErratiException e) {
                                System.out.println("\ninserisci i dati corretti\n");
                            }
                        }
                        case 3: {
                            try {
                                int i = 1;
                                System.out.print("Su quale agenda vuoi operare :\n");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                int numeroAgenda;
                                do {
                                    System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i - 1);

                                Agenda agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                agenda1.creaAgendaDaFile(agenda1);
                                System.out.print("Elenco degli appunti inserito all'agenda mediante il file\n");
                                for (Appuntamento iteratore: agenda1.getListaAppuntamentiDiUnAgenda()) {
                                    System.out.print(iteratore.toString());
                                }


                                //agenda.getListaAgende().add(agendaRiempitaAppuntamentiFile);
                                System.out.println("\n--------------------------------------------------------------------------");
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            }
                        }
                        case 4: {
                            try {
                                int i = 1;
                                if (agenda.getListaAgende().size() == 0) throw new AgendaInesistenteException("");
                                System.out.print("Su quale agenda vuoi operare :\n");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                int numeroAgenda;
                                do {
                                    System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i - 1);

                                Agenda agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                agenda.ScritturaAgendaSulFile(agenda1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            } catch (AgendaInesistenteException e) {
                                System.out.println("errore:non hai nessuna agenda");
                            }
                            System.out.println("--------------------------------------------------------------------------");
                            break;
                        }
                        case 5: {
                            try {
                                int i = 1;
                                System.out.print("Su quale agenda vuoi operare :\n");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                int numeroAgenda;
                                do {
                                    System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i - 1);
                                Agenda agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                System.out.print("Inserisci la data dell'appuntamento :");
                                Scanner scan = new Scanner(System.in);
                                LocalDate dataAppuntamento = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
                                //DateTimeFormatter ld = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                System.out.print("Inserisci l'ora dell'appuntamento :");
                                //String time = scan.next();  default format: hh:mm:ss
                                //LocalTime oraAppuntamento = LocalTime.parse(time);
                                LocalTime oraAppuntamento = LocalTime.of(scan.nextInt(), scan.nextInt());
                                System.out.print("Inserisci la durata dell'appuntamento :");
                                int durataAppuntamento = scanner.nextInt();
                                System.out.print("inserisci il nome della persona da incontrare all'appuntamento :");
                                String nomeAppuntamento = scanner.nextLine();
                                nomeAppuntamento = scanner.nextLine();
                                System.out.print("Inserisci luogo dell'appuntamento :");
                                String luogoAppuntamento = scanner.nextLine();

                                //debuggin LocalDate.parse(dataAppuntamento.format(ld))
                                Appuntamento appuntamento = new Appuntamento(dataAppuntamento, oraAppuntamento, durataAppuntamento, nomeAppuntamento, luogoAppuntamento);
                                agenda.inserisciAppuntamentoAllAgenda(agenda1, appuntamento);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("\nerrore:inserisci i dati in maniera corretta\n");
                            } catch (AppuntamentoInesistenteException e) {
                                System.out.println("\nInserisci un appuntamento esistente\n");
                            } catch (InputErratiException e) {
                                System.out.println("\ninserisci i dati corretti\n");
                            } catch (SovrapposizioneAppuntamentiException e) {
                                System.out.println("\nappuntamenti sovrapposti\n");
                            }
                        }
                        case 6: {
                            try {
                                int i = 1;
                                Agenda agenda1;
                                int numeroAgenda;
                                if (agenda.getListaAgende().size() == 0) throw new AgendaInesistenteException("");
                                System.out.print("Su quale agenda vuoi operare :\n");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                do {
                                    System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i);
                                agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                if (agenda1.getListaAppuntamentiDiUnAgenda().size() == 0)
                                    throw new AppuntamentoInesistenteException("");
                                i = 1;
                                System.out.print("Su quale appuntamento vuoi operare :");
                                for (Appuntamento iteratore : agenda1.getListaAppuntamentiDiUnAgenda()) {
                                    System.out.println(i + " " + iteratore.toString());
                                    i++;
                                }
                                int numeroAppello;
                                do {
                                    System.out.print("inserisci il numero dell'appuntamento di cui vuoi operare :");
                                    numeroAppello = scanner.nextInt();
                                } while (numeroAppello < 1 || numeroAppello > i - 1);

                                try {
                                    System.out.print("reinserisci la data dell' appuntamento:");
                                    Scanner scan2 = new Scanner(System.in);
                                    LocalDate dataAppuntamento2 = LocalDate.of(scan2.nextInt(), scan2.nextInt(), scan2.nextInt());
                                    DateTimeFormatter ld2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                    System.out.print("reinserisci l'orario dell' appuntamento:");
                                    String time2 = scan2.next();  //default format: hh:mm:ss
                                    LocalTime oraAppuntamento2 = LocalTime.parse(time2);
                                    System.out.print("reinserisci la durata dell' appuntamento:");
                                    int durataAppuntamento2 = scanner.nextInt();
                                    System.out.print("reinserisci il nome della persona dell' appuntamento:");
                                    String nomeAppuntamento2 = scanner.nextLine();
                                    System.out.print("reinserisci il luogo dell' appuntamento:");
                                    String luogoAppuntamento2 = scanner.nextLine();
                                    Appuntamento appuntamento = new Appuntamento(LocalDate.parse(dataAppuntamento2.format(ld2)), oraAppuntamento2, durataAppuntamento2, nomeAppuntamento2, luogoAppuntamento2);
                                    agenda.modificaAppuntamento(agenda1, appuntamento);
                                    // qua vado ad togliere l'appuntamento che sono andato a modificare, faccio i-1 perché i parte da 1 e ArrayList da 0
                                    agenda.getListaAppuntamentiDiUnAgenda().remove(i - 1);
                                } catch (DateTimeParseException e) {
                                    System.out.println("errore:inserisci i dati in maniera corretta");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            } catch (AppuntamentoInesistenteException e) {
                                System.out.println("\nerrore:Inserisci un appuntamento esistente\n");
                            } catch (InputErratiException e) {
                                System.out.println("\nerrore:inserisci i dati corretti\n");
                            } catch (AgendaInesistenteException e) {
                                System.out.println("\nerrore:non hai agende disponibili\n");
                            }
                        }
                        case 7: {
                            try {
                                Agenda agenda1;
                                int numeroAgenda;
                                Scanner scan;
                                int i = 1;
                                System.out.print("Su quale agenda vuoi operare :");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                do {
                                    System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i);
                                agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                for (Appuntamento iteratore : agenda1.getListaAppuntamentiDiUnAgenda()) {
                                    System.out.println(iteratore.toString());
                                }
                                if (agenda1.getListaAppuntamentiDiUnAgenda().size() == 0)
                                    throw new AppuntamentoInesistenteException("");
                                System.out.print("Inserisci la data dell'appuntamento :");
                                scan = new Scanner(System.in);
                                LocalDate dataAppuntamento = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
                                //DateTimeFormatter ld3 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                //LocalDate.parse(dataAppuntamento3.format(ld3)))
                                Appuntamento appuntamento = agenda.cercaAppuntamentoPerData(agenda1, dataAppuntamento);
                                System.out.print(appuntamento.toString());
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            } catch (AppuntamentoInesistenteException e) {
                                System.out.println("\nappuntamento/i non disponibile\n");
                            }
                        }
                        case 8: {
                            try {
                                int i = 1;
                                int numeroAgenda;
                                Agenda agenda1;
                                System.out.print("Su quale agenda vuoi operare :");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                do {
                                    System.out.print("\ninserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i);
                                agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                for (Appuntamento iteratore : agenda1.getListaAppuntamentiDiUnAgenda()) {
                                    System.out.println(iteratore.toString());
                                }
                                if (agenda1.getListaAppuntamentiDiUnAgenda().size() == 0)
                                    throw new AppuntamentoInesistenteException("");
                                System.out.print("Inserisci il nome della persona con cui hai l'appuntamento :");
                                String nomeAppuntamento = scanner.nextLine();
                                Appuntamento appuntamento5 = agenda.cercaAppuntamentoPerNome(agenda1, nomeAppuntamento);
                                System.out.println("gli appuntamenti trovati a questo nome sono:\n");
                                System.out.print(appuntamento5.toString());
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("errore:inserisci i dati in maniera corretta");
                            } catch (AppuntamentoInesistenteException e) {
                                System.out.println("\nappuntamento/i non disponibile\n");
                            } catch (InputErratiException e) {
                                System.out.println("\ninserisci i dati corretti\n");
                            }
                        }
                        case 9: {
                            try {
                                int i = 1;
                                int numeroAgenda;
                                if (agenda.getListaAgende().size() == 0) throw new AgendaInesistenteException("");
                                System.out.print("Su quale agenda vuoi operare :\n");
                                for (Agenda iteratore : agenda.getListaAgende()) {
                                    System.out.println(i + " " + iteratore.getNomeAgenda());
                                    i++;
                                }
                                do {
                                    System.out.print("inserisci il numero dell'agenda di cui vuoi operare :");
                                    numeroAgenda = scanner.nextInt();
                                } while (numeroAgenda < 1 || numeroAgenda > i);
                                Agenda agenda1 = agenda.getListaAgende().get(numeroAgenda - 1);
                                if (agenda1.getListaAppuntamentiDiUnAgenda().size() == 0)
                                    throw new AppuntamentoInesistenteException("");
                                ArrayList<Appuntamento> appuntamentiOrdinati = new ArrayList<>();
                                appuntamentiOrdinati = agenda1.elencoAppuntamentiOrdinatiPerData(agenda1);
                                System.out.println("\n");
                                for (Appuntamento iteratore : appuntamentiOrdinati) {
                                    System.out.println(iteratore.toString());
                                }
                            } catch (AgendaInesistenteException e) {
                                System.out.println("\nerrore:non hai agende disponibili\n");
                            } catch (InputErratiException e) {
                                System.out.println("\nerrore:inserisci i dati corretti\n");
                            } catch (AppuntamentoInesistenteException e) {
                                System.out.print("errore:non hai appuntamenti in questa agenda");
                                break;
                            }
                        }
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("errore:inserisci i dati in maniera corretta");
                    break;
                }
            }
            while (parolaScelta < 1 || parolaScelta > 9);
        }
    }
}
