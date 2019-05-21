package itujoker.calculator;


public class Numerical_Diff {
    double x, h;
    String function;

    public Numerical_Diff(String function, double x, double h) {
        this.function = function;
        this.h = h;
        this.x = x;
    }


    public double f(double a) {

        String gecici = this.function;
        for (int i = 0; i < gecici.length(); i++) {
            if (gecici.charAt(i) == 'y' && i < gecici.length() - 1) {
                gecici = gecici.substring(0, i) + a + gecici.substring(i + 1);

            } else if (gecici.charAt(i) == 'y' && gecici.length() - 1 == i) {
                gecici = gecici.substring(0, i) + a;
            }
        }


        return new Calculator().calculate(gecici, MainActivity.isDeg);

    }


    public double find_Derivative() {
        return (1 / (2 * h)) * (f(x - 2 * h) - 4 * f(x - h) + 3 * f(x));
    }
}