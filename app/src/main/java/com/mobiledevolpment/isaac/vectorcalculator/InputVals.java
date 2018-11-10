package com.mobiledevolpment.isaac.vectorcalculator;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by LEHMANIT17 on 3/12/2018.
 */

public class InputVals extends AppCompatActivity{

    public Button bv;
    public double[] inputV;
    public EditText[][] inputVectorEditText = new EditText[2][3];
    private Context contextI;

    public InputVals(double[] InputVectorA_B, Context context){
        // set the new input
        inputV = new double[6];
        for (int x = 0; x < 6; x++) {
            inputV[x] = InputVectorA_B[x];
        }
        contextI = context;
    }


    public void createButton(String titleTXT) {
        bv = new Button(contextI);
        bv.setBackground(contextI.getDrawable(R.drawable.rounded_corner));
        bv.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);
        bv.setLayoutParams(params);

        bv.setText(titleTXT);
    }

    public void setInputTXTViews(EditText[][] IT) {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                inputVectorEditText[y][x] = IT[y][x];
            }
        }
    }

    public void setOnClickListener() {
        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the input as the input that was used to make the current calculation

                //vibrate on press
                vibrate();

                //Vector A
                for (int x = 0; x < 3; x++) {
                    String curVal = Double.toString(inputV[x]);
                    inputVectorEditText[0][x].setText(curVal);
                }

                //Vector B
                for (int x = 0; x < 3; x++) {
                    String curVal = Double.toString(inputV[x + 3]);
                    inputVectorEditText[1][x].setText(curVal);
                }
            }
        });
    }

    public Button getButton() {
        return bv;
    }

    public double[] returnInput() {
        return inputV;
    }

    private void vibrate() {
        Vibrator vibe = (Vibrator) contextI.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
    }
}
