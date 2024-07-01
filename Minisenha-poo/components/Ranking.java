package components;

import java.io.*;
import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Ranking {
    private List<Player> players;
    private String filePath;
    private JTable leaderboardTable;
    private Object[][] data;
    private int cont;

    public Ranking(String filePath) {
        this.filePath = filePath;
        this.players = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                players.add(new Player(name, score));
            }
            Collections.sort(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(String name, int score) {
        Player newPlayer = new Player(name, score);
        players.add(newPlayer);
        Collections.sort(players);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Player player : players) {
                writer.write(player.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printRanking() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public JTable getLeaderboardTable() {
        String[] columnNames = { "Posição / Nome", "Pontuação" };
        
        data = new Object[][] { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }};

        cont = 0;

        for (Player player : players) {
            data[cont][0] = (players.indexOf(player) + 1) + ". " + player.getName();
            data[cont][1] = player.getScore();
            cont++;
        }
        printRanking();
        TableModel model = new DefaultTableModel(data, columnNames);
        this.leaderboardTable = new JTable(model);
        return this.leaderboardTable;
    }
}