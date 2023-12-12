package com.example.to_doapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentHomeBinding
import com.example.to_doapp.model.ToDoModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ToDoClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.toDoClickListener = this


        viewModel.toDoList.observe(viewLifecycleOwner) {
            println(it)
        }

        binding.fragmentHomeFab.setOnClickListener {
            viewModel.insertToDo()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

    override fun onToDoClick(id: Int) {

    }

    override fun onToDoChecked(toDoModel: ToDoModel) {
    }
}