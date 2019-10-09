package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class MainViewModel : ViewModel() {

    private val chatRepository = ChatRepository
    private val chats = Transformations.map(chatRepository.loadChats()) {chats ->
        chats.filter { !it.isArchived }
                .map { it.toChatItem() }
                .sortedBy { it.id }
    }
    private val query = mutableLiveData("")

    fun getChatData(): LiveData<List<ChatItem>> {
        val items = MediatorLiveData<List<ChatItem>>()
        val filter = {
            if (query.value.isNullOrEmpty()) {
                items.value = chats.value
            } else {
                items.value = chats?.value.orEmpty().filter {
                    it.title.contains(query.value?.trim() ?: "", true)
                }
            }
        }
        items.addSource(chats) {filter()}
        items.addSource(query) {filter()}
        return items
    }

    fun addToArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun restoreFromArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))
    }

    fun handleSearchQuery(queryText: String?) {
        query.value = queryText
    }

}