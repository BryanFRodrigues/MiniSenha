import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import components.Configuracao;
import components.Cor;
import components.PinoColorido;
import components.Player;
import components.Ranking;
import components.RespostaAntigas;
import components.TentativaAtual;
import components.TentativasAntigas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.List;

public class MiniSenhaGUI extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private static JPanel tentativaAtualPanelJogo;
    private JPanel tentativaAtualPanelTeste;
    private JPanel respostaPanelTeste;
    private JTable leaderboardTable;
    private Integer numeroDeTentatiInteger = 0;
    private Integer numeroDeTentatiIntegerTeste = 0;
    private static PinoColorido[] pinosSenha; 
    private static Integer numeroDeTentativas = 0;
    private static Integer numeroDePinos = 0;

    public static String getRandomColor() {
        String[] colors = {
            "RED",
            "GREEN",
            "YELLOW",
            "BLUE",
            "MAGENTA",
            "ORANGE",
            "GRAY",
            "PINK"
        };
        
        Random random = new Random();
        int index = random.nextInt(colors.length); // Gera um índice aleatório
        return colors[index];
    }

    public static void setSenha() {
        pinosSenha = new PinoColorido[numeroDePinos];
        for (int i = 0; i < numeroDePinos; i++) {
            pinosSenha[i] = PinoColorido.criaPinoColorido(getRandomColor());
            System.out.println(pinosSenha[i].getCor());
        }
    }

    public static Boolean verificaTentativa(PinoColorido[] tentativa) {
        for (int i = 0; i < numeroDePinos; i++) {
            if (tentativa[i].getCor() != pinosSenha[i].getCor()) {
                return false;
            }
        }
        return true;
        
    }

    public static Integer getAcertos(PinoColorido[] tentativa) {
        Integer acertos = 0;
        for (int i = 0; i < numeroDePinos; i++) {
            if (tentativa[i].getCor() == pinosSenha[i].getCor()) {
                acertos++;
            }
        }
        return acertos;
        
    }

    public MiniSenhaGUI(Integer numeroDeTentativasParams, Integer numeroDePinosParams) {
        this.numeroDeTentativas = numeroDeTentativasParams;
        this.numeroDePinos = numeroDePinosParams;

        setTitle("Mini Senha Game");
        // setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSenha();

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        setSize(Toolkit.getDefaultToolkit().getScreenSize()); // Define o tamanho para o tamanho da tela

        // Configuração do painel de configuração
        Configuracao configuracao = new Configuracao(numeroDePinos, numeroDeTentativas);
        Ranking ranking = new Ranking("./components/leaderboard.txt");

        // Criação dos paineis
        JPanel menuPanel = new JPanel();
        JPanel jogoPanel = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar
        JPanel testePanel = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar
        JPanel leaderboardPanel = new JPanel(new BorderLayout());

        

        // Adicionando títulos aos paineis para identificação
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        jogoPanel.setBorder(BorderFactory.createTitledBorder("Jogo"));
        testePanel.setBorder(BorderFactory.createTitledBorder("Teste"));
        leaderboardPanel.setBorder(BorderFactory.createTitledBorder("Leaderboard"));

        // Configuração do layout dos paineis
        menuPanel.setLayout(new FlowLayout());
        leaderboardPanel.setLayout(new BorderLayout());

        // Adicionando leaderboard ao painel

        leaderboardTable = ranking.getLeaderboardTable();
        leaderboardTable.getColumnModel().getColumn(0).setPreferredWidth(150); // ajusta a largura da primeira coluna

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        leaderboardPanel.add(scrollPane, BorderLayout.CENTER);

        // Adicionando botões ao menu
        JButton jogoButton = new JButton("Jogo Normal");
        JButton testeButton = new JButton("Teste");

        menuPanel.add(jogoButton);
        menuPanel.add(testeButton);

        // CardLayout para alternar entre Jogo e Teste
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(jogoPanel, "Jogo");
        mainPanel.add(testePanel, "Teste");

        // Simulando tentativas anteriores com círculos vazios e quadrado ao lado
        TentativasAntigas tentativasAnterioresPanelJogo = new TentativasAntigas(numeroDeTentativas, numeroDePinos); // Usando GridBagLayout para

        tentativaAtualPanelJogo = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tentativaAtualPanelJogo.setBorder(BorderFactory.createTitledBorder("Sua Tentativa"));

        TentativaAtual tentativaAtual = new TentativaAtual(tentativaAtualPanelJogo, numeroDePinos);

        // Botão para submeter a tentativa
        JButton submitButtonJogo = new JButton("Submeter Tentativa");
        submitButtonJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar lógica para processar a tentativa atual
                // e atualizar o painel de tentativas anteriores do jogo, por exemplo.
                PinoColorido[] pinos = tentativaAtual.getPinos();
                Cor [] cores = new Cor[numeroDePinos];
                for (int i = 0; i < numeroDePinos; i++) {
                    cores[i] = pinos[i].getCor();
                    System.out.println(pinos[i].getCor());
                }
                Integer acertos = getAcertos(pinos);

                tentativasAnterioresPanelJogo.setPinos(numeroDeTentatiInteger, cores, acertos);
                numeroDeTentatiInteger++;
                if (verificaTentativa(pinos)) {
                    String input = JOptionPane.showInputDialog(null, "Parabéns você acertou, digite seu nome:", "Entrada de Nome", JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Você digitou: " + input);
                    ranking.addPlayer(input, (configuracao.getSelecionarPinosSenhas() * 500) / (numeroDeTentatiInteger));
                    
                    MiniSenhaGUI telaNova = new MiniSenhaGUI(numeroDeTentativas, numeroDePinos);
                    telaNova.setVisible(true);
                    dispose();
                } else {
                    if( numeroDeTentatiInteger >= numeroDeTentativas) { 
                        JOptionPane.showMessageDialog(null, "Você perdeu! Tente novamente!");
                        MiniSenhaGUI telaNova = new MiniSenhaGUI(numeroDeTentativas, numeroDePinos);
                        telaNova.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tente novamente!");
                    }
                }
            }
        });
        tentativaAtualPanelJogo.add(submitButtonJogo);

        // Adicionando componentes à tela de jogo com GridBagLayout para centralizar
        GridBagConstraints gbcJogoPanel = new GridBagConstraints();
        gbcJogoPanel.gridx = 0;
        gbcJogoPanel.gridy = 0;
        gbcJogoPanel.weightx = 1.0; // Expandir na horizontal
        gbcJogoPanel.weighty = 1.0; // Expandir na vertical
        gbcJogoPanel.fill = GridBagConstraints.BOTH; // Preencher ambos os eixos
        jogoPanel.add(tentativasAnterioresPanelJogo.getPanel(), gbcJogoPanel);

        gbcJogoPanel.gridy = 1;
        gbcJogoPanel.weighty = 0.0; // Não expandir
        jogoPanel.add(tentativaAtualPanelJogo, gbcJogoPanel);
        
        // teste
        tentativaAtualPanelTeste = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tentativaAtualPanelTeste.setBorder(BorderFactory.createTitledBorder("Sua Tentativa"));
        TentativasAntigas tentativasAnterioresPanelJogoTeste = new TentativasAntigas(numeroDeTentativas, numeroDePinos); // Usando GridBagLayout para
        TentativaAtual tentativaAtualTeste = new TentativaAtual(tentativaAtualPanelTeste, numeroDePinos);
        
        // Botão para submeter a tentativa
        JButton submitButtonJogoPanelTeste = new JButton("Submeter Tentativa");
        submitButtonJogoPanelTeste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar lógica para processar a tentativa atual
                // e atualizar o painel de tentativas anteriores do jogo, por exemplo.
                PinoColorido[] pinos = tentativaAtualTeste.getPinos();
                Cor [] cores = new Cor[configuracao.getSelecionarPinosSenhas()];
                for (int i = 0; i < numeroDePinos; i++) {
                    cores[i] = pinos[i].getCor();
                    System.out.println(pinos[i].getCor());
                }
                Integer acertos = getAcertos(pinos);

                tentativasAnterioresPanelJogoTeste.setPinos(numeroDeTentatiIntegerTeste, cores, acertos);
                numeroDeTentatiIntegerTeste++;

                if (verificaTentativa(pinos)) {
                    String input = JOptionPane.showInputDialog(null, "Parabéns você acertou, digite seu nome:", "Entrada de Nome", JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Você digitou: " + input);
                } else {
                    JOptionPane.showMessageDialog(null, "Tente novamente!");
                }
            }
        });
        tentativaAtualPanelTeste.add(submitButtonJogoPanelTeste);

        RespostaAntigas respostaAntigas = new RespostaAntigas();

        // Criando a área de resposta na tela de teste
        respostaPanelTeste = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        respostaPanelTeste.setBorder(BorderFactory.createTitledBorder("Resposta"));
        respostaAntigas.respostaAntigas(respostaPanelTeste, numeroDePinos, pinosSenha);

        // Adicionando componentes à tela de teste com GridBagLayout para centralizar
        GridBagConstraints gbcTestePanel = new GridBagConstraints();
        gbcTestePanel.gridx = 0;
        gbcTestePanel.gridy = 0;
        gbcTestePanel.weightx = 1.0; // Expandir na horizontal
        gbcTestePanel.weighty = 1.0; // Expandir na vertical
        gbcTestePanel.fill = GridBagConstraints.BOTH; // Preencher ambos os eixos
        testePanel.add(tentativasAnterioresPanelJogoTeste.getPanel(), gbcTestePanel);

        gbcTestePanel.gridy = 1;
        gbcTestePanel.weighty = 0.0; // Não expandir
        testePanel.add(tentativaAtualPanelTeste, gbcTestePanel);

        gbcTestePanel.gridx = 1;
        gbcTestePanel.gridy = 0;
        gbcTestePanel.gridheight = 2;
        gbcTestePanel.weightx = 0.2; // Menos espaço para a resposta
        gbcTestePanel.fill = GridBagConstraints.VERTICAL; // Preencher verticalmente
        testePanel.add(respostaPanelTeste, gbcTestePanel);
        
        // adicionando botão para alterar configurações
        JPanel configuracaoPanel = configuracao.getConfiguracaoPanel();

        JButton submitButtonConfigs = new JButton("Alterar Configurações!");
        
        configuracaoPanel.add(submitButtonConfigs, BorderLayout.SOUTH);

        submitButtonConfigs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir Tela2
                MiniSenhaGUI telaNova = new MiniSenhaGUI(configuracao.getSelecionarTentativas(), configuracao.getSelecionarPinosSenhas());
                telaNova.setVisible(true);
                dispose();
            }
        });

        // Adicionando paineis ao Frame
        add(menuPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(configuracao.getConfiguracaoPanel(), BorderLayout.WEST);
        add(leaderboardPanel, BorderLayout.EAST);

        // Adicionando ActionListeners para os botões
        jogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Jogo");
            }
        });

        testeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Teste");
            }
        });

        // Exibir a interface
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MiniSenhaGUI(5, 4);
            }
        });

    }
}
