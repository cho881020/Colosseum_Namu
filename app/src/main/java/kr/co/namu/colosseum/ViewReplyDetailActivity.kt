package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_reply_detail.*
import kr.co.namu.colosseum.adapters.ReplyAdapter
import kr.co.namu.colosseum.datas.Reply
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

//    몇번 의견에 대한 조회인지
    var mReplyId = 0

//    답글 목록을 저장할 배열
    val mReReplyList = ArrayList<Reply>()

    lateinit var mAdapter : ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        postBtn.setOnClickListener {
//            답글을 등록하고 => 입력 내용 비워주기

            val inputContent = contentEdt.text.toString()

            if (inputContent.length < 5) {
                Toast.makeText(mContext, "답글도 최소 5자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerUtil.postRequestReReply(mContext, mReplyId, inputContent, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    다시 답글 목록을 서버에서 불러오자
                    getTopicReplyFromServer()

                    runOnUiThread {
//                        입력한 내용 비워주기
                        contentEdt.setText("")
                    }

                }

            })

        }

    }

    override fun setValues() {

//        몇번 댓글을 보러온건지 저장
        mReplyId = intent.getIntExtra("replyId", 0)

//        화면에 댓글 데이터 표시
        writerNickNameTxt.text = intent.getStringExtra("writerNick")
        sideTxt.text = "(${intent.getStringExtra("side")})"
        contentTxt.text = intent.getStringExtra("content")

//        어댑터 생성 / 리스트뷰 연결
        mAdapter = ReplyAdapter(mContext, R.layout.reply_list_item, mReReplyList)
        reReplyListView.adapter = mAdapter

    }

    override fun onResume() {
        super.onResume()

        getTopicReplyFromServer()
    }

    fun getTopicReplyFromServer() {

        ServerUtil.getRequestReplyDetail(mContext, mReplyId, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")

                val reply = data.getJSONObject("reply")

//                새로 담아주기 전에 목록 내부 비워주기
                mReReplyList.clear()

//                답글 목록을 받아서 리스트뷰에 반영
                val replies = reply.getJSONArray("replies")

                for (i in 0..replies.length()-1) {
                    val replyJson = replies.getJSONObject(i)

                    val reply = Reply.getReplyFromJson(replyJson)

                    mReReplyList.add(reply)

                }

                runOnUiThread {
//                     리스트뷰 내용 새로고침
                    mAdapter.notifyDataSetChanged()
                }

            }

        })

    }

}