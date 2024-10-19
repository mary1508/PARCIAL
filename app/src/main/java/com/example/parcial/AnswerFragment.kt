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
        val textViewExplanation: TextView = view.findViewById(R.id.textViewExplanation)

        // Mostrar la retroalimentación sobre la respuesta
        textViewFeedback.text = if (isCorrect) {
            getString(R.string.correct_text)
        } else {
            getString(R.string.incorrect_text)
        }

        // Obtener la explicación de la respuesta
        val explanation = getExplanationForQuestion(requireContext(), questionIndex)
        textViewExplanation.text = explanation

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

    private fun getExplanationForQuestion(context: Context, index: Int): String {
        return when (index) {
            0 -> context.getString(R.string.question_1_explanation)
            1 -> context.getString(R.string.question_2_explanation)
            // Agregar las demás explicaciones aquí
            else -> "No hay explicación disponible."
        }
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
