/* 2. You have a file that stores data about players' scores they achieved in different matches against different
  teams at different points in time, You have to identify the highest score of each player among all the matches If
  the entry is only a string that is the name of the player, and if the entry is pak_55_01_nov that implies the match
  against Pakistan scored 55 runs on November first.
*/
package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    public static void main(String[] args) {
        String csvFile = "F:\\java project\\untitled\\src\\Scores.csv";
        String line;
        String cvsSplitBy = ",";
        Map<String, Integer> playerScores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String playerName = data[0];
                for (int i = 1; i < data.length; i++) {
                    String[] matchData = data[i].split("_");
                    int score = Integer.parseInt(matchData[1]);
                    playerScores.put(playerName, Math.max(playerScores.getOrDefault(playerName, 0), score));
                }
            }

            // Print highest score of each player
            for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

