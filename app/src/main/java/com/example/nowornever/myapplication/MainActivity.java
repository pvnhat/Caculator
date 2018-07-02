
package com.example.nowornever.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvShow, tvNum1;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,
            btnDot, btnMulti, btnSub, btnDivide, btnAdd, btnResult,
            btnAc,btnFraction,btnPercent;
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    String lastResult = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.tvShow);
        tvNum1 = (TextView) findViewById(R.id.tvNum1);

        showLastResult();
        if (!lastResult.equals(""))
            tvShow.setText(lastResult);

        int arrButtons[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot, R.id.btnMulti, R.id.btnSub,
                R.id.btnDivide, R.id.btnAdd, R.id.btnResult, R.id.btnAc, R.id.btnFraction, R.id.btnPercent};

        for (int arrButton: arrButtons) {
            View v = findViewById(arrButton);
            v.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String string= tvShow.getText().toString();
        if (string.equals("0")) {
            string = "";
        }
        switch (v.getId()){
            case R.id.btn0:
                tvShow.setText(string += "0");
                break;
            case R.id.btn1:
                tvShow.setText(string + "1");
                break;
            case R.id.btn2:
                tvShow.setText(string + "2");
                break;
            case R.id.btn3:
                tvShow.setText(string + "3");
                break;
            case R.id.btn4:
                tvShow.setText(string + "4");
                break;
            case R.id.btn5:
                tvShow.setText(string + "5");
                break;
            case R.id.btn6:
                tvShow.setText(string + "6");
                break;
            case R.id.btn7:
                tvShow.setText(string + "7");
                break;
            case R.id.btn8:
                tvShow.setText(string + "8");
                break;
            case R.id.btn9:
                tvShow.setText(string + "9");
                break;
            case R.id.btnAc:
                tvShow.setText("");
                string = "";
                break;
            case R.id.btnFraction:
                if (string.charAt(0) == '-') {
                    string = string.substring(0, 0) + string.substring(0 + 1);
                    tvShow.setText(string);
                }
                else
                    tvShow.setText("-" + string);
                break;
            case R.id.btnAdd:
                operator = '+';
                num1 = Double.parseDouble(string);
                tvShow.setText("");
                tvNum1.setText(num1 + "+");
                string = "";
                break;
            case R.id.btnSub:
                operator = '-';
                num1 = Double.parseDouble(string);
                tvShow.setText("");
                tvNum1.setText(num1 + "-");
                string = "";
                break;
            case R.id.btnMulti:
                operator = '*';
                num1 = Double.parseDouble(string);
                tvShow.setText("");
                tvNum1.setText(num1 + "*");
                string = "";
                break;
            case R.id.btnDivide:
                operator = '/';
                num1 = Double.parseDouble(string);
                tvShow.setText("");
                tvNum1.setText(num1 + "/");
                string = "";
                break;
            case R.id.btnPercent:
                operator = '%';
                num1 = Double.parseDouble(string);
                tvShow.setText("");
                tvNum1.setText(num1 + "%");
                string = "";
                break;
            case R.id.btnResult:
                num2 = Double.parseDouble(string);
                if (operator == '+') {
                    result = num1 + num2;
                    tvNum1.setText(num1 + " + " + num2);
                } else if(operator == '-'){
                    result = num1 - num2;
                    tvNum1.setText(num1 + " - " + num2);
                } else if (operator == '*'){
                    result = num1 * num2;
                    tvNum1.setText(num1 + " * " + num2);
                } else if (operator == '%'){
                    result = num1 % num2;
                    tvNum1.setText(num1 + " % " + num2);
                }else {
                    result = num1 / num2;
                    tvNum1.setText(num1 + " / " + num2);
                }

                tvShow.setText(result + "");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.caculator_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuClear:
                tvShow.setText("");
                tvNum1.setText("");
            case R.id.menuSave: {
                SharedPreferences sharedPreferences =
                        getSharedPreferences("Result", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lastResult", String.valueOf(result));
                editor.commit();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    void showLastResult() {
        SharedPreferences sharedPreferences =
                getSharedPreferences("Result", Context.MODE_PRIVATE);
        lastResult = sharedPreferences.getString("lastResult","");
    }
}
