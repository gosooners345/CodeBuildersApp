package com.codebuildersapp.release1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.io.FileWriter

class CodeActivity : AppCompatActivity() {
    var verifyText: String? = ""
    var htmlButton: Button? = null
    var paragraphButton: Button? = null
    var divButton: Button? = null
    var headButton: Button? = null
    var bodyButton: Button? = null
    var submitButton: Button? = null
    var breakButton: Button? = null
    var buttonButton: Button? = null
    var h1Button: Button? = null
    var h2Button: Button? = null
    var h3Button: Button? = null
    var h4Button: Button? = null
    var h5Button: Button? = null
    var h6Button: Button? = null
    var linkButton: Button? = null
    var tableButton: Button? = null
    var tableRowButton: Button? = null
    var tableDetlButton: Button? = null
    var tableFooterButton: Button? = null
    var labelButton: Button? = null
    var asideButton: Button? = null
    var inputButton: Button? = null
    var linkRefButton: Button? = null
    var mainButton: Button? = null
    var headerButton: Button? = null
    var footerButton: Button? = null
    var quoteButton: Button? = null
    var formButton: Button? = null
    var scriptButton: Button? = null
    var styleButton: Button? = null
    var strikerButton: Button? = null
    var listButton: Button? = null
    var unListButton: Button? = null
    var orListButton: Button? = null
    var saveButton: Button? = null
    var switchon = 0
    var codeEditor: TextInputEditText? = null
    var codeSection: String? = null
    var projectName = ""
    var fileType = ""
    var activity_id = 0
    var fileNameView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)
        fileNameView = findViewById(R.id.page_title_TV)
        buttonInitializer()
        activity_id = intent.getIntExtra("INTENT_ID", -1)
        when (activity_id) {
            1 -> {
                fileType = intent.getStringExtra("DOCUMENTTYPE")
                codeSection = if (fileType == "html") DOC_HEADER_HTML else ""
            }
            2 -> codeSection = if (intent.getStringExtra("FILEDATA") == "") DOC_HEADER_HTML else intent.getStringExtra("FILEDATA")
        }
        verifyText = codeSection
        projectName = intent.getStringExtra("PROJECT")
        fileNameView!!.text = (projectName)
        codeEditor!!.setText(codeSection)
        codeEditor!!.setSelection(codeSection!!.length)
        codeEditor!!.performClick()
    }

    //This activates all of the buttons on screen when the application loads
    @SuppressLint("NewApi")
    var textTagClicker = View.OnClickListener { view ->
        val selectionInt = codeSection!!.length
        val selector = codeEditor!!.selectionStart
        val selectionEnd = codeEditor!!.selectionEnd
        var stylesheet = false
        var javaScript = false
        var htmlFile = false
        if (fileType == "css" || projectName.contains("css")) {
            stylesheet = true
            javaScript = false
            htmlFile = false
        } else if (fileType.contains("html") || projectName.contains("html")) {
            stylesheet = false
            htmlFile = true
            javaScript = false
        }
        when (view.id) {
            R.id.headActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("head{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(selector, HEAD_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.indexOf("</head>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("head{\r\n}"))
            }
            R.id.htmlActionButton -> {
                codeSection = codeEditor!!.text.toString()
                if (stylesheet) codeSection = codeEditor!!.text!!.append("html{\r\n}").toString() else if (htmlFile) if (switchon == 0) {
                    codeSection = codeEditor!!.text!!.append("<html>").toString()
                } else if (switchon == 1) {
                    codeSection = codeEditor!!.text!!.append("</html>").toString()
                }
                codeEditor!!.setText(codeSection)
                if (htmlFile) {
                    if (switchon == 0) codeEditor!!.setSelection(codeSection!!.indexOf("<html>") + 6) else if (switchon == 1) codeEditor!!.setSelection(codeSection!!.indexOf("</html>") - 1)
                    switchon++
                } else codeEditor!!.setSelection(codeSection!!.lastIndexOf("html{\r\n}"))
            }
            R.id.paragraphActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("p{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, PARAGRAPH_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</p>")) else if (stylesheet) codeEditor!!.setSelection(codeSection!!.lastIndexOf("p{\r\n}"))
            }
            R.id.divActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("div{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(selectionInt, DIV_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</div>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("div{\r\n}"))
            }
            R.id.asideActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("aside{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(selectionInt, ASIDE_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</aside>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("aside{\r\n}"))
            }
            R.id.labelActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("label{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(selectionInt, LABEL_ACTIONSTRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("<label>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("label{\r\n}"))
            }
            R.id.inputActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("input{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(selector, INPUT_ACTIONSTRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</input>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("input{\r\n}"))
            }
            R.id.bodyActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("body{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(selector, BODY_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.indexOf("</body>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("body{\r\n}"))
            }
            R.id.breakActionButton -> {
                codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, BREAK_STRING).toString()
                codeEditor!!.setText(codeSection)
                codeEditor!!.setSelection(codeSection!!.lastIndexOf("<br>"))
            }
            R.id.buttonActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("button{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, BUTTON_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</button>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("button{\r\n}"))
            }
            R.id.h1ActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("h1{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, H1_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</h1>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("h1{\r\n}"))
            }
            R.id.h2ActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("h2{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, H2_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</h2>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("h2{\r\n}"))
            }
            R.id.h3ActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("h3{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, H3_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</h3>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("h3{\r\n}"))
            }
            R.id.h4ActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("h4{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, H4_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</h4>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("h4{\r\n}"))
            }
            R.id.h5ActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("h5{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, H5_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</h5>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("h5{\r\n}"))
            }
            R.id.h6ActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("h5{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, H6_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</h6>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("h6{\r\n}"))
            }
            R.id.mainActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("main{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, MAIN_ACTIONSTRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</main>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("main{\r\n}"))
            }
            R.id.formActionButton -> {
                codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, FORM_STRING).toString()
                codeEditor!!.setText(codeSection)
                codeEditor!!.setSelection(codeSection!!.lastIndexOf("</form>"))
            }
            R.id.quoteActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("q{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, QUOTATION_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</q>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("q{\r\n}"))
            }
            R.id.headerActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("header{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, HEADER_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.indexOf("</header>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("header{\r\n}"))
            }
            R.id.footerActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("footer{\r\n}").toString() else if (htmlFile) codeEditor!!.text!!.insert(codeEditor!!.selectionStart, FOOTER_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.indexOf("</footer>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("footer{\r\n}"))
            }
            R.id.strikerActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("footer{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, STRIKER_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</s>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("s{\r\n}"))
            }
            R.id.scriptActionButton -> {
                codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, SCRIPT_STRING).toString()
                codeEditor!!.setText(codeSection)
                codeEditor!!.setSelection(codeSection!!.lastIndexOf("</script>"))
            }
            R.id.styleActionButton -> {
                codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, STYLE_STRING).toString()
                codeEditor!!.setText(codeSection)
                codeEditor!!.setSelection(codeSection!!.lastIndexOf("</style>"))
            }
            R.id.linkActionButton -> {
                codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, "<link rel=\" \" type=\"\" href=\"\" >").toString()
                codeEditor!!.setText(codeSection)
                codeEditor!!.setSelection(codeSection!!.lastIndexOf("<link"))
            }
            R.id.aHrefActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("a{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, LINK_REF_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</a>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("a{\r\n}"))
            }
            R.id.tableActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("table{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, TABLE_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</table>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("table{\r\n}"))
            }
            R.id.tableDetlActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("td{\r\n}").toString() else if (htmlFile) codeEditor!!.text!!.insert(codeEditor!!.selectionStart, TABLE_DETAIL_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</td>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("td{\r\n}"))
            }
            R.id.tableRowActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("tr{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, TABLE_ROW_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</tr>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("tr{\r\n}"))
            }
            R.id.tableFootActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("tfoot{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, TABLE_FOOTER_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</tfoot>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("tfoot{\r\n}"))
            }
            R.id.listActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("li{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, LIST_ITEM_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</li>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("li{\r\n}"))
            }
            R.id.unorderedListActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("ul{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, UNORDERED_LIST_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</ul>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("ul{\r\n}"))
            }
            R.id.orderedListActionButton -> {
                if (stylesheet) codeSection = codeEditor!!.text!!.append("ol{\r\n}").toString() else if (htmlFile) codeSection = codeEditor!!.text!!.insert(codeEditor!!.selectionStart, ORDERED_LIST_STRING).toString()
                codeEditor!!.setText(codeSection)
                if (htmlFile) codeEditor!!.setSelection(codeSection!!.lastIndexOf("</ol>")) else codeEditor!!.setSelection(codeSection!!.lastIndexOf("ol{\r\n}"))
            }
            R.id.submit_Button -> {
                val webIntent = Intent(applicationContext, browserActivity::class.java)
                codeSection = codeEditor!!.text.toString()
                webIntent.putExtra("textData", codeSection)
                startActivity(webIntent)
            }
            R.id.saveButton -> saveFile()
        }
    }

    //activates buttons
    private fun buttonInitializer() {
        htmlButton = findViewById(R.id.htmlActionButton)
        paragraphButton = findViewById(R.id.paragraphActionButton)
        divButton = findViewById(R.id.divActionButton)
        headButton = findViewById(R.id.headActionButton)
        bodyButton = findViewById(R.id.bodyActionButton)
        breakButton = findViewById(R.id.breakActionButton)
        codeEditor = findViewById(R.id.code_block_EditText)
        submitButton = findViewById(R.id.submit_Button)
        asideButton = findViewById(R.id.asideActionButton)
        linkRefButton = findViewById(R.id.linkActionButton)
        linkRefButton!!.setOnClickListener(textTagClicker)
        asideButton!!.setOnClickListener(textTagClicker)
        inputButton = findViewById(R.id.inputActionButton)
        inputButton!!.setOnClickListener(textTagClicker)
        labelButton = findViewById(R.id.labelActionButton)
        labelButton!!.setOnClickListener(textTagClicker)
        htmlButton!!.setOnClickListener(textTagClicker)
        paragraphButton!!.setOnClickListener(textTagClicker)
        divButton!!.setOnClickListener(textTagClicker)
        headButton!!.setOnClickListener(textTagClicker)
        breakButton!!.setOnClickListener(textTagClicker)
        bodyButton!!.setOnClickListener(textTagClicker)
        submitButton!!.setOnClickListener(textTagClicker)
        buttonButton = findViewById(R.id.buttonActionButton)
        buttonButton!!.setOnClickListener(textTagClicker)
        h1Button = findViewById(R.id.h1ActionButton)
        h1Button!!.setOnClickListener(textTagClicker)
        h2Button = findViewById(R.id.h2ActionButton)
        h2Button!!.setOnClickListener(textTagClicker)
        h3Button = findViewById(R.id.h3ActionButton)
        h3Button!!.setOnClickListener(textTagClicker)
        h4Button = findViewById(R.id.h4ActionButton)
        h4Button!!.setOnClickListener(textTagClicker)
        h5Button = findViewById(R.id.h5ActionButton)
        h5Button!!.setOnClickListener(textTagClicker)
        h6Button = findViewById(R.id.h6ActionButton)
        h6Button!!.setOnClickListener(textTagClicker)
        headerButton = findViewById(R.id.headerActionButton)
        headerButton!!.setOnClickListener(textTagClicker)
        scriptButton = findViewById(R.id.scriptActionButton)
        scriptButton!!.setOnClickListener(textTagClicker)
        footerButton = findViewById(R.id.footerActionButton)
        footerButton!!.setOnClickListener(textTagClicker)
        mainButton = findViewById(R.id.mainActionButton)
        mainButton!!.setOnClickListener(textTagClicker)
        formButton = findViewById(R.id.formActionButton)
        formButton!!.setOnClickListener(textTagClicker)
        tableButton = findViewById(R.id.tableActionButton)
        tableButton!!.setOnClickListener(textTagClicker)
        tableDetlButton = findViewById(R.id.tableDetlActionButton)
        tableDetlButton!!.setOnClickListener(textTagClicker)
        tableRowButton = findViewById(R.id.tableRowActionButton)
        tableRowButton!!.setOnClickListener(textTagClicker)
        tableFooterButton = findViewById(R.id.tableFootActionButton)
        tableFooterButton!!.setOnClickListener(textTagClicker)
        quoteButton = findViewById(R.id.quoteActionButton)
        quoteButton!!.setOnClickListener(textTagClicker)
        strikerButton = findViewById(R.id.strikerActionButton)
        strikerButton!!.setOnClickListener(textTagClicker)
        styleButton = findViewById(R.id.styleActionButton)
        styleButton!!.setOnClickListener(textTagClicker)
        listButton = findViewById(R.id.listActionButton)
        listButton!!.setOnClickListener(textTagClicker)
        unListButton = findViewById(R.id.unorderedListActionButton)
        unListButton!!.setOnClickListener(textTagClicker)
        orListButton = findViewById(R.id.orderedListActionButton)
        orListButton!!.setOnClickListener(textTagClicker)
        linkButton = findViewById(R.id.aHrefActionButton)
        linkButton!!.setOnClickListener(textTagClicker)
        saveButton = findViewById(R.id.saveButton)
        saveButton!!.setOnClickListener(textTagClicker)
    }

    //Save file method
    @SuppressLint("NewApi")
    private fun saveFile() {
        val saveFile: String = if (activity_id == 1) "$projectName.$fileType" else projectName
        codeSection = codeEditor!!.text.toString()
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        if (saveFile.contains("css")) intent.type = "text/css" else intent.type = "text/html"
        if (activity_id == 2) {
            if (codeSection != verifyText) {
                intent.putExtra(Intent.EXTRA_TITLE, saveFile)
                startActivityForResult(intent, SAVE_REQUEST_CODE)
            } else {
                Toast.makeText(applicationContext, "No changes were made. File unchanged", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        } else {
            intent.putExtra(Intent.EXTRA_TITLE, saveFile)
            startActivityForResult(intent, SAVE_REQUEST_CODE)
        }
    }

    //Handles Save activity
    public override fun onActivityResult(requestCode: Int, resultCode: Int,
                                         resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        var currentUri: Uri? = null
        if (requestCode == SAVE_REQUEST_CODE) {
            if (resultData != null) {
                currentUri = resultData.data
                writeFileContent(currentUri)
            }
        }
        onBackPressed()
    }

    //Writes code to file
    private fun writeFileContent(uri: Uri?) {
        try {
            val pfd = this.contentResolver.openFileDescriptor(uri!!, "w")
            val fileStream = FileWriter(pfd!!.fileDescriptor)
            val textContent = codeEditor!!.text.toString()
            fileStream.write(textContent)
            fileStream.flush()
            fileStream.close()
            pfd.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        private const val CREATE_NEW_CODE_FILE = 1
        private const val CSS_FILE = 3
        private const val DOC_HEADER_HTML = "<!DOCTYPE html>\r\n"
        private const val HTML_STRING = "<html>\r\n</html>"
        private const val HEAD_STRING = "<head> \r\n</head>"
        private const val PARAGRAPH_STRING = "<p> \r\n </p>"
        private const val BREAK_STRING = "<br>"
        private const val DIV_STRING = "<div> \r\n </div>"
        private const val BUTTON_STRING = "<button>  </button>"
        private const val LINK_REF_STRING = "<a href =\" \"> </a>"
        private const val BODY_STRING = "<body> \r\n </body>"
        private const val H1_STRING = "<h1> </h1>"
        private const val H2_STRING = "<h2>  </h2>"
        private const val H3_STRING = "<h3>  </h3>"
        private const val H4_STRING = "<h4> </h4>"
        private const val H5_STRING = "<h5> </h5>"
        private const val H6_STRING = "<h6> </h6>"
        private const val TABLE_STRING = "<table> \r\n </table>"
        private const val TABLE_FOOTER_STRING = "<tfoot> \r\n</tfoot>"
        private const val TABLE_DETAIL_STRING = "<td> </td>"
        private const val TABLE_ROW_STRING = "<tr> \r\n</tr>"
        private const val ORDERED_LIST_STRING = "<ol>\r\n </ol>"
        private const val LIST_ITEM_STRING = "<li> </li>"
        private const val UNORDERED_LIST_STRING = "<ul> \r\n </ul>"
        private const val QUOTATION_STRING = "<q> </q>"
        private const val MAIN_ACTIONSTRING = "<main> \r\n</main>"
        private const val HEADER_STRING = "<header> \r\n</header>"
        private const val FOOTER_STRING = "<footer>\r\n </footer>"
        private const val FORM_STRING = "<form> \r\n</form>"
        private const val SCRIPT_STRING = "<script> </script>"
        private const val STRIKER_STRING = "<s> </s>"
        private const val STYLE_STRING = "<style>\r\n</style>"
        private const val ASIDE_STRING = "<aside> \r\n</aside>"
        private const val INPUT_ACTIONSTRING = "<input>"
        private const val LABEL_ACTIONSTRING = "<label>"
        private const val SAVE_REQUEST_CODE = 33
    }
}