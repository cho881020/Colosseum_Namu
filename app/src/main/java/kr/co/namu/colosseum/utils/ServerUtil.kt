package kr.co.namu.colosseum.utils

import android.content.Context
import org.json.JSONObject

class ServerUtil {

//    액티비티에서 서버의 응답을 받았을때 처리할 코드를 담아두는 도구.
    interface JsonResponseHandler {
        fun onResponse(json : JSONObject)
    }

    companion object {

//        서버 접근 주소 담는 변수
        private val BASE_URL = "http://15.165.177.142"

//        로그인 요청을 해주는 함수
        fun postRequestLogin(context: Context, handler:JsonResponseHandler?) {

        }

    }

}