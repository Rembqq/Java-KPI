package org.example.lab7;

import org.example.lab1.nojavacompiler.AverageLengthString;

public class AverageLengthLambda implements getAverageLength{
    public AverageLengthLambda() {}


    public String[] calculate() {
        return new String[]{""};
    }



//    public String[] getStrings(String[] args) {
//        int sum = 0;
//
//        for(String i : args) {
//            sum += i.length();
//        }
//        int average = Math.round((float) sum / args.length);
//
//        String[] suitableStrings = new String[args.length];
//        int suitableStringsIndex = 0;
//
//        for(int i = 0; i < args.length; ++i) {
//            if(args[i].length() < average) {
//                suitableStrings[suitableStringsIndex++] = args[i];
//            }
//        }
//
//        return java.util.Arrays.copyOf(suitableStrings, suitableStringsIndex);
//    }

    static String[] execute(getAverageLength g) {
        return g.calculate();
    }


    public static void main(String[] args) {

        getAverageLength lambda = () -> {
            System.out.print("Your args: ");
            for(String i: args) {
                System.out.print(i + " ");
            }
            System.out.println();

            ///
            ///
            ///
            ///

            int sum = 0;

            for(String i : args) {
                sum += i.length();
            }
            int average = Math.round((float) sum / args.length);

            String[] suitableStrings = new String[args.length];
            int suitableStringsIndex = 0;

            for(int i = 0; i < args.length; ++i) {
                if(args[i].length() < average) {
                    suitableStrings[suitableStringsIndex++] = args[i];
                }
            }

            String[] arr = java.util.Arrays.copyOf(suitableStrings, suitableStringsIndex);;

            ///
            ///
            ///
            ///

            System.out.print("Args below average length: ");
            for(String i: arr) {
                System.out.print(i + " ");
            }
            return arr;
        };

        String[] newArr = execute(lambda);
    }
}
