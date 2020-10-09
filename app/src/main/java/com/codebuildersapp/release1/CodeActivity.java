package com.codebuildersapp.release1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

import com.google.android.material.textfield.TextInputEditText;



public class CodeActivity extends AppCompatActivity {
    OutputStream outStream;
private static final int CREATE_NEW_CODE_FILE = 1;
    //File file = new File(sdCard,"");
    private static final int CSS_FILE = 3;
    InputStream inStream;
Button htmlButton, paragraphButton, divButton, headButton,bodyButton,submitButton,breakButton,buttonButton;
Button h1Button, h2Button, h3Button, h4Button, h5Button, h6Button,linkButton;
Button tableButton, tableRowButton, tableDetlButton, tableFooterButton;
Button labelButton, asideButton, inputButton;
Button mainButton, headerButton,footerButton, quoteButton, formButton, scriptButton,styleButton,strikerButton;
Button listButton, unListButton, orListButton,saveButton;
private static final String DOC_HEADER_HTML="<!DOCTYPE html>\r\n",HTML_STRING= "\r\n<html>\r\n</html>",HEAD_STRING="<head> \r\n</head>",
        PARAGRAPH_STRING = "<p> \r\n </p>", BREAK_STRING="<br>",DIV_STRING="<div> \r\n </div>",
    BUTTON_STRING="<button>  </button>",LINK_REF_STRING="<a href =\" \"> </a>",BODY_STRING = "<body> \r\n </body>",
    H1_STRING = "<h1> </h1>",H2_STRING = "<h2>  </h2>", H3_STRING = "<h3>  </h3>",H4_STRING="<h4> </h4>",
    H5_STRING = "<h5> </h5>", H6_STRING = "<h6> </h6>",
        TABLE_STRING="<table> \r\n </table>",TABLE_FOOTER_STRING="<tfoot> \r\n</tfoot>",
        TABLE_DETAIL_STRING="<td> </td>",TABLE_ROW_STRING="<tr> \r\n</tr>",
    ORDERED_LIST_STRING="<ol>\r\n </ol>",LIST_ITEM_STRING="<li> </li>",UNORDERED_LIST_STRING="<ul> \r\n </ul>",
    QUOTATION_STRING="<q> </q>",MAIN_ACTIONSTRING="<main> \r\n</main>",
    HEADER_STRING = "<header> \r\n</header>", FOOTER_STRING = "<footer>\r\n </footer>",FORM_STRING = "<form> \r\n</form>",
    SCRIPT_STRING="<script> </script>",STRIKER_STRING = "<s> </s>", STYLE_STRING="<style>\r\n</style>", ASIDE_STRING="<aside> \r\n</aside>",
        INPUT_ACTIONSTRING="<input>",LABEL_ACTIONSTRING="<label>";

private static final int SAVE_REQUEST_CODE = 33;
TextInputEditText codeEditor;
String codeSection,projectName="",fileType="";
int activity_id;
TextView fileNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        fileNameView = findViewById(R.id.page_title_TV);
        buttonInitializer();
        activity_id = getIntent().getIntExtra("INTENT_ID", -1);
        codeEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                codeSection = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//This determines what the application needs to do in order to display the right data
        //Aka new file or reading in a file
        switch (activity_id) {
            case 1:
                fileType = getIntent().getStringExtra("DOCUMENTTYPE");
                if (fileType.equals("html"))
                    codeSection = DOC_HEADER_HTML;
                else
                    codeSection = "";
                break;
            case 2:
                if (getIntent().getStringExtra("FILEDATA").equals(""))
                    codeSection = DOC_HEADER_HTML;
                else
                    codeSection = getIntent().getStringExtra("FILEDATA");
                break;

        }
        projectName = getIntent().getStringExtra("PROJECT");
        fileNameView.setText(projectName);
        codeEditor.setText(codeSection);
        codeEditor.setSelection(codeSection.length());
        codeEditor.performClick();

    }
    //This activates all of the buttons on screen when the application loads
    Button.OnClickListener textTagClicker = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View view) {
            int selectionInt =codeSection.length(),selector= codeEditor.getSelectionStart(),selectionEnd=codeEditor.getSelectionEnd();
            switch (view.getId()) {
                case R.id.headActionButton:
                    codeSection = codeEditor.getText().insert(selector, HEAD_STRING).toString();
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.indexOf("</head>") - 1);
                    break;
                case R.id.htmlActionButton:
                    codeSection = codeEditor.getText().toString();
                    if (codeSection.contains("<html>"))
                        codeSection = codeSection.concat("\r\n" + "</html>");
                    else if (!codeSection.contains("<html>"))
                        codeSection = codeSection.concat("<html>");
                    else if (codeSection.contains("<html>") & codeSection.contains("</html>"))
                        codeSection.concat(HTML_STRING);
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.length());
                    break;
                case R.id.paragraphActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("p{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), PARAGRAPH_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</p>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("p{\r\n}"));
                    break;
                case R.id.divActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("div{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(selectionInt, DIV_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</div>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("div{\r\n}"));
                    break;
                case R.id.asideActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("aside{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(selectionInt, ASIDE_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</aside>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("aside{\r\n}"));
                    break;
                case R.id.labelActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("label{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(selectionInt, LABEL_ACTIONSTRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("<label>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("label{\r\n}"));
                    break;
                case R.id.inputActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("input{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(selectionInt, INPUT_ACTIONSTRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</input>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("input{\r\n}"));
                    break;
                case R.id.bodyActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("body{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(selectionInt, BODY_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.indexOf("</body>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("body{\r\n}"));
                    break;
                case R.id.breakActionButton:
                    codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), BREAK_STRING).toString();
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("<br>"));
                    break;
                case R.id.buttonActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("button{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), BUTTON_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</button>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("button{\r\n}"));
                    break;
                case R.id.h1ActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("h1{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), H1_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</h1>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("h1{\r\n}"));
                    break;
                case R.id.h2ActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("h2{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), H2_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</h2>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("h2{\r\n}"));
                    break;
                case R.id.h3ActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("h3{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), H3_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</h3>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("h3{\r\n}"));
                    break;
                case R.id.h4ActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("h4{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), H4_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</h4>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("h4{\r\n}"));
                    break;
                case R.id.h5ActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("h5{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), H5_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</h5>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("h5{\r\n}"));
                    break;
                case R.id.h6ActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("h5{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), H6_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</h6>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("h6{\r\n}"));
                    break;
                case R.id.mainActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("main{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), MAIN_ACTIONSTRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.indexOf("</main>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("main{\r\n}"));
                    break;
                case R.id.formActionButton:
                    codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), FORM_STRING).toString();
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</form>") - 1);
                    break;
                case R.id.quoteActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("q{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), QUOTATION_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</q>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("q{\r\n}"));
                    break;
                case R.id.headerActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("header{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), HEADER_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.indexOf("</header>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("header{\r\n}"));
                    break;
                case R.id.footerActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("footer{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeEditor.getText().insert(codeEditor.getSelectionStart(), FOOTER_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.indexOf("</footer>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("footer{\r\n}"));
                    break;
                case R.id.strikerActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("footer{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), STRIKER_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</s>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("s{\r\n}"));
                    break;
                case R.id.scriptActionButton:
                    codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), SCRIPT_STRING).toString();
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</script>") - 1);
                    break;
                case R.id.styleActionButton:
                    codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), STYLE_STRING).toString();
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</style>") - 1);
                    break;
                case R.id.linkActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("a{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), LINK_REF_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</a>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("a{\r\n}"));
                    break;
                case R.id.tableActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("table{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), TABLE_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</table>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("table{\r\n}"));
                    break;
                case R.id.tableDetlActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("td{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeEditor.getText().insert(codeEditor.getSelectionStart(), TABLE_DETAIL_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</td>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("td{\r\n}"));
                    break;
                case R.id.tableRowActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("tr{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), TABLE_ROW_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</tr>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("tr{\r\n}"));
                    break;
                case R.id.tableFootActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("tfoot{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), TABLE_FOOTER_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</tfoot>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("tfoot{\r\n}"));
                    break;
                case R.id.listActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("li{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), LIST_ITEM_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</li>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("li{\r\n}"));
                    break;
                case R.id.unorderedListActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("ul{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), UNORDERED_LIST_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</ul>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("ul{\r\n}"));
                    break;
                case R.id.orderedListActionButton:
                    if (fileType.equals("css") || projectName.contains("css"))
                        codeSection = codeEditor.getText().append("ol{\r\n}").toString();
                    else if (fileType == "html" || projectName.contains("html"))
                        codeSection = codeEditor.getText().insert(codeEditor.getSelectionStart(), ORDERED_LIST_STRING).toString();
                    codeEditor.setText(codeSection);
                    if (fileType == "html" || projectName.contains("html"))
                        codeEditor.setSelection(codeSection.lastIndexOf("</ol>") - 1);
                    else
                        codeEditor.setSelection(codeSection.lastIndexOf("ol{\r\n}"));
                    break;
                case R.id.submit_Button:
                    Intent webIntent = new Intent(getApplicationContext(), browserActivity.class);
                    codeSection = codeEditor.getText().toString();
                    webIntent.putExtra("textData", codeSection);
                    startActivity(webIntent);
                    break;
                case R.id.saveButton:
                    SaveFile();
                    break;
            }

        }};

    //activates buttons
    private void buttonInitializer() {
        htmlButton = findViewById(R.id.htmlActionButton);
        paragraphButton = findViewById(R.id.paragraphActionButton);
        divButton = findViewById(R.id.divActionButton);
        headButton = findViewById(R.id.headActionButton);
        bodyButton = findViewById(R.id.bodyActionButton);
        breakButton = findViewById(R.id.breakActionButton);
        codeEditor = findViewById(R.id.code_block_EditText);
        submitButton = findViewById(R.id.submit_Button);
        asideButton = findViewById(R.id.asideActionButton);
        asideButton.setOnClickListener(textTagClicker);
        inputButton = findViewById(R.id.inputActionButton);
        inputButton.setOnClickListener(textTagClicker);
        labelButton = findViewById(R.id.labelActionButton);
        labelButton.setOnClickListener(textTagClicker);
        htmlButton.setOnClickListener(textTagClicker);
        paragraphButton.setOnClickListener(textTagClicker);
        divButton.setOnClickListener(textTagClicker);
        headButton.setOnClickListener(textTagClicker);
        breakButton.setOnClickListener(textTagClicker);
        bodyButton.setOnClickListener(textTagClicker);
        submitButton.setOnClickListener(textTagClicker);
        buttonButton = findViewById(R.id.buttonActionButton);
        buttonButton.setOnClickListener(textTagClicker);
        h1Button = findViewById(R.id.h1ActionButton);
        h1Button.setOnClickListener(textTagClicker);
        h2Button = findViewById(R.id.h2ActionButton);
        h2Button.setOnClickListener(textTagClicker);
        h3Button = findViewById(R.id.h3ActionButton);
        h3Button.setOnClickListener(textTagClicker);
        h4Button = findViewById(R.id.h4ActionButton);
        h4Button.setOnClickListener(textTagClicker);
        h5Button = findViewById(R.id.h5ActionButton);
        h5Button.setOnClickListener(textTagClicker);
        h6Button = findViewById(R.id.h6ActionButton);
        h6Button.setOnClickListener(textTagClicker);
        headerButton = findViewById(R.id.headerActionButton);
        headerButton.setOnClickListener(textTagClicker);
        scriptButton = findViewById(R.id.scriptActionButton);
        scriptButton.setOnClickListener(textTagClicker);
        footerButton = findViewById(R.id.footerActionButton);
        footerButton.setOnClickListener(textTagClicker);
        mainButton = findViewById(R.id.mainActionButton);
        mainButton.setOnClickListener(textTagClicker);
        formButton = findViewById(R.id.formActionButton);
        formButton.setOnClickListener(textTagClicker);
        tableButton = findViewById(R.id.tableActionButton);
        tableButton.setOnClickListener(textTagClicker);
        tableDetlButton = findViewById(R.id.tableDetlActionButton);
        tableDetlButton.setOnClickListener(textTagClicker);
        tableRowButton = findViewById(R.id.tableRowActionButton);
        tableRowButton.setOnClickListener(textTagClicker);
        tableFooterButton = findViewById(R.id.tableFootActionButton);
        tableFooterButton.setOnClickListener(textTagClicker);
        quoteButton = findViewById(R.id.quoteActionButton);
        quoteButton.setOnClickListener(textTagClicker);
        strikerButton = findViewById(R.id.strikerActionButton);
        strikerButton.setOnClickListener(textTagClicker);
        styleButton = findViewById(R.id.styleActionButton);
        styleButton.setOnClickListener(textTagClicker);
        listButton = findViewById(R.id.listActionButton);
        listButton.setOnClickListener(textTagClicker);
        unListButton = findViewById(R.id.unorderedListActionButton);
        unListButton.setOnClickListener(textTagClicker);
        orListButton = findViewById(R.id.orderedListActionButton);
        orListButton.setOnClickListener(textTagClicker);
        linkButton = findViewById(R.id.linkActionButton);
        linkButton.setOnClickListener(textTagClicker);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(textTagClicker);

    }

    //Save file method
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void SaveFile() {
        String saveFile;
        if (activity_id == 1)
            saveFile = projectName + "." + fileType;
        else
            saveFile = projectName;
        codeSection = codeEditor.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        if (saveFile.contains("css"))
            intent.setType("text/css");
        else
            intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_TITLE, saveFile);
        startActivityForResult(intent, SAVE_REQUEST_CODE);

    }

    //Handles Save activity
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);
        Uri currentUri = null;

        if (requestCode == SAVE_REQUEST_CODE) {

            if (resultData != null) {
                currentUri = resultData.getData();
                writeFileContent(currentUri);
            }
        }
    }

    //Writes code to file
    private void writeFileContent(Uri uri)
    {
        try{
            ParcelFileDescriptor pfd =
                    this.getContentResolver().
                            openFileDescriptor(uri, "w");

            FileOutputStream fileOutputStream =
                    new FileOutputStream(pfd.getFileDescriptor());

            String textContent =
                    codeEditor.getText().toString();

            fileOutputStream.write(textContent.getBytes());

            fileOutputStream.close();
            pfd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

