package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_reply_detail.*

class ViewReplyDetailActivity : BaseActivity() {

//    몇번 의견에 대한 조회인지
    var mReplyId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

    }

    override fun setValues() {

//        몇번 댓글을 보러온건지 저장
        mReplyId = intent.getIntExtra("replyId", 0)

//        화면에 댓글 데이터 표시
        writerNickNameTxt.text = intent.getStringExtra("writerNick")
        sideTxt.text = "(${intent.getStringExtra("side")})"
        contentTxt.text = intent.getStringExtra("content")


    }

}