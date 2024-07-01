package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TentativasAntigas {
  private JPanel tentativasAnterioresPanelJogo;
  private UmaTentativaAntiga[] tentativasAntigas;
  private Dicas[] dicas;

  public TentativasAntigas(Integer numeroTentativas, Integer numeroDePinos) {
    tentativasAntigas = new UmaTentativaAntiga[numeroTentativas];
    dicas = new Dicas[numeroTentativas];

    this.tentativasAnterioresPanelJogo = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar
    tentativasAnterioresPanelJogo.setBorder(BorderFactory.createTitledBorder("Tentativas Anteriores"));

    for (int i = 0; i < numeroTentativas; i++) {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = i;
      gbc.anchor = GridBagConstraints.LINE_START;

      // Painel para a linha de tentativa anterior
      JPanel linhaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

      // Adiciona quadrado com 4 círculos ao lado
      
      dicas[i] = new Dicas(numeroDePinos);
      
      linhaPanel.add(dicas[i].getPanel());

      // Adiciona círculos da tentativa anterior
      tentativasAntigas[i] = new UmaTentativaAntiga("GRAY", numeroDePinos);
      linhaPanel.add(tentativasAntigas[i].getPanel());

      tentativasAnterioresPanelJogo.add(linhaPanel, gbc);
    }

  }

  public JPanel getPanel() {
    return tentativasAnterioresPanelJogo;
  }

  public void setPinos(int tentativa, Cor[] cores, Integer acertos) {
    tentativasAntigas[tentativa].setPinos(cores);
    dicas[tentativa].setPinos(acertos);
  }
}
