package com.mobiledevolpment.isaac.vectorcalculator;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopUpCalculatorActivity extends AppCompatActivity {

    private TextView input, answer;

    private Button clear, divide, multiply, delete, seven, eight, nine, minus, four, five, six, plus,
            one, two, three, equals, zero, decimal;


    private Calculator_Math math;


    //Array of buttons and textViews
    private Button numbers[] = new Button[10];
    private Button input_btns[] = new Button[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_calculator);

        //Set the title
        setTitle("Calculator");

        //instantiate all of the items on the screen to be used/edited

        // Initialize Input Buttons and add buttons to an array
        clear = (Button) findViewById(R.id.Clear);
        input_btns[0] = clear;

        divide = (Button) findViewById(R.id.divide);
        input_btns[1] = divide;

        multiply = (Button) findViewById(R.id.multiply);
        input_btns[2] = multiply;

        delete = (Button) findViewById(R.id.delete);
        input_btns[3] = delete;

        minus = (Button) findViewById(R.id.minus);
        input_btns[4] = minus;

        plus = (Button) findViewById(R.id.plus);
        input_btns[5] = plus;

        equals = (Button) findViewById(R.id.equals);
        input_btns[6] = equals;

        decimal = (Button) findViewById(R.id.decimal);
        input_btns[7] = decimal;

        //------------------------------------------------------------------------------------------

        // Initialize Input Buttons and add buttons to an array
        zero = (Button) findViewById(R.id.zero);
        numbers[0] = zero;

        one = (Button) findViewById(R.id.onec);
        numbers[1] = one;

        two = (Button) findViewById(R.id.two);
        numbers[2] = two;

        three = (Button) findViewById(R.id.three);
        numbers[3] = three;

        four = (Button) findViewById(R.id.four);
        numbers[4] = four;

        five = (Button) findViewById(R.id.five);
        numbers[5] = five;

        six = (Button) findViewById(R.id.six);
        numbers[6] = six;

        seven = (Button) findViewById(R.id.seven);
        numbers[7] = seven;

        eight = (Button) findViewById(R.id.eight);
        numbers[8] = eight;

        nine = (Button) findViewById(R.id.nine);
        numbers[9] = nine;

        //------------------------------------------------------------------------------------------

        //Instantiate math class.
        math = new Calculator_Math();

        //------------------------------------------------------------------------------------------

        // Initialize TextViews and add TextViews to an array
        input = (TextView) findViewById(R.id.screen_input);
        answer = (TextView) findViewById(R.id.screen_answer);

        //------------------------------------------------------------------------------------------

        //Add the onclick listeners to the buttons by looping through them all
        for (int x = 0; x < 10; x++) {
            final int place = x;
            numbers[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(50);

                    math.add_input_number(place, input);
                }
            });
        }

        for (int x = 0; x < 8; x++) {
            final int place = x;
            input_btns[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(50);

                    math.add_input_buttons(place, answer, input);
                }
            });
        }
    }
}