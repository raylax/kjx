package org.inurl.kjx.parser

class Interface(private val cp: ConstantPool, private val nameIndex: Int) {

    fun getName() = cp.getString(nameIndex)

}