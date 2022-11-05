package utils

fun String.replaceLastElement(oldChar: Char, newChar: Char) =
    if (this.contains(oldChar)) {
        "${this.substringBeforeLast(oldChar)}$newChar${this.substringAfterLast(oldChar)}"
    } else {
        this
    }

