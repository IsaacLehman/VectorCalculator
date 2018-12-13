package com.mobiledevolpment.isaac.vectorcalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.InflateException;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Vectors extends AppCompatActivity {

    private EditText VecA_i_EditTXT, VecA_j_EditTXT, VecA_k_EditTXT, VecB_i_EditTXT, VecB_j_EditTXT, VecB_k_EditTXT;
    private Button ClearButton, ResetButton, CrossButton, DotButton, AddButton, SubtractButton, LineButton, MagnitudeButton, AngleA_B, UnitVButton, CalculatorButton, ProjectionButton;
    private ScrollView OutputScrollView;
    private LinearLayout outputLayout;

    private String EditStandard = "";
    private double[] AVector = new double[3];
    private double[] BVector = new double[3];

    private ArrayList<Double[]> inputSaved = new ArrayList<>();
    private ArrayList<Button> outputButtons = new ArrayList<>();
    private double[] allValues;

    private EditText[][] inputVectorEditTexts = new EditText[2][3];

    private VectorMethods vecMeth = new VectorMethods();
    private InputVals customButtonClass;

    private ArrayList<InputVals> customButtonClassArrayList = new ArrayList<>();

    private int outputLogCount = 1, outputSpot = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vectors);

        //---------------

        VecA_i_EditTXT = (EditText) findViewById(R.id.Vec_A_i_editTXT);
        VecA_j_EditTXT = (EditText) findViewById(R.id.Vec_A_j_editTXT);
        VecA_k_EditTXT = (EditText) findViewById(R.id.Vec_A_k_editTXT);

        inputVectorEditTexts[0][0] = VecA_i_EditTXT;
        inputVectorEditTexts[0][1] = VecA_j_EditTXT;
        inputVectorEditTexts[0][2] = VecA_k_EditTXT;

        //----

        VecB_i_EditTXT = (EditText) findViewById(R.id.Vec_B_i_editTXT);
        VecB_j_EditTXT = (EditText) findViewById(R.id.Vec_B_j_editTXT);
        VecB_k_EditTXT = (EditText) findViewById(R.id.Vec_B_k_editTXT);

        inputVectorEditTexts[1][0] = VecB_i_EditTXT;
        inputVectorEditTexts[1][1] = VecB_j_EditTXT;
        inputVectorEditTexts[1][2] = VecB_k_EditTXT;

        //----

        // set the input to only accept numbers
//        for (int y = 0; y < 2; y++) {
//            for (int x = 0; x < 3; x++) {
//                inputVectorEditTexts[y][x].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
//            }
//        }

        resetInput();

        //---------------

        // OutputLog_TextView = (TextView) findViewById(R.id.Vector_output_txtView);

        //---------------

        OutputScrollView = (ScrollView) findViewById(R.id.VScrollView);

        //---------------

        outputLayout = (LinearLayout) findViewById(R.id.outputLayout);

        //---------------

        ClearButton = (Button) findViewById(R.id.ClearV_button);
        ResetButton = (Button) findViewById(R.id.ResetVInput_button);
        CrossButton = (Button) findViewById(R.id.CrossProduct_button);
        DotButton = (Button) findViewById(R.id.DotProduct_button);
        AddButton = (Button) findViewById(R.id.Vaddditon_button);
        SubtractButton = (Button) findViewById(R.id.Vsubtraction_button);
        LineButton = (Button) findViewById(R.id.ABline_button);
        MagnitudeButton = (Button) findViewById(R.id.V_Magnitude_button);
        AngleA_B = (Button) findViewById(R.id.AngleAB_button);
        UnitVButton = (Button) findViewById(R.id.UnitV_button);
        CalculatorButton = (Button) findViewById(R.id.Calculator_button);
        ProjectionButton = (Button) findViewById(R.id.Vprojection_button);

        //------------------------------------------------------------------------------------------

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();

                //resetInput();

                outputLogCount = 1;
                outputSpot = 0;
                inputSaved.clear();
                outputButtons.clear();

                // clear the log
                //OutputLog_TextView.setText("");

                outputLayout.removeAllViews();
            }
        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();

                resetInput();
            }
        });

        CrossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();

                vecMeth.computeCrossProduct();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printCrossProduct() + "\n");

                finalise();
            }
        });

        DotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();

                vecMeth.computeVdotProduct();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printDotProduct() + "\n");

                finalise();
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();

                vecMeth.addVector();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printAplusB() + "\n");

                finalise();
            }
        });

        SubtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();

                vecMeth.subtractVector();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printAminusB() + "\n");

                finalise();
            }
        });

        LineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();

                addButton("- " + outputLogCount + " -\n" + vecMeth.computeLineFrom_A_to_B(AVector, BVector) + "\n");

                finalise();

            }
        });

        MagnitudeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();

                vecMeth.computeMagnitude();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printVMagnitude() + "\n");

                finalise();

            }
        });

        AngleA_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();

                vecMeth.computeAngleA_B();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printAngleBetweenA_B() + "\n");

                finalise();
            }
        });

        UnitVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();

                vecMeth.computeUnitVector();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printUnitVector() + "\n");

                finalise();
            }
        });

        CalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyBoard();
                vibrate();

                // try to prevent inflate exception
                try {
                    Intent intent = new Intent(getApplicationContext(), PopUpCalculatorActivity.class);
                    startActivity(intent);
                } catch (InflateException e) {
                    Toast.makeText(Vectors.this, "Inflate Exception",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(Vectors.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        ProjectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();

                vecMeth.project_a_onto_b();

                addButton("- " + outputLogCount + " -\n" + vecMeth.printAonBProjection() + "\n");

                finalise();
            }
        });

    }

    private void initialize() {
        hideKeyBoard();
        vibrate();

        getVector();
        setVecMethVectors();
    }

    private void finalise() {
        ++outputLogCount;
        ++outputSpot;

        OutputScrollView.post(new Runnable() {
            @Override
            public void run() {
                OutputScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void addTextView(String t) {
        TextView tv = new TextView(getBaseContext());
        tv.setBackground(getDrawable(R.drawable.rounded_corner));
        tv.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);
        tv.setLayoutParams(params);

        tv.setText(t);
        outputLayout.addView(tv);
    }

    private void addButton(String t) {

        // the spot of the newly added button
        int endOfArraySpot = (customButtonClassArrayList.size());

        // add a new button to the array list with the current input Vals
        customButtonClass = new InputVals(allValues, this);
        customButtonClassArrayList.add(customButtonClass);
        customButtonClassArrayList.get(endOfArraySpot).createButton(t);
        customButtonClassArrayList.get(endOfArraySpot).setOnClickListener();
        customButtonClassArrayList.get(endOfArraySpot).setInputTXTViews(inputVectorEditTexts);

        // add to Linear Layout in ScrollView
        outputLayout.addView(customButtonClassArrayList.get(endOfArraySpot).getButton());


        //------------------------------------------------------------------------------------------

//        setInputValues();
//
//        Button bv = new Button(getBaseContext());
//        bv.setBackground(getDrawable(R.drawable.rounded_corner));
//        bv.setGravity(Gravity.CENTER);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, 15, 0, 15);
//        bv.setLayoutParams(params);
//
//        bv.setText(t);
//        outputLayout.addView(bv);
//
//        outputButtons.add(bv);
//
//        bv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int x = 0; x < 6; x++) {
//                    //inputVectorEditTexts[0][x].setText(val.toString());
//                }
//
//                for (int x = 0; x < 3; x++) {
//                    String temp = inputVectorEditTexts[1][x].getText().toString();
//                    temp = makeSureDouble(temp);
//
//                    BVector[x] = Double.parseDouble(temp);
//                }
//            }
//        });


    }

    private void setInputValues() {
        allValues = new double[6];
    }

    private void hideKeyBoard() {
        //Makes the keyboard go away when button is pressed
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void vibrate() {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
    }

    private void resetInput() {
        // reset the input
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                inputVectorEditTexts[y][x].setText("");
                if (x == 0) {
                    inputVectorEditTexts[y][x].setHint(R.string.input_hintI);
                } else if (x == 1) {
                    inputVectorEditTexts[y][x].setHint(R.string.input_hintJ);
                } else {
                    inputVectorEditTexts[y][x].setHint(R.string.input_hintK);
                }

            }
        }

        outputLogCount = 1;
    }

    private void getVector() {

        setInputValues();

        // make sure the input is a double and put into a double

        for (int x = 0; x < 3; x++) {
            String temp = inputVectorEditTexts[0][x].getText().toString();
            temp = makeSureDouble(temp);

            double val = Double.parseDouble(temp);
            AVector[x] = val;

            allValues[x] = val;
        }

        for (int x = 0; x < 3; x++) {
            String temp = inputVectorEditTexts[1][x].getText().toString();
            temp = makeSureDouble(temp);

            double val = Double.parseDouble(temp);
            BVector[x] = val;

            allValues[x + 3] = val;
        }
    }

    private void setVecMethVectors() {
        for (int x = 0; x < AVector.length; x++) {
            vecMeth.aVector[x] = AVector[x];
        }

        for (int x = 0; x < BVector.length; x++) {
            vecMeth.bVector[x] = BVector[x];
        }
    }

    private String makeSureDouble(String input) {

        String returnValue;

        if (input.contains(".")) {
            returnValue = input;
        } else if (input.equals("")) {
            returnValue = "0.0";
        } else if(input.equals("-.")) {
            returnValue = input.concat("0");
        } else {
            returnValue = input.concat(".0");
        }

        return returnValue;
    }
}