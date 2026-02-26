package com.example.recyclerviewpm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recyclerviewpm.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var word : String? = null
    private var position = -1


    // recibiendo parametros con bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            word = it.getString("selectWord")
            position = it.getInt("position",-1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding!!.textViewSelected.text = word
        binding.editTextWord.setText(word)


        // Botón para actualizar palabra

        binding!!.buttonUpdate.setOnClickListener {

            val newWord = binding!!.editTextWord.text.toString()
            if (position >= 0) // validacion
                {

            val bundle = Bundle()
                    bundle.putInt("position",position)
                    bundle.putString("newWord", newWord)
                    parentFragmentManager.setFragmentResult("updateWord",bundle)
                    //Sirve para volver al fragmento anterior, eliminando el fragment actual del Back Stack (pila de retroceso).
                    parentFragmentManager.popBackStack()
        }
    }


        // Botón para Eliminar


        binding!!.buttonUpdate.setOnClickListener {

            val newWord = binding!!.editTextWord.text.toString()
            if (position >= 0) // validacion
            {

                val bundle = Bundle()
                bundle.putInt("position",position)
                bundle.putString("newWord", newWord)
                parentFragmentManager.setFragmentResult("updateWord",bundle)
                //Sirve para volver al fragmento anterior, eliminando el fragment actual del Back Stack (pila de retroceso).
                parentFragmentManager.popBackStack()
            }
        }


        binding!!.buttonDelete.setOnClickListener {

            val newWord = binding!!.editTextWord.text.toString()
            if (position >= 0) // validacion
            {

                val bundle = Bundle()
                bundle.putInt("position",position)
                parentFragmentManager.setFragmentResult("deleteWord",bundle)
                //Sirve para volver al fragmento anterior, eliminando el fragment actual del Back Stack (pila de retroceso).
                parentFragmentManager.popBackStack()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}