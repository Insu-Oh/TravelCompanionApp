package com.example.travelcompanionapp;

public class Converter {
    public static double convert(String category, String from, String to, double value) {
        // call a proper function based on the category
        switch (category) {
            case "Currency":
                return convertCurrency(from, to, value);
            case "Fuel Efficiency":
                return convertFuelEfficiency(from, to, value);
            case "Liquid Volume":
                return convertLiquidVolume(from, to, value);
            case "Distance":
                return convertDistance(from, to, value);
            case "Temperature":
                return convertTemperature(from, to, value);
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

    private static double convertFuelEfficiency(String from, String to, double value) {
        if (from.equals("mpg") && to.equals("km/L")) {
            // if mpg -> km/L
            return value * 0.425;

        } else if (from.equals("km/L") && to.equals("mpg")) {
            // if km/L -> mpg
            return value / 0.425;
        }

        return value;
    }

    private static double convertLiquidVolume(String from, String to, double value) {
        if (from.equals("Gallon") && to.equals("Liter")) {
            // if Gallon -> Liter
            return value * 3.785;
        } else if (from.equals("Liter") && to.equals("Gallon")) {
            // if Liter -> Gallon
            return value / 3.785;
        }

        return value;
    }

    private static double convertDistance(String from, String to, double value) {
        if (from.equals("Nautical Mile") && to.equals("Kilometer")) {
            // Nautical Mile -> Kilometer
            return value * 1.852;
        } else if (from.equals("Kilometer") && to.equals("Nautical Mile")) {
            // Kilometer -> Nautical Mile
            return value / 1.852;
        }

        return value;
    }

    private static double convertTemperature(String from, String to, double value) {
        if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            // Celsius -> Fahrenheit
            return (value * 1.8) + 32;
        } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            // Fahrenheit -> Celsius
            return (value -32) / 1.8;
        } else if (from.equals("Celsius") && to.equals("Kelvin")) {
            // Celsius -> Kelvin
            return value + 273.15;
        }

        return 0;
    }
}
