package ru.skillbranch.devintensive.data

import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.utils.DataGenerator

object CacheManager {
    val chats = mutableLiveData(DataGenerator.stabChats)

    fun loadChats() = chats

}