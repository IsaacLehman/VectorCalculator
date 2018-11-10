package com.mobiledevolpment.isaac.vectorcalculator;

import android.widget.TextView;

import java.util.Scanner;

public class Math2_0 {

//    private String inputTextFromTextView;
//
//    public Math2_0() {
//
//    }
//
//    public void add_input_number(int place, TextView input) {
//        inputTextFromTextView = input.getText().toString();
//        inputTextFromTextView += place;
//        input.setText(inputTextFromTextView);
//    }
//
//    public void add_input_buttons(int place, TextView answer, TextView input) {
//        inputTextFromTextView = input.getText().toString();
//
//        String newInputOperator = "-1";
//        // if the input is an operator turn into text
//        switch (place) {
//            case 1 : newInputOperator = "÷";
//                    break;
//            case 2 : newInputOperator = "x";
//                break;
//            case 4 : newInputOperator = "-";
//                break;
//            case 5 : newInputOperator = "+";
//                break;
//        }
//
//
//            // if the previous input is a number insert the operator
//            // if not, but the operator is (-) still input
//            // do nothing
//        String lastItem = inputTextFromTextView.substring(inputTextFromTextView.length() - 1);
//        if (!newInputOperator.equals("-1")
//                && ( lastItem.equals("+") || lastItem.equals("-") || lastItem.equals("x") || lastItem.equals("÷") )) {
//            return;
//        }
//
//
//        // if operable
//        if (place != 0 && place != 3 && place != 6 && place != 7) {
//
//            String Input = "";
//            int lengthofString = input.getText().toString().length();
//            String inputStringtxt = input.getText().toString();
//
//            // make sure there is a number at the beginning
//            if (lengthofString > 0 && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("+")
//                    && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("-")
//                    && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("x")
//                    && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("÷")) {
//
//                if (place == 1) {
//                    Input = "÷";
//                } else if (place == 2) {
//                    Input = "x";
//                } else if (place == 5) {
//                    Input = "+";
//                }
//            }
//
//            if (place == 4) {
//                if (lengthofString > 0 && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("+")
//                        && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("-")
//                        && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("x")
//                        && !inputStringtxt.substring(lengthofString - 1, lengthofString).equals("÷")) {
//                    Input = "-";
//                } else if (lengthofString == 0) {
//                    Input = "-";
//                }
//            }
//
//            //check to make sure previous value wa not .
//            int index = inputText.length();
//            if (inputText.length() > 0 && inputText.substring(index - 1).equals(".")) {
//                String currentText = inputText;
//                currentText += "0" + Input;
//                input.setText(currentText);
//            } else {
//                String currentText = input.getText().toString();
//                currentText += Input;
//                input.setText(currentText);
//            }
//        }
//
//        // if clear
//        if (place == 0) {
//            input.setText(null);
//            answer.setText(null);
//        }
//
//        // if delete
//        if (place == 3 && input.getText().toString().length() > 0) {
//            String currentText = input.getText().toString();
//            currentText = currentText.substring(0, currentText.length() - 1);
//            input.setText(currentText);
//        }
//
//        // if decimal
//        if (place == 7) {
//            //check to make sure previous value wa not .
//            int index = inputText.length();
//            if (inputText.length() > 0 && inputText.substring(index - 1).equals(".")) {
//            } else if (inputText.length() > 0 && inputText.substring(index - 1, index).equals("+") || inputText.length() > 0 && inputText.substring(index - 1, index).equals("-") || inputText.length() > 0 && inputText.substring(index - 1, index).equals("x")
//                    || inputText.length() > 0 && inputText.substring(index - 1, index).equals("÷")) {
//                String currentText = inputText;
//                currentText += "0.";
//                input.setText(currentText);
//            } else if (inputText == null || inputText.length() == 0) {
//                String currentText = inputText;
//                currentText += "0.";
//                input.setText(currentText);
//            } else if (check_for_decimal(inputText)) {
//                // makes sure there isn't a decimal between it and an operator
//            } else {
//                String currentText = input.getText().toString();
//                currentText += ".";
//                input.setText(currentText);
//            }
//        }
//
//        // if equals
//        int lengthOfString = input.getText().toString().length();
//        if (place == 6 && inputText.length() > 0
//                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("+")
//                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("-")
//                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("x")
//                && !inputText.substring(lengthOfString - 1, lengthOfString).equals("÷")) {
//
//            //System.out.println("working");
//            equals(input.getText().toString(), answer);
//        }
//    }
}
