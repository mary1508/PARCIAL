package com.example.parcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        // Obtener los argumentos pasados
        arguments?.let { args ->
            val score = args.getInt("score", 0) // Obtén la puntuación

            // Muestra la puntuación total usando la cadena de strings.xml
            val textViewResult: TextView = view.findViewById(R.id.textViewResult)
            textViewResult.text = getString(R.string.score_text, score) // Usa la cadena con el marcador
        }

        view.findViewById<Button>(R.id.btnRetry).setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment)
        }

        return view
    }
}
