package com.example.insertionsortapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        inputField = findViewById(R.id.inputField);
        resultView = findViewById(R.id.resultView);
        Button sortButton = findViewById(R.id.sortButton);

        // Set the click listener for the sort button
        sortButton.setOnClickListener(v -> {
            // Read input from the user
            String input = inputField.getText().toString().trim();
            if (input.equalsIgnoreCase("quit")) {
                finish(); // Exit the app
            } else {
                processInput(input);
            }
        });
    }

    private void processInput(String input) {
        // Validate input and convert it to an integer array
        int[] numbers = validateAndParseInput(input);
        if (numbers == null) {
            return;
        }

        // Perform Insertion Sort and display intermediate steps
        insertionSort(numbers);
    }

    private int[] validateAndParseInput(String input) {
        String[] elements = input.split("\\s+");
        if (elements.length < 3 || elements.length > 8) {
            Toast.makeText(this, getString(R.string.error_invalid_size), Toast.LENGTH_LONG).show();

            return null;
        }

        int[] arr = new int[elements.length];
        try {
            for (int i = 0; i < elements.length; i++) {
                int value = Integer.parseInt(elements[i]);
                if (value < 0 || value > 9) {
                    Toast.makeText(this, "Error: Array elements must be between 0 and 9.", Toast.LENGTH_LONG).show();
                    return null;
                }
                arr[i] = value;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error: Please enter valid integers only.", Toast.LENGTH_LONG).show();
            return null;
        }
        return arr;
    }

    private void insertionSort(int[] arr) {
        // Display the input array
        resultView.append("Input Array: " + Arrays.toString(arr) + "\n");
        resultView.append("Insertion Sort (Intermediate Steps)\n");

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;

            // Display the intermediate step
            resultView.append(Arrays.toString(arr) + "\n");
        }
    }
}
