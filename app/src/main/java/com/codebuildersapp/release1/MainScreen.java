package com.codebuildersapp.release1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class MainScreen extends AppCompatActivity {
ExtendedFloatingActionButton newFileFab;
String filePath = "";
String projectsFolder= (Environment.getExternalStorageDirectory().getPath()+"/CodeBuildersProjects/");
String[] permissions = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        permissions[0]=Manifest.permission.READ_EXTERNAL_STORAGE;
        permissions[1]=Manifest.permission.WRITE_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Access Granted",Toast.LENGTH_SHORT).show();
            boolean available = false;
            boolean readable = false;

            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state))
                available = true;
            else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            {readable=true;available=true;
            }
            else
                available=false;
            Snackbar.make(findViewById(R.id.main_screen_master),projectsFolder,Snackbar.LENGTH_INDEFINITE).show();

        }
else{
            ActivityCompat.requestPermissions(this,permissions, 1);
            Toast.makeText(this,"Access Needs accepted",Toast.LENGTH_SHORT).show();

        }
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
    public void onFileClick(int position)
    {}
}