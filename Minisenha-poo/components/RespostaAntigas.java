package components;

import javax.swing.*;
import java.awt.*;

public class RespostaAntigas {
    
    public void respostaAntigas(JPanel panel, int pinosSenhas, PinoColorido[] pinos) {

        for (int i = 0; i < pinosSenhas; i++) {
            pinos[i].setEnabled(false);
            panel.add(pinos[i]);
        }
    }
}
