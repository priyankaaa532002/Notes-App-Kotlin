package com.example.quicknotes.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quicknotes.R
import com.example.quicknotes.ViewModel.NotesViewModel
import com.example.quicknotes.databinding.FragmentHomeBinding
import com.example.quicknotes.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding

    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        //2.
        viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->

            binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
        })


        //filters
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }
        binding.filterNone.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }


}