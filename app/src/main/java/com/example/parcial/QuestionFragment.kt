package com.example.parcial


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var nextButton: Button
    private var currentQuestionIndex = 0

    private val questions = listOf(
        R.string.question_1 to listOf(R.string.question_1_option_1, R.string.question_1_option_2, R.string.question_1_option_3, R.string.question_1_option_4),
        R.string.question_2 to listOf(R.string.question_2_option_1, R.string.question_2_option_2, R.string.question_2_option_3, R.string.question_2_option_4),
        // Añadir más preguntas aquí
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout de fragment_question
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        questionTextView = view.findViewById(R.id.questionTextView)
        radioGroup = view.findViewById(R.id.radioGroup)
        option1 = view.findViewById(R.id.option1)
        option2 = view.findViewById(R.id.option2)
        option3 = view.findViewById(R.id.option3)
        option4 = view.findViewById(R.id.option4)
        nextButton = view.findViewById(R.id.nextButton)

        // Mostrar la primera pregunta
        showQuestion()

        // Al hacer clic en el botón siguiente, avanzar a la siguiente pregunta
        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                showQuestion()
            } else {
                // Aquí podrías navegar al fragmento de resultados
            }
        }
    }

    private fun showQuestion() {
        // Obtener el recurso de la pregunta actual
        val (questionResId, optionsResIds) = questions[currentQuestionIndex]

        // Mostrar la pregunta
        questionTextView.text = getString(questionResId)

        // Mostrar las opciones
        option1.text = getString(optionsResIds[0])
        option2.text = getString(optionsResIds[1])
        option3.text = getString(optionsResIds[2])
        option4.text = getString(optionsResIds[3])

        // Limpiar la selección de respuestas anteriores
        radioGroup.clearCheck()
    }
}
