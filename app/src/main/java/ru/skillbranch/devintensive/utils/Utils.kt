package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> = when {
        fullName == null -> null to null
        fullName.trim().isEmpty() -> null to null
        else -> fullName.trim().replace(Regex(" +"), " ").split(" ").let {
            it.firstOrNull() to it.getOrNull(1)
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}