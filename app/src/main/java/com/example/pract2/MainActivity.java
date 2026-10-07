package com.example.pract2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputA, inputB;
    private TextView outputResult, opSymbol;
    private String currentOp = "+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputA       = findViewById(R.id.inputA);
        inputB       = findViewById(R.id.inputB);
        outputResult = findViewById(R.id.outputResult);
        opSymbol     = findViewById(R.id.opSymbol);

        findViewById(R.id.keyPlus).setOnClickListener(v   -> setOp("+"));
        findViewById(R.id.keyMinus).setOnClickListener(v  -> setOp("−"));
        findViewById(R.id.keyTimes).setOnClickListener(v  -> setOp("×"));
        findViewById(R.id.keyDivide).setOnClickListener(v -> setOp("÷"));

        findViewById(R.id.keyReset).setOnClickListener(v -> {
            inputA.setText("");
            inputB.setText("");
            outputResult.setText("= 0");
            currentOp = "+";
            opSymbol.setText("+");
        });

        outputResult.setOnClickListener(v -> calculate());
    }

    private void setOp(String op) {
        currentOp = op;
        opSymbol.setText(op);
    }

    private void calculate() {
        String sA = inputA.getText().toString().trim();
        String sB = inputB.getText().toString().trim();
        if (sA.isEmpty() || sB.isEmpty()) {
            Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show();
            return;
        }
        double a, b;
        try {
            a = Double.parseDouble(sA);
            b = Double.parseDouble(sB);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Некорректное число", Toast.LENGTH_SHORT).show();
            return;
        }
        double r;
        switch (currentOp) {
            case "+": r = a + b; break;
            case "−": r = a - b; break;
            case "×": r = a * b; break;
            case "÷":
                if (b == 0) {
                    outputResult.setText("Ошибка: Деление на ноль");
                    Toast.makeText(this, "Деление на ноль!", Toast.LENGTH_SHORT).show();
                    return;
                }
                r = a / b;
                break;
            default: r = 0;
        }
        String formatted = (r == (long) r) ? String.valueOf((long) r) : String.valueOf(r);
        outputResult.setText("= " + formatted);
    }
}