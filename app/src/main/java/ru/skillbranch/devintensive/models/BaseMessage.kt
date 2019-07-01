package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
        val id: String,
        val from: User?,
        val chat: Chat,
        val isIncoming: Boolean = false,
        val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory { // По моему это все таки не абстрактная фабрика, а фабричный метод
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String = "text", payload: Any?): BaseMessage {
            lastId++
            return when (type) {
                "text" -> TextMessage(lastId.toString(), from, chat, date = date, text = payload as String)
                "image" -> ImageMessage(lastId.toString(), from, chat, date = date, image = payload as String)
                else -> throw IllegalStateException()
            }
        }

    }
}