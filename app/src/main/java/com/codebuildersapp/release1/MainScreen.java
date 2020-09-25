package com.codebuildersapp.release1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainScreen extends AppCompatActivity {
ExtendedFloatingActionButton newFileFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        newFileFab = findViewById(R.id.new_file_button);
        newFileFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"New file stub",Snackbar.LENGTH_LONG).show();
                int ACTIVITY_ID = 1;
                Intent intent = new Intent(getApplicationContext(),CodeActivity.class);
                intent.putExtra("activity_ID",ACTIVITY_ID);
                startActivity(intent);
            }
        });
    }
}