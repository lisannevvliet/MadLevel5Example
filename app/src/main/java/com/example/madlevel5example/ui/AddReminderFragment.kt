package com.example.madlevel5example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5example.R
import com.example.madlevel5example.databinding.FragmentAddReminderBinding
import com.example.madlevel5example.model.Reminder

//const val REQ_REMINDER_KEY = "req_reminder"
//const val BUNDLE_REMINDER_KEY = "bundle_reminder"

class AddReminderFragment : Fragment() {

    private var _binding: FragmentAddReminderBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddReminder.setOnClickListener {
            onAddReminder()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onAddReminder() {
        val reminderText = binding.etReminderName.text.toString()

        if (reminderText.isNotBlank()) {
            //set the data as fragmentResult, we are listening for REQ_REMINDER_KEY in RemindersFragment!
            // setFragmentResult(REQ_REMINDER_KEY, bundleOf(Pair(BUNDLE_REMINDER_KEY, reminderText)))
            viewModel.insertReminder(Reminder(reminderText))
            //"pop" the backstack, this means we destroy
            //this fragment and go back to the RemindersFragment
            findNavController().popBackStack()

        } else {
            Toast.makeText(
                    activity,
                    R.string.not_valid_reminder, Toast.LENGTH_SHORT
            ).show()
        }
    }
}