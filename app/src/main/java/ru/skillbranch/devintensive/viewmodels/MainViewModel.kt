package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class MainViewModel : ViewModel() {

    private val chatRepository = ChatRepository
    private val chats = Transformations.map(chatRepository.loadChats()) {chats ->
        chats.filter { !it.isArchived }
                .map { it.toChatItem() }
                .sortedBy { it.id }
    }

    fun getChatData(): LiveData<List<ChatItem>> {
        return chats
    }

    fun addToArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun restoreFromeArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))
    }

}