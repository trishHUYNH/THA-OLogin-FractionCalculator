public class FractionCalculator {

    public static class Fraction {
        int numerator;
        int denominator;

        public Fraction(String fraction) {
            parseFraction(fraction);
        }

        public Fraction(int num, int denom) {
            numerator = num;
            denominator = denom;
        }

        // Simplifies fraction to final form
        // 2/4 -> 1/2
        public void simplify() {

        }

        // 3_3/4 = 15/4
        // (Denominator * whole number) + numerator / denominator
        public void mixedToImproper(String mixedFraction) {

            String[] splitFraction = mixedFraction.split("");
            int wholeNum = Integer.parseInt(splitFraction[0]);
            int num = Integer.parseInt(splitFraction[2]);
            int denom = Integer.parseInt(splitFraction[4]);

            // (Denominator * whole number) + numerator
            numerator = (denom * wholeNum)  + num;
            denominator = denom;
        }

        public void improperToMixed() {

        }

        public String prettyPrintFraction() {
            return numerator + "/" + denominator;
        }

        // Parses string into numerator and denominator
        // If needed, normalizes mixed fractions
        // 1/2, 3_3/4, 2
        private void parseFraction(String fraction) {

            if (fraction.contains("_")) {
                // Fraction is mixed number, change it to improper fraction
                mixedToImproper(fraction);
            } else {

                String[] splitFraction = fraction.split("");
                numerator = Integer.parseInt(splitFraction[0]);

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
            return new Fraction(1, 2);
        } else {
            return new Fraction(fractionA.numerator + fractionB.numerator, fractionA.denominator);
        }
    }

    // Finds the difference between 1st and 2nd fraction
    private static Fraction subtract(Fraction fractionA, Fraction fractionB) {
        if (fractionA.denominator != fractionB.denominator) {
            // Find common denominator
            return new Fraction(1, 2);
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
    private static void findCommonDenom() {

    }

    public static void main(String[] args) {
        String input = "1/2 / 1/6";
        System.out.println(calculateInput(input).prettyPrintFraction());
    }
}
