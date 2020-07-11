package kr.co.namu.colosseum.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.namu.colosseum.R
import kr.co.namu.colosseum.datas.Topic

class TopicAdapter(val mContext:Context, val resId:Int, val mList:ArrayList<Topic>) : ArrayAdapter<Topic>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

//    한줄씩 직접 그려주는 함수

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.topic_list_item, null)
        }
        val row = tempRow!!

        val topicImg = row.findViewById<ImageView>(R.id.topicImg)
        val titleTxt = row.findViewById<TextView>(R.id.titleTxt)

        val data = mList[position]

        titleTxt.text = data.title

//        인터넷 주소의 이미지를 => (Glide) 라이브러리로 다운받아서 => 이미지뷰에 적용

        Glide.with(mContext).load(data.imageUrl).into(topicImg)


        return row
    }

}