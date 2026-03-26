package com.example.travelcompanionapp;

public class Converter {
    public static double convert(String category, String from, String to, double value) {
        // call a proper function based on the category
        switch (category) {
            case "Currency":
                return convertCurrency(from, to, value);
//            case "Travel":
//                return convertTravel(from, to, value);
//            case "Temperature":
//                return convertTemperature(from, to, value);
        }
        return 0;
    }

    // Convert Currency
    private static double convertCurrency(String from, String to, double value) {
        // Use USD as the base unit
        double usdValue= 0;

        // Convert the input value to USD
        switch (from) {
            case "USD":
                usdValue = value;
                break;
            case "AUD":
                usdValue = value / 1.55;
                break;
            case "EUR":
                usdValue = value / 0.92;
                break;
            case "JPY":
                usdValue = value / 148.50;
                break;
            case "GBP":
                usdValue = value / 0.78;
                break;
        }

        // Convert the USD value to the target currency
        switch (to) {
            case "USD":
                return usdValue;
            case "AUD":
                return usdValue * 1.55;
            case "EUR":
                return usdValue * 0.92;
            case "JPY":
                return usdValue * 148.50;
            case "GBP":
                return usdValue * 0.78;
        }

        // Return 0 if it goes wrong
        return 0;
    }
}
