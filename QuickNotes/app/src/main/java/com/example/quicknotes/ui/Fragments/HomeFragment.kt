package com.example.quicknotes.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quicknotes.Model.Notes
import com.example.quicknotes.R
import com.example.quicknotes.ViewModel.NotesViewModel
import com.example.quicknotes.databinding.FragmentHomeBinding
import com.example.quicknotes.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    //Declare
    val viewModel : NotesViewModel by viewModels()


    //search ke liye list
    var oldMyNotes = arrayListOf<Notes>()

    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        //2.
        viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->

            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(),notesList)
            binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvAllNotes.adapter = adapter
        })


        //filters
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{notesList ->

                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{notesList ->

                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner,{notesList ->

                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                //binding.rvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
                binding.rvAllNotes.adapter = adapter
            })
        }
        binding.filterNone.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->

                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rvAllNotes.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint="Search Note Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })



        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {

        val newFilteredList = arrayListOf<Notes>()
        for(i in oldMyNotes){
            if(i.title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}