package operazioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OperazioneListener implements ActionListener {

    private final int numeroOperazione;
    private final JFrame frame;

    public OperazioneListener(int numeroOperazione, JFrame frame) {
        this.numeroOperazione = numeroOperazione;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        creaInterfacciaGrafica();
    }

    private void creaInterfacciaGrafica() {
        InterfacciaGrafica ig = new InterfacciaGrafica(numeroOperazione);
        ig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ig.setVisible(true);
    }
}
