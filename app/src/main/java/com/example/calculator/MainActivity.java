package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.textView);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button3);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button1);

        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);

        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);

        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDevide = findViewById(R.id.buttonDevide);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        Button buttonC = findViewById(R.id.c);
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = newNumber.getText().toString();
                if (result.length() > 0)
                    result = result.substring(0, result.length() - 1);
                newNumber.setText(result);
            }
        });




        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNumber.setText("");
                operand1 = null;
                operand2 = null;
                result.setText("");
                displayOperation.setText("");
                pendingOperation = "=";
            }
        });


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                if (value.length() != 0 ) {
                    perfromOperation(value, op);
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonDevide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);


    }



    private void perfromOperation(String value, String op) {
        if (operand1 == null) {
            operand1 = Double.valueOf(value);
        }
        else {
            operand2 = Double.valueOf(value);

            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "*":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}