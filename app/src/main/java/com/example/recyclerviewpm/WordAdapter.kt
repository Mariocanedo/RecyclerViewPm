package com.example.recyclerviewpm

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewpm.databinding.WordItemListBinding

/*****************4.- EXTIENDO LA CLASE WORDADATER de Recyclerview.Adapter CONTIENE LA CLASE INTERNA WORDVIEWHOLER */
// 4 -implementar los métos de adapter OnCreateViewHolder, OnBindViewHolder, getItemCount()
class WordAdapter
/***************** Paso 2.Representación de los datos */
    (private val mwordList: MutableList<String?> ): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {








    //6 ESTE METODO INICIALIZA LAS VISTAS LAS INFLA
    // EN EL CONSTRUCTOR RECIBE UN OBJETO LE PASAMOS BINDING
    // INSTANCIAMOS VIEWBINDING PARA RETONAR NUESTRO VIEWHOLDER CON ESA DEPENDENCIA

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        val mBinding = WordItemListBinding.inflate(LayoutInflater.from(parent.getContext()),
        parent, false)
        return WordViewHolder(mBinding)

    }





    // paso 7 ESTE  MÉTODO DIBUJA LAS VISTAS
    // VA POSICIONANDO CADA ELEMENTO DEL RV
    override fun onBindViewHolder(holder: WordViewHolder, position: Int
    ) {

        val elemento =mwordList.get(position)
        holder.textView.setText(elemento)

    }




    override fun getItemCount(): Int {

        return mwordList.size
    }


    /*****************1. Paso Crear clase interna que se llama ViewHolder */
    // INICIALIZAMOs TEXVIEW QUE ES DENTRO DEL XML  Y LLAMAMOS MBINDING QUE ES DE LA CLASE XML QUE CONTIENE TEXVIEW
    // NOS PEDIRA IMPLEMENTAR SU CONSTRUCTOR DE LA SUPER CLASE
    // CAMBIAMOS EL ELEMENTO ANTERIOR Y REFERENCIAMOS A LA CLASE BINDING QUE REPRESENTA NUESTRO LAYOUT WORD ITEM LIST


    inner class WordViewHolder(mBinding: WordItemListBinding) :
        RecyclerView.ViewHolder(mBinding.getRoot()) {

        val textView: TextView

        init {

            textView = mBinding.textView
        }
    }



}