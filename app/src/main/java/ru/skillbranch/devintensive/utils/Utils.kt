package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> = when {
        fullName == null -> null to null
        fullName.trim().isEmpty() -> null to null
        else -> fullName.trim().split(" ").let {
            it.firstOrNull() to it.getOrNull(1)
        }
    }
}