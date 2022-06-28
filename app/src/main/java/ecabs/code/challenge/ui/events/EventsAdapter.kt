package ecabs.code.challenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import ecabs.code.challenge.R
import ecabs.code.challenge.common.DataBoundListAdapter
import ecabs.code.challenge.data.item.Events
import ecabs.code.challenge.databinding.EventItemBinding

/**
 * A RecyclerView adapter for [Events] class.
 */
class EventsAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val clickCallback: ((Events) -> Unit)?
) : DataBoundListAdapter<Events, EventItemBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Events>() {
        override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): EventItemBinding {
        val binding = DataBindingUtil.inflate<EventItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.event_item,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.item?.let {
                clickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: EventItemBinding, item: Events) {
        binding.item = item
    }
}
