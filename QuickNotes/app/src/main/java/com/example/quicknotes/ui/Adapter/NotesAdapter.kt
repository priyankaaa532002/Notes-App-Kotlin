package com.example.quicknotes.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknotes.Model.Notes
import com.example.quicknotes.R
import com.example.quicknotes.databinding.ItemNotesBinding
import com.example.quicknotes.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, val notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
                    //Contructor
    class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        holder.binding.notesTitle.text = notesList[position].title
        holder.binding.notesSubTitle.text = notesList[position].subTitle
        holder.binding.notesDate.text = notesList[position].date

        if(notesList[position].priority == "1"){
            holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
        }else if(notesList[position].priority == "2"){
            holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
        }else{
            holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(notesList[position])
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}