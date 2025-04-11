package ro.IOwithEnums;

import java.io.Serializable;
import java.time.Duration;
//Athlete class representing a biathlon athlete
public class Athlete implements Comparable<Athlete>, Serializable {
    private final int number;
    private final String name;
    private final String country;
    private final String SkiTime;
    private final String[] shootings;
    private final int penaltySeconds;
    private final Duration finalTime;
    //Constructor for the Athlete class
    public Athlete(int number, String name, String country, String SkiTime, String[] shootings) {
        this.number = number;
        this.name = name;
        this.country = country;
        this.SkiTime = SkiTime;
        this.shootings = shootings;
        this.penaltySeconds = calculatePenalties(shootings);
        this.finalTime = calculateFinalTime(SkiTime, penaltySeconds);
    }

    private int calculatePenalties(String[] shootings) { //Calculate penalties based on shooting range
        int penalties = 0;
        for (String range : shootings) {
            for (char c : range.toCharArray()) {
                if (c == 'o') penalties += 10; //10 seconds penalty for each 'o' (missed target)
            }
        }
        return penalties;
    }
    //Calculate the final time based on ski time and penalties
    private Duration calculateFinalTime(String rawTime, int penaltySeconds) {
        String[] parts = rawTime.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return Duration.ofMinutes(minutes).plusSeconds(seconds).plusSeconds(penaltySeconds);
    }

    public String getFormattedTime() {
        long totalSeconds = finalTime.getSeconds();
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d (%s + %ds)", minutes, seconds, SkiTime, penaltySeconds);
    }
    //Getters for the athlete's properties
    public String getName() {
        return name;
    }

    @Override //Overriding the compareTo method to sort athletes by their final time
    public int compareTo(Athlete other) {
        return this.finalTime.compareTo(other.finalTime);
    }

    public String[] getShootings() {
        return shootings;
    }

    public String getCountry() {
        return country;
    }

    public int getNumber() {
        return number;
    }

    public void calculatePenalties() {
    }
}