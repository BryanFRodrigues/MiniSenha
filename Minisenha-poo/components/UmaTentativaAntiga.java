package components;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class UmaTentativaAntiga {
  private PinoColorido[] pinos;
  private Integer NUMERO_PINOS = 6;
  private JPanel tentativa;

  public UmaTentativaAntiga(String defaultColor, Integer numeroDePinos) {
    this.NUMERO_PINOS = numeroDePinos;
    this.tentativa = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar

    pinos = new PinoColorido[NUMERO_PINOS];
    // Criando círculos para a tentativa atual
    for (int i = 0; i < NUMERO_PINOS; i++) {
      pinos[i] = PinoColorido.criaPinoColorido(defaultColor);
      pinos[i].setEnabled(false);
      tentativa.add(pinos[i]);
    }

  }

  public JPanel getPanel() {
    return tentativa;
  }
  public PinoColorido[] getPinos() {
    return pinos;
  }

  public void setPinos(Cor[] cores) {
    for (int i = 0; i < NUMERO_PINOS; i++) {
      pinos[i].setCor(cores[i]);
    }
  }

}
