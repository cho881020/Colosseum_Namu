package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getUserInfoFromServer()
    }

//    임시로 하는 작업. => 서버에서 내 정보를 받아와서 닉네임 뿌려주기

    fun getUserInfoFromServer() {
        ServerUtil.getRequestMyInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                runOnUiThread {

//                    사용자 닉네임을 받아서 => 텍스트뷰에 반영

                    val data = json.getJSONObject("data")
                    val user = data.getJSONObject("user")
                    val nickName = user.getString("nick_name")

                    userNickTxt.text = nickName

                }

            }

        })
    }

}