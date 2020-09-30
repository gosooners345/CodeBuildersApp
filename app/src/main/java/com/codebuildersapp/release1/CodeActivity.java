package com.codebuildersapp.release1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.*;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class CodeActivity extends AppCompatActivity {
Button htmlButton, paragraphButton, divButton, headButton,bodyButton,submitButton,breakButton,buttonButton;
Button h1Button, h2Button, h3Button, h4Button, h5Button, h6Button,linkButton;
Button tableButton, tableRowButton, tableDetlButton, tableFooterButton;
Button labelButton, asideButton, inputButton;
Button mainButton, headerButton,footerButton, quoteButton, formButton, scriptButton,styleButton,strikerButton;
Button listButton, unListButton, orListButton;
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

TextInputEditText codeEditor;
String codeSection;
int activity_id;
TextView fileNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        fileNameView = findViewById(R.id.page_title_TV);
        buttonInitializer();




        codeSection = DOC_HEADER_HTML;
activity_id=getIntent().getIntExtra("activity_id",0);
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
   codeEditor.setText(codeSection);
    }
    Button.OnClickListener textTagClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int selectionInt =codeSection.length(),selector= codeEditor.getSelectionStart(),selectionEnd=codeEditor.getSelectionEnd();
          
            switch (view.getId())
            {
                case R.id.headActionButton:codeSection=codeEditor.getText().insert(selector, HEAD_STRING).toString();
                ;
                codeEditor.setText(codeSection);
                codeEditor.setSelection(codeSection.indexOf("</head>")-1);

                  break;
                case R.id.htmlActionButton:codeSection= codeEditor.getText().toString();
                if (codeSection.contains("<html>"))codeSection=codeSection.concat("\r\n"+"</html>");else if(!codeSection.contains("<html>")) codeSection=codeSection.concat("<html>");
                else if(codeSection.contains("<html>")&codeSection.contains("</html>")) codeSection.concat(HTML_STRING);
                codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("<html>"));break;

                case R.id.paragraphActionButton:
                  codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(), PARAGRAPH_STRING).toString();
                  codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</p>")-1);
                break;
                case  R.id.divActionButton:   codeSection=codeEditor.getText().insert(selectionInt, DIV_STRING).toString(); codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</div>")-1);break;
                case  R.id.asideActionButton:   codeSection=codeEditor.getText().insert(selectionInt,ASIDE_STRING).toString(); codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</aside>")-1);break;
                    case  R.id.labelActionButton:   codeSection=codeEditor.getText().insert(selectionInt,LABEL_ACTIONSTRING).toString(); codeEditor.setText(codeSection);
                codeEditor.setSelection(codeSection.lastIndexOf("<label>")-1);break;
                case  R.id.inputActionButton:   codeSection=codeEditor.getText().insert(selectionInt,INPUT_ACTIONSTRING).toString(); codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("</div>")-1);break;
                case R.id.bodyActionButton:codeSection=codeEditor.getText().toString();
                    codeSection=codeEditor.getText().insert(selectionInt,BODY_STRING).toString();
                    codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.indexOf("</body>")-1);break;
                case R.id.breakActionButton: codeSection=codeEditor.getText().insert(selectionInt,BREAK_STRING).toString(); codeEditor.setText(codeSection);
                    codeEditor.setSelection(codeSection.lastIndexOf("<br>"));break;
                case R.id.buttonActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),BUTTON_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</button>")-1);break;
                case R.id.h1ActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),H1_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</h1>")-1);break;
                case R.id.h2ActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),H2_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</h2>")-1);break;
                case R.id.h3ActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),H3_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</h3>")-1);break;
                case R.id.h4ActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),H4_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</h4>")-1);break;
                case R.id.h5ActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),H5_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</h5>")-1);break;
                case R.id.h6ActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),H6_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</h6>")-1);break;
                case R.id.mainActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),MAIN_ACTIONSTRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.indexOf("</main>")-1);break;
                case R.id.formActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),FORM_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</form>")-1);break;
                case R.id.quoteActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),QUOTATION_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</q>")-1);break;
                case R.id.headerActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),HEADER_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.indexOf("</header>")-1);break;
                case R.id.footerActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),FOOTER_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.indexOf("</footer>")-1);break;
                case R.id.strikerActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),STRIKER_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</s>")-1);break;
                case R.id.scriptActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),SCRIPT_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</script>")-1);break;
                case R.id.styleActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),STYLE_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</style>")-1);break;
                case R.id.linkActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),LINK_REF_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</a>")-1);break;
                case R.id.tableActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),TABLE_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</table>")-1);break;
                case R.id.tableDetlActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),TABLE_DETAIL_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</td>")-1);break;
                case R.id.tableRowActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),TABLE_ROW_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</tr>")-1);break;
                case R.id.tableFootActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),TABLE_FOOTER_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</tfoot>")-1);break;
                case R.id.listActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),LIST_ITEM_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</li>")-1);break;
                case R.id.unorderedListActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),UNORDERED_LIST_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</ul>")-1);break;
                case R.id.orderedListActionButton:codeSection=codeEditor.getText().insert(codeEditor.getSelectionStart(),ORDERED_LIST_STRING).toString();
                    codeEditor.setText(codeSection); codeEditor.setSelection(codeSection.lastIndexOf("</ol>")-1);break;
                case R.id.submit_Button:
                    Intent webIntent = new Intent(getApplicationContext(),browserActivity.class);
                    codeSection=codeEditor.getText().toString();
                    webIntent.putExtra("textData",codeSection);
                    startActivity(webIntent);
            }

        }};
      

    private void buttonInitializer() {
        htmlButton = findViewById(R.id.htmlActionButton);
        paragraphButton = findViewById(R.id.paragraphActionButton);
        divButton = findViewById(R.id.divActionButton);
        headButton = findViewById(R.id.headActionButton);
        bodyButton = findViewById(R.id.bodyActionButton);
        breakButton = findViewById(R.id.breakActionButton);
        codeEditor = findViewById(R.id.code_block_EditText);
        submitButton = findViewById(R.id.submit_Button);
asideButton=findViewById(R.id.asideActionButton);
asideButton.setOnClickListener(textTagClicker);
inputButton=findViewById(R.id.inputActionButton);
inputButton.setOnClickListener(textTagClicker);
labelButton=findViewById(R.id.labelActionButton);
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

    }


    private void SaveFile(){

    }
}