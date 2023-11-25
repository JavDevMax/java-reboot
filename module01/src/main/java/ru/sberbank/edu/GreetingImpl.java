package ru.sberbank.edu;

public class GreetingImpl implements Greeting {

    private static final String hobby = "Doing homework";

    /**
     * Method return my hobby
     */
    @Override
    public String getBestHobby() {
        return hobby;
    }
}
