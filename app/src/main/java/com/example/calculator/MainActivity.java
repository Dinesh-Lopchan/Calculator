package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Initializing and declaring UI with their respective elements
    TextView calculator1;
    EditText answer;

    //Initializing different variables
    double Result = 0.0;
    double Number = 0.0;
    char Operator = ' ';
    boolean NewNumber = true;
    boolean Decimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing UI components
        calculator1 = findViewById(R.id.calculator1);
        answer = findViewById(R.id.answer);

        // assigning to calculator buttons
        Button button0 = findViewById(R.id.zero);
        Button button1 = findViewById(R.id.one);
        Button button2 = findViewById(R.id.two);
        Button button3 = findViewById(R.id.three);
        Button button4 = findViewById(R.id.four);
        Button button5 = findViewById(R.id.five);
        Button button6 = findViewById(R.id.six);
        Button button7 = findViewById(R.id.seven);
        Button button8 = findViewById(R.id.eight);
        Button button9 = findViewById(R.id.nine);
        Button Decimal = findViewById(R.id.decimal);
        Button Clear = findViewById(R.id.clear);
        Button Equals = findViewById(R.id.equal);
        Button Add = findViewById(R.id.add);
        Button Subtract = findViewById(R.id.subtract);
        Button Multiply = findViewById(R.id.multiply);
        Button Divide = findViewById(R.id.divide);

        // assigning Set onClickListeners to all calculator buttons to handle user input
        button0.setOnClickListener(v -> NumberClicked(0));
        button1.setOnClickListener(v -> NumberClicked(1));
        button2.setOnClickListener(v -> NumberClicked(2));
        button3.setOnClickListener(v -> NumberClicked(3));
        button4.setOnClickListener(v -> NumberClicked(4));
        button5.setOnClickListener(v -> NumberClicked(5));
        button6.setOnClickListener(v -> NumberClicked(6));
        button7.setOnClickListener(v -> NumberClicked(7));
        button8.setOnClickListener(v -> NumberClicked(8));
        button9.setOnClickListener(v -> NumberClicked(9));
        Decimal.setOnClickListener(v -> DecimalClicked());
        Clear.setOnClickListener(v -> ClearClicked());
        Equals.setOnClickListener(v -> EqualsClicked());
        Add.setOnClickListener(v -> OperatorClicked('+'));
        Subtract.setOnClickListener(v -> OperatorClicked('-'));
        Multiply.setOnClickListener(v -> OperatorClicked('*'));
        Divide.setOnClickListener(v -> OperatorClicked('/'));
    }

    // code for when number is clicked in the calculator
    private void NumberClicked(int number) {
        if (NewNumber) {
            answer.setText(String.valueOf(number));
            NewNumber = false;
        } else {
            String currentText = answer.getText().toString();
            if (!Decimal) {
                int currentValue = Integer.parseInt(currentText);
                currentValue = currentValue * 10 + number;
                answer.setText(String.valueOf(currentValue));
            } else {
                answer.append(String.valueOf(number));
            }
        }

    }

    // code for when operator is clicked in the calculator
    private void OperatorClicked(char operator) {
        if (!NewNumber) {
            calculate(); // Perform the previous operation
            NewNumber = true;
        }
        Operator = operator;
        Decimal = false;
        calculator1.setText(String.valueOf(operator));
    }

    // code for when decimal is clicked in the calculator
    private void DecimalClicked() {
        if (!Decimal) {
            if (NewNumber) {
                answer.setText("0.");
                NewNumber = false;
            } else {
                answer.append(".");
            }
            Decimal = true;
        }
    }

    // code for when equal is clicked in the calculator
    private void EqualsClicked() {
        calculate();
        NewNumber = true;
        Decimal = false;
        calculator1.setText("");
    }

    // code for when clear is clicked in the calculator
    private void ClearClicked() {
        Result = 0.0;
        Number = 0.0;
        Operator = ' ';
        NewNumber = true;
        Decimal = false;
        answer.setText("0");
        calculator1.setText("");
    }

    // Code to perform  calculations based on user input
    private void calculate() {
        String input = answer.getText().toString();
        double enteredValue = Double.parseDouble(input);
        switch (Operator) {
            case '+':
                Result += enteredValue;
                break;
            case '-':
                Result -= enteredValue;
                break;

            case '*':
                Result *= enteredValue;
                break;
            case '/':
                if (enteredValue != 0) {
                    Result /= enteredValue;
                } else {
                    answer.setError("Error!!");
                }
                break;
            default:
                Result = enteredValue;
                break;
        }
        // Displays the output when equal button clicked
        answer.setText(String.valueOf(Result));
        // Stores the number for the next calculation
        Number = enteredValue;
    }
}