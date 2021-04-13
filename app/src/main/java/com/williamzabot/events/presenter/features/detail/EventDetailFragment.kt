package com.williamzabot.events.presenter.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.williamzabot.events.R
import com.williamzabot.events.databinding.FragmentEventDetailBinding
import com.williamzabot.events.presenter.extensions.toTime
import com.williamzabot.events.presenter.extensions.urlImage
import com.williamzabot.events.presenter.features.checkin.CheckinDialogFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment() {

    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EventDetailFragmentArgs>()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            titleEvent.text = args.event.title
            dateEvent.text = args.event.date.toTime()
            imageEvent.urlImage(args.event.image)
            priceEvent.text = getString(R.string.sign).plus(" ").plus(args.event.price)
            descriptionEvent.text = args.event.description

            buttonCheckin.setOnClickListener {
                val direction = CheckinDialogFragmentDirections.globalActionToCheckin(args.event)
                navController.navigate(direction)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}