package com.example.parcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val btnStart: Button = view.findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            view.findViewById<Button>(R.id.btnStart).setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_questionFragment)
            }
        }
        return view
    }
}
