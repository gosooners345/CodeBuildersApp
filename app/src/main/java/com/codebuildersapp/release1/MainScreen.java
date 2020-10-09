package com.codebuildersapp.release1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Objects;

public class MainScreen extends AppCompatActivity {
    ExtendedFloatingActionButton newFileFab, openFileFab;
    String filePath = "";
    private static final int OPEN_DOC_HTML_FILE = 2;
    String fileName = "";
    static String[] permissions = new String[2];
    String fileType = "";
    String fileData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        permissions[0] = Manifest.permission.READ_EXTERNAL_STORAGE;
        permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Access Granted", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, permissions, 1);
            Toast.makeText(this, "Access Needs accepted", Toast.LENGTH_SHORT).show();

        }
        newFileFab = findViewById(R.id.new_file_button);
        newFileFab.setOnClickListener(newFabListener);
        openFileFab = findViewById(R.id.openFileButton);
        openFileFab.setOnClickListener(openFileListener);

    }

    public void onFileClick(int position) {
        final int INTENT_ID = 2;


    }

    ExtendedFloatingActionButton.OnClickListener openFileListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/*");
            startActivityForResult(intent, OPEN_DOC_HTML_FILE);
        }
    };

    //Loading a document
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == OPEN_DOC_HTML_FILE
                && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    String[] chunks = uri.getLastPathSegment().split("/");
                    fileName = chunks[chunks.length - 1];
                    fileData = readTextFromUri(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            resultData = new Intent(getApplicationContext(), CodeActivity.class);
            resultData.putExtra("FILEDATA", fileData);
            resultData.putExtra("PROJECT", fileName);
            resultData.putExtra("DOCUMENTTYPE", fileType);
            resultData.putExtra("INTENT_ID", 2);
            startActivity(resultData);
        }
    }

    ExtendedFloatingActionButton.OnClickListener newFabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final int INTENT_ID = 1;
            final Intent intent1 = new Intent(getApplicationContext(), CodeActivity.class);
            setContentView(R.layout.new_file_dialog);
            setTitle("New File");
            final EditText projectTextBox = findViewById(R.id.projectTextBox);
            Spinner projectType = findViewById(R.id.projectFileType);
            projectType.setOnItemSelectedListener(newFileTypeListener);
            Button saveButton = findViewById(R.id.saveNewFileButton), cancelButton = findViewById(R.id.cancelFileButton);
            ;

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String fileName = projectTextBox.getText().toString();
                    intent1.putExtra("INTENT_ID", INTENT_ID);
                    intent1.putExtra("PROJECT", fileName);
                    intent1.putExtra("DOCUMENTTYPE", fileType);
                    startActivity(intent1);
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            });
        }
    };
    //
    AdapterView.OnItemSelectedListener newFileTypeListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            fileType = String.format("%s", adapterView.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }
}