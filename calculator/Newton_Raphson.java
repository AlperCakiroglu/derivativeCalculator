package itujoker.calculator;


public class Newton_Raphson {
    private double x;//initial guess
    private double e;//absolute err
    private int n;
    private double d;
    private String function;

    public Newton_Raphson(double absolute_err, double d, int n, double initial_guess,String function) {
        this.function=function;
        this.e = absolute_err;
        this.d = d;
        this.n = n;
        this.x = initial_guess;
    }

    private double f(Double a){

        String gecici=function;
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
    private double fder(Double a){
        return new Numerical_Diff(function,a,0.01).find_Derivative();
    }

    public double findRoot(){
        for(int i=1;i<=n;i+=2){
            double f=f(x);
            double f1=fder(x);
            /*if(Math.abs(f1)<d){
                return 61.61616161;//to small slope
            }*/
            double x1=x- f/f1;
            if(Math.abs((x1-x)/x1)<e){
                return x1;
            }
            x=x1;
        }
        return 61.616161;//cant find the root
    }
}
