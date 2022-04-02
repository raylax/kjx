package org.inurl.kjx.parser

import java.util.*

class Field(
    private val cp: ConstantPool,
    private val access: Int,
    private val nameIndex: Int,
    val descriptorIndex: Int) {

    var annotations: List<Annotation> = mutableListOf()

    var constantValue: ConstantValue? = null

    fun getName() = cp.getString(nameIndex)

    fun getDescriptor() = cp.getString(descriptorIndex)

    override fun toString(): String {
        val joiner = StringJoiner(" ")
        when {
            Defs.isPublic(access) -> joiner.add("public")
            Defs.isProtected(access) -> joiner.add("protected")
            Defs.isPrivate(access) -> joiner.add("private")
        }
        if (Defs.isStatic(access)) joiner.add("static")
        if (Defs.isTransient(access)) joiner.add("transient")
        when {
            Defs.isFinal(access) -> joiner.add("final")
            Defs.isVolatile(access) -> joiner.add("volatile")
        }
        joiner.add(cp.parseClass(descriptorIndex))
        joiner.add(cp.getString(nameIndex))
        constantValue?.let {
            joiner.add("=")
            joiner.add(it.toString())
        }
        return if (annotations.isEmpty()) "$joiner;" else "${annotations.joinToString("\n")}\n$joiner;"
    }

    abstract class ConstantValue(val cp: ConstantPool, val index: Int) {
        override fun toString() = cp.get(index).toString()
    }

    class IntValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class ShortValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class CharValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class ByteValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class FloatValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class LongValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class DoubleValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class StringValue(cp: ConstantPool, index: Int): ConstantValue(cp, index)
    class BooleanValue(cp: ConstantPool, index: Int): ConstantValue(cp, index) {
        override fun toString() = cp.getBoolean(index).toString()
    }

}