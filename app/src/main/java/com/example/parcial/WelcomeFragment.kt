package com.example.parcial

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val btnStart: Button = view.findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            // Navegar al Fragmento de Preguntas
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, QuestionFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
