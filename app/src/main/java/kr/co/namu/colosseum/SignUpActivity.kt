package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

                    runOnUiThread {

                        val code = json.getInt("code")

                        if (code == 200) {
                            Toast.makeText(mContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else {
//                            가입에 실패하는 이유는 여러가지. (이메일 중복, 닉네임 중복)
//                            서버가 알려주는 실패 사유를 토스트로 띄우자.

                            val failReason = json.getString("message")

                            Toast.makeText(mContext, failReason, Toast.LENGTH_SHORT).show()

                        }

                    }

                }

            })

        }

    }

    override fun setValues() {

    }

}