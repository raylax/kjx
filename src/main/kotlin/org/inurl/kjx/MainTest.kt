package org.inurl.kjx

import org.inurl.kjx.parser.JarParser
import java.io.File

object MainTest {

    @JvmStatic
    fun main(args: Array<String>) {
//        val file = File("E:\\source\\java\\test\\target\\classes\\Test.class")
//        ClassParser(DataInputStream(file.inputStream()), file.name).parse()
        val file = File("E:\\package\\rt.jar")
        JarParser(file).parse()
    }

}