package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTV, solutionTV;
    MaterialButton buttonC, buttonAC;
    MaterialButton buttonOpenBracket, buttonCloseBracket;
    MaterialButton buttonPlus, buttonMultiply, buttonDivide, buttonMinus, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.query_tv);

        assignId(buttonC, R.id.buttons_C);
        assignId(buttonAC, R.id.buttons_AC);
        assignId(buttonOpenBracket, R.id.buttons_openBracket);
        assignId(buttonCloseBracket, R.id.buttons_closeBracket);
        assignId(buttonMultiply, R.id.buttons_multiply);
        assignId(buttonDivide, R.id.buttons_divide);
        assignId(buttonMinus, R.id.buttons_minus);
        assignId(buttonEqual, R.id.buttons_equals);
        assignId(buttonPlus, R.id.buttons_plus);
        assignId(button0, R.id.buttons_zero);
        assignId(button1, R.id.buttons_1);
        assignId(button2, R.id.buttons_2);
        assignId(button3, R.id.buttons_3);
        assignId(button4, R.id.buttons_4);
        assignId(button5, R.id.buttons_5);
        assignId(button6, R.id.buttons_6);
        assignId(button7, R.id.buttons_7);
        assignId(button8, R.id.buttons_8);
        assignId(button9, R.id.buttons_9);
        assignId(buttonPoint, R.id.buttons_point);
    }

    void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();
        double result;

        if (buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }

        else if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            solutionTV.setText(dataToCalculate);
            return;
        }

        else {
            if (!buttonText.equals("=")) {
                dataToCalculate = dataToCalculate + buttonText;
                solutionTV.setText(dataToCalculate);
            }
        }

        if (buttonText.equals("=")) {
            result = evaluate(dataToCalculate);
            resultTV.setText(Double.toString(result));
            // add exception cases

            return;
        }
    }

    /* public boolean isNumber(String str) {
        try {
            Double.parseDouble(str); // Try to parse the string as a double
            return true; // Parsing succeeded, so it's a valid number
        } catch (NumberFormatException e) {
            return false; // Parsing failed, so it's not a valid number
        }
    } */

    public static double evaluate(String expression) {
        // Remove spaces from the expression
        expression = expression.replaceAll("\\s+", "");

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one step to process the next character correctly
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // Operator encountered
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    char operator = operators.pop();
                    double result = applyOperator(operator, operand1, operand2);
                    numbers.push(result);
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = numbers.pop();
            double operand1 = numbers.pop();
            char operator = operators.pop();
            double result = applyOperator(operator, operand1, operand2);
            numbers.push(result);
        }

        return numbers.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-');
    }

    private static double applyOperator(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
