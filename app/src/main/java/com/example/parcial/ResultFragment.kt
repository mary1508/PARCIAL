package com.example.parcial

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        // Aquí puedes calcular la puntuación total y mostrarla
        val textViewResult: TextView = view.findViewById(R.id.textViewResult)
        textViewResult.text = "Tu puntuación total es: ..."

        val btnRetry: Button = view.findViewById(R.id.btnRetry)
        btnRetry.setOnClickListener {
            // Reiniciar el juego volviendo al WelcomeFragment
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, WelcomeFragment())
                .commit()
        }

        return view
    }
}
