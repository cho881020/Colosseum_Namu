package kr.co.namu.colosseum.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    //    액티비티에서 서버의 응답을 받았을때 처리할 코드를 담아두는 도구.
    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {

        //        서버 접근 주소 담는 변수
        private val BASE_URL = "http://15.165.177.142"

//        진행중인 토론 주제 목록 가져오기

        fun getRequestMainInfo(context: Context, handler: JsonResponseHandler?) {

            //            서버 통신 담당 변수
            val client = OkHttpClient()

//            주소를 설정하는 기능
            val myUrl = "${BASE_URL}/v2/main_info".toHttpUrlOrNull()!!.newBuilder()

//            GET 방식 요청 : 주소에 우리가 보내줄 정보들을 이어 적어야함. (GET/DELETE  Vs. POST/PUT/PATCH 는 사용방법이 다르다)
//            myUrl.addEncodedQueryParameter("need_replies", false.toString())

//            모든 데이터가 주소에 첨부되면 주소 작성 마무리 => String으로 변환
            val urlStr = myUrl.build().toString()

//            최종 요청 정보 담긴 Request 만들기
            val request = Request.Builder()
                .url(urlStr)
                .get()
//              헤더에 데이터 첨부는 리퀘스트를 만들때 해야함
//              헤더 데이터 : (ContextUtil에 저장해둔) 토큰값 첨부
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

//            실제 서버에 요청 날리기
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                    실제 서버 응답이 돌아왔을때 실행됨.
//                    응답 내용을 저장
                    val bodyStr = response.body?.string()

//                    이 내용으로 Json객체 생성

                    val json = JSONObject(bodyStr)

                    Log.d("서버 응답 내용", json.toString())

//                    hanlder 변수에 응답처리 코드가 들어있다면 실행
                    handler?.onResponse(json)

                }

            })


        }


//        임시 작업 : 내 정보를 가져오는 기능.

        fun getRequestMyInfo(context: Context, handler: JsonResponseHandler?) {

            //            서버 통신 담당 변수
            val client = OkHttpClient()

//            주소를 설정하는 기능
            val myUrl = "${BASE_URL}/user_info".toHttpUrlOrNull()!!.newBuilder()

//            GET 방식 요청 : 주소에 우리가 보내줄 정보들을 이어 적어야함. (GET/DELETE  Vs. POST/PUT/PATCH 는 사용방법이 다르다)
            myUrl.addEncodedQueryParameter("need_replies", false.toString())

//            모든 데이터가 주소에 첨부되면 주소 작성 마무리 => String으로 변환
            val urlStr = myUrl.build().toString()

//            최종 요청 정보 담긴 Request 만들기
            val request = Request.Builder()
                .url(urlStr)
                .get()
//              헤더에 데이터 첨부는 리퀘스트를 만들때 해야함
//              헤더 데이터 : (ContextUtil에 저장해둔) 토큰값 첨부
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

//            실제 서버에 요청 날리기
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                    실제 서버 응답이 돌아왔을때 실행됨.
//                    응답 내용을 저장
                    val bodyStr = response.body?.string()

//                    이 내용으로 Json객체 생성

                    val json = JSONObject(bodyStr)

                    Log.d("서버 응답 내용", json.toString())

//                    hanlder 변수에 응답처리 코드가 들어있다면 실행
                    handler?.onResponse(json)

                }

            })


       }

//        로그인 요청을 해주는 함수 => 화면에서 입력한 아이디/비번 받아야함.

        fun postRequestLogin(
            context: Context,
            id: String,
            pw: String,
            handler: JsonResponseHandler?
        ) {

//            서버 통신 담당 변수
            val client = OkHttpClient()

//            어느 주소로 통신할지
            val urlStr = "${BASE_URL}/user"

//            서버에 들고갈 짐을 FormData에 담자 (POST / PUT / PATCH에서 이 방식)
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

//            요청 정보를 종합하는 변수
            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .build()

//            실제 API호출
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                    실제 서버 응답이 돌아왔을때 실행됨.
//                    응답 내용을 저장
                    val bodyStr = response.body?.string()

//                    이 내용으로 Json객체 생성

                    val json = JSONObject(bodyStr)

                    Log.d("서버 응답 내용", json.toString())

//                    hanlder 변수에 응답처리 코드가 들어있다면 실행
                    handler?.onResponse(json)

                }

            })

        }

//        회원가입 요청을 해주는 함수 => 아이디 / 비번 / 닉네임을 받자.

        fun putRequestSignUp(
            context: Context,
            id: String,
            pw: String,
            nick: String,
            handler: JsonResponseHandler?
        ) {

//            서버 통신 담당 변수
            val client = OkHttpClient()

//            어느 주소로 통신할지
            val urlStr = "${BASE_URL}/user"

//            서버에 들고갈 짐을 FormData에 담자 (POST / PUT / PATCH에서 이 방식)
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .add("nick_name", nick)
                .build()

//            요청 정보를 종합하는 변수
            val request = Request.Builder()
                .url(urlStr)
                .put(formData)
                .build()

//            실제 API호출
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                    실제 서버 응답이 돌아왔을때 실행됨.
//                    응답 내용을 저장
                    val bodyStr = response.body?.string()

//                    이 내용으로 Json객체 생성

                    val json = JSONObject(bodyStr)

                    Log.d("서버 응답 내용", json.toString())

//                    hanlder 변수에 응답처리 코드가 들어있다면 실행
                    handler?.onResponse(json)

                }

            })

        }


    }

}