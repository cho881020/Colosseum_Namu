package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_reply.*

class EditReplyActivity : BaseActivity() {

    var topicId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reply)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        topicId = intent.getIntExtra("topicId", 0)

        mySideTxt.text = intent.getStringExtra("mySideTitle")

    }


}