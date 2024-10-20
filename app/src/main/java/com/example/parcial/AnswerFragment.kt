package com.example.parcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnswerFragment : Fragment() {

    private lateinit var resultTextView: TextView
    private lateinit var explanationTextView: TextView
    private lateinit var nextButton: Button

    private var isCorrectAnswer = false
    private var explanationTextResId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout de fragment_answer
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar las vistas
        resultTextView = view.findViewById(R.id.resultTextView)
        explanationTextView = view.findViewById(R.id.explanationTextView)
        nextButton = view.findViewById(R.id.nextButton)

        // Obtener los argumentos del bundle (para determinar si la respuesta fue correcta o no y la explicación)
        arguments?.let {
            isCorrectAnswer = it.getBoolean("isCorrectAnswer", false)
            explanationTextResId = it.getInt("explanationTextResId", 0)
        }

        // Mostrar si la respuesta fue correcta o incorrecta
        if (isCorrectAnswer) {
            resultTextView.text = getString(R.string.correct_text)
        } else {
            resultTextView.text = getString(R.string.incorrect_text)
        }

        // Mostrar la explicación de la respuesta
        explanationTextView.text = getString(explanationTextResId)

        // Manejar el clic en el botón "Siguiente"
        nextButton.setOnClickListener {
            // Aquí puedes navegar a la siguiente pregunta o al fragmento de resultados
        }
    }
}
