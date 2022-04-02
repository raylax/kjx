package org.inurl.kjx

import org.inurl.kjx.parser.ClassParser
import java.io.DataInputStream
import java.io.FileInputStream

object MainTest {

    private val argsRegex = """\[*(?:(?:L.*?;)|[ZBCSIFDJ])""".toRegex()

    @JvmStatic
    fun main(args: Array<String>) {
        val source = FileInputStream("E:\\source\\java\\test\\target\\classes\\com\\test\\Test.class")
        ClassParser(DataInputStream(source)).parse()
//        val data = arrayOf("()V", "([Ljava/lang/String;)V", "([B)V", "([BII)V", "()V", "([[Ljava/lang/Object;[[Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "(II)[Ljava/lang/String;")
//        for (item in data) {
//            parse(item)
//        }
    }

    fun parse(text: String) {
        val endIndex = text.indexOf(')')
        val returnType = text.substring(endIndex + 1)
//        println(returnType)
        val args = text.substring(1, endIndex)
        println(args)
        println(argsRegex.findAll(args).toList().joinToString { it.value })
    }

}