package org.inurl.kjx

import org.inurl.kjx.parser.ClassParser
import java.io.DataInputStream
import java.io.File

object MainTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("E:\\source\\java\\test\\target\\classes\\com\\test\\Test.class")
        ClassParser(DataInputStream(file.inputStream()), file.name).parse()
    }

}