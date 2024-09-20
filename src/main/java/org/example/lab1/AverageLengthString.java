package org.example.lab1;

public class AverageLengthString {

    public AverageLengthString() {}

    public String[] getStrings(String[] args) {
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

        return java.util.Arrays.copyOf(suitableStrings, suitableStringsIndex);
    }


    public static void main(String[] args) {
        AverageLengthString als = new AverageLengthString();
        String[] arr = als.getStrings(args);
        for(String i: arr) {
            System.out.println(i);
        }
    }
}