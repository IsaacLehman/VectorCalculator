package com.mobiledevolpment.isaac.vectorcalculator;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Math2_0 {

    private String inputTextFromTextView;
    private String lastItem;

    private final String PLUS = "+", MINUS = "-", MULTIPLY = "x", DIVIDE = "";

    public Math2_0() {

    }

    /**
     * use for when a number on the keypad is pressed
     *
     * @param place
     * @param input
     */
    public void add_input_number(int place, TextView input) {
        inputTextFromTextView = input.getText().toString();
        inputTextFromTextView += place;
        input.setText(inputTextFromTextView);

    }

    /**
     * use for when a operator on the keypad is pressed
     *
     * @param place
     * @param answer
     * @param input
     * @throws IOException
     */
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
            case 1:
                // if divide
                operator("÷", "divide", input);
                break;
            case 2:
                // if multiply
                operator("x", "multiply", input);
                break;
            case 4:
                // if subtract
                // still add the subtract operator if there is nothing there (ex. -6, a negative number)
                operator("-", "subtract", input);
                break;
            case 5:
                // if add
                operator("+", "add", input);
                break;
            //--------------------------------------------------------------------------------------
            // computational operators: (Clear, delete, =, .)
            case 0:
                // if clear
                input.setText(null);
                answer.setText(null);
                break;
            case 3:
                // if delete
                // first make sure there is something to delete
                if (!inputTextFromTextView.equals("NOTHING") && !lastItem.equals("NOTHING")) {
                    String currentText = input.getText().toString();
                    currentText = currentText.substring(0, currentText.length() - 1);
                    input.setText(currentText);
                }
                break;
            case 6:
                // if equals
                if (lastItem.equals("NOTHING") || hasMathOperator()) {
                    // make toast text
                    throw new IOException("Invalid Computation");
                } else {
                    equals(inputTextFromTextView, answer);
                }
                break;
            case 7:
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
     * <p>
     * If the first item is a (-) ignore so you can have a negative number
     *
     * @param toCheck
     * @return
     */
    private boolean hasOperator(ArrayList<String> toCheck) {

        for (int i = 0; i < toCheck.size(); i++) {
            String current = toCheck.get(i);
            if ((i == 0) && current.equals("-")) {
                // do nothing if the first item is a negative sign
            } else if (hasMathOperator(current)) {
                // if there is a math operator, return true
                return true;
            }
        }

        return false;
    }

    /**
     * computes the answer to the input and displays it to the answer TextView
     *
     * @param inputText
     * @param answertxt
     */
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

        // compute math
        multiply_and_divide(input);
        add_and_subtract(input);

        // set the textView to the answe
        answer = ("=" + ArrayListToString(input));
        answertxt.setText(answer);
    }

    /**
     * while there are still multiply and divde symbols in the arrayList, compute
     * from left to right
     * <p>
     * ToDo: allow for negative numbers at places other than the front
     *
     * @param in
     */
    public void multiply_and_divide(ArrayList<String> in) {

        for (int i = 0; i < in.size(); i++) {
            /*
             * if the current spot is a x or ÷ take the number to the left, and the number
             * to the right compute
             */

            String current = in.get(i);
            if (current.equals("÷") || current.equals("x")) {
                Double left = Double.parseDouble(in.get(i - 1));
                Double right = Double.parseDouble(in.get(i + 1)); // will always have the number to right
                Double newNum;

                // if multiply
                if (current.equals("x")) {
                    newNum = left * right;

                    // format number and add to arrayList in left number spot
                    String newNumReturn = String.valueOf(newNum);
                    in.set(i - 1, newNumReturn);

                    // remove the operator and the number to the right
                    in.remove(i + 1);
                    in.remove(i);

                    // change the index to make sure no Exceptions
                    i -= 2;
                } else {
                    // if divide
                    newNum = left / right;

                    // format number and add to arrayList in left number spot
                    String newNumReturn = String.valueOf(newNum);
                    in.set(i - 1, newNumReturn);

                    // remove the operator and the number to the right
                    in.remove(i + 1);
                    in.remove(i);

                    // change the index to make sure no Exceptions
                    i -= 2;
                }
            }
        }

    }

    /**
     * while there are still plus and minus symbols in the arrayList, compute from
     * left to right
     *
     * @param in
     */
    public void add_and_subtract(ArrayList<String> in) {

        for (int i = 0; i < in.size(); i++) {
            /*
             * if the current spot is a x or ÷ take the number to the left, and the number
             * to the right compute
             */

            String current = in.get(i);
            if (current.equals("+") || current.equals("-")) {

                if (i == 0) {
                    Double left = Double.parseDouble(in.get(i + 1));
                    left = (-1.0) * left;
                    String newNumReturn = formatNumber(left);
                    in.set(1, newNumReturn);
                    in.remove(0);
                    i--;

                    continue;
                } else {
                    Double left = Double.parseDouble(in.get(i - 1));
                }
                Double left = Double.parseDouble(in.get(i - 1));
                Double right = Double.parseDouble(in.get(i + 1)); // will always have the number to right
                Double newNum;

                // if add
                if (current.equals("+")) {
                    newNum = left + right;

                    String newNumReturn = String.valueOf(newNum);

                    in.set(i - 1, newNumReturn);

                    // remove the operator and the number to the right
                    in.remove(i + 1);
                    in.remove(i);

                    // change the index to make sure no Exceptions
                    i -= 2;
                } else {
                    // if subtract
                    newNum = left - right;

                    // format number and add to arrayList in left number spot
                    String newNumReturn = String.valueOf(newNum);
                    in.set(i - 1, newNumReturn);

                    // remove the operator and the number to the right
                    in.remove(i + 1);
                    in.remove(i);

                    // change the index to make sure no Exceptions
                    i -= 2;
                }
            }
        }

    }

    /**
     * formats a double with 3 decimal places
     *
     * @param in
     * @return
     */
    private String formatNumber(Double in) {
        return String.format(Locale.US, "%.3f", in);
    }

    /**
     * What happens when a math operator is pressed
     * also throws exception if bad input to later be displayed as a toast
     *
     * @param newInputOperator
     * @param type
     * @throws IOException
     */
    private void operator(String newInputOperator, String type, TextView input) throws IOException {
        if (lastItem.equals("NOTHING") && !inputTextFromTextView.equals("-")) {
            // make toast text
            throw new IOException("Please enter a number first");
        } else if (hasMathOperator()) {
            // make toast text
            throw new IOException("Must have a number before to " + type);
        } else if (lastItem.equals(".")) {
            inputTextFromTextView += ("0" + newInputOperator);
        } else {
            inputTextFromTextView += newInputOperator;
        }

        input.setText(inputTextFromTextView);
    }

    /**
     * if the last item was a math operator, return true
     *
     * @return
     */
    private boolean hasMathOperator() {
        return (lastItem.equals("+")
                || lastItem.equals("-")
                || lastItem.equals("x")
                || lastItem.equals("÷"));
    }

    /**
     * if the String contains a math operator, return true
     *
     * @return
     */
    private boolean hasMathOperator(String in) {
        return (in.contains("+")
                || in.contains("-")
                || in.contains("x")
                || in.contains("÷"));
    }

    /**
     * formats a String of math input from the user int a whitespace delimited string of doubles
     * ex. unedited: -90+70x7.0-9+0
     * edited: - 90.0 + 70.0 x 7.0 - 9.0 + 0.0
     *
     * @param input
     * @return
     */
    private String formatInput(String input) {
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

    /**
     * formats an arraylist to be output as the answer
     * <p>
     * ToDo: maybe add a space if a negative number?
     *
     * @param in
     * @return
     */
    private String ArrayListToString(ArrayList<String> in) {
        StringBuilder out = new StringBuilder();

        for (String current : in) {
            out.append(current);
        }

        return out.toString();
    }
}
