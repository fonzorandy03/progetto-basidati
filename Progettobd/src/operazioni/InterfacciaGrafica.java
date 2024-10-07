package operazioni;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class InterfacciaGrafica extends JFrame {

    private int numeroOperazione;
    private GestioneQuery query;

    public InterfacciaGrafica(int numeroOperazione) {
        this.numeroOperazione = numeroOperazione;
        this.query = new GestioneQuery();
        setupUI();
    }

    private void setupUI() {
        switch (numeroOperazione) {
            case 1:
                setupRegistrazioneScuderiaForm();
                break;
            case 2:
                setupRegistrazioneAutovetturaForm();
                break;
            case 3:
                setupAggiuntaNuovoPilotaForm();
                break;
            case 4 :
            	setupAggiungiFinanziamentoForm();
            	break;
            case 5 :
            	setupIscrizioneVetturaForm();
            	break;
            case 6:
            	setupRegistraRisultatoForm();
            	break;
            case 7:
            	setupRicercaComponenteForm();
            	break;
            case 8:
            	setupStampaSommaTotaleFinanziamentiPerOgniScuderiaForm();
            	break;
            case 9:
            	setupStampaAnnualeScuderieConFinanziamentiForm();
            	break;
            case 10:
            	setupPilotiVincitoriInCasaForm();
            	break;
            case 11:
            	setupVisualizzaPercentualeGentlemanDriverForm();
            	break;
            case 12:
            	setupStampaMensileCostruttoriForm();
            	break;
            case 13:
            	setupStampaClassificaFinaleVettureForm();
            	break;
            case 14:
            	setupStampaClassificheFinaliPuntiPerTipoMotoreForm();
            	break;
            case 15:
            	setupStampaReportPuntiMinutiPerScuderiaForm();
            	break;
            	
        }

        pack();
        setLocationRelativeTo(null);
        setTitle("Interfaccia operazioni Query");
    }

    private void setupRegistrazioneScuderiaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField idScuderiaField = new JTextField(10);
        JTextField numVettureField = new JTextField(10);
        JTextField paeseField = new JTextField(10);
        JTextField nomeScuderiaField = new JTextField(10);

        JButton registrazioneScuderiaButton = new JButton("Registra la scuderia");
        registrazioneScuderiaButton.addActionListener(e -> {
            int idScuderia = Integer.parseInt(idScuderiaField.getText());

            if (query.isIdScuderiaPresente(idScuderia)) {
                JOptionPane.showMessageDialog(this, "Il numero di identificazione Scuderia che hai inserito è già presente nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                query.RegistrazioneScuderia(
                        idScuderia,
                        nomeScuderiaField.getText(),
                        paeseField.getText(),
                        Integer.parseInt(numVettureField.getText())
                );
            }
        });

        mainPanel.add(new JLabel("ID Scuderia:"));
        mainPanel.add(idScuderiaField);
        mainPanel.add(new JLabel("Numero Vetture:"));
        mainPanel.add(numVettureField);
        mainPanel.add(new JLabel("Paese:"));
        mainPanel.add(paeseField);
        mainPanel.add(new JLabel("Nome della Scuderia:"));
        mainPanel.add(nomeScuderiaField);
        mainPanel.add(registrazioneScuderiaButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }

    private void setupRegistrazioneAutovetturaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField idVetturaField = new JTextField(10);
        JTextField numeroGaraField = new JTextField(10);
        JTextField modelloVeicoloField = new JTextField(10);
        JTextField idScuderiaField = new JTextField(10);
        JTextField codiceComponenteField = new JTextField(10);
        JTextField ragioneSocialeField = new JTextField(10);
        JTextField cilindrataField = new JTextField(10);
        JTextField tipoMotoreField = new JTextField(10);
        JTextField costoField = new JTextField(10);
        JTextField numCilindriField = new JTextField(10);
        JTextField numMarcieField = new JTextField(10);
        JTextField pesoField = new JTextField(10);
        JTextField materialeField = new JTextField(10);

        
        List<Integer> idEquipaggi = query.caricaIdEquipaggio();
        JComboBox<Integer> idEquipaggioComboBox = new JComboBox<>(idEquipaggi.toArray(new Integer[0]));
        String[] tipiComponente = {"cambio" ,"motore", "telaio"};
        JComboBox<String> tipoComponenteComboBox = new JComboBox<>(tipiComponente);
        String[] tipiMotore = {"turbo" ,"aspirato"};
        JComboBox<String> tipoMotoreComboBox = new JComboBox<>(tipiMotore);
        JButton registrazioneAutovetturaButton = new JButton("Registrazione Vettura");
        registrazioneAutovetturaButton.addActionListener(e -> {
            try {
                    if (idVetturaField.getText().isEmpty() || numeroGaraField.getText().isEmpty()
                            || modelloVeicoloField.getText().isEmpty() || idScuderiaField.getText().isEmpty()
                            || codiceComponenteField.getText().isEmpty() || ragioneSocialeField.getText().isEmpty()
                            || cilindrataField.getText().isEmpty() || costoField.getText().isEmpty()
                            
                            ) {
                        JOptionPane.showMessageDialog(null, "Completa tutti i campi richiesti.");
                        return;
                    }

                int idVettura = Integer.parseInt(idVetturaField.getText());
                int numeroGara = Integer.parseInt(numeroGaraField.getText());
                String modelloVeicolo = modelloVeicoloField.getText();
                int idScuderia = Integer.parseInt(idScuderiaField.getText());
                int codiceComponente = Integer.parseInt(codiceComponenteField.getText());
                String rag = ragioneSocialeField.getText();
                int cilindrata = Integer.parseInt(cilindrataField.getText());
                String tipoComponente = tipoComponenteComboBox.getSelectedItem().toString();
                String tipoMotore = tipoMotoreComboBox.getSelectedItem().toString();
                Double costo = Double.parseDouble(costoField.getText());
                int numCilindri = Integer.parseInt(numCilindriField.getText());
                int numMarcie = Integer.parseInt(numMarcieField.getText());
                Double peso = Double.parseDouble(pesoField.getText());
                String materiale = materialeField.getText();

               
                int idEquipaggio = (int) idEquipaggioComboBox.getSelectedItem();
                if (query.isIdVetturaPresente(idVettura)) {
                    JOptionPane.showMessageDialog(null, "Codice identificativo della vettura gia' presente all'interno del database.");
                    return;
                }
                if (query.isIdComponentePresente(codiceComponente)) {
                    JOptionPane.showMessageDialog(null, "Codice identificativo del componente gia' presente nel database.");
                    return;
                }
                query.InserimentoAutovetturaComp(idVettura, idEquipaggio, numeroGara, modelloVeicolo, idScuderia,
                        codiceComponente, rag, cilindrata, tipoComponente, tipoMotore, costo, numCilindri, numMarcie, peso,
                        materiale);
                
                JOptionPane.showMessageDialog(null, "Inserimento avvenuto con successo.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inserire valori numerici validi.");
                ex.printStackTrace();
            }
        });

        mainPanel.add(new JLabel("ID Vettura:"));
        mainPanel.add(idVetturaField);
        mainPanel.add(new JLabel("Numero durante la Gara:"));
        mainPanel.add(numeroGaraField);
        mainPanel.add(new JLabel("Modello del Veicolo:"));
        mainPanel.add(modelloVeicoloField);
        mainPanel.add(new JLabel("ID Scuderia:"));
        mainPanel.add(idScuderiaField);
        mainPanel.add(new JLabel("Codice Componente:"));
        mainPanel.add(codiceComponenteField);
        mainPanel.add(new JLabel("Ragione Sociale:"));
        mainPanel.add(ragioneSocialeField);
        mainPanel.add(new JLabel("Cilindrata:"));
        mainPanel.add(cilindrataField);
        mainPanel.add(new JLabel("Tipo Componente:"));
        mainPanel.add(tipoComponenteComboBox);
        mainPanel.add(new JLabel("Tipo Motore:"));
        mainPanel.add(tipoMotoreComboBox);
        mainPanel.add(new JLabel("Costo:"));
        mainPanel.add(costoField);
        mainPanel.add(new JLabel("Numero Cilindri:"));
        mainPanel.add(numCilindriField);
        mainPanel.add(new JLabel("Numero Marcie:"));
        mainPanel.add(numMarcieField);
        mainPanel.add(new JLabel("Peso:"));
        mainPanel.add(pesoField);
        mainPanel.add(new JLabel("Materiale:"));
        mainPanel.add(materialeField);
        mainPanel.add(new JLabel("ID Equipaggio:"));
        mainPanel.add(idEquipaggioComboBox);
        mainPanel.add(registrazioneAutovetturaButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }


    private void setupAggiuntaNuovoPilotaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField codFiscalePilotaField = new JTextField(10);
        JTextField idEquipaggioField = new JTextField(10);
        JTextField nomePilotaField = new JTextField(10);
        JTextField cognomePilotaField = new JTextField(10);
        JTextField dataNascitaPilotaField = new JTextField(10);
        JTextField nazionalitaPilotaField = new JTextField(10);
        JTextField numLicenzeField = new JTextField(10);
        JTextField dataAcquisizioneLicenzaField = new JTextField(10);
        String[] tipiPilota = {"PRO", "AM"};
        JComboBox<String> tipiPilotaComboBox = new JComboBox<>(tipiPilota);
        JButton aggiuntaPilotaButton = new JButton("Aggiunta Pilota");

        aggiuntaPilotaButton.addActionListener(e -> {
            try {
                if (codFiscalePilotaField.getText().isEmpty() || idEquipaggioField.getText().isEmpty()
                        || nomePilotaField.getText().isEmpty() || cognomePilotaField.getText().isEmpty()
                        || dataNascitaPilotaField.getText().isEmpty() || nazionalitaPilotaField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Inserisci obbligatoriamente tutti i campi.");
                    return;
                }

                if (query.isIdPilotaPresente(codFiscalePilotaField.getText())) {
                    JOptionPane.showMessageDialog(null, "Il Codice Fiscale del Pilota è gia' presente nel database.");
                    return;
                }
                String tipoPilota = tipiPilotaComboBox.getSelectedItem().toString();
                int numLicenze = 0;
                java.util.Date dataPrimaLicenza = null;
                if ("PRO".equals(tipoPilota)) {
                    numLicenze = Integer.parseInt(numLicenzeField.getText());
                } else if ("AM".equals(tipoPilota)) {
                    try {
                        dataPrimaLicenza = new SimpleDateFormat("yyyy-MM-dd").parse(dataAcquisizioneLicenzaField.getText());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        System.out.println("Errore durante la conversione della data. La data sara' null.");
                    }
                }

                query.AggiuntaPilota(
                        codFiscalePilotaField.getText(),
                        Integer.parseInt(idEquipaggioField.getText()),
                        nomePilotaField.getText(),
                        cognomePilotaField.getText(),
                        dataNascitaPilotaField.getText(),
                        nazionalitaPilotaField.getText(),
                        tipoPilota,
                        numLicenze,
                        dataPrimaLicenza
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inserire valori numerici validi.");
                ex.printStackTrace();
            }
        });

        mainPanel.add(new JLabel("Codice Fiscale Pilota:"));
        mainPanel.add(codFiscalePilotaField);
        mainPanel.add(new JLabel("ID Equipaggio:"));
        mainPanel.add(idEquipaggioField);
        mainPanel.add(new JLabel("Nome del Pilota:"));
        mainPanel.add(nomePilotaField);
        mainPanel.add(new JLabel("Cognome del Pilota:"));
        mainPanel.add(cognomePilotaField);
        mainPanel.add(new JLabel("Data di Nascita:"));
        mainPanel.add(dataNascitaPilotaField);
        mainPanel.add(new JLabel("Nazionalita':"));
        mainPanel.add(nazionalitaPilotaField);
        mainPanel.add(new JLabel("Tipo Pilota (PRO-AM):"));
        mainPanel.add(tipiPilotaComboBox);

        mainPanel.add(new JLabel("Numero delle Licenze (solo se PRO):"));
        mainPanel.add(numLicenzeField);

        mainPanel.add(new JLabel("Data Acquisizione Prima Licenza (solo se AM):"));
        mainPanel.add(dataAcquisizioneLicenzaField);

        mainPanel.add(aggiuntaPilotaButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }

    private void setupAggiungiFinanziamentoForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        List<Integer> idScuderiaList = query.caricaIdScuderia();
        List<String>gentlemanList = query.caricaGentleman();
        Integer[] idScuderiaArray = idScuderiaList.toArray(new Integer[0]);
        String[] gentlemanArray = gentlemanList.toArray(new String[0]);
        JComboBox<Integer> idScuderiaComboBox = new JComboBox<>(idScuderiaArray);
        JComboBox<String> gentlemanComboBox = new JComboBox<>(gentlemanArray);
        JTextField importoField = new JTextField(10);
        JTextField dataFinanziamentoField = new JTextField(10);
        JTextField tipoPagamentoField = new JTextField(10);

        JButton aggiungiFinanziamentoButton = new JButton("Aggiungi Finanziamento");
        aggiungiFinanziamentoButton.addActionListener(e -> {
            try {
                if (importoField.getText().isEmpty()
                        || dataFinanziamentoField.getText().isEmpty() || tipoPagamentoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Inserisci tutti i campi obbligatoriamente.");
                    return;
                }

                int idScuderia = (int) idScuderiaComboBox.getSelectedItem();
                String gentleMan = gentlemanComboBox.getSelectedItem().toString();                double importo = Double.parseDouble(importoField.getText());
                String dataFinanziamento = dataFinanziamentoField.getText();
                String tipoPagamento = tipoPagamentoField.getText();
                query.AggiungiFinanziamento(idScuderia, importo, dataFinanziamento, tipoPagamento, gentleMan);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inserire valori numerici validi.");
                ex.printStackTrace();
            }
        });

        mainPanel.add(new JLabel("ID Scuderia da finanziare:"));
        mainPanel.add(idScuderiaComboBox);
        mainPanel.add(new JLabel("Selezionare il Gentleman :"));
        mainPanel.add(gentlemanComboBox);
        mainPanel.add(new JLabel("Stabilire l'importo:"));
        mainPanel.add(importoField);
        mainPanel.add(new JLabel("Data Finanziamento inserita nel formato (YYYY-MM-DD):"));
        mainPanel.add(dataFinanziamentoField);
        mainPanel.add(new JLabel("Tipo Pagamento:"));
        mainPanel.add(tipoPagamentoField);
        mainPanel.add(aggiungiFinanziamentoButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }

    private void setupRegistraRisultatoForm() {
    	String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField idVetturaGaraField = new JTextField(10);
        JTextField nomeVetturaField = new JTextField(10);

        
        String[] motiviRitiroOptions = {"guasto", "incidente", "squalifica","conclusione corretta"};
        JComboBox<String> motivoRitiroComboBox = new JComboBox<>(motiviRitiroOptions);

        JTextField dataGaraField = new JTextField(10);
        JTextField idCircuitoField = new JTextField(10);
        JTextField risultatoField = new JTextField(10);

        JButton registraRisultatoButton = new JButton("Registra Risultato");
        registraRisultatoButton.addActionListener((ActionEvent e) -> {
            int idVetturaGara = Integer.parseInt(idVetturaGaraField.getText());
            String nomeGara = nomeVetturaField.getText();
            String motivoRitiro = (String) motivoRitiroComboBox.getSelectedItem();
            java.sql.Date dataGara = Date.valueOf(dataGaraField.getText());
            int idCircuito = Integer.parseInt(idCircuitoField.getText());
            int risultato = Integer.parseInt(risultatoField.getText());

            query.registraRisultato(risultato, motivoRitiro, nomeGara, dataGara, idCircuito, idVetturaGara);
        });

        mainPanel.add(new JLabel("ID Vettura Iscritta alla Gara:"));
        mainPanel.add(idVetturaGaraField);
        mainPanel.add(new JLabel("Nome della Gara:"));
        mainPanel.add(nomeVetturaField);
        mainPanel.add(new JLabel("Motivo del Ritiro:"));
        mainPanel.add(motivoRitiroComboBox);
        mainPanel.add(new JLabel("Data Gara inserita nel seguente formato (YYYY-MM-DD):"));
        mainPanel.add(dataGaraField);
        mainPanel.add(new JLabel("ID Circuito:"));
        mainPanel.add(idCircuitoField);
        mainPanel.add(new JLabel("Risultato Conseguito:"));
        mainPanel.add(risultatoField);
        mainPanel.add(registraRisultatoButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
    }

    private void setupIscrizioneVetturaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        List<Integer>idv = query.caricaidv();
        JComboBox<Integer> idvComboBox = new JComboBox<>(idv.toArray(new Integer[0]));
        
        List<String>nomeg = query.caricaNomegara();
        JComboBox<String> nomeCircuitoComboBox = new JComboBox<>(nomeg.toArray(new String[0]));

        JTextField dataGaraField = new JTextField(10);
        JTextField idCircuitoField = new JTextField(10);

        JButton iscrizioneVetturaButton = new JButton("Iscrizione Vettura alla Gara");
        iscrizioneVetturaButton.addActionListener(e -> {
            int idVetturaGara = (int) idvComboBox.getSelectedItem();
            String nomeCircuito = nomeCircuitoComboBox.getSelectedItem().toString();
            String dataGara = dataGaraField.getText();
            int idCircuito = Integer.parseInt(idCircuitoField.getText());
            query.IscrizioneVettura(nomeCircuito, dataGara, idCircuito, 0, null, idVetturaGara);
        });

        mainPanel.add(new JLabel("ID Vettura Da Iscrivere:"));
        mainPanel.add(idvComboBox);
        mainPanel.add(new JLabel("Nome Circuito Disponibile:"));
        mainPanel.add(nomeCircuitoComboBox);
        mainPanel.add(new JLabel("Data Gara inserita nel seguente formato (YYYY-MM-DD):"));
        mainPanel.add(dataGaraField);
        mainPanel.add(new JLabel("ID Circuito:"));
        mainPanel.add(idCircuitoField);
        mainPanel.add(iscrizioneVetturaButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }


    private void setupRicercaComponenteForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField idVetturaField = new JTextField(10);
        JTextField idComponenteField = new JTextField(10);

        JButton ricercaComponenteButton = new JButton("Ricerca del Componente");
        ricercaComponenteButton.addActionListener(e -> {
            int idVettura = Integer.parseInt(idVetturaField.getText());
            int idComponente = Integer.parseInt(idComponenteField.getText());
            String componenteInfo = query.RicercaComponente(idVettura, idComponente);
            if (componenteInfo != null) {
                JOptionPane.showMessageDialog(this, "Componente trovato:\n" + componenteInfo);
            } else {
                JOptionPane.showMessageDialog(this, "Componente non trovato");
            }
        });
        mainPanel.add(new JLabel("ID Vettura:"));
        mainPanel.add(idVetturaField);
        mainPanel.add(new JLabel("ID Componente:"));
        mainPanel.add(idComponenteField);
        mainPanel.add(ricercaComponenteButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupStampaSommaTotaleFinanziamentiPerOgniScuderiaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton stampaSommaFinanziamentiButton = new JButton("Stampa la Somma dei Finanziamenti per Scuderia");
        stampaSommaFinanziamentiButton.addActionListener(e -> {
            String report = query.StampaSommaTotaleFinanziamentiPerOgniScuderia();
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(stampaSommaFinanziamentiButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupStampaAnnualeScuderieConFinanziamentiForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton stampaAnnualeScuderieButton = new JButton("Stampa Annuale delle Scuderie con Finanziamenti");
        stampaAnnualeScuderieButton.addActionListener(e -> {
            String report = query.StampaAnnualeScuderieConFinanziamenti();
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(stampaAnnualeScuderieButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupPilotiVincitoriInCasaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField nazionalitaPilotaField = new JTextField(10);
        JTextField paeseField = new JTextField(10);

        JButton pilotiVincitoriInCasaButton = new JButton("Piloti chehanno vinto inc asa");
        pilotiVincitoriInCasaButton.addActionListener(e -> {
            String nazionalitaPilota = nazionalitaPilotaField.getText();
            String paese = paeseField.getText();
            query.PilotiVincitoriInCasa(nazionalitaPilota, paese);
        });

        mainPanel.add(new JLabel("Nazionalita' Pilota:"));
        mainPanel.add(nazionalitaPilotaField);
        mainPanel.add(new JLabel("Paese:"));
        mainPanel.add(paeseField);
        mainPanel.add(pilotiVincitoriInCasaButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupVisualizzaPercentualeGentlemanDriverForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton visualizzaPercentualeGentlemanDriverButton = new JButton("Visualizza Percentuale Gentleman");
        visualizzaPercentualeGentlemanDriverButton.addActionListener(e -> {
            String report = query.VisualizzaPercentualeGentlemanDriver();
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(visualizzaPercentualeGentlemanDriverButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupStampaMensileCostruttoriForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton stampaMensileCostruttoriButton = new JButton("Stampa Mensile Costruttori");
        stampaMensileCostruttoriButton.addActionListener(e -> {
        	String report = query.stampaMensileCostruttori();
            
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(stampaMensileCostruttoriButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupStampaClassificaFinaleVettureForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton stampaClassificaFinaleVettureButton = new JButton("Stampa la Classifica Finale delle Vetture");
        stampaClassificaFinaleVettureButton.addActionListener(e -> {
            String report = query.stampaClassificaFinaleVetture();
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(stampaClassificaFinaleVettureButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupStampaClassificheFinaliPuntiPerTipoMotoreForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton stampaClassificheFinaliPuntiPerTipoMotoreButton = new JButton("Stampa Classifiche Finali Punti per Tipo Motore");
        stampaClassificheFinaliPuntiPerTipoMotoreButton.addActionListener(e -> {
            String report=query.StampaClassificheFinaliPuntiPerTipoMotore();
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(stampaClassificheFinaliPuntiPerTipoMotoreButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }
    private void setupStampaReportPuntiMinutiPerScuderiaForm() {
        String url = "jdbc:mysql://localhost:3306/Campionato";
        String usr = "root";
        String psw = "";
        query.Connessione(url, usr, psw);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton stampaReportPuntiMinutiPerScuderiaButton = new JButton("Stampa Report dei Punti/Minuti per Scuderia");
        stampaReportPuntiMinutiPerScuderiaButton.addActionListener(e -> {
            String report = query.StampaReportPuntiMinutiPerScuderia();
            if (report != null) {
                JOptionPane.showMessageDialog(this, "report trovato:\n" + report);
            } else {
                JOptionPane.showMessageDialog(this, "report non trovato");
            }
        });

        mainPanel.add(stampaReportPuntiMinutiPerScuderiaButton);

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        pack();
    }

}

	