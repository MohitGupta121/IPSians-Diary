package com.mohit.ipsians_diary.ui.event.bvest

interface TaskListener {

    fun complete()
    fun onError(message:String)
}