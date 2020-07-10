package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        회원가입 버튼 처리
        okBtn.setOnClickListener {

//            입력 항목들 받아오기
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()
            val inputNick = nickNameEdt.text.toString()

//            이 입력 항목값들을 서버에 전달해서 회원가입
            ServerUtil.putRequestSignUp(mContext, inputId, inputPw, inputNick, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }

            })

        }

    }

    override fun setValues() {

    }

}