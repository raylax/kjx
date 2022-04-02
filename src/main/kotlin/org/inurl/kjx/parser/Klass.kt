package org.inurl.kjx.parser

class Klass {

    private val versionTable = arrayOf(
        "1.5", "1.6", "1.7", "1.8", "9", "10", "11", "12", "13", "14", "15", "16", "17"
    )
    private val versionOffset = 49

    lateinit var cp: ConstantPool

    var version: Int = -1

    var access: Int = 0

    var nameIndex: Int = 0

    var superNameIndex: Int = 0

    lateinit var interfaces: List<Interface>

    lateinit var fields: List<Field>

    lateinit var methods: List<Method>

    var annotations: List<Annotation> = mutableListOf()

    var signatureIndex: Int = 0

    var sourceFileIndex: Int = 0

    fun getName() = cp.parseClass(nameIndex)

    fun getSuperName() = cp.parseClass(superNameIndex)

    fun getVersion(): String {
        val i = version - versionOffset
        return if (i < 0 || i >= versionOffset) "Unknown($version)" else versionTable[i]
    }

}