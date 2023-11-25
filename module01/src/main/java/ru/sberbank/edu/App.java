package ru.sberbank.edu;
import ru.sberbank.edu.GreetingImpl;
import ru.sberbank.edu.GCD;

import java.util.Scanner;

/**
 *
 * Checking homework
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner Console = new Scanner( System.in );

        System.out.println( "First task" );
        System.out.println( new GreetingImpl().getBestHobby() );

        System.out.println( "Second task");
        System.out.println( "Enter the first number");
        int firstNumber = Console.nextInt();
        System.out.println( "Enter the second number");
        int secondNumber = Console.nextInt();

        System.out.println( new GCD().getDivisor( firstNumber, secondNumber) );

    }
}
