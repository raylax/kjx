package org.inurl.kjx.parser

import org.inurl.kjx.util.Log
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.zip.ZipInputStream

class JarParser(private val source: InputStream, private val name: String, private val ident: Int) {

    constructor(source: InputStream, name: String): this(source, name, 0)

    fun parse(): List<Klass> {
        if (ident == 0) {
            Log.info { "Parsing $name" }
        } else {
            Log.debug { "${"  ".repeat(ident)}- $name" }
        }
        val start = System.currentTimeMillis()
        val classes = mutableListOf<Klass>()
        val jar = ZipInputStream(source)
        val dataInput = DataInputStream(jar)
        generateSequence { jar.nextEntry }
            .forEach { e ->
                when {
                    e.name.endsWith(".class") -> classes += ClassParser(dataInput, "jar:$name!/${e.name}").parse()
                    e.name.endsWith(".jar") -> classes += JarParser(jar, "jar:$name!jar:${e.name}", ident + 1).parse()
                }
            }
        if (ident == 0) {
            Log.info { "Parsed $name ${System.currentTimeMillis() - start}ms" }
        }
        return classes
    }

}