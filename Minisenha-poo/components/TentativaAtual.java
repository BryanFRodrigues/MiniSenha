package components;

import javax.swing.*;

public class TentativaAtual {
  private PinoColorido[] pinos;
  private Integer NUMERO_PINOS = 6;

  public TentativaAtual(JPanel tentativaAtualPanelJogo, Integer numeroPinos) {
    this.NUMERO_PINOS = numeroPinos;
    pinos = new PinoColorido[NUMERO_PINOS];
    // Criando círculos para a tentativa atual
    for (int i = 0; i < NUMERO_PINOS; i++) {
      pinos[i] = PinoColorido.criaPinoColorido("RED");
      tentativaAtualPanelJogo.add(pinos[i]);
    }
  }

  public PinoColorido[] getPinos() {
    return pinos;
  }
}
