package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;

public class Configuracao {
  private JPanel configuracaoPanel;
  private Integer selecionarPinosSenhas = 4;
  private Integer selecionarTentativas = 5;

  public Configuracao(Integer pinoSenha, Integer tentativa) {
    // Criando painel de configuração
    this.configuracaoPanel = new JPanel();
    configuracaoPanel.setBorder(BorderFactory.createTitledBorder("Configuração"));
    configuracaoPanel.setLayout(new GridLayout(8, 1));
    // Adicionando opções de configuração ao painel de configuração
    configuracaoPanel.add(new JLabel("Pinos na Senha:"));
    JComboBox<Integer> pinosSenhaComboBox = new JComboBox<>(new Integer[] { 4, 5, 6 });

    pinosSenhaComboBox.addActionListener((ActionListener) new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selecionarPinosSenhas = (Integer) pinosSenhaComboBox.getSelectedItem();
      }
    });
    pinosSenhaComboBox.setSelectedItem(pinoSenha);

    configuracaoPanel.add(pinosSenhaComboBox);

    configuracaoPanel.add(new JLabel("Tentativas:"));
    JComboBox<Integer> tentativasComboBox = new JComboBox<>(new Integer[] { 5, 6, 7, 8 });
    tentativasComboBox.setSelectedItem(tentativa);
    tentativasComboBox.addActionListener((ActionListener) new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selecionarTentativas = (Integer) tentativasComboBox.getSelectedItem();
      }
    });

    configuracaoPanel.add(tentativasComboBox);

    configuracaoPanel.add(new JPanel()); // espaço em branco para preencher layout
  }

  public JPanel getConfiguracaoPanel() {
    return configuracaoPanel;
  }

  public Integer getSelecionarPinosSenhas() {
    return selecionarPinosSenhas;
  }

  public Integer getSelecionarTentativas() {
    return selecionarTentativas;
  }
}
