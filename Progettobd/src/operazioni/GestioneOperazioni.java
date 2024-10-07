/*package operazioni;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        QueryHandler queryHandler = new QueryHandler("jdbc:mysql://localhost:3306/Campionato", "root", "");

        int operazione;
        do {
            printMenu();
            operazione = in.nextInt();

            switch (operazione) {
                case 1:
                    queryHandler.registraScuderia(in);
                    break;
                case 2:
                    queryHandler.inserisciDatiAutovettura(in);
                    break;
                case 3:
                    queryHandler.aggiungiPilota(in);
                    break;
                case 4:
                    queryHandler.registraFinanziamento(in);
                    break;
                case 5:
                    queryHandler.iscriviVetturaAdGara(in);
                    break;
                case 6:
                    queryHandler.registraRisultatoGara(in);
                    break;
                case 7:
                    queryHandler.verificaMontaggioComponente(in);
                    break;
                case 8:
                    queryHandler.stampaSommaTotaleFinanziamenti();
                    break;
                case 9:
                    queryHandler.stampaAnnualeScuderieConFinanziamenti();
                    break;
                case 10:
                    queryHandler.visualizzaPilotiVincitoriInCasa(in);
                    break;
                case 11:
                    queryHandler.visualizzaPercentualeGentlemanDriver();
                    break;
                case 12:
                    queryHandler.stampaMensileCostruttori();
                    break;
                case 13:
                    queryHandler.stampaClassificaFinaleVetture();
                    break;
                case 14:
                    queryHandler.stampaClassificheFinaliPuntiPerTipoMotore();
                    break;
                case 15:
                    queryHandler.stampaReportPuntiMinutiPerScuderia();
                    break;
            }
        } while (operazione != -1);

        in.close();
    }

    private static void printMenu() {
        System.out.println("Quale operazione si desidera effettuare? (Inserire -1 per uscire)");
        System.out.println("1- Registrazione di una scuderia;");
        System.out.println("2- Inserimento dei dati di un’autovettura, compresi i componenti di cui è composta;");
        System.out.println("3- Aggiunta di un nuovo pilota ad un equipaggio");
        System.out.println("4- Registrazione di un finanziamento per una scuderia;");
        System.out.println("5- Iscrizione di una vettura ad una gara;");
        System.out.println("6- Registrazione del risultato conseguito da ciascuna vettura iscritta ad una gara;");
        System.out.println("7- Verifica della possibilità di montare un componente su una vettura;");
        System.out.println("8- Per ciascuna scuderia, stampare la somma totale dei finanziamenti ricevuti");
        System.out.println("9- Stampa annuale delle scuderie che hanno partecipato al campionato compreso il numero di finanziamenti;");
        System.out.println("10- Visualizzare i piloti che hanno vinto nel «circuito di casa»");
        System.out.println("11- Per ciascuna scuderia, visualizzare la percentuale di gentleman driver di cui si compone l’equipaggio;");
        System.out.println("12- Stampa mensile dei costruttori compreso il numero di componenti che ha fornito;");
        System.out.println("13- Stampa della classifica finale dei punti conseguiti da tutte le vetture;");
        System.out.println("14- Stampa delle classifiche finali di punti per tipo di motore;");
        System.out.println("15- Stampare un report che elenchi ciascuna scuderia sulla base del rapporto punti/minuti di gara, mediando tra le macchine appartenenti a ciascuna scuderia;");
    }
}

class QueryHandler {
    private GestioneQuery query;

    public QueryHandler(String url, String usr, String psw) {
        this.query = new GestioneQuery();
        query.Connessione(url, usr, psw);
    }

    public void registraScuderia(Scanner in) {
           			System.out.println("Inserire l'id scuderia");
        			 int id_Scuderia = in.nextInt();
				
					System.out.println("Inserire il numero di vetture");
					int numVetture = in.nextInt();
				
					System.out.println("Inserire il paese di appartenenza");
					String paese = in.next();
				
					System.out.println("Inserire il nome");
					String nome_scuderia = in.next();
					
					query.RegistrazioneScuderia(id_Scuderia, nome_scuderia, paese, numVetture);
					
    }

    public void inserisciDatiAutovettura(Scanner in) {
        System.out.println("Inserire l'id vettura");
       			 	int id_vettura = in.nextInt();
       			 	
       			 	System.out.println("Inserire l'id dell'equipaggio");
       			 	int ide = in.nextInt();
				
					System.out.println("Inserire il numero in gara");
					int numerog = in.nextInt();
				
					System.out.println("Inserire il modello veicolo");
					String modello = in.next();
				
					System.out.println("Inserire l'id della scuderia");
					int id_s = in.nextInt();
					
					System.out.println("Inserire il codice del componente");
					int codice = in.nextInt();
					
					System.out.println("Inserire la ragione sociale");
					String rag = in.next();
					
					System.out.println("Inserire la cilindrata");
					int cil = in.nextInt();
					
					System.out.println("Inserire il tipo del componente");
					String tipo = in.next();
					
					System.out.println("Inserire il tipo del motore");
					String tipom = in.next();
					
					System.out.println("Inserire il costo");
					Double costo = in.nextDouble();
					
					System.out.println("Inserire il numero dei cilindri");
					int numc = in.nextInt();
					
					System.out.println("Inserire il numero marcie");
					int nummarcie = in.nextInt();
					
					System.out.println("Inserire il peso");
					Double peso = in.nextDouble();
					
					System.out.println("Inserire il materiale");
					String mat = in.next();
					
        			query.InserimentoAutovetturaComp(id_vettura,ide,numerog,modello,id_s,codice,rag,cil,tipo,tipom,costo,numc,nummarcie,peso,mat);
        			
    }

    public void aggiungiPilota(Scanner in) {
       System.out.println("Inserire il codice fiscale del pilota");
       			 	String cod = in.next();
       			 	
        			System.out.println("Inserire l'id relativo all'equipaggio");
       			 	int id_equipaggio = in.nextInt();
       			 	
        			System.out.println("Inserire il nome");
       			 	String nome = in.next();
       			 	
       			 	System.out.println("Inserire il cognome");
    			 	String cognome = in.next();
    			 	
    			 	System.out.println("Inserire la data di nascita");
       			 	String data = in.next();
       			 	
       			 	System.out.println("Inserire la nazionalit�");
    			 	String naz = in.next();
    			 	
       			 	System.out.println("Inserire il tipo pilota (PRO-AM)");
    			 	String tipo = in.next();
    			 	int numl = 0;
    			 	Date dprima = "NULL";
					if(tipo == "PRO") {
           			 	System.out.println("Inserire num-licenze (PRO)");
        			 	numl = in.nextInt();
        			 	}
    			 	
    			 	else
    			 	{
           			 	System.out.println("Inserire data acquisizione prima licenza (AM)");
        			 	dprima = in.next();
        			 	}
        			query.AggiuntaPilota(cod,id_equipaggio,nome,cognome,data,naz,tipo,numl,dprima);
        			
    }

    public void registraFinanziamento(Scanner in) {
                   System.out.println("Inserire il relativo id della scuderia");
       			 	int id = in.nextInt();
       			 	
       			 	System.out.println("Inserire il codice fiscale del gentlmandriver");
       			 	String codfisc = in.next();
       			 	
       			 	System.out.println("Inserire l'importo del finanziamento");
       			 	double importo = in.nextDouble();
       			 	
       			 	System.out.println("Inserire la data del finanziamento (YYYY-MM-DD)");
       			 	String data = in.next();
       			 	
       			 	System.out.println("Inserire il tipo di pagamento (CONTANTI-BONIFICO-ECC.)");
       			 	String tipo = in.next();
       			 	
       			 	query.AggiungiFinanziamento(id,importo,data,tipo,codfisc);
        		
    }

    public void iscriviVetturaAdGara(Scanner in) {
       System.out.println("Inserire l'id della vettura che si vuole iscrivere");
       			 	int id = in.nextInt();
       			 	
        			System.out.println("Inserire il nome della vettura");
        			String nome = in.next();
        			
        			System.out.println("Inserire la data (YYYY-MM-DD)");
        			String data = in.next();
        			
        			System.out.println("Inserire l'id del circuito");
        			int idc = in.nextInt();
        			
        			query.IscrizioneVettura(id,nome,data,idc);
        		
    }

    public void registraRisultatoGara(Scanner in) {
        System.out.println("Inserire l'id della vettura iscritta alla gara per registrare il risultato");
       			 	int id = in.nextInt();
       			 	
       			 	System.out.println("Inserire il nome della vettura");
       			 	String nomev = in.next();
       			 	
       			 	System.out.println("Inserire il numero in gara");
       			 	int numero = in.nextInt();
       			 	
       			 	System.out.println("Inserire la data della gara (YYYY-MM-DD)");
       			 	String data = in.next();
       			 	
       			 	System.out.println("Inserire l'id della scuderia");
       			 	int idc = in.nextInt();
       			 	
       			 	System.out.println("Inserire il risultato conseguito");
       			 	int risultato = in.nextInt();
       			 	
       			 	query.RegistraRisultatoForm(id,numero,nomev,data,idc,risultato);
        		
    }

    public void verificaMontaggioComponente(Scanner in) {
	System.out.println("Inserire l'id della vettura");
        			int idv = in.nextInt();
        			
        			System.out.println("Inserire l'id del componente");
        			int idc = in.nextInt();
        			
        			query.RicercaComponente(idv,idc);
        		
    }

    public void stampaSommaTotaleFinanziamenti() {
        query.StampaSommaTotaleFinanziamentiPerOgniScuderia();
        			
    }

    public void stampaAnnualeScuderieConFinanziamenti() {
        query.StampaAnnualeScuderieConFinanziamenti();
        			
    }

    public void visualizzaPilotiVincitoriInCasa(Scanner in) {
       System.out.println("Inserire la nazione di appartenenza del pilota");
        			String paese = in.next();
        			
        			System.out.println("Inserire la nazionalita del pilota");
        			String nazionalitaPilota = in.next();
        			
        			query.PilotiVincitoriInCasa(nazionalitaPilota,paese);
        			
    }

    public void visualizzaPercentualeGentlemanDriver() {
       query.VisualizzaPercentualeGentlemanDriver();
        			break;
    }

    public void stampaMensileCostruttori() {
       query.stampaMensileCostruttori();
        			break;
    }

    public void stampaClassificaFinaleVetture() {
        query.stampaClassificaFinaleVetture();
        			break;
    }

    public void stampaClassificheFinaliPuntiPerTipoMotore() {
       query.StampaClassificheFinaliPuntiPerTipoMotore();
        			break;
    }

    public void stampaReportPuntiMinutiPerScuderia() {
      query.StampaReportPuntiMinutiPerScuderia();
        			break;
    }
}

class GestioneQuery {
   public static void main(String[] args) {
		GestioneQuery query = new GestioneQuery();
		String url = "jdbc:mysql://localhost:3306/Campionato";
		String usr = "root";
		String psw = "";
        int operazione = 0;
        Scanner in=new Scanner(System.in);
}
