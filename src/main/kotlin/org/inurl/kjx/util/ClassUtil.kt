package org.inurl.kjx.util

object ClassUtil {

    private val CLASS_NAME_REPLACER = Regex("[/\\\$]")

    fun normalize(name: String): String = when {
        name.startsWith("[") -> normalize(name.substring(1)) + "[]"
        (name.startsWith("L") && name.endsWith("]"))
            -> name.substring(1, name.length - 1).replace(CLASS_NAME_REPLACER, ".")
        name.contains(".") || name.contains("$")
            -> name.replace(CLASS_NAME_REPLACER, ".")
        else -> when (name) {
            "B" -> "byte"
            "C" -> "char"
            "D" -> "double"
            "F" -> "float"
            "I" -> "int"
            "J" -> "long"
            "S" -> "short"
            "Z" -> "boolean"
            else -> name
        }
    }

    fun unnormalize(name: String): String = when (name) {
        "byte" -> "B"
        "char" -> "C"
        "double" -> "D"
        "float" -> "F"
        "int" -> "I"
        "long" -> "J"
        "short" -> "S"
        "boolean" -> "Z"
        else -> when {
            name.endsWith("[]") -> "[" + unnormalize(name.substring(0, name.length - 2))
            name.contains(".") -> name.replace(".", "/")
            else -> name
        }
    }

    fun isObject(n: String): Boolean = !isPrimitive(n) && !isArray(n)

    fun isArray(n: String): Boolean = n.startsWith('[')

    fun isPrimitive(n: String): Boolean = when (n) {
        "B", "C", "D", "F", "I", "J", "S", "Z" -> true
        else -> false
    }

}