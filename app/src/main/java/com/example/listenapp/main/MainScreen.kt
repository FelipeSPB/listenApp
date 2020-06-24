package com.example.listenapp.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.listenapp.R
import com.example.listenapp.custom.ConfirmationDialog
import com.example.listenapp.fragment.MusicFragment
import com.example.listenapp.fragment.NewsFragment
import com.example.listenapp.fragment.PlaylistsFragment
import com.example.listenapp.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreen : AppCompatActivity() {
    val fNews: NewsFragment = NewsFragment.newInstance(Bundle())
    val fMusic = MusicFragment.newInstance(Bundle())
    val fPlay = PlaylistsFragment.newInstance(Bundle())

    //final Fragment fProfile = new SettingFragment();
    var manager = supportFragmentManager
    var botNav: BottomNavigationView? = null
    var layout: CoordinatorLayout? = null
    var context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        achaViews()
        setListener()
        fragLoad(fNews)
        //fragLoad(new NewsFragment(), "news");
    }

    private fun achaViews() {
        setContentView(R.layout.activity_main_screen)
        botNav = findViewById(R.id.bottom_nav)
        layout = findViewById(R.id.ms_msgbox)
    }

    private fun setListener() {
        botNav!!.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_news -> fragLoad(fNews)
                R.id.menu_music -> fragLoad(MusicFragment())
                R.id.menu_playlist -> fragLoad(fPlay)
                R.id.menu_profile -> fragLoad(ProfileFragment())
            }
            true
        }
    }

    private fun <T : Fragment> fragLoad(fragment: T) {
        val fragVoltou = fragBack(fragment.javaClass.name)
        if (!fragVoltou) {
            manager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(fragment.javaClass.name).commit()
        }
    }

    private fun fragBack(fragName: String): Boolean {
        return manager.popBackStackImmediate(fragName, 0)
    }

    override fun onBackPressed() {
        var selectedFragment: Fragment? = null
        for (fragment in manager.fragments) {
            if (fragment.isVisible) {
                selectedFragment = fragment
                break
            }
            return
        }
        if (selectedFragment is NewsFragment) {
            ConfirmationDialog(context, getString(R.string.act_end_title), getString(R.string.act_end_msg),
                    getString(R.string.Answer_yes), getString(R.string.Answer_no)).show()
        } else {
            fragLoad(fNews)
            botNav!!.selectedItemId = R.id.menu_news
        }
    }
}