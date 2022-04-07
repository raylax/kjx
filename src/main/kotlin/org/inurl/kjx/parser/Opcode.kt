package org.inurl.kjx.parser

open class Opcode {

    class NOP: Opcode()

    class ACONST: Opcode()

    class ICONST_M1: Opcode()

    class CONST(val value: Number): Opcode()

    class ALOAD(val arrayIndex: Int, val index: Int)

    class ASTORE(val arrayIndex: Int)

    class POP: Opcode()

    class POP2: Opcode()

    class DUP: Opcode()

    class DUP_X1: Opcode()

    class DUP_X2: Opcode()

    class DUP2: Opcode()

    class DUP2_X1: Opcode()

    class DUP2_X2: Opcode()

}