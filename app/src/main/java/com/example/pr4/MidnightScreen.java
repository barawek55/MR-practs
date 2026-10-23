package com.example.pr4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public final class MidnightScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.screen_midnight);

        findViewById(R.id.secretTrigger).setOnClickListener(this::onSecretOrbTapped);
    }

    private void onSecretOrbTapped(View source) {
        new AlertDialog.Builder(this)
                .setTitle("Ночной вопрос")
                .setMessage("Ты спишь?")
                .setCancelable(false)
                .setPositiveButton("Да", this::handleSleepYes)
                .setNegativeButton("Нет", this::handleSleepNo)
                .show();
    }

    private void handleSleepYes(DialogInterface dialog, int which) {
        finishAffinity();
    }

    private void handleSleepNo(DialogInterface dialog, int which) {
        Intent toHub = new Intent(this, HubScreen.class);
        toHub.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(toHub);
        finish();
    }
}