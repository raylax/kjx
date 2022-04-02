package org.inurl.kjx.parser

import org.inurl.kjx.util.Log
import java.io.DataInputStream
import java.io.EOFException
import java.nio.charset.StandardCharsets

class ClassParser(private val source: DataInputStream, private val name: String) {

    companion object {
        // 常量类型
        private const val ConstantUTF8 = 1
        private const val ConstantInteger = 3
        private const val ConstantFloat = 4
        private const val ConstantLong = 5
        private const val ConstantDouble = 6
        private const val ConstantClass = 7
        private const val ConstantString = 8
        private const val ConstantFieldRef = 9
        private const val ConstantMethodRef = 10
        private const val ConstantInterfaceMethodRef = 11
        private const val ConstantNameAndType = 12
        private const val ConstantMethodHandle = 15
        private const val ConstantMethodType = 16
        private const val ConstantDynamic = 17
        private const val ConstantInvokeDynamic = 18
        private const val ConstantModule = 19
        private const val ConstantPackage = 20
    }

    fun parse(): Klass {
        val start = System.currentTimeMillis()
        val magic = readUint4()
        if (magic != 0xCAFEBABE) {
            throw IllegalStateException("Unexpected magic")
        }
        val klass = Klass()
        klass.version = source.readInt()

        val cp = parseConstantPool()
        klass.cp = cp

        klass.access = readUint2()
        klass.nameIndex = readUint2()

        Log.debug { klass.getName() }

        klass.superNameIndex = readUint2()
        klass.interfaces = parseInterfaces(cp)

        klass.fields = parseFields(cp)

        klass.methods = parserMethods(cp)

        forEach {
            val attrName = cp.getString(readUint2())
            val len = readUint4()
            when (attrName) {
                "RuntimeVisibleAnnotations" -> klass.annotations += parseAnnotations(cp)
                "Signature" -> klass.signatureIndex = readUint2()
                "SourceFile" -> klass.sourceFileIndex = readUint2()
                else -> source.skip(len)
            }
        }

        return klass
    }

    private fun parserMethods(cp: ConstantPool): List<Method> {
        val methods: MutableList<Method> = mutableListOf()
        forEach {
            methods += parserMethod(cp)
        }
        return methods
    }

    private fun parserMethod(cp: ConstantPool): Method {
        val method = Method(cp, readUint2(), readUint2(), readUint2())
        forEach {
            val attrName = cp.getString(readUint2())
            val len = readUint4()
            when (attrName) {
                "RuntimeVisibleAnnotations" -> method.annotations += parseAnnotations(cp)
                "Code" -> method.code = parseCode(cp)
                else -> source.skip(len)
            }
        }
        return method
    }

    private fun parseStackFrames(cp: ConstantPool): List<StackFrame> {
        val stackFrames: MutableList<StackFrame> = mutableListOf()
        forEach {
            stackFrames += parseStackFrame(cp)
        }
        return stackFrames
    }

    private fun parseStackFrame(cp: ConstantPool): StackFrame =
        when (val frameType = readUint1()) {
            in 0..63 -> SameFrame(frameType)
            in 64..127 -> SameLocals1StackItemFrame(frameType, parseVerificationType(cp))
            247 -> SameLocals1StackItemFrameExtended(frameType, readUint2(), parseVerificationType(cp))
            in 248..250 -> ChopFrame(frameType, readUint2())
            251 -> SameFrameExtended(frameType, readUint2())
            in 252..254 -> AppendFrame(frameType, readUint2(), parseVerificationTypes(cp, frameType - 251))
            255 -> FullFrame(frameType, readUint2(), parseVerificationTypes(cp, readUint2()), parseVerificationTypes(cp, readUint2()))
            else -> throw IllegalStateException("$frameType")
        }

    private fun parseVerificationTypes(cp: ConstantPool, count: Int): List<VerificationType> {
        val verificationTypes: MutableList<VerificationType> = mutableListOf()
        for (i in 1..count) {
            verificationTypes += parseVerificationType(cp)
        }
        return verificationTypes
    }

    private fun parseVerificationType(cp: ConstantPool): VerificationType =
        when (readUint1()) {
            0 -> TopVariable()
            1 -> IntVariable()
            2 -> FloatVariable()
            3 -> DoubleVariable()
            4 -> LongVariable()
            5 -> NullVariable()
            6 -> UninitializedThisVariable()
            7 -> ObjectVariable(cp, readUint2())
            8 -> UninitializedVariable(readUint2())
            else -> throw IllegalStateException()
        }

    private fun parseCode(cp: ConstantPool): Code {
        val code = Code(readUint2(), readUint2(), readBytes(readUint4().toInt()))
        val exceptions: MutableList<Exception> = mutableListOf()

        forEach {
            exceptions += Exception(readUint2(), readUint2(), readUint2(), readUint2())
        }

        val lineNumberTable: MutableList<LineNumber> = mutableListOf()
        val localVariables: MutableList<LocalVariable> = mutableListOf()
        val stackFrames: MutableList<StackFrame> = mutableListOf()
        forEach {
            val attrName = cp.getString(readUint2())
            val len = readUint4()
            when (attrName) {
                "LineNumberTable" -> lineNumberTable += parseLineNumbers()
                "LocalVariableTable" -> localVariables += parseLocalVariables()
                "StackMapTable" -> stackFrames += parseStackFrames(cp)
                else -> source.skip(len)
            }
        }
        code.lineNumberTable = lineNumberTable
        code.localVariables = localVariables
        code.stackFrames = stackFrames
        return code
    }

    private fun parseLocalVariables(): List<LocalVariable> {
        val localVariables: MutableList<LocalVariable> = mutableListOf()
        forEach {
            localVariables += LocalVariable(readUint2(), readUint2(), readUint2(), readUint2(), readUint2())
        }
        return localVariables
    }

    private fun parseLineNumbers(): List<LineNumber> {
        val lineNumbers: MutableList<LineNumber> = mutableListOf()
        forEach {
            lineNumbers += LineNumber(readUint2(), readUint2())
        }
        return lineNumbers
    }

    private fun parseFields(cp: ConstantPool): List<Field> {
        val fields: MutableList<Field> = mutableListOf()
        forEach {
            fields += parseField(cp)
        }
        return fields
    }

    private fun parseField(cp: ConstantPool): Field {
        val field = Field(cp, readUint2(), readUint2(), readUint2())
        forEach {
            val attrName = cp.getString(readUint2())
            val len = readUint4()
            when (attrName) {
                "RuntimeVisibleAnnotations" -> field.annotations += parseAnnotations(cp)
                "ConstantValue" -> field.constantValue = parseConstantValue(cp, cp.getString(field.descriptorIndex))
                else -> source.skip(len)
            }
        }
        return field
    }

    private inline fun forEach(action: (i: Int) -> Unit) =
        (0 until readUint2()).forEach(action)

    private fun parseConstantValue(cp: ConstantPool, type: String): Field.ConstantValue =
        when (type) {
            "I" -> Field.IntValue(cp, readUint2())
            "S" -> Field.ShortValue(cp, readUint2())
            "C" -> Field.CharValue(cp, readUint2())
            "B" -> Field.ByteValue(cp, readUint2())
            "Z" -> Field.BooleanValue(cp, readUint2())
            "F" -> Field.FloatValue(cp, readUint2())
            "J" -> Field.LongValue(cp, readUint2())
            "D" -> Field.DoubleValue(cp, readUint2())
            "Ljava/lang/String;" -> Field.StringValue(cp, readUint2())
            else -> throw IllegalStateException(type)
        }

    private fun parseAnnotations(cp: ConstantPool): List<Annotation> {
        val annotations: MutableList<Annotation> = mutableListOf()
        forEach {
            annotations += parseAnnotation(cp)
        }
        return annotations
    }

    private fun parseAnnotation(cp: ConstantPool): Annotation {
        val typeIndex = readUint2()
        val valuePairs: MutableList<Annotation.ValuePair> = mutableListOf()
        forEach {
            valuePairs += Annotation.ValuePair(cp, readUint2(), parseAnnotationValue(cp))
        }
        return Annotation(cp, typeIndex, valuePairs)
    }

    private fun parseAnnotationValue(cp: ConstantPool): Annotation.Value =
        when (readUint1().toChar()) {
            'B' -> Annotation.ByteValue(cp, readUint2())
            'C' -> Annotation.CharValue(cp, readUint2())
            'D' -> Annotation.DoubleValue(cp, readUint2())
            'F' -> Annotation.FloatValue(cp, readUint2())
            'I' -> Annotation.IntValue(cp, readUint2())
            'J' -> Annotation.LongValue(cp, readUint2())
            'S' -> Annotation.ShortValue(cp, readUint2())
            'Z' -> Annotation.BooleanValue(cp, readUint2())
            's' -> Annotation.StringValue(cp, readUint2())
            'e' -> Annotation.EnumClassValue(cp, readUint2(), readUint2())
            'c' -> Annotation.ClassValue(cp, readUint2())
            '@' -> Annotation.AnnotationValue(cp, parseAnnotation(cp))
            '[' -> {
                val values: MutableList<Annotation.Value> = mutableListOf()
                forEach {
                    values += parseAnnotationValue(cp)
                }
                Annotation.ArrayValue(cp, values)
            }
            else -> throw IllegalStateException()
        }

    private fun parseInterfaces(cp: ConstantPool): List<Interface> {
        val interfaces: MutableList<Interface> = mutableListOf()
        forEach {
            interfaces += Interface(cp, readUint2())
        }
        return interfaces
    }

    private fun parseConstantPool(): ConstantPool {
        val constantCount = source.readUnsignedShort() - 1
        val constantPool = ConstantPool()
        var i = 1
        while (i <= constantCount) {
            if (readConstantItem(i, constantPool)) i++
            i++
        }
        return constantPool
    }

    private fun readConstantItem(i: Int, cp: ConstantPool): Boolean {
        val tag = readUint1()
        val item = when (tag) {
            ConstantClass -> cp.ClassRef(readUint2())
            ConstantFieldRef -> cp.FieldRef(readUint2(), readUint2())
            ConstantMethodRef -> cp.MethodRef(readUint2(), readUint2())
            ConstantInterfaceMethodRef -> cp.InterfaceMethodRef(readUint2(), readUint2())
            ConstantString -> cp.StringRef(readUint2())
            ConstantInteger -> cp.IntValue(source.readInt())
            ConstantFloat -> cp.FloatValue(source.readFloat())
            ConstantLong -> cp.LongValue(source.readLong())
            ConstantDouble -> cp.DoubleValue(source.readDouble())
            ConstantNameAndType -> cp.NameAndTypeRef(readUint2(), readUint2())
            ConstantUTF8 -> cp.StringValue(String(readBytes(readUint2()), StandardCharsets.UTF_8))
            ConstantMethodHandle -> cp.MethodHandle(readUint1(), readUint2())
            ConstantMethodType -> cp.MethodType(readUint2())
            ConstantDynamic -> cp.Dynamic(readUint2(), readUint2())
            ConstantInvokeDynamic -> cp.InvokeDynamic(readUint2(), readUint2())
            ConstantModule -> cp.Module(readUint2())
            ConstantPackage -> cp.Package(readUint2())
            else -> throw IllegalStateException("Unexpected constant tag $tag")
        }
        cp.add(i, item)
        return tag == ConstantLong || tag == ConstantDouble
    }

    private fun readBytes(n: Int): ByteArray {
        val bytes = ByteArray(n)
        val r = source.read(bytes)
        return if (r > -1) bytes else throw EOFException()
    }

    private fun readUint1(): Int {
        return source.readUnsignedByte()
    }

    private fun readUint2(): Int {
        return source.readUnsignedShort()
    }

    private fun readUint4(): Long {
        return Integer.toUnsignedLong(source.readInt())
    }

}