package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender

private const val STATUS = "STATUS"
private const val QUESTION = "QUESTION"

class MainActivity : AppCompatActivity(), View.OnClickListener, TextView.OnEditorActionListener {
    val benderImage: ImageView by lazy { iv_bender }
    
    val textTxt: TextView by lazy { tv_text }
    val messageEt: EditText by lazy { et_message }
    val sendBtn: ImageView by lazy { iv_send }
    val benderObj = Bender()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        savedInstanceState?.let { bundle ->
            benderObj.status = Bender.Status.valueOf(bundle.getString(STATUS) ?: Bender.Status.NORMAL.name)
            benderObj.question = Bender.Question.valueOf(bundle.getString(QUESTION) ?: Bender.Question.NAME.name)
        }
    
        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        
        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
        messageEt.setOnEditorActionListener(this)
    }
    
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        
        outState?.putString(STATUS, benderObj.status.name)
        outState?.putString(QUESTION, benderObj.question.name)
    }
    
    override fun onClick(v: View?) {
    
        when (v?.id) {
            R.id.iv_send -> {
	            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
                messageEt.setText("")
                val (r, g, b) = color
                benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
                textTxt.text = phrase

            }
        }
    
    }
    
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return when (v?.id) {
            R.id.et_message -> {
                Log.d("MainActivity", "action = $actionId")
                if (EditorInfo.IME_ACTION_DONE == actionId) {
                    val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
                    messageEt.setText("")
                    val (r, g, b) = color
                    benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
                    textTxt.text = phrase
                    hideKeyboard()
                    true
                } else {
                    false
                }
            }
            else -> true
        }
    }
}
