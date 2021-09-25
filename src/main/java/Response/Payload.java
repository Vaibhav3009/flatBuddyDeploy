package Response;

import model.Habits;
import model.Hobbies;

public class Payload {
    private Hobbies hobbies;
    private Habits habits;
    private Integer budgetMin;
    private Integer budgetMax;
    private String location;

    public Hobbies getHobbies() {
        return hobbies;
    }

    public void setHobbies(Hobbies hobbies) {
        this.hobbies = hobbies;
    }

    public Habits getHabits() {
        return habits;
    }

    public void setHabits(Habits habits) {
        this.habits = habits;
    }

    public Integer getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(Integer budgetMin) {
        this.budgetMin = budgetMin;
    }

    public Integer getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(Integer budgetMax) {
        this.budgetMax = budgetMax;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    }
