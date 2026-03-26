package com.example.travelcompanionapp;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Declare UI widgets
    private Spinner categorySpinner;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText input;
    private TextView resultText;
    private Button convertButton;

    // Currency Units
    List<String> currencyUnits = Arrays.asList("USD", "AUD", "EUR", "JPY", "GBP");

    // Fuel Efficiency Units
    List<String> fuelEfficiencyUnits = Arrays.asList(
            "mpg", "km/L"
    );
    // Liquid Volume Units
    List<String> liquidVolumeUnits = Arrays.asList(
            "Gallon", "Liter"
    );
    // Distance Units
    List<String> distanceUnits = Arrays.asList(
            "Nautical Mile", "Kilometer"
    );

    // Temperature Units
    List<String> temperatureUnits = Arrays.asList("Celsius", "Fahrenheit", "Kelvin");


    // Updates the from/to spinners based on the selected category
    private void updateUnitSpinners(String category) {
        List<String> units;
        // Select the list of units to display based on the category value
        switch (category) {
            case "Currency":
                units = currencyUnits;
                break;
            case "Fuel Efficiency":
                units = fuelEfficiencyUnits;
                break;
            case "Liquid Volume":
                units = liquidVolumeUnits;
                break;
            case "Distance":
                units = distanceUnits;
                break;
            case "Temperature":
                units = temperatureUnits;
                break;
            default:
                units = new ArrayList<>();
                break;
        }
        // Create an adapter to connect the selected unit list to the spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );
        // Set the layout for the dropdown list shown when the spinner is opened
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        // Apply the same unit list to both fromSpinner and toSpinner
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
    }

    private void handleConvert() {
        // Get input data
        String inputText = input.getText().toString();

        double inputValue; // Store the input value as a double
        inputValue = Double.parseDouble(inputText); // convert the string input to a double

        // Get selected item value
        String category = categorySpinner.getSelectedItem().toString();
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        // Convert
        double result = Converter.convert(category, fromUnit, toUnit, inputValue);
        String output = Double.toString(result);
        resultText.setText(output);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Link the spinner views from XML to this Java code
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        categorySpinner = findViewById(R.id.categorySpinner);
        input = findViewById(R.id.input);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);

        // Set the Default category to currency
        updateUnitSpinners("Currency");

        // update the unit lists when the user selects a category
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                updateUnitSpinners(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convertButton.setOnClickListener(v -> handleConvert());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}