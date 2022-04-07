package org.inurl.kjx.parser

import org.inurl.kjx.util.Log
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.zip.ZipInputStream

class JarParser(private val source: InputStream, private val name: String) {

    constructor(path: String): this(FileInputStream(path), path)

    constructor(file: File): this(FileInputStream(file), file.name)

    fun parse(): List<Klass> {
        Log.debug { "Parsing $name" }
        val start = System.currentTimeMillis()
        val classes = mutableListOf<Klass>()
        val jar = ZipInputStream(source)
        val dataInput = DataInputStream(jar)
        jar.use { zip ->
            generateSequence { zip.nextEntry }
                .forEach { e ->
                    when {
                        e.name.endsWith(".class") -> classes += ClassParser(dataInput, "jar:$name!/${e.name}").parse()
                        e.name.endsWith(".jar") -> classes += JarParser(jar, "jar:$name").parse()
                    }
                }
        }
        Log.debug { "Parsed $name ${System.currentTimeMillis() - start}ms" }
        return classes
    }

}