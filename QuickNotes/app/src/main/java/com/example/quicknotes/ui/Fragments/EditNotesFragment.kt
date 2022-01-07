package com.example.quicknotes.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.quicknotes.Model.Notes
import com.example.quicknotes.R
import com.example.quicknotes.ViewModel.NotesViewModel
import com.example.quicknotes.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding:FragmentEditNotesBinding
    val viewModel : NotesViewModel by viewModels()

    var priority:String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)
        binding.etTitle.setText(oldNotes.data.title)
        binding.etSubtitle.setText(oldNotes.data.subTitle)
        binding.etNotes.setText(oldNotes.data.notes)

        if(oldNotes.data.priority == "1"){
            priority="1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }else if(oldNotes.data.priority == "2"){
            priority="2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }else{
            priority="3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pGreen.setOnClickListener{
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority="1"
        }
        binding.pYellow.setOnClickListener{
            binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority="2"
        }
        binding.pRed.setOnClickListener{
            binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority="3"
        }

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
//        return inflater.inflate(R.layout.fragment_edit_notes, container, false)
    }

    private fun updateNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSubtitle.text.toString()
        val notes = binding.etNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        //yahan we gotta update id on clicking
        val data = Notes(oldNotes.data.id,title,subTitle,notes,notesDate.toString(),priority)
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Notes updated successfully", Toast.LENGTH_SHORT).show()

        //end karne ke liye
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            val bottomSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes=bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo=bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                Navigation.findNavController(binding.btnEditSaveNotes).navigate(R.id.action_editNotesFragment_to_homeFragment)

            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}