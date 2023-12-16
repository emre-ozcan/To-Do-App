package com.example.to_doapp.ui.newAndEdit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.to_doapp.databinding.FragmentNewAndEditBinding
import com.example.to_doapp.model.Priority
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAndEditFragment : Fragment() {
    private var _binding: FragmentNewAndEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NewAndEditViewModel>()
    private  val args by navArgs<NewAndEditFragmentArgs>()

    private var currentPriorityIndex = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAndEditBinding.inflate(inflater, container, false)

        if (args.toDoId == -1) {
            binding.fragmentNewAndEditDeleteButton.isVisible = false
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "New To-Do"
        } else {
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Edit To-Do"
            viewModel.getToDo(args.toDoId)
        }

        initializeView()

        viewModel.toDoModel.observe(viewLifecycleOwner) {
            binding.fragmentNewAndEditTitleEditText.setText(it.title)
            binding.fragmentNewAndEditDescriptionEditText.setText(it.description)
            binding.fragmentNewAndEditAutoComplete.setText(it.priority?.name, false)
            binding.fragmentNewAndEditCheckbox.isChecked = it.isChecked == true

            currentPriorityIndex = when (it.priority) {
                Priority.HIGH -> 0
                Priority.MEDIUM -> 1
                else -> 2
            }
        }

        return binding.root
    }

    private fun initializeView() {
        binding.fragmentNewAndEditSaveButton.setOnClickListener {
            handleSaveButton()
        }

        binding.fragmentNewAndEditAutoComplete.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                arrayOf(Priority.HIGH.name, Priority.MEDIUM.name, Priority.LOW.name)
            )
        )

        binding.fragmentNewAndEditAutoComplete.setOnItemClickListener { _, _, index, _ ->
            currentPriorityIndex = index
        }

        binding.fragmentNewAndEditDeleteButton.setOnClickListener {
            viewModel.deleteToDo()
            
            Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun handleSaveButton() {
        val title = binding.fragmentNewAndEditTitleEditText.text.toString()
        val description = binding.fragmentNewAndEditDescriptionEditText.text.toString()

        val priority = when (currentPriorityIndex) {
            0 -> Priority.HIGH
            1 -> Priority.MEDIUM
            else -> Priority.LOW
        }

        if (args.toDoId == -1) {
            viewModel.insertToDo(title, description, binding.fragmentNewAndEditCheckbox.isChecked, priority)
        } else {
            viewModel.updateToDo(title, description, binding.fragmentNewAndEditCheckbox.isChecked, priority)
        }


        Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}