package com.example.dora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CharacterFragment(private val characterImageRes: Int, private val description: String) : Fragment() {
    private lateinit var characterImageView: ImageView
    private lateinit var characterDescription: TextView
    private lateinit var selectButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)
        characterImageView = view.findViewById(R.id.characterImageView)
        characterDescription = view.findViewById(R.id.characterDescription)
        selectButton = view.findViewById(R.id.selectButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterImageView.setImageResource(characterImageRes)
        characterDescription.text = description

        selectButton.setOnClickListener {
        }
    }
}