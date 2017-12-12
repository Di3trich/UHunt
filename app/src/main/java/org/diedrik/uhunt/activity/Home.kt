package org.diedrik.uhunt.activity

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_base_uhunt.*
import kotlinx.android.synthetic.main.app_bar_base_uhunt.*
import kotlinx.android.synthetic.main.content_home.*
import org.diedrik.uhunt.R
import org.diedrik.uhunt.model.LiveEvent
import org.diedrik.uhunt.webservice.UHuntService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class Home : BaseUHuntActivity() {

    var call: Call<List<LiveEvent>>? = null
    var lastCall: Call<List<LiveEvent>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.content_home, frameLayout)
        title = getString(R.string.app_name)
    }

    override fun onPostResume() {
        super.onPostResume()
        initLiveEventPolling()
    }

    override fun onPause() {
        super.onPause()
        if (call != null && lastCall!=null) {
            Log.e("Pause", "...")
            call!!.cancel()
            lastCall!!.cancel()
        }
    }

    fun initLiveEventPolling() {
        val service = UHuntService.instance
        var lastId: Long = 0
        call = service.eventPoll(lastId)
        call!!.enqueue(object : Callback<List<LiveEvent>> {
            override fun onResponse(call: Call<List<LiveEvent>>, response: Response<List<LiveEvent>>) {
                if (response.isSuccessful()) {
                    val lista: List<LiveEvent>? = response.body()
                    for (event in lista!!) {
                        lastId = event.id
                    }
                    mensajeSaludo.text = lastId.toString()
                    lastCall = service.eventPoll(lastId)
                    lastCall!!.enqueue(this)
                    Log.e("call", call.toString())
                } else {
                    Log.e("Error", response.message())
                    Log.e("Error", call.request().url().toString())
                }
            }

            override fun onFailure(call: Call<List<LiveEvent>>, t: Throwable) {
                if (t is SocketTimeoutException) {
                    Log.i("INFO--0", "timeout poll")
                    lastCall = service.eventPoll(lastId)
                    lastCall!!.enqueue(this)
                    Log.e("call", call.toString())
                } else {
                    Log.e("Error--1", t.message)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(0).isChecked = true
    }
}
