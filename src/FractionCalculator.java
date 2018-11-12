public class FractionCalculator {

    public static class Fraction {
        int wholeNumber;
        int numerator;
        int denominator;
        boolean isMixed;

        public Fraction(String fraction) {
            parseFraction(fraction);
        }

        public Fraction(int num, int denom) {
            numerator = num;
            denominator = denom;
            isMixed = false;
        }

        // Simplifies fraction to final form
        // 2/4 -> 1/2
        public void simplify() {
            int greatestCommon = 1;

            // Start at 1 to avoid dividing by 0
            for(int i = 1; i <= numerator && i <= denominator; i++)  {
                // Checks if i is factor of both integers
                if(numerator % i == 0 && denominator % i == 0)
                    greatestCommon = i;
            }

            numerator = numerator / greatestCommon;
            denominator = denominator / greatestCommon;
        }

        // Converts mixed fraction to improper fraction
        // 3_3/4 = 15/4
        public void mixedToImproper() {

            numerator = (denominator * wholeNumber) + numerator;
            wholeNumber = 0;
            isMixed = false;
        }

        // Convert improper fraction to mixed fraction
        // 7/4 = 1_3/4
        public void improperToMixed(Fraction improperFraction) {

            wholeNumber = improperFraction.numerator / improperFraction.denominator;
            numerator = improperFraction.numerator % improperFraction.denominator;
            denominator = improperFraction.denominator;
            isMixed = true;

        }

        // Returns fraction in String format
        public String prettyPrintFraction() {
            String prettyFraction = null;
            if (isMixed) {
                prettyFraction = wholeNumber + "_" + numerator + "/" + denominator;
            } else {
                prettyFraction = numerator + "/" + denominator;
            }
            return prettyFraction;
        }

        // Parses string into numerator and denominator
        // If needed, normalizes mixed fractions
        // 1/2, 3_3/4, 2
        private void parseFraction(String fraction) {

            String[] splitFraction = fraction.split("");

            if (fraction.contains("_")) {
                // Fraction is mixed number, change it to improper fraction
                wholeNumber = Integer.parseInt(splitFraction[0]);
                numerator = Integer.parseInt(splitFraction[2]);
                denominator = Integer.parseInt(splitFraction[4]);
                isMixed = true;


            } else {

                numerator = Integer.parseInt(splitFraction[0]);
                isMixed = false;

                if (splitFraction.length == 1) {
                    denominator = 1;
                } else if (splitFraction.length == 3) {
                    denominator = Integer.parseInt(splitFraction[2]);
                }
            }

        }
    }


    public static Fraction calculateInput(String commandList){
        String[] parsedInput = splitInput(commandList);

        Fraction fractionA = new Fraction(parsedInput[0]);
        System.out.println(fractionA.prettyPrintFraction());

        Fraction fractionB = new Fraction(parsedInput[2]);
        System.out.println(fractionB.prettyPrintFraction());

        String operator = parsedInput[1];

        Fraction result = null;

        switch(operator) {

            case "+":
                System.out.println("Addition");
                result = add(fractionA, fractionB);
                break;

            case "-":
                System.out.println("Subtraction");
                result = subtract(fractionA, fractionB);
                break;

            case "*":
                System.out.println("Multiplication");
                result = multiply(fractionA, fractionB);
                break;

            case "/":
                System.out.println("Division");
                result =  divide(fractionA, fractionB);
                break;

        }

        return result;

    }

    // Splits user input into a string array & removes white space
    private static String[] splitInput(String command) {
        return command.split("\\s+");
    }

    // Adds two fractions
    private static Fraction add(Fraction fractionA, Fraction fractionB) {

        if (fractionA.denominator != fractionB.denominator) {
            // Find common denominator
            int commonDenominator = findCommonDenom(fractionA, fractionB);

            if (fractionA.denominator != commonDenominator) {
                fractionA.numerator = (commonDenominator / fractionA.denominator) * fractionA.numerator;
                fractionA.denominator = (commonDenominator / fractionA.denominator) * fractionA.denominator;
            }

            if (fractionB.denominator != commonDenominator) {
                fractionB.numerator = (commonDenominator / fractionB.denominator) * fractionB.numerator;
                fractionB.denominator = (commonDenominator / fractionB.denominator) * fractionB.denominator;
            }

            return new Fraction(fractionA.numerator + fractionB.numerator, fractionA.denominator);
        } else {
            return new Fraction(fractionA.numerator + fractionB.numerator, fractionA.denominator);
        }
    }

    // Finds the difference between 1st and 2nd fraction
    private static Fraction subtract(Fraction fractionA, Fraction fractionB) {
        if (fractionA.denominator != fractionB.denominator) {
            // Find common denominator
            int commonDenominator = findCommonDenom(fractionA, fractionB);

            if (fractionA.denominator != commonDenominator) {
                fractionA.numerator = (commonDenominator / fractionA.denominator) * fractionA.numerator;
                fractionA.denominator = (commonDenominator / fractionA.denominator) * fractionA.denominator;
            }

            if (fractionB.denominator != commonDenominator) {
                fractionB.numerator = (commonDenominator / fractionB.denominator) * fractionB.numerator;
                fractionB.denominator = (commonDenominator / fractionB.denominator) * fractionB.denominator;
            }
            return new Fraction(fractionA.numerator - fractionB.numerator, fractionA.denominator);
        } else {
            return new Fraction(fractionA.numerator - fractionB.numerator, fractionA.denominator);
        }
    }

    // Multiplies two fractions
    private static Fraction multiply(Fraction fractionA, Fraction fractionB) {
        return new Fraction(fractionA.numerator * fractionB.numerator, fractionA.denominator * fractionB.denominator);
    }

    // Divides two fractions
    private static Fraction divide(Fraction fractionA, Fraction fractionB) {
        return new Fraction(fractionA.numerator * fractionB.denominator, fractionA.denominator * fractionB.numerator);

    }

    // Finds common denominator between fractions
    private static int findCommonDenom(Fraction fractionA, Fraction fractionB) {

        int greatestCommon = 1;
        int denominatorA = fractionA.denominator;
        int denominatorB = fractionB.denominator;

        // Start at 1 to avoid dividing by 0
        for(int i = 1; i <= denominatorA && i <= denominatorB; i++)  {
            // Checks if i is factor of both integers
            if(denominatorA % i == 0 && denominatorB % i == 0)
                greatestCommon = i;
        }

        return (denominatorA * fractionB.denominator) / greatestCommon;

    }

    public static void main(String[] args) {
        String input = "1/2 + 1/6";

        Fraction result = calculateInput(input);
        result.simplify();

        System.out.println(result.prettyPrintFraction());
    }
}
