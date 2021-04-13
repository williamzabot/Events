package com.williamzabot.events.presenter.features.checkin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.williamzabot.events.R
import com.williamzabot.events.databinding.DialogCheckinBinding
import com.williamzabot.events.presenter.extensions.hideKeyboard
import com.williamzabot.events.presenter.features.checkin.CheckinViewModel.FieldState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckinDialogFragment : DialogFragment() {

    private var _binding: DialogCheckinBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CheckinDialogFragmentArgs>()
    private val viewModel by viewModels<CheckinViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogCheckinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeEvents()
        clickButtonCheckin()
    }

    private fun clickButtonCheckin() {
        binding.buttonFinishCheckin.setOnClickListener {
            viewModel.sendNameAndEmailForCheckin(args.event.id,
                binding.edittextName.text,
                binding.edittextEmail.text)
        }
    }

    private fun initView() {
        binding.eventNameCheckin.text = getString(R.string.event).plus(args.event.title)
    }

    private fun observeEvents() {
        viewModel.nameFieldState.observe(viewLifecycleOwner) { nameState ->
            when (nameState) {
                is FieldState.FieldOk -> {
                    binding.edittextName.error = null
                }
                is FieldState.FieldError -> {
                    binding.edittextName.error = getString(R.string.name_required)
                }
            }
        }

        viewModel.emailFieldState.observe(viewLifecycleOwner) { emailState ->
            when (emailState) {
                is FieldState.FieldOk -> {
                    binding.edittextEmail.error = null
                }
                is FieldState.FieldError -> {
                    binding.edittextEmail.error = getString(R.string.email_required)
                }
            }
        }

        viewModel.invalidEmail.observe(viewLifecycleOwner) {
            binding.edittextEmail.error = getString(R.string.invalid_email)
        }

        viewModel.checkinSuccess.observe(viewLifecycleOwner) {
            finishActionAndDismiss(R.string.success_checkin)
        }

        viewModel.checkinFailed.observe(viewLifecycleOwner) {
            finishActionAndDismiss(R.string.unknown_error)
        }

        viewModel.errorAPI.observe(viewLifecycleOwner) {
            finishActionAndDismiss(R.string.error_api)
        }
    }

    private fun finishActionAndDismiss(msg: Int) {
        (activity as AppCompatActivity).hideKeyboard()
        clearFields()
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        dismiss()
    }

    private fun clearFields() {
        binding.apply {
            edittextName.text?.clear()
            edittextEmail.text?.clear()
        }
    }
}