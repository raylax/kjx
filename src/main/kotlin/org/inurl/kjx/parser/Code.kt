package org.inurl.kjx.parser

class Code(val maxStack: Int, val maxLocals: Int, ops: ByteArray) {

    lateinit var exceptionTable: List<Exception>

    lateinit var lineNumberTable: List<LineNumber>

    lateinit var localVariables: List<LocalVariable>

    lateinit var stackFrames: List<StackFrame>

}