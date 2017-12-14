package org.diedrik.uhunt.model

import java.util.*

class LiveEvent {
    var id: Long = 0
    var type: String = ""
    var msg: Event = Event()

    companion object {
        val verdictMap: HashMap<Int, String> = hashMapOf(
                10 to "Submission error",
                15 to "Can't be judged",
                20 to "In queue",
                30 to "Compile error",
                35 to "Restricted function",
                40 to "Runtime error",
                45 to "Output limit",
                50 to "Time limit",
                60 to "Memory limit",
                70 to "Wrong answer",
                80 to "Presentation error",
                90 to "Accepted"
        )
        val languageMap: HashMap<Int, String> = hashMapOf(
                1 to "ANSI C",
                2 to "Java",
                3 to "C++",
                4 to "Pascal",
                5 to "C++11",
                6 to "Python3"
        )
    }

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

    fun getVerdict(): String? {
        return verdictMap[msg.ver]
    }

    fun getLanguage(): String? {
        return languageMap[msg.lan]
    }

    fun getRunTime(): Double {
        return msg.run.toDouble() / 1000
    }

    fun getMemory(): Int {
        return msg.mem
    }

    fun getRanking(): Int {
        return msg.rank
    }

    fun getTime(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = Date(msg.sbt)
        return calendar
    }

    fun getName(): String {
        return msg.name
    }

    fun getUserName(): String {
        return msg.uname
    }
}