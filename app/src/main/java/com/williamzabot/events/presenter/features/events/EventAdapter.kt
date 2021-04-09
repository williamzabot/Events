package com.williamzabot.events.presenter.features.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.williamzabot.events.databinding.ItemEventBinding
import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.presenter.extensions.toTime
import com.williamzabot.events.presenter.extensions.urlImage

class EventAdapter(private val clickEvent: (event: Event) -> Unit,
                   private val clickButton: (event : Event) -> Unit
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var events = listOf<Event>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(event: Event) {
            binding.apply {
                titleItemEvent.text = event.title
                dateItemEvent.text = event.date.toTime()
                imageItemEvent.urlImage(event.image)
                priceItemEvent.text = "R$ ${event.price}"

                buttonCheckinItem.setOnClickListener {
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