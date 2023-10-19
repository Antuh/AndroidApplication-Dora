package com.example.dora

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

class Character : FragmentActivity() {
    private lateinit var viewPager: ViewPager
    private val characters = listOf(
        Pair(R.drawable.characterone, "Виктория, активно занимается спортом и является чемпионкой по лёгкой атлетике"),
        Pair(R.drawable.charactertwo, "Арсений, уже как 10 лет работает в IT компании"),
        Pair(R.drawable.characterthree, "Анна, работает дизайнером в хорошей компании")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        viewPager = findViewById(R.id.viewPager)
        val adapter = CharacterPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    private inner class CharacterPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = characters.size
        override fun getItem(position: Int): Fragment {
            val (imageRes, description) = characters[position]
            return CharacterFragment(imageRes, description)
        }
    }
}