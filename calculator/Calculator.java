package itujoker.calculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Calculator {


    public double calculate(String input, boolean isDegC) {
        return evaluatePostfix(infixToPostfix(input), isDegC);
    }

    private Queue infixToPostfix(String infix) {
        String result = "";
        infix = turnString(infix);
        Stack<Character> s = new Stack();
        Queue q = new LinkedList();

        for (int i = 0; i < infix.length(); i++) {

            if ((infix.charAt(i) >= '0' && infix.charAt(i) <= '9')) {
                result = result + infix.charAt(i);
                if (i == infix.length() - 1 || !(infix.charAt(i + 1) >= '0' && infix.charAt(i + 1) <= '9')) {
                    q.add(result);
                    result = "";
                }

            } else if (checkIfOperator(infix.charAt(i))) {

                while (!s.isEmpty() && s.peek() != '(' && hasHigherPres(s.peek(), infix.charAt(i))) {
                    q.add(s.pop());
                }
                s.push(infix.charAt(i));
            } else if (infix.charAt(i) == '(') {

                s.push(infix.charAt(i));
            } else if (infix.charAt(i) == ')') {

                while (!s.isEmpty() && s.peek() != '(') {

                    q.add(s.pop());
                }
                s.pop();
            }


        }

        while (!s.isEmpty()) {

            q.add(s.pop());
        }

        return q;
    }

    private boolean checkIfOperator(char x) {
        if (x == '+' || x == '-' || x == '\u00D7' || x == '\u00F7' || x == '.' || x == 'N' || x == 'S' || x == 'D' || x == 'T'
                || x == 'L' || x == '!' || x == 'I' || x == 'O' || x == 'A' || x == '\u03c0' || x == 'e' || x == 'R' || x == '^' || x == '√' || x == '%')
            return true;
        return false;
    }

    private boolean hasHigherPres(char first, char sec) {
        if (givePres(first) >= givePres(sec))
            return true;
        else
            return false;
    }

    private int givePres(char x) {
        if (x == '+' || x == '-')
            return 1;
        else if (x == '\u00D7' || x == '\u00F7')
            return 2;
        else if (x == '.' || x == 'N' || x == 'S' || x == 'D' || x == 'T' || x == 'L' || x == '!' || x == 'I'
                || x == 'O' || x == 'A' || x == '\u03c0' || x == 'e' || x == 'R' || x == '^' || x == '√' || x == '%')
            return 3;
        else return 31;

    }

    //////////////////////////////////////arranging string//////////////////////////////////////
    private String turnString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                if (i == 0 || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-' || s.charAt(i - 1) == '×' || s.charAt(i - 1) == '\u00F7' || s.charAt(i - 1) == '(') {
                    s = s.substring(0, i) + 'N' + s.substring(i + 1);///put m for negative numbers
                }
            } else if (i < s.length() - 1 && s.substring(i, i + 2).equals("ln")) {
                s = s.substring(0, i) + 'R' + s.substring(i + 2);
            } else if (i < s.length() - 2) {
                if (s.substring(i, i + 3).equals("sin")) {
                    s = s.substring(0, i) + 'S' + s.substring(i + 3);

                } else if (s.substring(i, i + 3).equals("cos")) {
                    s = s.substring(0, i) + 'D' + s.substring(i + 3);

                } else if (s.substring(i, i + 3).equals("tan")) {
                    s = s.substring(0, i) + 'T' + s.substring(i + 3);

                } else if (s.substring(i, i + 3).equals("log")) {
                    s = s.substring(0, i) + 'L' + s.substring(i + 3);

                } else if (i < s.length() - 3) {
                    if (s.substring(i, i + 4).equals("asin")) {
                        s = s.substring(0, i) + 'I' + s.substring(i + 4);

                    } else if (s.substring(i, i + 4).equals("acos")) {
                        s = s.substring(0, i) + 'O' + s.substring(i + 4);

                    } else if (s.substring(i, i + 4).equals("atan")) {
                        s = s.substring(0, i) + 'A' + s.substring(i + 4);

                    }
                }

            }
        }
        return s;
    }


    ///////////////////////////////////calculate postfix//////////////////////////////////////////

    private double evaluatePostfix(Queue q, boolean isDegC) {
        Stack<String> s = new Stack();
        while (!q.isEmpty()) {
            String res = q.poll().toString();
            try {

                double number = Double.parseDouble(res);
                s.push(res);

            } catch (NumberFormatException e) {
                double result;


                if (res.equals("N")) {
                    result = Double.parseDouble(s.pop()) * -1;
                } else if (res.equals("S")) {
                    if (isDegC) {
                        result = Math.sin(Math.toRadians(Double.parseDouble(s.pop())));

                    } else
                        result = Math.sin(Double.parseDouble(s.pop()));
                } else if (res.equals("D")) {
                    if (isDegC) {
                        result = Math.cos(Math.toRadians(Double.parseDouble(s.pop())));
                    } else
                        result = Math.cos(Double.parseDouble(s.pop()));
                } else if (res.equals("T")) {
                    if (isDegC) {
                        result = Math.tan(Math.toRadians(Double.parseDouble(s.pop())));
                    } else
                        result = Math.tan(Double.parseDouble(s.pop()));
                } else if (res.equals("I")) {
                    if (isDegC) {

                        result = (Math.asin(Double.parseDouble(s.pop())) * 180 / Math.PI);
                    } else
                        result = Math.asin(Double.parseDouble(s.pop()));

                } else if (res.equals("O")) {
                    if (isDegC) {

                        result = (Math.acos(Double.parseDouble(s.pop())) * 180 / Math.PI);
                    } else
                        result = Math.acos(Double.parseDouble(s.pop()));

                } else if (res.equals("A")) {
                    if (isDegC) {

                        result = (Math.atan(Double.parseDouble(s.pop())) * 180 / Math.PI);
                    } else
                        result = Math.atan(Double.parseDouble(s.pop()));

                } else if (res.equals("!")) {
                    result = 1;
                    double number = Double.parseDouble(s.pop());
                    for (int i = 2; i <= number; i++) {
                        result *= i;
                    }
                    if (number < 0)
                        result = Double.NaN;

                } else if (res.equals("%")) {
                    result = Double.parseDouble(s.pop()) / 100;
                } else if (res.equals("L")) {
                    result = Math.log10(Double.parseDouble(s.pop()));
                } else if (res.equals("R")) {
                    result = Math.log(Double.parseDouble(s.pop()));
                } else if (res.equals("\u03c0")) {
                    result = Math.PI;
                } else if (res.equals("e")) {
                    result = Math.exp(1.0);
                } else if (res.equals("√")) {
                    result = Math.sqrt(Double.parseDouble(s.pop()));
                } else {///aritmetics
                    result = performOperand(res, s.pop(), s.pop());
                }

                s.push(Double.toString(result));
            }
        }

        return Double.parseDouble(s.pop());
    }

    private double performOperand(String x, String a, String b) {

        if (x.equals("+"))
            return Double.parseDouble(b) + Double.parseDouble(a);
        else if (x.equals("-"))
            return Double.parseDouble(b) - Double.parseDouble(a);
        else if (x.equals("\u00D7"))
            return Double.parseDouble(b) * Double.parseDouble(a);
        else if (x.equals("\u00F7"))
            return Double.parseDouble(b) / Double.parseDouble(a);
        else if (x.equals(".")) {
            if (b.charAt(0) == '-' && b.charAt(1)=='0')
                return -1 * Double.parseDouble((long) Double.parseDouble(b) + "." + a);
            else
                return Double.parseDouble((long) Double.parseDouble(b) + "." + a);
        } else if (x.equals("^"))
            return Math.pow(Double.parseDouble(b), Double.parseDouble(a));

        return 31;

    }


}