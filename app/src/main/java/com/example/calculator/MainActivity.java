package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView result,solution;
    MaterialButton ButtonC, ButtonOpenBracket, ButtonCloseBracket;
    MaterialButton ButtonDivide,buttonMultiply,ButtonPlus,ButtonMinus,ButtonEqual;
    MaterialButton Button0,Button1, Button2, Button3, Button4, Button5,Button6, Button7, Button8, Button9;
    MaterialButton ButtonAC, ButtonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(ButtonC, R.id.buttonC);
        assignId(ButtonOpenBracket, R.id.button_open_bracket);
        assignId(ButtonCloseBracket, R.id.button_close_bracket);
        assignId(ButtonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(ButtonPlus, R.id.button_plus);
        assignId(ButtonMinus, R.id.button_minus);
        assignId(ButtonEqual, R.id.button_equal);
        assignId(Button0, R.id.button0);
        assignId(Button1, R.id.button1);
        assignId(Button2, R.id.button2);
        assignId(Button3, R.id.button3);
        assignId(Button4, R.id.button4);
        assignId(Button5, R.id.button5);
        assignId(Button6, R.id.button6);
        assignId(Button7, R.id.button7);
        assignId(Button8, R.id.button8);
        assignId(Button9, R.id.button9);
        assignId(ButtonAC, R.id.button_clear);
        assignId(ButtonDot, R.id.button_dot);







    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText((CharSequence) result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;

        }

        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript", 1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}