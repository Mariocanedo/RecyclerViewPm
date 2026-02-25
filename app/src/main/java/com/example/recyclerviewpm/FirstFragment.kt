package com.example.recyclerviewpm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewpm.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    // 1. creamos una lista que representa los datos

    private val datalist : MutableList<String?> = ArrayList<String?>()
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Adapter declarado a nivel de clase (NO local)
    private lateinit var  adapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //1 Instanciar el adapter y le pasamos los datos con las palabras
        // CARGAR LOS DATOS
        setData()
        //val adapter = WordAdapter(datalist)

        // 2️⃣ IMPORTANTE: Inicializamos el adapter de la clase (sin "val")
       // adapter = WordAdapter(datalist)
       adapter= WordAdapter(datalist as MutableList<String?> as MutableList<String>){

           selectWord, position ->
           // Crear fragmento destino
           val secondFragment = SecondFragment()
           // Crear bundle y pasar la palabra y posición

           // captamos la selección de la palabra
           val bundle = Bundle()
           bundle.putString("selectWord", selectWord)
           bundle.putInt("position", position)
           secondFragment.arguments = bundle

           parentFragmentManager.beginTransaction()
               .replace(R.id.nav_host_fragment_content_main, secondFragment)
               .addToBackStack(null)
               .commit()



       }



        // para probar las palabras
        Log.d("Listado", setData().toString())

        //2 le pasamos el adapter al recyclerView

        binding.Rv.setAdapter(adapter)

        // paso 3 le indicamos al recyclerview como mostrar los datos

        binding.Rv.setLayoutManager(LinearLayoutManager(getContext()))
        binding.Rv.setHasFixedSize(true)


        // Configuramos el FAB para agregar nuevas palabras
         binding!!.fab.setOnClickListener {

             addNewWord()
         }

    }


    private fun setData(): MutableList<String?>{


        for ( i in  0..98){

            datalist.add("Palabra" +i)
        }
        return  datalist
    }


    // crea palabras por medio del Button fav
    private fun addNewWord(){

        val newIndex = datalist.size
        val newWord = "Palabra $newIndex"
        datalist.add(newWord)
        adapter.notifyItemInserted(newIndex)
        binding!!.Rv.scrollToPosition(newIndex)

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}