package operazioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}

class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Menu delle Query");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> operazioniComboBox = new JComboBox<>(new String[]{
                "Numero 1 - Registrazione di una scuderia",
                "Numero 2 - Inserimento dei dati di un’autovettura, compresi i componenti di cui è composta",
                "Numero 3 - Aggiunta di un nuovo pilota ad un equipaggio",
                "Numero 4 - Registrazione di un finanziamento per una scuderia",
                "Numero 5 - Iscrizione di una vettura ad una gara",
                "Numero 6 - Registrazione del risultato conseguito da ciascuna vettura iscritta ad una gara",
                "Numero 7 - Verifica della possibilità di montare un componente su una vettura",
                "Numero 8 - Per ciascuna scuderia, stampare la somma totale dei finanziamenti ricevuti",
                "Numero 9 - Stampa annuale delle scuderie che hanno partecipato al campionato compreso il numero di finanziamenti",
                "Numero 10 - Visualizzare i piloti che hanno vinto nel «circuito di casa»",
                "Numero 11 - Per ciascuna scuderia, visualizzare la percentuale di gentleman driver di cui si compone l’equipaggio",
                "Numero 12 - Stampa mensile dei costruttori compreso il numero di componenti che ha fornito",
                "Numero 13 - Stampa della classifica finale dei punti conseguiti da tutte le vetture",
                "Numero 14 - Stampa delle classifiche finali di punti per tipo di motore",
                "Numero 15 - Stampare un report che elenchi ciascuna scuderia sulla base del rapporto punti/minuti di gara, mediando tra le macchine appartenenti a ciascuna scuderia"
        });

        operazioniComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = operazioniComboBox.getSelectedIndex();
                if (selectedIndex != -1) {
                    new OperazioneListener(selectedIndex + 1, MainFrame.this).actionPerformed(e);
                }
            }
        });

        mainPanel.add(operazioniComboBox, BorderLayout.CENTER);
        add(mainPanel);
        setLocationRelativeTo(null);
    }
}
