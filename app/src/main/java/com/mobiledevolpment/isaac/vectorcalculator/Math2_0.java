package com.mobiledevolpment.isaac.vectorcalculator;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Math2_0 {

    private String inputTextFromTextView;
    private String lastItem;

    public Math2_0() {

    }

    public void add_input_number(int place, TextView input) {
        inputTextFromTextView = input.getText().toString();
        inputTextFromTextView += place;
        input.setText(inputTextFromTextView);

    }

    public void add_input_buttons(int place, TextView answer, TextView input) throws IOException {

        if (input != null && input.getText().length() > 0) {
            inputTextFromTextView = input.getText().toString();
            lastItem = inputTextFromTextView.substring(inputTextFromTextView.length() - 1);
        } else {
            inputTextFromTextView = "NOTHING";
            lastItem = "NOTHING";
        }

        // if the previous input is a number insert the operator
        // if not, but the operator is (-) still input
        // if the previous item is a "." add a 0 before the operator (ex. 5.9+...
        // do nothing

        // if the input is an operator turn into text
        switch (place) {
            //--------------------------------------------------------------------------------------
            // math operators: (+, -, x, ÷)
            case 1 :
                // if divide
                operator("÷", "divide");
                break;
            case 2 :
                // if multiply
                operator("x", "multiply");
                break;
            case 4 :
                // if subtract
                // still add the subtract operator if there is nothing there (ex. -6, a negative number)
                operator("-", "subtract");
                break;
            case 5 :
                // if add
                operator("+", "add");
                break;
            //--------------------------------------------------------------------------------------
            // computational operators: (Clear, delete, =, .)
            case 0 :
                // if clear
                input.setText(null);
                answer.setText(null);
                break;
            case 3 :
                // if delete
                // first make sure there is something to delete
                if (!inputTextFromTextView.equals("NOTHING") && !lastItem.equals("NOTHING")) {
                    String currentText = input.getText().toString();
                    currentText = currentText.substring(0, currentText.length() - 1);
                    input.setText(currentText);
                }
                break;
            case 6 :
                // if equals
                if (lastItem.equals("NOTHING") || hasMathOperator()) {
                    // make toast text
                    throw new IOException("Invalid Computation");
                } else {
                    equals(inputTextFromTextView, answer);
                }
                break;
            case 7 :
                // if decimal
                // check if the last char was a decimal
                if (lastItem.equals(".")) {
                    // make toast text
                    throw new IOException("Can't have two \".\"");
                } else if (hasMathOperator()) {
                    // if there is a math operator right before
                    // make toast text
                    throw new IOException("Invalid \".\" placement");
                } else if (lastItem.equals("NOTHING")) {
                    // add a 0 before the decimal
                    input.setText("0.");
                } else {
                    inputTextFromTextView += ".";
                    input.setText(inputTextFromTextView);
                }
                break;
        }

    }

    /**
     * check if the arraylist still contains operators
     *
     * ToDo: change so if the first item is a (-) ignore so you can have a negative number
     * @param toCheck
     * @return
     */
    private  boolean hasOperator (ArrayList<String> toCheck) {
        for (String current : toCheck) {
            if (hasMathOperator(current)) {
                return true;
            }
        }
        return false;
    }

    private void equals(String inputText, TextView answertxt) {
        String answer = "";
        // make a clean version of the input
        String formatedInput = formatInput(inputText);

        // add every item to an arrayList
        Scanner toArrayList = new Scanner(formatedInput);
        ArrayList<String> input = new ArrayList<>();

        while (toArrayList.hasNext()) {
            input.add(toArrayList.next());
        }

        while (hasOperator(input)) {

            multiply_and_divide(input);
            add_and_subtract(input);


        }

        answer = ("=" + answer);
        answertxt.setText(answer);
    }

    private void multiply_and_divide(ArrayList<String> in) {

    }

    private void add_and_subtract(ArrayList<String> in) {

    }

    /**
     * What happens when a math operator is pressed
     * also throws exception if bad input to later be displayed as a toast
     * @param newInputOperator
     * @param type
     * @throws IOException
     */
    public void operator(String newInputOperator, String type) throws IOException {
        if (lastItem.equals("NOTHING") && !inputTextFromTextView.equals("-")) {
            // make toast text
            throw new IOException("Please enter a number first");
        } else if (hasMathOperator()){
            // make toast text
            throw new IOException("Must have a number before to " + type);
        } else if(lastItem.equals(".")) {
            inputTextFromTextView += ("0" + newInputOperator);
        } else {
            inputTextFromTextView += newInputOperator;
        }
    }

    /**
     * if the last item was a math operator, return true
     * @return
     */
    public boolean hasMathOperator() {
        return (lastItem.equals("+")
                || lastItem.equals("-")
                || lastItem.equals("x")
                || lastItem.equals("÷"));
    }

    /**
     * if the String contains a math operator, return true
     * @return
     */
    public boolean hasMathOperator(String in) {
        return (in.contains("+")
                || in.contains("-")
                || in.contains("x")
                || in.contains("÷"));
    }

    /**
     * formats a String of math input from the user int a whitespace delimited string of doubles
     * ex. unedited: -90+70x7.0-9+0
     *     edited: - 90.0 + 70.0 x 7.0 - 9.0 + 0.0
     * @param input
     * @return
     */
    public String formatInput(String input) {
        String unformated_Input = input;
        StringBuilder formated = new StringBuilder();

        // a scanner of the unformated string that goes through every charachter
        Scanner in = new Scanner(unformated_Input);
        in.useDelimiter("");

        String current = "";

        while (in.hasNext()) {

            String next = in.next();
            if (next.equals("+") || next.equals("-") || next.equals("x") || next.equals("÷")) {

                // if not a double, make a double
                if (!current.equals("") && !current.contains(".")) {
                    current += ".0";
                    // add a space for delimiter use later
                    current += (" " + next);
                } else if (!current.equals("")) {
                    // add a space for delimiter use later
                    current += (" " + next);
                } else {
                    current += next;
                }

                formated.append(current + " ");
                current = "";
            } else {
                // make the number
                current += next;
            }
        }

        // if the last item is a number, turn into a double and append
        if (!current.equals("") && !current.contains(".")) {
            current += ".0";
        }
        formated.append(current);

        // return the formated string
        return formated.toString();
    }

}
