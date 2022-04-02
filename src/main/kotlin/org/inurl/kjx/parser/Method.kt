package org.inurl.kjx.parser

import java.util.*

class Method(
    private val cp: ConstantPool,
    private val access: Int,
    private val nameIndex: Int,
    private val descriptorIndex: Int) {

    var annotations: List<Annotation> = mutableListOf()

    lateinit var code: Code

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
        if (Defs.isFinal(access)) joiner.add("final")
        val descriptor = cp.parseDescriptor(descriptorIndex)
        joiner.add(descriptor.returnType)
        val args = "(" + descriptor.argTypes.joinToString(", ") + ")"
        joiner.add(getName() + args)
        return joiner.toString()
    }

}