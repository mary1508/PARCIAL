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
import androidx.navigation.fragment.NavHostFragment

class QuestionFragment : Fragment() {

    private var questionIndex: Int = 0
    private var correctAnswerIndex: Int = 0

    private val questions = listOf(
        R.string.question_1,
        R.string.question_2,
        R.string.question_3,
        R.string.question_4,
        R.string.question_5,
        R.string.question_6,
        R.string.question_7,
        R.string.question_8,
        R.string.question_9,
        R.string.question_10
    )

    private val answers = listOf(
        listOf(R.string.question_1_option_1, R.string.question_1_option_2, R.string.question_1_option_3, R.string.question_1_option_4),
        listOf(R.string.question_2_option_1, R.string.question_2_option_2, R.string.question_2_option_3, R.string.question_2_option_4),
        listOf(R.string.question_3_option_1, R.string.question_3_option_2, R.string.question_3_option_3, R.string.question_3_option_4),
        listOf(R.string.question_4_option_1, R.string.question_4_option_2, R.string.question_4_option_3, R.string.question_4_option_4),
        listOf(R.string.question_5_option_1, R.string.question_5_option_2, R.string.question_5_option_3, R.string.question_5_option_4),
        listOf(R.string.question_6_option_1, R.string.question_6_option_2, R.string.question_6_option_3, R.string.question_6_option_4),
        listOf(R.string.question_7_option_1, R.string.question_7_option_2, R.string.question_7_option_3, R.string.question_7_option_4),
        listOf(R.string.question_8_option_1, R.string.question_8_option_2, R.string.question_8_option_3, R.string.question_8_option_4),
        listOf(R.string.question_9_option_1, R.string.question_9_option_2, R.string.question_9_option_3, R.string.question_9_option_4),
        listOf(R.string.question_10_option_1, R.string.question_10_option_2, R.string.question_10_option_3, R.string.question_10_option_4)
    )

    private val correctAnswers = listOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0) // Índices correctos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // Cargar primera pregunta
        loadQuestion(view)

        val nextButton = view.findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            if (checkAnswer(view)) {
                questionIndex++
                if (questionIndex < questions.size) {
                    loadQuestion(view)
                } else {
                    // Navegar al fragmento de resultados (implementación puede variar según tu app)
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_questionFragment_to_resultFragment)
                }
            }
        }

        return view
    }

    private fun loadQuestion(view: View) {
        // Configura la pregunta
        val questionText = view.findViewById<TextView>(R.id.questionTextView)
        questionText.text = getString(questions[questionIndex])

        // Configura las opciones de respuesta
        val options = answers[questionIndex]
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val option1 = view.findViewById<RadioButton>(R.id.option1)
        val option2 = view.findViewById<RadioButton>(R.id.option2)
        val option3 = view.findViewById<RadioButton>(R.id.option3)
        val option4 = view.findViewById<RadioButton>(R.id.option4)

        option1.text = getString(options[0])
        option2.text = getString(options[1])
        option3.text = getString(options[2])
        option4.text = getString(options[3])

        radioGroup.clearCheck()

        // Actualiza el índice de la respuesta correcta
        correctAnswerIndex = correctAnswers[questionIndex]
    }

    private fun checkAnswer(view: View): Boolean {
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val selectedOptionIndex = when (radioGroup.checkedRadioButtonId) {
            R.id.option1 -> 0
            R.id.option2 -> 1
            R.id.option3 -> 2
            R.id.option4 -> 3
            else -> -1
        }

        if (selectedOptionIndex == correctAnswerIndex) {
            // Respuesta correcta
            // Aquí podrías mostrar un mensaje o hacer algo
            return true
        } else {
            // Respuesta incorrecta
            // Aquí podrías mostrar un mensaje o hacer algo
            return false
        }
    }
}
