package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_reply.*
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class EditReplyActivity : BaseActivity() {

    var topicId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reply)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        postReplyBtn.setOnClickListener {

            val inputContent = contentEdt.text.toString()

            if (inputContent.length < 5) {
                Toast.makeText(mContext, "의견은 최소 5자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerUtil.postRequestReply(mContext, topicId, inputContent, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    runOnUiThread {
                        Toast.makeText(mContext, "의견 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                }

            })


        }

    }

    override fun setValues() {

        topicId = intent.getIntExtra("topicId", 0)

        mySideTxt.text = intent.getStringExtra("mySideTitle")

    }


}