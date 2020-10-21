package com.codebuildersapp.release1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class MainScreen : AppCompatActivity() {
    var newFileFab: ExtendedFloatingActionButton? = null
    var openFileFab: ExtendedFloatingActionButton? = null
    var filePath = ""
    var fileName = ""
    var fileType = ""
    var fileData = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        permissions[0] = Manifest.permission.READ_EXTERNAL_STORAGE
        permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Access Granted", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, permissions, 1)
            Toast.makeText(this, "Access Needs accepted", Toast.LENGTH_SHORT).show()
        }
        newFileFab = findViewById(R.id.new_file_button)
        newFileFab!!.setOnClickListener(newFabListener)
        openFileFab = findViewById(R.id.openFileButton)
        openFileFab!!.setOnClickListener(openFileListener)
    }


    private var openFileListener = View.OnClickListener {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/*"
        startActivityForResult(intent, OPEN_DOC_HTML_FILE)
    }

    //Loading a document
    public override fun onActivityResult(requestCode: Int, resultCode: Int,
                                         resultData: Intent?) {
        var resultData = resultData
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == OPEN_DOC_HTML_FILE
                && resultCode == RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            var uri: Uri? = null
            if (resultData != null) {
                uri = resultData.data
                try {
                    val chunks = uri!!.lastPathSegment!!.split("/").toTypedArray()
                    fileName = chunks[chunks.size - 1]
                    fileData = readTextFromUri(uri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            resultData = Intent(applicationContext, CodeActivity::class.java)
            resultData.putExtra("FILEDATA", fileData)
            resultData.putExtra("PROJECT", fileName)
            resultData.putExtra("DOCUMENTTYPE", fileType)
            resultData.putExtra("INTENT_ID", 2)
            startActivity(resultData)
        }
    }

    var newFabListener = View.OnClickListener {
        val INTENT_ID = 1
        val intent1 = Intent(applicationContext, CodeActivity::class.java)
        setContentView(R.layout.new_file_dialog)
        title = "New File"
        val projectTextBox = findViewById<EditText>(R.id.projectTextBox)
        val projectType = findViewById<Spinner>(R.id.projectFileType)
        projectType.onItemSelectedListener = newFileTypeListener
        val saveButton = findViewById<Button>(R.id.saveNewFileButton)
        val cancelButton = findViewById<Button>(R.id.cancelFileButton)
        saveButton.setOnClickListener {
            val fileName = projectTextBox.text.toString()
            intent1.putExtra("INTENT_ID", INTENT_ID)
            intent1.putExtra("PROJECT", fileName)
            intent1.putExtra("DOCUMENTTYPE", fileType)
            startActivity(intent1)
        }
        cancelButton.setOnClickListener { recreate() }
    }

    //
    var newFileTypeListener: OnItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
            fileType = String.format("%s", adapterView.selectedItem.toString())
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }

    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri?): String {
        var textBuilder = ""
        contentResolver.openInputStream(uri!!).use { inputStream ->
            BufferedReader(
                    InputStreamReader(Objects.requireNonNull(inputStream))).use { reader ->
                var line: String
                while (reader.readLine().also { line = it } != null) {
                    textBuilder += line
                }
            }
        }
        return textBuilder
    }

    companion object {
        private const val OPEN_DOC_HTML_FILE = 2
        var permissions = arrayOfNulls<String>(2)
    }
}