package com.williamzabot.events.presenter.features.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.williamzabot.events.databinding.ItemEventBinding
import com.williamzabot.events.domain.model.EventItem
import com.williamzabot.events.presenter.extensions.toTime
import com.williamzabot.events.presenter.extensions.urlImage
import com.williamzabot.events.presenter.features.checkin.CheckinDialogFragmentDirections

class EventAdapter(private val clickEvent: (event: EventItem) -> Unit,
                   private val clickButton: (event : EventItem) -> Unit
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var events = listOf<EventItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(event: EventItem) {
            binding.apply {
                titleItemEvent.text = event.title
                dateItemEvent.text = event.date.toTime()
                imageItemEvent.urlImage(event.image)
                priceItemEvent.text = "R$ ${event.price}"

                buttonCheckin.setOnClickListener {
                    clickButton(event)
                }
            }
            itemView.setOnClickListener {
                clickEvent(event)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size
}