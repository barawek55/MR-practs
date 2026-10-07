package com.example.pract3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final Button[] allButtons = new Button[4];
    private boolean[] testAnswers;

    private static final int[] CORRECT_INDEXES = {4, 5, 6, 7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allButtons[0] = findViewById(R.id.btnOne);
        allButtons[1] = findViewById(R.id.btnTwo);
        allButtons[2] = findViewById(R.id.btnThree);
        allButtons[3] = findViewById(R.id.btnFour);

        allButtons[0].setOnClickListener(v ->
                Toast.makeText(this, "Кнопка номер 1 нажата", Toast.LENGTH_SHORT).show());

        allButtons[1].setOnClickListener(v ->
                Toast.makeText(this, "Кнопка номер 2 нажата", Toast.LENGTH_LONG).show());

        allButtons[2].setOnClickListener(v -> showCustomDialog());

        allButtons[3].setOnClickListener(v -> showQuizDialog());
    }

    private void showCustomDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Кнопка 3")
                .setIcon(R.drawable.test_icon)          // test_icon.png в res/drawable
                .setMessage("Это кастомное диалоговое окно. Выберите действие ниже.")
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, which) -> {
                    for (Button b : allButtons) b.setTextColor(Color.RED);
                })
                .setNegativeButton("Отмена", (dialog, which) -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Окно закрыто", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void showQuizDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_quiz, null);

        CheckBox[] boxes = {
                view.findViewById(R.id.cb0), view.findViewById(R.id.cb1),
                view.findViewById(R.id.cb2), view.findViewById(R.id.cb3),
                view.findViewById(R.id.cb4), view.findViewById(R.id.cb5),
                view.findViewById(R.id.cb6), view.findViewById(R.id.cb7)
        };

        new MaterialAlertDialogBuilder(this)
                .setView(view)
                .setPositiveButton("Проверить", (dialog, which) -> {
                    testAnswers = new boolean[boxes.length];
                    for (int i = 0; i < boxes.length; i++) {
                        testAnswers[i] = boxes[i].isChecked();
                    }
                    checkQuiz();
                })
                .setNegativeButton("Закрыть", null)
                .show();
    }

    private void checkQuiz() {
        ArrayList<Integer> selected = new ArrayList<>();
        for (int i = 0; i < testAnswers.length; i++) {
            if (testAnswers[i]) selected.add(i);
        }

        boolean allCorrect = true;
        if (selected.size() != CORRECT_INDEXES.length) {
            allCorrect = false;
        } else {
            for (int idx : CORRECT_INDEXES) {
                if (!selected.contains(idx)) {
                    allCorrect = false;
                    break;
                }
            }
        }

        if (allCorrect) {
            Toast.makeText(this, "Верно! Планеты-гиганты: Юпитер, Сатурн, Уран, Нептун.",
                    Toast.LENGTH_LONG).show();
        } else {
            for (Button b : allButtons) b.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Ошибка! Кнопки скрыты.", Toast.LENGTH_SHORT).show();
        }
    }
}