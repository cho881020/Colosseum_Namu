package kr.co.namu.colosseum.datas

import org.json.JSONObject

class Topic {

    var id = 0
    var title = ""
    var imageUrl = ""

    val sideList = ArrayList<TopicSide>()

    var mySide : TopicSide? = null

    companion object {

        fun getTopicFromJson(json: JSONObject) : Topic {
            val topic = Topic()

            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")

//            선택 진영 목록을 파싱해서 => sideList에 담아주기

            val sides = json.getJSONArray("sides")

            for (i in 0..sides.length()-1) {

                val sideJson = sides.getJSONObject(i)

                val topicSide = TopicSide.getTopicSideFromJson(sideJson)

                topic.sideList.add(topicSide)

            }

//            내 선택진영 정보가 있다면 등록
            if (!json.isNull("my_side")) {

                val mySideJson = json.getJSONObject("my_side")
                topic.mySide = TopicSide.getTopicSideFromJson(mySideJson)
            }

            return topic
        }

    }

}