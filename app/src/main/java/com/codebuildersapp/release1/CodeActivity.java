package com.codebuildersapp.release1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class CodeActivity extends AppCompatActivity {
Button htmlButton, paragraphButton, divButton, headerButton,bodyButton,submitButton;
TextInputEditText codeEditor;
String codeSection;
int activity_id;
TextView fileNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        htmlButton=findViewById(R.id.htmlActionButton);
        paragraphButton = findViewById(R.id.paragraphActionButton);
        divButton=findViewById(R.id.divActionButton);
        headerButton = findViewById(R.id.headActionButton);
        bodyButton= findViewById(R.id.bodyActionButton);
        codeEditor = findViewById(R.id.code_block_EditText);
        submitButton=findViewById(R.id.submit_Button);
fileNameView=findViewById(R.id.page_title_TV);
        htmlButton.setOnClickListener(textTagClicker); paragraphButton.setOnClickListener(textTagClicker);
        divButton.setOnClickListener(textTagClicker);
        headerButton.setOnClickListener(textTagClicker);
        bodyButton.setOnClickListener(textTagClicker);
        submitButton.setOnClickListener(textTagClicker);
        codeSection = "<!DOCTYPE html>";
activity_id=getIntent().getIntExtra("activity_id",0);
codeEditor.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
codeSection = Objects.requireNonNull(codeEditor.getText()).toString();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
});
    }
    Button.OnClickListener textTagClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String concat = codeEditor.getText().toString().substring(codeEditor.getSelectionStart(),codeEditor.getSelectionEnd()+1);
            switch (view.getId())
            {
                case R.id.headActionButton:
                    //codeEditor.setText(new StringBuilder().append(getString(R.string.head_opener)).append(concat).append(getString(R.string.head_end)).toString());                break;
                    codeEditor.append("<head>");break;
                case R.id.htmlActionButton:codeEditor.setText(new StringBuilder().append("<html>").append(concat).append("</html>").toString());                break;
                case R.id.paragraphActionButton:codeEditor.setText(new StringBuilder().append("<p>").append(concat).append(" </p>"));break;
                case  R.id.divActionButton:codeEditor.setText(new StringBuilder().append("<div>" ).append(concat).append("</div>"));break;
                case R.id.bodyActionButton:codeEditor.setText(new StringBuilder().append("<body>" ).append(concat).append(" </body"));break;
                case R.id.breakActionButton: codeEditor.append("<br>");break;
                case R.id.submit_Button:
                    Intent webIntent = new Intent(getApplicationContext(),browserActivity.class);
                    codeSection+=codeEditor.getText();
                    webIntent.putExtra("textData",codeSection);
                    startActivity(webIntent);
            }

        }
    };
}