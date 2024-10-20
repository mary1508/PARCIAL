package com.example.parcial

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AnswerFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultTextView: TextView = view.findViewById(R.id.resultTextView)
        val explanationTextView: TextView = view.findViewById(R.id.explanationTextView)

        arguments?.let { args ->
            // Cambia los nombres de los parámetros a los que definiste en el nav_graph
            val isCorrectAnswer = args.getBoolean("isCorrect", false) // Cambiado
            val explanationTextResId = args.getInt("explanationId", 0) // Cambiado

            // Verifica si el valor de isCorrectAnswer es correcto
            resultTextView.text = if (isCorrectAnswer) {
                getString(R.string.correct_text)
            } else {
                getString(R.string.incorrect_text)
            }

            // Solo asigna la explicación si hay un recurso válido
            if (explanationTextResId != 0) {
                explanationTextView.text = getString(explanationTextResId)
            }
        }

        // Temporizador para regresar al fragmento anterior
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Puedes actualizar un TextView con el tiempo restante si quieres
            }

            override fun onFinish() {
                findNavController().popBackStack()
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null) // Evitar fugas de memoria
    }
}
