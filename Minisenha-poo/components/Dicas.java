package components;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Dicas {
  private PinoPB[] circleLabels;
  private Integer NUMERO_PINOS = 6;
  private JPanel quadradoPanel;

  public Dicas(Integer numeroDePinos) {
    this.NUMERO_PINOS = numeroDePinos;
    quadradoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    quadradoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Espaço à esquerda dos quadrados
    circleLabels = new PinoPB[NUMERO_PINOS];
    // Criando círculos para a tentativa atual
    for (int i = 0; i < NUMERO_PINOS; i++) {
        circleLabels[i] = PinoPB.criaPinoPreto();
        circleLabels[i].setOpaque(true);
        circleLabels[i].setPreferredSize(new Dimension(15, 15));
        circleLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quadradoPanel.add(circleLabels[i]);
    }
  }

  public JPanel getPanel() {
    return quadradoPanel;
  }

  public void setPinos(Integer acertos) {
    for (int i = 0; i < acertos; i++) {
      circleLabels[i].setCor(new Cor("WHITE", Color.WHITE));
    }
  }

}
