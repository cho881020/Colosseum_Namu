package kr.co.namu.colosseum.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""

    lateinit var user : User
    lateinit var side : TopicSide

    companion object {

//        JSONObject 넣으면 => Reply로 변환해주는 기능

        fun getReplyFromJson(json: JSONObject) : Reply {

            val r = Reply()

            r.id = json.getInt("id")
            r.content = json.getString("content")

//            작성자 정보 추출
            val userJson = json.getJSONObject("user")
//            의견의 작성자로 반영
            r.user = User.getUserFromJson(userJson)

//            이 의견의 선택 진영 정보 추출
            val sideJson = json.getJSONObject("selected_side")
            r.side = TopicSide.getTopicSideFromJson(sideJson)

            return r

        }


    }

}