package org.diedrik.uhunt.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_base_uhunt.*
import kotlinx.android.synthetic.main.app_bar_base_uhunt.*
import org.diedrik.uhunt.R

class Home : BaseUHuntActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.content_home, frame_layout)
        title = getString(R.string.app_name)
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(0).isChecked = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
