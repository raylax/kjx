package org.inurl.kjx

import org.inurl.kjx.parser.JarParser

object MainTest {

    @JvmStatic
    fun main(args: Array<String>) {
        JarParser("D:\\runtime\\jdk1.8.0_291\\jre\\lib\\rt.jar").parse()
    }

}