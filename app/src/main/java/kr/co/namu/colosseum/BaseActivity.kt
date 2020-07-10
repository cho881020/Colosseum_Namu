package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

//    this (어느 화면에서 쓰는지) 를 대입해야 될 상황에서 대신 사용해주는 변수
    val mContext = this

//    다른 화면들이 이 두개의 함수를 반드시 구현해야 하도록 강제.
    abstract fun setupEvents()
    abstract fun setValues()

}