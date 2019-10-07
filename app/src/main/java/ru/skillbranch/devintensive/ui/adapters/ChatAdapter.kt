package ru.skillbranch.devintensive.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_group.*
import kotlinx.android.synthetic.main.item_chat_single.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.models.data.ChatType

class ChatAdapter(val listener: (ChatItem) -> Unit) : RecyclerView.Adapter<ChatAdapter.ChatItemViewHolder>() {


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_chat_single -> SingleViewHolder(inflater.inflate(viewType, parent, false))
            R.layout.item_chat_group -> GroupViewHolder(inflater.inflate(viewType, parent, false))
            else -> SingleViewHolder(inflater.inflate(R.layout.item_chat_single, parent, false))
        }
    }

    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(_items[position], listener)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].chatType) {
            ChatType.SINGLE -> R.layout.item_chat_single
            ChatType.GROUP -> R.layout.item_chat_group
            ChatType.ARCHIVE -> 0
        }
    }


    abstract class ChatItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: ChatItem, listener: (ChatItem) -> Unit)
    }

    class SingleViewHolder(containerView: View) : ChatItemViewHolder(containerView),
            ItemTouchViewHolder {

        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            if (item.avatar == null) {
                Glide.with(itemView)
                        .clear(iv_avatar_single)
                iv_avatar_single.setInitials(item.initials)
            } else {

                // TODO
                Glide.with(itemView)
                        .load(item.avatar)
                        .into(iv_avatar_single)
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

    class GroupViewHolder(containerView: View) : ChatItemViewHolder(containerView),
            ItemTouchViewHolder {

        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            iv_avatar_group.setInitials(item.initials)


            with(tv_date_group) {
                visibility = if (item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }
            with(tv_counter_group) {
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }
            with(tv_message_author) {
                visibility = if (item.author != null) View.VISIBLE else View.GONE
                text = item.author
            }
            tv_title_group.text = item.title
            tv_message_group.text = item.shortDescription

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