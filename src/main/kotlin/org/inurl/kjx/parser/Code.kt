package org.inurl.kjx.parser

import kotlin.system.exitProcess

class Code(val cp: ConstantPool, val maxStack: Int, val maxLocals: Int, val ops: ByteArray) {

    class Label() {

    }

    lateinit var exceptionTable: List<Exception>

    lateinit var lineNumberTable: List<LineNumber>

    lateinit var localVariables: List<LocalVariable>

    lateinit var localVariableTypes: List<LocalVariableType>

    lateinit var stackFrames: List<StackFrame>

    lateinit var labels: Array<Label?>

    fun parse() {
        labels = arrayOfNulls(ops.size)
        var currentIndex = 0


        fun read1(): Int = ops[currentIndex++].toInt()
        fun readU1(): Int = ops[currentIndex++].toInt() and 0xff
        fun readU2(): Int =
            ((ops[currentIndex++].toInt() and 0xff) shl 8) or
            (ops[currentIndex++].toInt() and 0xff)
        fun read2(): Int = readU2().toShort().toInt()
        fun readU4(): Int =
            ((ops[currentIndex++].toInt() and 0xff) shl 24) or
            ((ops[currentIndex++].toInt() and 0xff) shl 16) or
            ((ops[currentIndex++].toInt() and 0xff) shl 8) or
            (ops[currentIndex++].toInt() and 0xff)

        while (currentIndex < ops.size) {
            val opcode = ops[currentIndex].toInt() and 0xff
            val opcodeName = "%3d ".format(currentIndex) + Opcodes.getName(opcode)
            when (opcode) {
                Opcodes.NOP,
                Opcodes.ACONST_NULL,
                Opcodes.ICONST_M1,
                Opcodes.ICONST_0,
                Opcodes.ICONST_1,
                Opcodes.ICONST_2,
                Opcodes.ICONST_3,
                Opcodes.ICONST_4,
                Opcodes.ICONST_5,
                Opcodes.LCONST_0,
                Opcodes.LCONST_1,
                Opcodes.FCONST_0,
                Opcodes.FCONST_1,
                Opcodes.FCONST_2,
                Opcodes.DCONST_0,
                Opcodes.DCONST_1,
                Opcodes.IALOAD,
                Opcodes.LALOAD,
                Opcodes.FALOAD,
                Opcodes.DALOAD,
                Opcodes.AALOAD,
                Opcodes.BALOAD,
                Opcodes.CALOAD,
                Opcodes.SALOAD,
                Opcodes.IASTORE,
                Opcodes.LASTORE,
                Opcodes.FASTORE,
                Opcodes.DASTORE,
                Opcodes.AASTORE,
                Opcodes.BASTORE,
                Opcodes.CASTORE,
                Opcodes.SASTORE,
                Opcodes.POP,
                Opcodes.POP2,
                Opcodes.DUP,
                Opcodes.DUP_X1,
                Opcodes.DUP_X2,
                Opcodes.DUP2,
                Opcodes.DUP2_X1,
                Opcodes.DUP2_X2,
                Opcodes.SWAP,
                Opcodes.IADD,
                Opcodes.LADD,
                Opcodes.FADD,
                Opcodes.DADD,
                Opcodes.ISUB,
                Opcodes.LSUB,
                Opcodes.FSUB,
                Opcodes.DSUB,
                Opcodes.IMUL,
                Opcodes.LMUL,
                Opcodes.FMUL,
                Opcodes.DMUL,
                Opcodes.IDIV,
                Opcodes.LDIV,
                Opcodes.FDIV,
                Opcodes.DDIV,
                Opcodes.IREM,
                Opcodes.LREM,
                Opcodes.FREM,
                Opcodes.DREM,
                Opcodes.INEG,
                Opcodes.LNEG,
                Opcodes.FNEG,
                Opcodes.DNEG,
                Opcodes.ISHL,
                Opcodes.LSHL,
                Opcodes.ISHR,
                Opcodes.LSHR,
                Opcodes.IUSHR,
                Opcodes.LUSHR,
                Opcodes.IAND,
                Opcodes.LAND,
                Opcodes.IOR,
                Opcodes.LOR,
                Opcodes.IXOR,
                Opcodes.LXOR,
                Opcodes.I2L,
                Opcodes.I2F,
                Opcodes.I2D,
                Opcodes.L2I,
                Opcodes.L2F,
                Opcodes.L2D,
                Opcodes.F2I,
                Opcodes.F2L,
                Opcodes.F2D,
                Opcodes.D2I,
                Opcodes.D2L,
                Opcodes.D2F,
                Opcodes.I2B,
                Opcodes.I2C,
                Opcodes.I2S,
                Opcodes.LCMP,
                Opcodes.FCMPL,
                Opcodes.FCMPG,
                Opcodes.DCMPL,
                Opcodes.DCMPG,
                Opcodes.IRETURN,
                Opcodes.LRETURN,
                Opcodes.FRETURN,
                Opcodes.DRETURN,
                Opcodes.ARETURN,
                Opcodes.RETURN,
                Opcodes.ARRAYLENGTH,
                Opcodes.ATHROW,
                Opcodes.MONITORENTER,
                Opcodes.MONITOREXIT,
                -> {
                    currentIndex += 1
                    println(opcodeName)
                }
                Opcodes.ILOAD_0,
                Opcodes.ILOAD_1,
                Opcodes.ILOAD_2,
                Opcodes.ILOAD_3,
                Opcodes.LLOAD_0,
                Opcodes.LLOAD_1,
                Opcodes.LLOAD_2,
                Opcodes.LLOAD_3,
                Opcodes.FLOAD_0,
                Opcodes.FLOAD_1,
                Opcodes.FLOAD_2,
                Opcodes.FLOAD_3,
                Opcodes.DLOAD_0,
                Opcodes.DLOAD_1,
                Opcodes.DLOAD_2,
                Opcodes.DLOAD_3,
                Opcodes.ALOAD_0,
                Opcodes.ALOAD_1,
                Opcodes.ALOAD_2,
                Opcodes.ALOAD_3,
                -> {
                    currentIndex += 1
                    println("$opcodeName ${(opcode - Opcodes.ILOAD_0) and 0x3}")
                }
                Opcodes.ISTORE_0,
                Opcodes.ISTORE_1,
                Opcodes.ISTORE_2,
                Opcodes.ISTORE_3,
                Opcodes.LSTORE_0,
                Opcodes.LSTORE_1,
                Opcodes.LSTORE_2,
                Opcodes.LSTORE_3,
                Opcodes.FSTORE_0,
                Opcodes.FSTORE_1,
                Opcodes.FSTORE_2,
                Opcodes.FSTORE_3,
                Opcodes.DSTORE_0,
                Opcodes.DSTORE_1,
                Opcodes.DSTORE_2,
                Opcodes.DSTORE_3,
                Opcodes.ASTORE_0,
                Opcodes.ASTORE_1,
                Opcodes.ASTORE_2,
                Opcodes.ASTORE_3,
                -> {
                    currentIndex += 1
                    println("$opcodeName ${(opcode - Opcodes.ISTORE_0) and 0x3}")
                }
                Opcodes.IFEQ,
                Opcodes.IFNE,
                Opcodes.IFLT,
                Opcodes.IFGE,
                Opcodes.IFGT,
                Opcodes.IFLE,
                Opcodes.IF_ICMPEQ,
                Opcodes.IF_ICMPNE,
                Opcodes.IF_ICMPLT,
                Opcodes.IF_ICMPGE,
                Opcodes.IF_ICMPGT,
                Opcodes.IF_ICMPLE,
                Opcodes.IF_ACMPEQ,
                Opcodes.IF_ACMPNE,
                Opcodes.GOTO,
                Opcodes.JSR,
                Opcodes.IFNULL,
                Opcodes.IFNONNULL,
                -> {
                    currentIndex++
                    println("$opcodeName #${currentIndex - 1 + read2()}")
                }
                Opcodes.GOTO_W,
                Opcodes.JSR_W,
                -> {
                    currentIndex++
                    println("$opcodeName #${readU4()}")
                }
                Opcodes.WIDE -> {
                    currentIndex++
                    val subOpcode = ops[currentIndex++].toInt() and 0xff
                    val subOpcodeName = Opcodes.getName(subOpcode)
                    when (subOpcode) {
                        Opcodes.ILOAD,
                        Opcodes.FLOAD,
                        Opcodes.ALOAD,
                        Opcodes.LLOAD,
                        Opcodes.DLOAD,
                        Opcodes.ISTORE,
                        Opcodes.FSTORE,
                        Opcodes.ASTORE,
                        Opcodes.LSTORE,
                        Opcodes.DSTORE,
                        Opcodes.RET,
                        -> {
                            println("$opcodeName $subOpcodeName ${readU2()}")
                        }
                        Opcodes.IINC -> {
                            println("$opcodeName $subOpcodeName ${readU2()} ${readU2()}")
                        }
                        else -> throw IllegalStateException()
                    }
                }
                Opcodes.ILOAD,
                Opcodes.LLOAD,
                Opcodes.FLOAD,
                Opcodes.DLOAD,
                Opcodes.ALOAD,
                Opcodes.ISTORE,
                Opcodes.LSTORE,
                Opcodes.FSTORE,
                Opcodes.DSTORE,
                Opcodes.ASTORE,
                Opcodes.RET,
                -> {
                    currentIndex++
                    println("$opcodeName ${readU1()}")
                }
                Opcodes.BIPUSH,
                Opcodes.NEWARRAY,
                -> {
                    currentIndex++
                    println("$opcodeName ${read1()}")
                }
                Opcodes.SIPUSH -> {
                    currentIndex++
                    println("$opcodeName ${read2()}")
                }
                Opcodes.LDC -> {
                    currentIndex++
                    println("$opcodeName ${cp.get(readU1())}")
                }
                Opcodes.LDC_W,
                Opcodes.LDC2_W,
                -> {
                    currentIndex++
                    println("$opcodeName ${cp.get(readU2())}")
                }
                Opcodes.GETSTATIC,
                Opcodes.PUTSTATIC,
                Opcodes.GETFIELD,
                Opcodes.PUTFIELD,
                Opcodes.INVOKEVIRTUAL,
                Opcodes.INVOKESPECIAL,
                Opcodes.INVOKESTATIC,
                Opcodes.NEW,
                Opcodes.ANEWARRAY,
                Opcodes.CHECKCAST,
                Opcodes.INSTANCEOF,
                -> {
                    currentIndex++
                    println("$opcodeName ${cp.get(readU2())}")
                }
                Opcodes.IINC -> {
                    currentIndex++
                    println("$opcodeName ${readU1()} ${read1()}")
                }
                Opcodes.INVOKEINTERFACE -> {
                    currentIndex++
                    println("$opcodeName ${cp.get(readU2())}")
                    currentIndex += 2
                }
                Opcodes.INVOKEDYNAMIC -> {
                    currentIndex++
                    println("$opcodeName ${cp.get(readU2())}")
                    currentIndex += 2
                }
                Opcodes.MULTIANEWARRAY -> {
                    currentIndex += 4
                }
                Opcodes.LOOKUPSWITCH -> {
                    currentIndex += 4 - (currentIndex and 3)
                    readU4()
                    val numPairs = readU4()
                    val keys = IntArray(numPairs)
                    val values = arrayOfNulls<Label>(numPairs)
                    for (i in 0 until numPairs) {
                        keys[i] = readU4()
                        values[i] = labels[readU4()]
                    }
                }
                Opcodes.TABLESWITCH -> {
                    currentIndex += 4 - (currentIndex and 3)
                    labels[readU4()] = Label()
                    val low = readU4()
                    var numTableEntries = readU4() - low + 1
                    while (numTableEntries-- > 0) {
                        labels[readU4()] = Label()
                    }
                }
                else -> throw IllegalStateException("$opcode")
            }
        }
    }

}