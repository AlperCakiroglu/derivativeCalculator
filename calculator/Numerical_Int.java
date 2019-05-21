package itujoker.calculator;



public class Numerical_Int {
    double a,b,n;//n is range
    String func;

    public Numerical_Int(String func, double a, double b,  double n) {
        this.a = a;
        this.b = b;
        this.func = func;
        this.n = n;
    }

    private double f(Double a){

        String gecici=func;
        for(int i=0;i<gecici.length();i++){
            if(gecici.charAt(i)=='y'&& i<gecici.length()-1){
                gecici=gecici.substring(0,i)+a+gecici.substring(i+1);

            }
            else if(gecici.charAt(i)=='y' && gecici.length()-1==i){
                gecici=gecici.substring(0,i)+a;
            }
        }


        return new Calculator().calculate(gecici,MainActivity.isDeg);


    }

    public double calculate_Int(){

        double h=(b-a)/n;
        double sum1=f(a+h/2);
        double sum2=0;

        for(int i=1;i<n;i++){
            sum1+=f(a+h*i+h/2);
            sum2+=f(a+h*i);
        }
        return (h/6) * (f((double)a)+f((double)b)+4*sum1+2*sum2);

    }
}
