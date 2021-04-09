package com.williamzabot.events.presenter.features.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.williamzabot.events.R
import com.williamzabot.events.databinding.FragmentEventsBinding
import com.williamzabot.events.presenter.extensions.navigateWithAnimations
import com.williamzabot.events.presenter.features.checkin.CheckinDialogFragmentDirections
import com.williamzabot.events.presenter.features.events.EventsFragmentDirections.Companion.eventsToDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel by viewModels<EventsViewModel>()
    private val navController by lazy { findNavController() }
    private val eventAdapter by lazy {
        EventAdapter(
            { event ->
                navController.navigateWithAnimations(eventsToDetail(event))
            }, { event ->
                val direction = CheckinDialogFragmentDirections.globalActionToCheckin(event)
                navController.navigate(direction)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewEvents.adapter = eventAdapter
        observeEvents()
        viewModel.getEvents()
    }

    private fun observeEvents() {
        viewModel.events.observe(viewLifecycleOwner) { events ->
            eventAdapter.events = events
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.unknown_error, Toast.LENGTH_LONG).show()
        }
    }
}