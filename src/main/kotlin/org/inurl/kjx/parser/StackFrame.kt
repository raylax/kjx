package org.inurl.kjx.parser

abstract class StackFrame(val frameType: Int)

class SameFrame(frameType: Int): StackFrame(frameType)

class SameLocals1StackItemFrame(frameType: Int, stack: VerificationType): StackFrame(frameType)

class SameLocals1StackItemFrameExtended(frameType: Int, offsetDelta: Int, stack: VerificationType): StackFrame(frameType)

class ChopFrame(frameType: Int, offsetDelta: Int): StackFrame(frameType)

class SameFrameExtended(frameType: Int, offsetDelta: Int): StackFrame(frameType)

class AppendFrame(frameType: Int, offsetDelta: Int, locals: List<VerificationType>): StackFrame(frameType)

class FullFrame(frameType: Int, offsetDelta: Int, locals: List<VerificationType>, stacks: List<VerificationType>): StackFrame(frameType)


// Verification Type

open class VerificationType

class TopVariable: VerificationType()

class IntVariable: VerificationType()

class FloatVariable: VerificationType()

class NullVariable: VerificationType()

class UninitializedThisVariable: VerificationType()

class ObjectVariable(cp: ConstantPool, cpIndex: Int): VerificationType()

class UninitializedVariable(private val offset: Int): VerificationType()

class LongVariable: VerificationType()

class DoubleVariable: VerificationType()
