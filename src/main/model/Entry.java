package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents an entry with a muscle group, repetition, weight, name of workout, and set
public class Entry implements Writable {
    private String muscleGroup; // name of muscle group being worked out
    private int repetition;     // number of repetitions
    private int weight;         // the weight of the equipment
    private String nameWorkout;    // name of the workout done
    private int set;            // how many sets

    // REQUIRES: muscleGroup has a non-zero length
    //           repetition, weight, nameWorkout, and set are non-zero integers
    // EFFECTS: creates an entry that tracks the workout user just completed
    public Entry(String muscleGroup, int weight, int repetition, String nameWorkout, int set) {
        this.muscleGroup = muscleGroup;
        this.weight = weight;
        this.repetition = repetition;
        this.nameWorkout = nameWorkout;
        this.set = set;
    }

    // EFFECTS: Produce true is user is progress overloading
    public Boolean isProgressiveOverloading() {
        if (set > 3 || repetition < 6 || repetition > 10) {
            return false;
        } else {
            return true;
        }
    }

    // Puts the entry object into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("muscleGroup", muscleGroup);
        json.put("weight", weight);
        json.put("repetition", repetition);
        json.put("nameWorkout", nameWorkout);
        json.put("set", set);
        return json;
    }


    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getNameWorkout() {
        return nameWorkout;
    }

    public int getRepetition() {
        return repetition;
    }

    public int getWeight() {
        return weight;
    }

    public int getSet() {
        return set;
    }

}
