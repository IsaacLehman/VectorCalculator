package com.mobiledevolpment.isaac.vectorcalculator;

import android.widget.TextView;

/**
 * Created by Isaac on 5/30/2017.
 */

public class Calculator_Math {

    private int number_of_numbers_before = 0;
    private int number_of_numbers_after = 0;

    private String answer = "";

    public Calculator_Math() {
    }

    public void add_input_number(int place, TextView input) {
        String currentText = input.getText().toString();
        currentText += place;
        input.setText(currentText);
    }

    public void add_input_buttons(int place, TextView answer, TextView input) {
        String inputText = input.getText().toString();

        // if operable
        if (place != 0 && place != 3 && place != 6 && place != 7) {

            String Input = "";
            int lengthofString = input.getText().toString().length();
            String inputStringtxt = input.getText().toString();

            // make sure there is a number at the beginning
            if (lengthofString > 0 && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("+")
                    && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("-")
                    && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("x")
                    && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("÷")) {

                if (place == 1) {
                    Input = "÷";
                } else if (place == 2) {
                    Input = "x";
                } else if (place == 5) {
                    Input = "+";
                }
            }

            if (place == 4) {
                if (lengthofString > 0 && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("+")
                        && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("-")
                        && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("x")
                        && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("÷")) {
                    Input = "-";
                } else if (lengthofString == 0) {
                    Input = "-";
                }
            }

            //check to make sure previous value wa not .
            int index = inputText.length();
            if (inputText.length() > 0 && inputText.substring(index - 1).equals(".")) {
                String currentText = inputText;
                currentText += "0" + Input;
                input.setText(currentText);
            } else {
                String currentText = input.getText().toString();
                currentText += Input;
                input.setText(currentText);
            }
        }

        // if clear
        if (place == 0) {
            input.setText(null);
            answer.setText(null);
        }

        // if delete
        if (place == 3 && input.getText().toString().length() > 0) {
            String currentText = input.getText().toString();
            currentText = currentText.substring(0, currentText.length() - 1);
            input.setText(currentText);
        }

        // if decimal
        if (place == 7) {
            //check to make sure previous value wa not .
            int index = inputText.length();
            if (inputText.length() > 0 && inputText.substring(index - 1).equals(".")) {
            } else if (inputText.length() > 0 && inputText.substring(index - 1, index).equals("+") || inputText.length() > 0 && inputText.substring(index - 1, index).equals("-") || inputText.length() > 0 && inputText.substring(index - 1, index).equals("x")
                    || inputText.length() > 0 && inputText.substring(index - 1, index).equals("÷")) {
                String currentText = inputText;
                currentText += "0.";
                input.setText(currentText);
            } else if (inputText == null || inputText.length() == 0) {
                String currentText = inputText;
                currentText += "0.";
                input.setText(currentText);
            } else if (check_for_decimal(inputText)) {
                // makes sure there isn't a decimal between it and an operator
            } else {
                String currentText = input.getText().toString();
                currentText += ".";
                input.setText(currentText);
            }
        }

        // if equals
        int lengthOfString = input.getText().toString().length();
        if (place == 6 && inputText.length() > 0
                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("+")
                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("-")
                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("x")
                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("÷")) {

            //System.out.println("working");
            equals(input.getText().toString(), answer);
        }
    }

    private boolean check_for_decimal(String inputText) {
        int index = inputText.length();

        String howManyLeft = inputText;

        boolean has_decimal = false;

        // find how many numbers are before said operator
        while (index > 0 && !howManyLeft.substring(index - 1, index).equals("+") && !howManyLeft.substring(index - 1, index).equals("-") && !howManyLeft.substring(index - 1, index).equals("x")
                && !howManyLeft.substring(index - 1, index).equals("÷")) {

            if (howManyLeft.substring(index - 1, index).equals(".")) {
                has_decimal = true;
            }
            index--;
        }

        return has_decimal;
    }

    private void equals(String inputText, TextView answertxt) {
        //check for + - x ÷
        answer = inputText;
        // use this to find if string begins with -
        String remander;

        if (answer.substring(0,1).equals("-")) {
            remander = answer.substring(1, answer.length());
        } else {
            remander = answer;
        }

        //System.out.println("working");

        while (remander.contains("+") || remander.contains("-") || remander.contains("x") || remander.contains("÷")) {

            //System.out.println("working");

            divide(answer);
            multiply(answer);
            subtract(answer);
            add(answer);

            if (answer.substring(0,1).equals("-")) {
                remander = answer.substring(1, answer.length());
            } else {
                remander = answer;
            }
        }

        answertxt.setText("= " + answer);
    }

    private void howManyBeforAndAfter(String have, String inputText) {

        //System.out.println("number_of_numbers_before " +  have + " " + number_of_numbers_before + "\nnumber_of_numbers_after " +  have + " " + number_of_numbers_after);

        String remainder = answer.substring(1, answer.length());
        String beginning = answer.substring(0, 1);

        if (beginning.equals("-")) {
            inputText = remainder;
            howManyLogic(have, inputText);
        } else {
            howManyLogic(have, inputText);
        }


//        System.out.println("--------------------------------------------------------------------------------------------------");
//        System.out.println("number of numbers before  " + have + " " + number_of_numbers_before + "  |" + "\nnumber of numbers after   " + have + " " + number_of_numbers_after + "  |");

    }

    private void howManyLogic(String have, String inputText) {
        if (inputText.contains(have)) {
            int index = inputText.indexOf(have);
            int index2 = inputText.indexOf(have) + 1;


            // find how many numbers are before said operator
            while (index > 0
                    && !inputText.substring(index - 1, index).equals("+")
                    && !inputText.substring(index - 1, index).equals("-")
                    && !inputText.substring(index - 1, index).equals("x")
                    && !inputText.substring(index - 1, index).equals("÷")) {

                number_of_numbers_before++;
                index--;
            }

//            if (!inputText.substring(index2, (index2 + 1)).equals("+")) {
//                System.out.println("number of numbers after is + working!!!");
//            }
//
//            if (!inputText.substring(index2, (index2 + 1)).equals("-")) {
//                System.out.println("number of numbers after is - working!!!");
//            }
//
//            if (!inputText.substring(index2, (index2 + 1)).equals("x")) {
//                System.out.println("number of numbers after is x working!!!");
//            }
//
//            if (!inputText.substring(index2, (index2 + 1)).equals("÷")) {
//                System.out.println("number of numbers after is ÷ working!!!");
//            }


            // find how many numbers are after said operator
            while (index2 < inputText.length()
                    && !inputText.substring(index2, (index2 + 1)).equals("+")
                    && !inputText.substring(index2, (index2 + 1)).equals("-")
                    && !inputText.substring(index2, (index2 + 1)).equals("x")
                    && !inputText.substring(index2, (index2 + 1)).equals("÷")) {

                //System.out.println("number of numbers after is working!!!");

                number_of_numbers_after++;
                index2++;
            }
        }
    }

    private void Math(String have, String inputText) {

        String remainder = answer.substring(1, answer.length());
        String beginning = answer.substring(0, 1);

        String check="";

        int indexOfHave = 0;

        if (beginning.equals("-")) {
            check = remainder;
            indexOfHave = check.indexOf(have) + 1;
            number_of_numbers_before++;
        } else {
            check = inputText;
            indexOfHave = inputText.indexOf(have);
        }


        if (check.contains(have)) {
            double before = 0.0, after = 0.0, newNumber = 0.0;


            try {
                before = Double.parseDouble(inputText.substring(indexOfHave - number_of_numbers_before, indexOfHave));
                after = Double.parseDouble(inputText.substring(indexOfHave + 1, indexOfHave + number_of_numbers_after + 1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            System.out.println("   -----     ");
            System.out.println("number Before = " + before);
            System.out.println("                " + have);
            System.out.println("number After  = " + after);


            if (have.equals("÷")) {
                newNumber = before / after;
            } else if (have.equals("x")) {
                newNumber = before * after;
            } else if (have.equals("-")) {
                newNumber = before - after;
            } else if (have.equals("+")) {
                newNumber = before + after;
            }

            System.out.println("number New    = " + newNumber);
            System.out.println("index = " + indexOfHave);
            System.out.println("   -----     ");

            //System.out.println("number of numbers after is working!!! " + newNumber);

            answer = inputText.substring(0, indexOfHave - number_of_numbers_before) + newNumber + inputText.substring(indexOfHave + 1 + number_of_numbers_after);
        }
    }

    private void divide(String inputText) {
        number_of_numbers_before = 0;
        number_of_numbers_after = 0;

        howManyBeforAndAfter("÷", inputText);
        Math("÷", inputText);
    }

    private void multiply(String inputText) {
        number_of_numbers_before = 0;
        number_of_numbers_after = 0;

        howManyBeforAndAfter("x", inputText);
        Math("x", inputText);
    }

    private void add(String inputText) {
        number_of_numbers_before = 0;
        number_of_numbers_after = 0;

        howManyBeforAndAfter("+", inputText);
        Math("+", inputText);
    }

    private void subtract(String inputText) {
        number_of_numbers_before = 0;
        number_of_numbers_after = 0;

        howManyBeforAndAfter("-", inputText);
        Math("-", inputText);
    }
}
