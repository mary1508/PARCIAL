package com.example.parcial

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class QuestionFragment : Fragment() {

    private var currentQuestionIndex: Int = 0
    private var score: Int = 0
    private var isFirstQuestion: Boolean = true
    private var timer: CountDownTimer? = null
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

    // Aquí van los índices de respuesta correcta, ajusta según el orden de las respuestas
    private val correctAnswers = listOf(1, 1, 3, 1, 1, 3, 1, 1, 2, 1) // Cambia a 0-indexed

    private val explanations = listOf(
        R.string.explanation_question_1,
        R.string.explanation_question_2,
        R.string.explanation_question_3,
        R.string.explanation_question_4,
        R.string.explanation_question_5,
        R.string.explanation_question_6,
        R.string.explanation_question_7,
        R.string.explanation_question_8,
        R.string.explanation_question_9,
        R.string.explanation_question_10
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadQuestion(view)

        val nextButton = view.findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            checkAnswer(view)
        }
    }

    private fun loadQuestion(view: View) {
        val questionText = view.findViewById<TextView>(R.id.questionTextView)
        questionText.text = getString(questions[currentQuestionIndex])

        val options = answers[currentQuestionIndex]
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val option1 = view.findViewById<RadioButton>(R.id.option1)
        val option2 = view.findViewById<RadioButton>(R.id.option2)
        val option3 = view.findViewById<RadioButton>(R.id.option3)
        val option4 = view.findViewById<RadioButton>(R.id.option4)

        option1.text = getString(options[0])
        option2.text = getString(options[1])
        option3.text = getString(options[2])
        option4.text = getString(options[3])

        radioGroup.clearCheck() // Limpiar selecciones anteriores
        val timerTextView = view.findViewById<TextView>(R.id.timerTextView)

        // Iniciar el temporizador solo cuando la pregunta esté cargada
        timer?.cancel() // Cancelar cualquier temporizador anterior
        timer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Tiempo restante: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                navigateToAnswer(false) // Tiempo agotado, moverse a la respuesta como incorrecta
            }
        }.start()
    }

    private fun checkAnswer(view: View) {
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val selectedOptionIndex = when (radioGroup.checkedRadioButtonId) {
            R.id.option1 -> 0
            R.id.option2 -> 1
            R.id.option3 -> 2
            R.id.option4 -> 3
            else -> -1
        }

        // Solo proceder si se seleccionó una opción
        if (selectedOptionIndex != -1) {
            val isCorrect = selectedOptionIndex == correctAnswers[currentQuestionIndex]
            if (isCorrect) score++ // Incrementar la puntuación si es correcta

            // Cancelar el temporizador ya que se seleccionó una respuesta
            timer?.cancel()

            navigateToAnswer(isCorrect)
        }
    }

    private fun navigateToAnswer(isCorrect: Boolean) {
        val action = QuestionFragmentDirections.actionQuestionFragmentToAnswerFragment(
            isCorrect = isCorrect,  // Nombre de parámetro correcto
            explanationId = explanations[currentQuestionIndex],  // Nombre de parámetro correcto
            questionId = questions[currentQuestionIndex]  // ID de la pregunta
        )
        findNavController().navigate(action)
    }


    private fun moveToNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            view?.let { loadQuestion(it) }
        } else {
            navigateToResult()
        }
    }

    private fun navigateToResult() {
        val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(score, questions.size)
        findNavController().navigate(action)
    }


    override fun onResume() {
        super.onResume()
        if (isFirstQuestion) {
            isFirstQuestion = false
            loadQuestion(requireView())
        } else {
            moveToNextQuestion()
        }
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel() // Cancelar el temporizador si el usuario sale del fragmento
    }
}
