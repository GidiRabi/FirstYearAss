package Exe.Ex2;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.*;

/** author - Gal Ben Ami
 * ID:206374498
 */



/**
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * <p>
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author boaz.benmoshe
 */


public class Ex2 {
    /**
     * Epsilon value for numerical computation, it serves as a "close enough" threshold.
     */
    public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
    /**
     * The zero polynom is represented as an array with a single (0) entry.
     */
    public static final double[] ZERO = {0};

    /**
     * Two polynoms are equal if and only if they have the same coefficients - up to an epsilon (aka EPS) value.
     *
     * @param p1 first polynom
     * @param p2 second polynom
     * @return true iff p1 represents the same polynom as p2.
     */


    /*
    checks each figure in one of the arrays,
    if the function runs into a single figure that is not equal to its overlapping
    figure in the other array the answer will be false and the function will stop to run.
    making sure that the absolute value of the subtraction of two figures in the same index
    is less than EPS.
     */
    public static boolean equals(double[] p1, double[] p2) {
        boolean ans = true;
        // *** add your code here ***

        if(p1 == null || p2 == null)
            return false;

        if (p1.length == p2.length) {
            for (int i = 0; i < p1.length; i++) {
                if (p1[i] != p2[i]) {
                    ans = false;
                }
                if (Math.abs(p1[i] - p2[i]) <= EPS) {
                    ans = true;
                }
            }
        } else {
            ans = false;
        }
        return ans;
    }


// **************************


    /**
     * Computes the f(x) value of the polynom at x.
     *
     * @param poly
     * @param x
     * @return f(x) - the polynom value at x.
     */

    /*
    for loop goes over each figure in the array.
    the answer will be the sum of each figure multiplied by the given x^the index.
     */

    public static double f(double[] poly, double x) {
        double ans = 0;
        // *** add your code here ***
    if (poly == null || poly.length == 0 || Double.isNaN(x) || Double.isInfinite(x)) {
        return ans ;
    }
        for (int i = 0; i < poly.length; i++) {
            ans += poly[i] * Math.pow(x, i);
        }
        return ans;
    }


// **************************


    /**
     * Computes a String representing the polynom.
     * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
     *
     * @param poly the polynom represented as an array of doubles
     * @return String representing the polynom:
     */


    /*
     * if poly[i] equals 0, the function checks if theres another figure coming up.
     * if the coming up figure is positive - add "+"
     * in case it is a negative one add it to the sum as it is (with its "-")
     */

    public static String poly(double[] poly) {
        String ans = "";
        if(poly == null || poly.length == 0)
            return ans;
        boolean nextValIsNegative;
        // *** add your code here ***

        for (int i = poly.length - 1; i >= 0; i--) {
            if (poly[i] == 0) {
                nextValIsNegative = checkNextValIsNegative(poly, i);
                if (!nextValIsNegative) {
                    ans += "+";
                }
                continue;
            } else if (i == 0) {
                ans += "" + poly[i];
            } else if (i == 1) {
                if (poly[i] == 1) {
                    ans += "x ";
                    continue;
                }
                ans += poly[i] + "x";
            } else {

                ans += poly[i] + "x^" + i;
            }
            if ((i - 1) >= 0 && poly[i - 1] > 0) {
                ans += "+";
            }
        }
        return ans;

    }


    /**
     * Given a polynom (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented iteratively (none recursive).
     *
     * @param p   - the polynom
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     */

    /*
        This function find the 'x' that when we calculate p(x) we get p(x)=0
        We assume that p(x1)*p(x2) always <= 0 so we take the middle of the x1 and x2
        : middleX = (x1 + x2) / 2
        now we want to get closer to p(middleX) = 0.
        Every iteration we check if the left side or right side of the current interval
        assume that p(x1)*p(middleX) or p(middleX)*p(x2) <= 0
        then we update x1 or x2 to the middleX, and then we update the middleX .
        we stop when p(middleX) <= eps.
     */
    public static double root(double[] p, double x1, double x2, double eps) {
        double fX1 = f(p, x1);
        double fX2 = f(p, x2);
        if (fX1 * fX2 > 0) {
            return -9999999;
        }

        double middleX = (x1 + x2) / 2;             // middle x
        double fMiddleX = f(p, middleX);            // f(middle x)
        double absFMiddleX = Math.abs(fMiddleX);    // |f(middle x)|

        while (absFMiddleX > eps) {

            if (fX1 * fMiddleX <= 0) {
                x2 = middleX;
            } else {
                x1 = middleX;
            }
            middleX = (x1 + x2) / 2;
            fMiddleX = f(p, middleX);
            absFMiddleX = Math.abs(fMiddleX);
        }
        return middleX;
    }


    /**
     * Given a polynom (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented recursivly.
     *
     * @param p   - the polynom
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     */


     /*
      * Same explanation as root function but here,
      * We implement a recursive function.
      * Recursive function should have a stop case and step case,
      * so our stop case is when p(middleX)=absFMiddleX <= eps
      * and in our step case we examine the edges of the following two sections:
      *  p(x1)*p(middleX) or p(middleX)*p(x2) <= 0
      * And these testers among the segments fulfill the condition that the markers of the end numbers be different.
      */
    public static double root_rec(double[] p, double x1, double x2, double eps) {

        // *** add your code here ***

        double fX1 = f(p, x1);
        double fX2 = f(p, x2);
        if (fX1 * fX2 > 0) {
            return -9999999;
        }
        double middleX = (x1 + x2) / 2;
        double fMiddleX = f(p, middleX);
        double absFMiddleX = Math.abs(fMiddleX);
        // stop case
        if (absFMiddleX <= eps) {
            return middleX;
        }
        // step case
        if (fX1 * fMiddleX <= 0) {
            return root_rec(p, x1, middleX, eps);
        } else {
            return root_rec(p, middleX, x2, eps);

        }
    }


    /**
     * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
     * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
     *
     * @param p1  - first polynom
     * @param p2  - second polynom
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
     */

    /*
     * while loop that goes on until we get our wanted answer.
     * if the multiplication of (p1x1 - p2x1) * (p1x12 - p2x12) <= 0 that mean the wanted 'x' answer
     * is still between that range, and for each iteration we set the rightside x to be closer and closer to the left side
     *
     * if the term (p1x1 - p2x1) * (p1x12 - p2x12) <= 0 doesnt happen anymore, that mean that the wanted 'x' is on the other side
     * of the range.
     * these action goes on until we get the wanted 'x'.
     *
     */
    public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
        double x12 = (x1 + x2) / 2;
        double p1x1 = f(p1, x1);
        double p1x2 = f(p1, x2);
        double p2x1 = f(p2, x1);
        double p2x2 = f(p2, x2);
        // *** add your code here ***
        double p1x12 = f(p1, x12);
        double p2x12 = f(p2, x12);

        if ((p1x1 - p2x1) * (p1x2 - p2x2) > 0) {
            return -999999;
        }

        while (Math.abs(p1x12 - p2x12) > eps) {

            if ((p1x1 - p2x1) * (p1x12 - p2x12) <= 0) {
                x2 = x12;

            } else {
                x1 = x12;

            }
            x12 = (x1 + x2) / 2;
            p1x12 = f(p1, x12);
            p2x12 = f(p2, x12);
        }
        return x12;
    }

    // **************************

    /**
     * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes".
     * This function computes an approximation of the area between the polynoms within the x-range.
     * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
     *
     * @param p1            - first polynom
     * @param p2            - second polynom
     * @param x1            - minimal value of the range
     * @param x2            - maximal value of the range
     * @param numberOfBoxes - a natural number representing the number of boxes between xq and x2.
     * @return the approximated area between the two polynoms within the [x1,x2] range.
     */

    /*
     * I preferred to write the documentation for this function in the code lines.
     */
    public static double area(double[] p1, double[] p2, double x1, double x2, int numberOfBoxes) {

        double ans = 0;
        // *** add your code here ***
        double width = (x2 - x1) / numberOfBoxes; // we calculate the width for every 'rectangle' by Riemann integral;
        for (int i = 0; i < numberOfBoxes; i++) { // we run on every 'rectangle' and sum all the area's that we got from every 'rectangle' area
            double x = x1 + i * width; // we choose our 'x' to be the left 'x' of the current rectangle
            double y1 = f(p1, x);
            double y2 = f(p2, x);
            double height = Math.abs(y1-y2); // the height of the current rectangle
            ans += width * height; // area formula
        }
        // **************************
        return ans;
    }

    /**
     * This function computes the array representation of a polynom from a String
     * representation. Note:given a polynom represented as a double array,
     * getPolynomFromString(poly(p)) should return an array equals to p.
     *
     * @param p - a String representing polynom.
     * @return - answer
     */

    /* for this current function I preferred to add the documentation in the lines of the code,
     * so it will be more understandable.
     */
    public static double[] getPolynomFromString(String p) {
        // *** add your code here ***
          if(Objects.equals(p, "")){
            return ZERO;
        }
        String[] splitted = p.split("(?=[-+])"); //  we are splitting the string every time theres '+' or '-' chars, into splitted[i] for each index
        String[] coefficients = new String[splitted.length]; // creating a new String array which will held the coefficients
        int[] powers = new int[splitted.length];  // creating a new int array which will held the powers in our poly
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].contains("x")) {         // we check if splitted[i] contains "x" char
                coefficients[i] = splitted[i].substring(0, splitted[i].indexOf("x"));        //we substring splitted[i] and take the string before "x"
                if (splitted[i].contains("x^")) {          // we check if the power is greater than one
                    powers[i] = Integer.parseInt(splitted[i].substring(splitted[i].indexOf("^") + 1)); // here we are inserting into our power array the current power
                                                                                                       // we do this using the substring function which split the array according to the
                                                                                                        // data we insert.

                } else if (splitted[i].lastIndexOf("x") == splitted[i].length() - 1) {          // in case x power is one
                    powers[i] = 1;
                }
            } else {
                coefficients[i] = splitted[i];
            }
        }
        double[] result = new double[powers[0]+1]; // in this part we are flip our array into the necessary representation.
        for (int i = 0; i < coefficients.length; i++) {
            int power = powers[i];
            double coefficient = Double.parseDouble(coefficients[i]);
            result[power] = coefficient; // initialize result array with the necessary representation.
        }
        return result;
        // **************************
    }


    /**
     * This function computes the polynom which is the sum of two polynoms (p1,p2)
     *
     * @param p1
     * @param p2
     * @return
     */
/*
 * we create a new array which will be in the size of the bigger array
 * for loop add the sum of each p1[i] and p2[i] to sum[i]
 * sum array contains each product of the sum of two figures.
 */
    public static double[] add(double[] p1, double[] p2) {
        // *** add your code here ***
        int size = max(p1.length, p2.length);
        double[] sum = new double[size];
        for (int i = 0; i < p1.length; i++) {
            sum[i] = p1[i];
        }
        for (int i = 0; i < p2.length; i++) {
            sum[i] = sum[i] + p2[i];
        }
        return sum;
        // **************************
    }


    /**
     * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
     *
     * @param p1
     * @param p2
     * @return
     */


    /* first we create a new array and fill it with 0.0 values,that array will contain the multiplied figures
     * for loop goes over each p1[i] and for each one it takes p2[j] and multiply them
     * the result of each multiplication goes to mulResult array in place [i+j]
     */

    public static double[] mul(double[] p1, double[] p2) {
        // *** add your code here ***
        double[] mulResult = new double[p1.length + p2.length - 1];
        for (int i = 0; i < p1.length + p2.length - 1; i++){
            mulResult[i] = 0;
        }
        for (int i = 0; i < p1.length; i++) {
            for (int j = 0; j < p2.length; j++) {
                mulResult[i + j] += p1[i] * p2[j];
            }
        }
        return mulResult;
        // **************************
    }

    /**
     * This function computes the derivative polynom of po.
     *
     * @param po
     * @return
     */

/* D is an array which will get the coefficients from the po array.
 * will be less 1 than po in size because the free coefficient gets removed from the polynom.
 * D elements will start from P[i+1] because theres a free variable at the start
 * of every polynom.
 * D[i] is a po[i+1] multiplied by its index + 1
 */
    public static double[] derivative(double[] po) {
        // *** add your code here ***

        double[] D = new double[po.length - 1];
        for (int i = 0; i < D.length; i++)
            D[i] = po[i + 1] * (i + 1);

        return D;
        // **************************
    }

    /**
     * This function computes a polynomial representation from a set of 2D points on the polynom.
     * Note: this function only works for a set of points containing three points, else returns null.
     *
     * @param xx
     * @param yy
     * @return an array of doubles representing the coefficients of the polynom.
     * Note: you can assume xx[0]!=xx[1]!=xx[2]
     */

    /*
     * I used this site to help me figure this function out:
     * https://math.stackexchange.com/questions/680646/get-polynomial-function-from-3-points
     * This function get as argument two double arrays represent three of 2D points (xi,yi) : 0 <= i <= 2
     * Then its calculate the polynom that represent the formula:
     *                                                          ax^2+bx+c
     *  and return the array that represent the coefficient of this polynomial.
     */
    public static double[] PolynomFromPoints(double[] xx, double[] yy) {
        double[] ans = null;
        if (xx != null && yy != null && xx.length == 3 && yy.length == 3) {
            // *** add your code here ***
            double a = (xx[0] * (yy[2] - yy[1]) + xx[1] * (yy[0] - yy[2]) + xx[2] * (yy[1] - yy[0])) / ((xx[0] - xx[1]) * (xx[0] - xx[2]) * (xx[1] - xx[2]));
            double b = (yy[1] - yy[0]) / (xx[1] - xx[0]) - a * (xx[0] + xx[1]);
            double c = yy[0] - a * xx[0] * xx[0] - b * xx[0];
            ans = new double[]{c, b, a};
            // **************************
        }
        return ans;
    }

    ///////////////////// Private /////////////////////
    // you can add any additional functions (private) below

    private static boolean checkNextValIsNegative(double[] arr, int index) {
        // This private method check if arr[index-1] is negative
        if (index > 0 && index < arr.length) {
            return (arr[index - 1] <= 0);
        }
        return false;
    }

}
