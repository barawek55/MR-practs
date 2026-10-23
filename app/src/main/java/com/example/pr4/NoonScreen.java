package com.example.pr4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public final class NoonScreen extends AppCompatActivity implements View.OnClickListener {

    private static final int ALERT_SLOT_WORK_END = 0x0F01;

    private Button backLink;
    private Button advanceLink;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.screen_noon);
        fetchWidgets();
        connectHandlers();
        fireWorkEndAlert();
    }

    private void fetchWidgets() {
        backLink = findViewById(R.id.navHome);
        advanceLink = findViewById(R.id.navForward);
    }

    private void connectHandlers() {
        backLink.setOnClickListener(this);
        advanceLink.setOnClickListener(this);
    }

    private void fireWorkEndAlert() {
        new AlertDispatcher(this).post(
                "Скоро конец рабочего дня",
                "Завершайте задачи и готовьтесь к вечеру.",
                ALERT_SLOT_WORK_END);
    }

    @Override
    public void onClick(View source) {
        if (source == backLink) {
            navigateBackToHub();
        } else if (source == advanceLink) {
            navigateToNextScreen();
        }
    }

    private void navigateBackToHub() {
        Intent toHub = new Intent(this, HubScreen.class);
        toHub.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(toHub);
        finish();
    }

    private void navigateToNextScreen() {
        startActivity(new Intent(this, DuskScreen.class));
    }
}