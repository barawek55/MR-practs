package com.example.pr4;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public final class HubScreen extends AppCompatActivity implements View.OnClickListener {

    private static final int POST_NOTIF_TICKET = 0xA1;
    private final Map<View, Class<? extends Activity>> destinations = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.screen_hub);
        requestNotificationGrantIfNeeded();
        wireTiles();
    }

    private void requestNotificationGrantIfNeeded() {
        if (Build.VERSION.SDK_INT < 33) return;
        if (checkSelfPermission("android.permission.POST_NOTIFICATIONS")
                == PackageManager.PERMISSION_GRANTED) return;
        requestPermissions(
                new String[]{"android.permission.POST_NOTIFICATIONS"},
                POST_NOTIF_TICKET);
    }

    private void wireTiles() {
        destinations.put(findViewById(R.id.tileDawn), DawnScreen.class);
        destinations.put(findViewById(R.id.tileNoon), NoonScreen.class);
        destinations.put(findViewById(R.id.tileDusk), DuskScreen.class);
        destinations.put(findViewById(R.id.tileMidnight), MidnightScreen.class);
        for (View tile : destinations.keySet()) {
            tile.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View source) {
        Class<? extends Activity> target = destinations.get(source);
        if (target != null) {
            startActivity(new Intent(this, target));
        }
    }
}