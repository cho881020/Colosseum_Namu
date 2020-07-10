package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
//            입력한 아이디 / 비번 받아오기
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            서버에 로그인 요청 시도

        }

    }

    override fun setValues() {

    }


}