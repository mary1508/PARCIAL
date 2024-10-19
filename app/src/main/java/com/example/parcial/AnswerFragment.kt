package com.example.parcial

class AnswerFragment : Fragment() {
    private var isCorrect: Boolean = false
    private var questionIndex: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCorrect = arguments?.getBoolean(ARG_IS_CORRECT) ?: false
        questionIndex = arguments?.getInt(ARG_QUESTION_INDEX) ?: -1

        val view = inflater.inflate(R.layout.fragment_answer, container, false)
        val textViewFeedback: TextView = view.findViewById(R.id.textViewFeedback)
        textViewFeedback.text = if (isCorrect) {
            "¡Correcto!"
        } else {
            "Incorrecto. La respuesta correcta era: ${loadQuestions(requireContext())[questionIndex].options[loadQuestions(requireContext())[questionIndex].correctAnswerIndex]}"
        }

        val btnNext: Button = view.findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            if (questionIndex < loadQuestions(requireContext()).size - 1) {
                // Navegar al siguiente fragmento de preguntas
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, QuestionFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                // Si es la última pregunta, ir al ResultFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ResultFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }

    companion object {
        private const val ARG_IS_CORRECT = "isCorrect"
        private const val ARG_QUESTION_INDEX = "questionIndex"

        fun newInstance(isCorrect: Boolean, questionIndex: Int) = AnswerFragment().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_IS_CORRECT, isCorrect)
                putInt(ARG_QUESTION_INDEX, questionIndex)

            }
        }
    }
}
