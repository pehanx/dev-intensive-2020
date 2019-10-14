package ru.skillbranch.devintensive.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.models.data.ChatType
import ru.skillbranch.devintensive.ui.viewholders.ChatItemViewHolder
import ru.skillbranch.devintensive.ui.viewholders.GroupViewHolder
import ru.skillbranch.devintensive.ui.viewholders.SingleViewHolder
import ru.skillbranch.devintensive.ui.viewholders.ArchiveViewHolder

private const val TAG = "ChatAdapter"

class ChatAdapter(val listener: (ChatItem) -> Unit) : RecyclerView.Adapter<ChatItemViewHolder>(), AdapterWithChatItems {


    private var _items: List<ChatItem> = listOf()
    override val items get() = _items

    fun updateData(newItems: List<ChatItem>) {

        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return _items[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun getOldListSize(): Int = _items.size
            override fun getNewListSize(): Int = newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return _items[oldItemPosition] == newItems[newItemPosition]
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
            R.layout.item_chat_archive -> ArchiveViewHolder(inflater.inflate(viewType, parent, false))
            else -> SingleViewHolder(inflater.inflate(R.layout.item_chat_single, parent, false))
        }
    }

    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(_items[position], listener)
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "$position ${items[position].chatType}")
        return when (items[position].chatType) {
            ChatType.SINGLE -> R.layout.item_chat_single
            ChatType.GROUP -> R.layout.item_chat_group
            ChatType.ARCHIVE -> R.layout.item_chat_archive
        }
    }
}