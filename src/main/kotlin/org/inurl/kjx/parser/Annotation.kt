package org.inurl.kjx.parser

open class Annotation(
    private val cp: ConstantPool,
    private val typeIndex: Int,
    private val valuePairs: List<ValuePair>) {

    class ValuePair(
        private val cp: ConstantPool,
        private val nameIndex: Int,
        private val value: Value
    ) {
        override fun toString() = cp.getString(nameIndex) + " = " + value
    }

    abstract class Value

    abstract class ConstantValueIndex(private val cp: ConstantPool, private val index: Int): Value() {
        override fun toString() = cp.get(index).toString()
    }

    open class ByteValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class CharValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class DoubleValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class FloatValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class IntValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class LongValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class ShortValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class BooleanValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class StringValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class EnumClassValue(cp: ConstantPool, val typeIndex: Int, val constIndex: Int): Value()

    class ClassValue(cp: ConstantPool, index: Int): ConstantValueIndex(cp, index)

    class AnnotationValue(cp: ConstantPool, val annotation: Annotation): Value()

    class ArrayValue(cp: ConstantPool, val values: List<Value>): Value() {
        override fun toString() = values.joinToString(", ", "{", "}")
    }

    override fun toString() = "@" + cp.parseClass(typeIndex) +  valuePairs.joinToString(", ", "(", ")")

}

