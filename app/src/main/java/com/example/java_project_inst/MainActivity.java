package com.example.java_project_inst;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView name, group;
    ImageView imageView;
    boolean textsVisible = true;
    boolean imageVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        group = findViewById(R.id.group);
        imageView = findViewById(R.id.imageView);

        Button btnToggleText  = findViewById(R.id.btnToggleText);
        Button btnToggleImage = findViewById(R.id.btnToggleImage);

        btnToggleText.setOnClickListener(v -> {
            textsVisible = !textsVisible;
            int vis = textsVisible ? View.VISIBLE : View.INVISIBLE;
            name.setVisibility(vis);
            group.setVisibility(vis);
        });

        btnToggleImage.setOnClickListener(v -> {
            imageVisible = !imageVisible;
            imageView.setVisibility(imageVisible ? View.VISIBLE : View.INVISIBLE);
        });
    }
}