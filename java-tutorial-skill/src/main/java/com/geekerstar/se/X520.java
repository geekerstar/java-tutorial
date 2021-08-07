package com.geekerstar.se;

public class X520 {
    public static void main(String[] args) {
        double a, b, n = 10;
        String message = " I'm fucking love Xjjdog ";

        int print_message = 4;
        if (message.length() % 2 != 0) {
            message += " ";
        }
        for (a = 0; a < n; a++) {
            for (b = 0; b <= 4 * n; b++) {
                double distance1 = Math.sqrt(Math.pow(a - n, 2) + Math.pow(b - n, 2));
                double distance2 = Math.sqrt(Math.pow(a - n, 2) + Math.pow(b - 3 * n, 2));
                if (distance1 < n + 0.5 || distance2 < n + 0.5) {
                    System.out.print("S");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        for (a = 1; a < 2 * n; a++) {
            for (b = 0; b < a; b++)
                System.out.print(" ");
            for (b = 0; b < 4 * n + 1 - 2 * a; b++) {
                if (a >= print_message - 1 && a <= print_message + 1) {
                    double point = b - (4 * n - 2 * a - message.length()) / 2;

                    // prints message after leaving
                    // appropriate space
                    if (point < message.length() &&
                            point >= 0) {
                        if (a == print_message)
                            System.out.print
                                    (message.charAt((int)point));
                        else
                            System.out.print(" ");
                    }

                    else
                        System.out.print("S");
                }

                else
                    System.out.print("S");
            }

            System.out.println();
        }
    }
}
