package com.example.parcial

class QuestionFragment : Fragment() {
    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        questions = loadQuestions(requireContext())
        progressBar = view.findViewById(R.id.progressBar)

        showQuestion(view)
        return view
    }

    private fun showQuestion(view: View) {
        val questionTextView: TextView = view.findViewById(R.id.textViewQuestion)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroupOptions)
        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)

        // Configurar la pregunta actual
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question

        // Limpiar las opciones anteriores
        radioGroup.removeAllViews()
        currentQuestion.options.forEach { option ->
            val radioButton = RadioButton(requireContext()).apply {
                text = option
            }
            radioGroup.addView(radioButton)
        }

        btnSubmit.setOnClickListener {
            val selectedOptionIndex = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.checkedRadioButtonId))
            val isCorrect = selectedOptionIndex == currentQuestion.correctAnswerIndex

            // Navegar al Fragmento de Respuesta
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AnswerFragment.newInstance(isCorrect, currentQuestionIndex))
                .addToBackStack(null)
                .commit()

            // Actualizar la barra de progreso
            progressBar.progress = ((currentQuestionIndex + 1) * 100) / questions.size
        }
    }
}
