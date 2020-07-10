package kr.co.namu.colosseum.utils

import android.content.Context

class ContextUtil {

    companion object {

//        메모장 파일 이름에 대응되는 개념을 변수로.
        private val prefName = "ColosseumPref"

//        저장할 항목 이름도 변수로. (사용자 토큰)
        private val USER_TOKEN = "USER_TOKEN"

//        토큰 저장 기능

        fun setUserToken(context: Context, token: String) {
//            파일이름을 갖고 메모장 열어주기
            val myPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            열린 메모장에 토큰값 기록
            myPref.edit().putString(USER_TOKEN, token).apply()
        }

//        저장된 토큰 조회 기능

        fun getUserToken(context: Context) : String {
//            메모장 열어주기
            val myPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            열린 메모장에 => USER_TOKEN 항목에 저장된 값을 리턴
//            null인 경우가 없도록 처리해서 리턴.
            return myPref.getString(USER_TOKEN, "")!!

        }

    }

}