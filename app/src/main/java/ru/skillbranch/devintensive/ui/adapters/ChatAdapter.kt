package ru.skillbranch.devintensive.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_single.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem

class ChatAdapter(val listener: (ChatItem) -> Unit) : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {


    private var _items: List<ChatItem> = listOf()
    val items get() = _items

    fun updateData(newItems: List<ChatItem>) {

        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return _items[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun getOldListSize(): Int = _items.size
            override fun getNewListSize(): Int = newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return _items[oldItemPosition] == _items[newItemPosition]
            }

        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        _items = newItems

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SingleViewHolder(inflater.inflate(R.layout.item_chat_single, parent, false))
    }

    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        holder.bind(_items[position], listener)
    }

    class SingleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer,
            ItemTouchViewHolder {

        fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            if (item.avatar == null) {
                iv_avatar_single.setInitials(item.initials)
            } else {
                // TODO
            }
            sv_indicator.visibility = if (item.isOnline) View.VISIBLE else View.GONE
            with(tv_date_single) {
                visibility = if (item.lastMessageDate == null) View.GONE else View.VISIBLE
                text = item.lastMessageDate
            }
            with(tv_counter_single) {
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }
            tv_title_single.text = item.title
            tv_message_single.text = item.shortDescription

            itemView.setOnClickListener { listener(item) }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }

    }
}