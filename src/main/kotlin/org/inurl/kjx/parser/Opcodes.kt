package org.inurl.kjx.parser

class Opcodes {

    companion object {

        const val NOP = 0
        const val ACONST_NULL = 1
        const val ICONST_M1 = 2
        const val ICONST_0 = 3
        const val ICONST_1 = 4
        const val ICONST_2 = 5
        const val ICONST_3 = 6
        const val ICONST_4 = 7
        const val ICONST_5 = 8
        const val LCONST_0 = 9
        const val LCONST_1 = 10
        const val FCONST_0 = 11
        const val FCONST_1 = 12
        const val FCONST_2 = 13
        const val DCONST_0 = 14
        const val DCONST_1 = 15
        const val BIPUSH = 16
        const val SIPUSH = 17
        const val LDC = 18
        const val LDC_W = 19
        const val LDC2_W = 20
        const val ILOAD = 21
        const val LLOAD = 22
        const val FLOAD = 23
        const val DLOAD = 24
        const val ALOAD = 25
        const val ILOAD_0 = 26
        const val ILOAD_1 = 27
        const val ILOAD_2 = 28
        const val ILOAD_3 = 29
        const val LLOAD_0 = 30
        const val LLOAD_1 = 31
        const val LLOAD_2 = 32
        const val LLOAD_3 = 33
        const val FLOAD_0 = 34
        const val FLOAD_1 = 35
        const val FLOAD_2 = 36
        const val FLOAD_3 = 37
        const val DLOAD_0 = 38
        const val DLOAD_1 = 39
        const val DLOAD_2 = 40
        const val DLOAD_3 = 41
        const val ALOAD_0 = 42
        const val ALOAD_1 = 43
        const val ALOAD_2 = 44
        const val ALOAD_3 = 45
        const val IALOAD = 46
        const val LALOAD = 47
        const val FALOAD = 48
        const val DALOAD = 49
        const val AALOAD = 50
        const val BALOAD = 51
        const val CALOAD = 52
        const val SALOAD = 53
        const val ISTORE = 54
        const val LSTORE = 55
        const val FSTORE = 56
        const val DSTORE = 57
        const val ASTORE = 58
        const val ISTORE_0 = 59
        const val ISTORE_1 = 60
        const val ISTORE_2 = 61
        const val ISTORE_3 = 62
        const val LSTORE_0 = 63
        const val LSTORE_1 = 64
        const val LSTORE_2 = 65
        const val LSTORE_3 = 66
        const val FSTORE_0 = 67
        const val FSTORE_1 = 68
        const val FSTORE_2 = 69
        const val FSTORE_3 = 70
        const val DSTORE_0 = 71
        const val DSTORE_1 = 72
        const val DSTORE_2 = 73
        const val DSTORE_3 = 74
        const val ASTORE_0 = 75
        const val ASTORE_1 = 76
        const val ASTORE_2 = 77
        const val ASTORE_3 = 78
        const val IASTORE = 79
        const val LASTORE = 80
        const val FASTORE = 81
        const val DASTORE = 82
        const val AASTORE = 83
        const val BASTORE = 84
        const val CASTORE = 85
        const val SASTORE = 86
        const val POP = 87
        const val POP2 = 88
        const val DUP = 89
        const val DUP_X1 = 90
        const val DUP_X2 = 91
        const val DUP2 = 92
        const val DUP2_X1 = 93
        const val DUP2_X2 = 94
        const val SWAP = 95
        const val IADD = 96
        const val LADD = 97
        const val FADD = 98
        const val DADD = 99
        const val ISUB = 100
        const val LSUB = 101
        const val FSUB = 102
        const val DSUB = 103
        const val IMUL = 104
        const val LMUL = 105
        const val FMUL = 106
        const val DMUL = 107
        const val IDIV = 108
        const val LDIV = 109
        const val FDIV = 110
        const val DDIV = 111
        const val IREM = 112
        const val LREM = 113
        const val FREM = 114
        const val DREM = 115
        const val INEG = 116
        const val LNEG = 117
        const val FNEG = 118
        const val DNEG = 119
        const val ISHL = 120
        const val LSHL = 121
        const val ISHR = 122
        const val LSHR = 123
        const val IUSHR = 124
        const val LUSHR = 125
        const val IAND = 126
        const val LAND = 127
        const val IOR = 128
        const val LOR = 129
        const val IXOR = 130
        const val LXOR = 131
        const val IINC = 132
        const val I2L = 133
        const val I2F = 134
        const val I2D = 135
        const val L2I = 136
        const val L2F = 137
        const val L2D = 138
        const val F2I = 139
        const val F2L = 140
        const val F2D = 141
        const val D2I = 142
        const val D2L = 143
        const val D2F = 144
        const val I2B = 145
        const val I2C = 146
        const val I2S = 147
        const val LCMP = 148
        const val FCMPL = 149
        const val FCMPG = 150
        const val DCMPL = 151
        const val DCMPG = 152
        const val IFEQ = 153
        const val IFNE = 154
        const val IFLT = 155
        const val IFGE = 156
        const val IFGT = 157
        const val IFLE = 158
        const val IF_ICMPEQ = 159
        const val IF_ICMPNE = 160
        const val IF_ICMPLT = 161
        const val IF_ICMPGE = 162
        const val IF_ICMPGT = 163
        const val IF_ICMPLE = 164
        const val IF_ACMPEQ = 165
        const val IF_ACMPNE = 166
        const val GOTO = 167
        const val JSR = 168
        const val RET = 169
        const val TABLESWITCH = 170
        const val LOOKUPSWITCH = 171
        const val IRETURN = 172
        const val LRETURN = 173
        const val FRETURN = 174
        const val DRETURN = 175
        const val ARETURN = 176
        const val RETURN = 177
        const val GETSTATIC = 178
        const val PUTSTATIC = 179
        const val GETFIELD = 180
        const val PUTFIELD = 181
        const val INVOKEVIRTUAL = 182
        const val INVOKESPECIAL = 183
        const val INVOKESTATIC = 184
        const val INVOKEINTERFACE = 185
        const val INVOKEDYNAMIC = 186
        const val NEW = 187
        const val NEWARRAY = 188
        const val ANEWARRAY = 189
        const val ARRAYLENGTH = 190
        const val ATHROW = 191
        const val CHECKCAST = 192
        const val INSTANCEOF = 193
        const val MONITORENTER = 194
        const val MONITOREXIT = 195
        const val WIDE = 196
        const val MULTIANEWARRAY = 197
        const val IFNULL = 198
        const val IFNONNULL = 199
        const val GOTO_W = 200
        const val JSR_W = 201

        private val OPCODE_CACHE = mutableMapOf<Int, String>()

        fun getName(opcode: Int) = OPCODE_CACHE[opcode]

        init {
            OPCODE_CACHE[0] = "NOP"
            OPCODE_CACHE[1] = "ACONST_NULL"
            OPCODE_CACHE[2] = "ICONST_M1"
            OPCODE_CACHE[3] = "ICONST_0"
            OPCODE_CACHE[4] = "ICONST_1"
            OPCODE_CACHE[5] = "ICONST_2"
            OPCODE_CACHE[6] = "ICONST_3"
            OPCODE_CACHE[7] = "ICONST_4"
            OPCODE_CACHE[8] = "ICONST_5"
            OPCODE_CACHE[9] = "LCONST_0"
            OPCODE_CACHE[10] = "LCONST_1"
            OPCODE_CACHE[11] = "FCONST_0"
            OPCODE_CACHE[12] = "FCONST_1"
            OPCODE_CACHE[13] = "FCONST_2"
            OPCODE_CACHE[14] = "DCONST_0"
            OPCODE_CACHE[15] = "DCONST_1"
            OPCODE_CACHE[16] = "BIPUSH"
            OPCODE_CACHE[17] = "SIPUSH"
            OPCODE_CACHE[18] = "LDC"
            OPCODE_CACHE[19] = "LDC_W"
            OPCODE_CACHE[20] = "LDC2_W"
            OPCODE_CACHE[21] = "ILOAD"
            OPCODE_CACHE[22] = "LLOAD"
            OPCODE_CACHE[23] = "FLOAD"
            OPCODE_CACHE[24] = "DLOAD"
            OPCODE_CACHE[25] = "ALOAD"
            OPCODE_CACHE[26] = "ILOAD_0"
            OPCODE_CACHE[27] = "ILOAD_1"
            OPCODE_CACHE[28] = "ILOAD_2"
            OPCODE_CACHE[29] = "ILOAD_3"
            OPCODE_CACHE[30] = "LLOAD_0"
            OPCODE_CACHE[31] = "LLOAD_1"
            OPCODE_CACHE[32] = "LLOAD_2"
            OPCODE_CACHE[33] = "LLOAD_3"
            OPCODE_CACHE[34] = "FLOAD_0"
            OPCODE_CACHE[35] = "FLOAD_1"
            OPCODE_CACHE[36] = "FLOAD_2"
            OPCODE_CACHE[37] = "FLOAD_3"
            OPCODE_CACHE[38] = "DLOAD_0"
            OPCODE_CACHE[39] = "DLOAD_1"
            OPCODE_CACHE[40] = "DLOAD_2"
            OPCODE_CACHE[41] = "DLOAD_3"
            OPCODE_CACHE[42] = "ALOAD_0"
            OPCODE_CACHE[43] = "ALOAD_1"
            OPCODE_CACHE[44] = "ALOAD_2"
            OPCODE_CACHE[45] = "ALOAD_3"
            OPCODE_CACHE[46] = "IALOAD"
            OPCODE_CACHE[47] = "LALOAD"
            OPCODE_CACHE[48] = "FALOAD"
            OPCODE_CACHE[49] = "DALOAD"
            OPCODE_CACHE[50] = "AALOAD"
            OPCODE_CACHE[51] = "BALOAD"
            OPCODE_CACHE[52] = "CALOAD"
            OPCODE_CACHE[53] = "SALOAD"
            OPCODE_CACHE[54] = "ISTORE"
            OPCODE_CACHE[55] = "LSTORE"
            OPCODE_CACHE[56] = "FSTORE"
            OPCODE_CACHE[57] = "DSTORE"
            OPCODE_CACHE[58] = "ASTORE"
            OPCODE_CACHE[59] = "ISTORE_0"
            OPCODE_CACHE[60] = "ISTORE_1"
            OPCODE_CACHE[61] = "ISTORE_2"
            OPCODE_CACHE[62] = "ISTORE_3"
            OPCODE_CACHE[63] = "LSTORE_0"
            OPCODE_CACHE[64] = "LSTORE_1"
            OPCODE_CACHE[65] = "LSTORE_2"
            OPCODE_CACHE[66] = "LSTORE_3"
            OPCODE_CACHE[67] = "FSTORE_0"
            OPCODE_CACHE[68] = "FSTORE_1"
            OPCODE_CACHE[69] = "FSTORE_2"
            OPCODE_CACHE[70] = "FSTORE_3"
            OPCODE_CACHE[71] = "DSTORE_0"
            OPCODE_CACHE[72] = "DSTORE_1"
            OPCODE_CACHE[73] = "DSTORE_2"
            OPCODE_CACHE[74] = "DSTORE_3"
            OPCODE_CACHE[75] = "ASTORE_0"
            OPCODE_CACHE[76] = "ASTORE_1"
            OPCODE_CACHE[77] = "ASTORE_2"
            OPCODE_CACHE[78] = "ASTORE_3"
            OPCODE_CACHE[79] = "IASTORE"
            OPCODE_CACHE[80] = "LASTORE"
            OPCODE_CACHE[81] = "FASTORE"
            OPCODE_CACHE[82] = "DASTORE"
            OPCODE_CACHE[83] = "AASTORE"
            OPCODE_CACHE[84] = "BASTORE"
            OPCODE_CACHE[85] = "CASTORE"
            OPCODE_CACHE[86] = "SASTORE"
            OPCODE_CACHE[87] = "POP"
            OPCODE_CACHE[88] = "POP2"
            OPCODE_CACHE[89] = "DUP"
            OPCODE_CACHE[90] = "DUP_X1"
            OPCODE_CACHE[91] = "DUP_X2"
            OPCODE_CACHE[92] = "DUP2"
            OPCODE_CACHE[93] = "DUP2_X1"
            OPCODE_CACHE[94] = "DUP2_X2"
            OPCODE_CACHE[95] = "SWAP"
            OPCODE_CACHE[96] = "IADD"
            OPCODE_CACHE[97] = "LADD"
            OPCODE_CACHE[98] = "FADD"
            OPCODE_CACHE[99] = "DADD"
            OPCODE_CACHE[100] = "ISUB"
            OPCODE_CACHE[101] = "LSUB"
            OPCODE_CACHE[102] = "FSUB"
            OPCODE_CACHE[103] = "DSUB"
            OPCODE_CACHE[104] = "IMUL"
            OPCODE_CACHE[105] = "LMUL"
            OPCODE_CACHE[106] = "FMUL"
            OPCODE_CACHE[107] = "DMUL"
            OPCODE_CACHE[108] = "IDIV"
            OPCODE_CACHE[109] = "LDIV"
            OPCODE_CACHE[110] = "FDIV"
            OPCODE_CACHE[111] = "DDIV"
            OPCODE_CACHE[112] = "IREM"
            OPCODE_CACHE[113] = "LREM"
            OPCODE_CACHE[114] = "FREM"
            OPCODE_CACHE[115] = "DREM"
            OPCODE_CACHE[116] = "INEG"
            OPCODE_CACHE[117] = "LNEG"
            OPCODE_CACHE[118] = "FNEG"
            OPCODE_CACHE[119] = "DNEG"
            OPCODE_CACHE[120] = "ISHL"
            OPCODE_CACHE[121] = "LSHL"
            OPCODE_CACHE[122] = "ISHR"
            OPCODE_CACHE[123] = "LSHR"
            OPCODE_CACHE[124] = "IUSHR"
            OPCODE_CACHE[125] = "LUSHR"
            OPCODE_CACHE[126] = "IAND"
            OPCODE_CACHE[127] = "LAND"
            OPCODE_CACHE[128] = "IOR"
            OPCODE_CACHE[129] = "LOR"
            OPCODE_CACHE[130] = "IXOR"
            OPCODE_CACHE[131] = "LXOR"
            OPCODE_CACHE[132] = "IINC"
            OPCODE_CACHE[133] = "I2L"
            OPCODE_CACHE[134] = "I2F"
            OPCODE_CACHE[135] = "I2D"
            OPCODE_CACHE[136] = "L2I"
            OPCODE_CACHE[137] = "L2F"
            OPCODE_CACHE[138] = "L2D"
            OPCODE_CACHE[139] = "F2I"
            OPCODE_CACHE[140] = "F2L"
            OPCODE_CACHE[141] = "F2D"
            OPCODE_CACHE[142] = "D2I"
            OPCODE_CACHE[143] = "D2L"
            OPCODE_CACHE[144] = "D2F"
            OPCODE_CACHE[145] = "I2B"
            OPCODE_CACHE[146] = "I2C"
            OPCODE_CACHE[147] = "I2S"
            OPCODE_CACHE[148] = "LCMP"
            OPCODE_CACHE[149] = "FCMPL"
            OPCODE_CACHE[150] = "FCMPG"
            OPCODE_CACHE[151] = "DCMPL"
            OPCODE_CACHE[152] = "DCMPG"
            OPCODE_CACHE[153] = "IFEQ"
            OPCODE_CACHE[154] = "IFNE"
            OPCODE_CACHE[155] = "IFLT"
            OPCODE_CACHE[156] = "IFGE"
            OPCODE_CACHE[157] = "IFGT"
            OPCODE_CACHE[158] = "IFLE"
            OPCODE_CACHE[159] = "IF_ICMPEQ"
            OPCODE_CACHE[160] = "IF_ICMPNE"
            OPCODE_CACHE[161] = "IF_ICMPLT"
            OPCODE_CACHE[162] = "IF_ICMPGE"
            OPCODE_CACHE[163] = "IF_ICMPGT"
            OPCODE_CACHE[164] = "IF_ICMPLE"
            OPCODE_CACHE[165] = "IF_ACMPEQ"
            OPCODE_CACHE[166] = "IF_ACMPNE"
            OPCODE_CACHE[167] = "GOTO"
            OPCODE_CACHE[168] = "JSR"
            OPCODE_CACHE[169] = "RET"
            OPCODE_CACHE[170] = "TABLESWITCH"
            OPCODE_CACHE[171] = "LOOKUPSWITCH"
            OPCODE_CACHE[172] = "IRETURN"
            OPCODE_CACHE[173] = "LRETURN"
            OPCODE_CACHE[174] = "FRETURN"
            OPCODE_CACHE[175] = "DRETURN"
            OPCODE_CACHE[176] = "ARETURN"
            OPCODE_CACHE[177] = "RETURN"
            OPCODE_CACHE[178] = "GETSTATIC"
            OPCODE_CACHE[179] = "PUTSTATIC"
            OPCODE_CACHE[180] = "GETFIELD"
            OPCODE_CACHE[181] = "PUTFIELD"
            OPCODE_CACHE[182] = "INVOKEVIRTUAL"
            OPCODE_CACHE[183] = "INVOKESPECIAL"
            OPCODE_CACHE[184] = "INVOKESTATIC"
            OPCODE_CACHE[185] = "INVOKEINTERFACE"
            OPCODE_CACHE[186] = "INVOKEDYNAMIC"
            OPCODE_CACHE[187] = "NEW"
            OPCODE_CACHE[188] = "NEWARRAY"
            OPCODE_CACHE[189] = "ANEWARRAY"
            OPCODE_CACHE[190] = "ARRAYLENGTH"
            OPCODE_CACHE[191] = "ATHROW"
            OPCODE_CACHE[192] = "CHECKCAST"
            OPCODE_CACHE[193] = "INSTANCEOF"
            OPCODE_CACHE[194] = "MONITORENTER"
            OPCODE_CACHE[195] = "MONITOREXIT"
            OPCODE_CACHE[196] = "WIDE"
            OPCODE_CACHE[197] = "MULTIANEWARRAY"
            OPCODE_CACHE[198] = "IFNULL"
            OPCODE_CACHE[199] = "IFNONNULL"
            OPCODE_CACHE[200] = "GOTO_W"
            OPCODE_CACHE[201] = "JSR_W"
        }

    }

}