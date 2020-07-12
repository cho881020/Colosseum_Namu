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
import kr.co.namu.colosseum.datas.Reply
import kr.co.namu.colosseum.datas.Topic

class ReplyAdapter(val mContext:Context, val resId:Int, val mList:ArrayList<Reply>) : ArrayAdapter<Reply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

//    한줄씩 직접 그려주는 함수

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.reply_list_item, null)
        }
        val row = tempRow!!

        return row
    }

}