package ex1;

import java.util.Scanner;

public class Ex1 {
	
	
	
	/* My ID : 206374498
	 * 
	 * Ex1Function used to find the GPCD of two natural numbers.
	 * it gets 2 numbers from the user,first it uses 'gcd' function
	 * to find the highest gcd of the two numbers.
	 * after that, using 'maxPrimeFactor' will extract the
	 * greatest Prime number of the gcd which also
	 * divides both numbers without remainder.
	 * if there is no such number, user will get 1 as the GPCD. 
	 */
	
	public static long Ex1Function(long num1, long num2) {
        long gcd = gcd(num1,num2);
        return maxPrimeFactor(gcd);
    }

    public static long gcd(long num1, long num2) {
        if (num1 < num2) {
            long temp = num1;
            num1 = num2 ;
            num2 = temp;
        }
        
        /*
         * Euclid's algorithm
         * This algorithm is based on the fact that the greatest common divisor of two numbers does not change if the
         * larger number is replaced by its difference with the smaller number.
         */
        
        while (num2 != 0) {
            long temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public static long maxPrimeFactor(long gcd) {
        long maxPrime = 1;
        
        /* This while loop will run until gcd cannot be divided by 2
         * and will update maxPrime if a greater prime factor is found
         * For example , gcd = 12
         * 12 % 2 = 0, so maxPrime = 2 and then gcd = 6
         * 6 % 2 = 0, so maxPrime = 2 and then gcd = 3
         * and then 3 % 2 != 0, so we break out of the loop
         */
        
        while (gcd % 2 == 0) {
            maxPrime = 2;
            gcd = gcd / 2;
        }
        
        /* This for loop will run until gcd cannot be divided by any odd number
         * and will update maxPrime if a greater prime factor is found
         * For example , gcd = 15
         * 15 % 3 = 0, so maxPrime = 3 and then gcd = 5
         * 5 % 3 != 0, so we break out of the while loop
         * then we increase 'i' by 2 so 5 % 5 = 0, so maxPrime = 5 and gcd = 1
         * then 1 % 5 != 0, so we break out of the while loop
         * then gcd = 1, so we break out of the for loop,
         * then we return maxPrime = 5.
         */
        
        for (int i = 3; i <= Math.sqrt(gcd); i = i + 2) {
            while (gcd % i == 0) {
                maxPrime = i;
                gcd = gcd / i;
            }
        }
        if (gcd > 2) {
            maxPrime = gcd;
        }	
        return maxPrime;
    }
    
    /*
     * main function uses scanner to get the numbers from
     * the user, and return Ex1Funtion answer to the user.
     */
	public static void main(String[] args) {			

		long num1 = 1;
		long num2 = 1;

		System.out.println("Enter first number");
		Scanner sc = new Scanner (System.in);
		num1 = sc.nextLong();
		
		System.out.println("Enter second number");
		num2 = sc.nextLong();
		
		sc.close();
	
	long start = System.nanoTime();						
	long answer = Ex1Function(num1, num2);						
	long end = System.nanoTime();						
	double dt = (end - start)/(1000.0);					
	
	System.out.println
	("The GPCD is " + answer + " The time took to find the GPCD is " + dt + " micro seconds. ");
	}
	
    
}

