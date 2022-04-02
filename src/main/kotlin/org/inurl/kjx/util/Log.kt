package org.inurl.kjx.util

class Log {

    companion object {
        fun debug(message: () -> String) {
            println(message())
        }
        fun info(message: () -> String) {
            println(message())
        }
    }

}
