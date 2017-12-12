package org.diedrik.uhunt.model

import android.provider.Contacts

/**
 * Created by jquispe on 06/12/2017.
 */
class LiveEvent {
    var id: Long = 0
    var type: String = ""
    var msg: Event = Event()

    inner class Event {
        var sid: Long = 0
        var uid: Int = 0
        var pid: Int = 0
        var ver: Int = 0
        var lan: Int = 0
        var run: Int = 0
        var mem: Int = 0
        var rank: Int = 0
        var sbt: Long = 0
        var name: String = ""
        var uname: String = ""
    }

    fun getSubmissionId(): Long {
        return msg.sid
    }

    fun getUserId(): Int {
        return msg.uid
    }

    fun getProblemId(): Int {
        return msg.pid
    }
}