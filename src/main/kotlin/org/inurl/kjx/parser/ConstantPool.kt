package org.inurl.kjx.parser

import org.inurl.kjx.MainTest
import java.util.*
import kotlin.collections.HashMap

class ConstantPool {

    companion object {
        private val argsRegex = """\[*(?:(?:L.*?;)|[ZBCSIFDJ])""".toRegex()
    }

    private val items: MutableMap<Int, Item>

    init {
        this.items = HashMap()
    }

    fun add(index: Int, item: Item) {
        items += index to item
    }

    fun get(index: Int): Item = items[index] ?: throw IllegalStateException()

    fun getBoolean(index: Int): Boolean = getInt(index) == 1

    fun getInt(index: Int): Int =
        when (val v = get(index)) {
            is IntValue -> v.value
            else -> throw IllegalStateException()
        }

    fun getString(index: Int): String =
        when (val item = items[index]) {
            is StringValue -> item.value
            is StringRef -> getString(item.stringIndex)
            else -> throw IllegalStateException()
        }

    fun parseClass(index: Int) = parseClassName(getString(index))

    fun parseDescriptor(index: Int): Descriptor {
        val joiner = StringJoiner(" ")
        val descriptor = getString(index)

        val endIndex = descriptor.indexOf(')')
        var returnType = descriptor.substring(endIndex + 1)

        returnType = if (returnType == "V") "void" else parseClassName(returnType)

        val args = descriptor.substring(1, endIndex)
        return if (args.isNotEmpty()) {
            val argsTypes = argsRegex.findAll(args)
                .map { parseClassName(it.value) }
                .toList()
            Descriptor(argsTypes, returnType)
        } else {
            Descriptor(emptyList(), returnType)
        }
    }

    class Descriptor(val argTypes: List<String>, val returnType: String)

    private fun parseClassName(n: String): String {
        return when {
            n.startsWith("L") -> n.substring(1, n.length - 1).replace("/", ".")
            n.startsWith("[") -> parseClassName(n.substring(1)) + "[]"
            else -> when (n) {
                "B" -> "byte"
                "C" -> "char"
                "D" -> "double"
                "F" -> "float"
                "I" -> "int"
                "J" -> "long"
                "S" -> "short"
                "Z" -> "boolean"
                else -> n
            }
        }
    }

    open class Item

    inner class ClassRef(val nameIndex: Int): Item()

    inner class FieldRef(override val classIndex: Int, override val nameAndTypeIndex: Int): ClassNameAndTypeRef(classIndex, nameAndTypeIndex)

    inner class MethodRef(override val classIndex: Int, override val nameAndTypeIndex: Int): ClassNameAndTypeRef(classIndex, nameAndTypeIndex)

    inner class InterfaceMethodRef(override val classIndex: Int, override val nameAndTypeIndex: Int): ClassNameAndTypeRef(classIndex, nameAndTypeIndex)

    inner class StringRef(val stringIndex: Int): Item() {
        override fun toString() = get(stringIndex).toString()
    }

    abstract inner class Value<T>(open val value: T): Item() {
        override fun toString(): String {
            return value.toString()
        }
    }

    inner class IntValue(override val value: Int): Value<Int>(value)

    inner class FloatValue(override val value: Float): Value<Float>(value)

    inner class LongValue(override val value: Long): Value<Long>(value)

    inner class DoubleValue(override val value: Double): Value<Double>(value)

    inner class StringValue(override val value: String): Value<String>(value) {
        override fun toString(): String {
            return """"$value""""
        }
    }

    inner class NameAndTypeRef(val nameIndex: Int, val descriptorIndex: Int): Item()

    inner class MethodHandle(val referenceKind: Int, val referenceIndex: Int): Item()

    inner class MethodType(val descriptorIndex: Int): Item()

    inner class InvokeDynamic(override val bootstrapMethodAttrIndex: Int, override val nameAndTypeIndex: Int): Dynamic(bootstrapMethodAttrIndex, nameAndTypeIndex)

    inner class Module(val nameIndex: Int): Item()

    inner class Package(val nameIndex: Int): Item()

    open inner class Dynamic(open val bootstrapMethodAttrIndex: Int, open val nameAndTypeIndex: Int): Item()

    abstract inner class ClassNameAndTypeRef(open val classIndex: Int, open val nameAndTypeIndex: Int): Item()

}