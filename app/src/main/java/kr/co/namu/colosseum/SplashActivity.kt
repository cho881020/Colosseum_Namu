package kr.co.namu.colosseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kr.co.namu.colosseum.utils.ContextUtil

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        Handler().postDelayed({

            if (ContextUtil.getUserToken(mContext) == "") {
//                로그인을 안한 상태 => 로그인화면으로 이동하자

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

            }
            else {
//                토큰이 저장됨 => 로그인에 성공한 상태 => 메인으로 이동하자

                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)

            }

            finish()

        }, 2500)

    }

}