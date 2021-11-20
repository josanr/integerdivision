package ru.josanr.integerdivision;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You must enter dividend and divisor");
        }

        try {
            int dividend = Integer.parseInt(args[0]);
            int divider = Integer.parseInt(args[1]);
            var printer = new DivisionPrinter();

            String formattedOutput = printer.print(new LongDivision(dividend, divider));
            System.out.println(formattedOutput);
        }catch (NumberFormatException e) {
            System.out.println("Argument not a number: " + e.getMessage());
        }
    }
}
