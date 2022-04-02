package org.inurl.kjx.parser

class Defs {

    companion object {
        private const val ACC_PUBLIC        = 0x0001
        private const val ACC_PRIVATE       = 0x0002
        private const val ACC_PROTECTED     = 0x0004
        private const val ACC_STATIC        = 0x0008
        private const val ACC_FINAL         = 0x0010
        private const val ACC_SUPER         = 0x0020
        private const val ACC_VOLATILE      = 0x0040
        private const val ACC_INTERFACE     = 0x0200
        private const val ACC_ABSTRACT      = 0x0400
        private const val ACC_TRANSIENT     = 0x0800
        private const val ACC_SYNTHETIC     = 0x1000
        private const val ACC_ANNOTATION    = 0x2000
        private const val ACC_ENUM          = 0x4000
        private const val ACC_MODULE        = 0x8000

        fun isPublic(access: Int) = (access and ACC_PUBLIC) != 0
        fun isPrivate(access: Int) = (access and ACC_PRIVATE) != 0
        fun isProtected(access: Int) = (access and ACC_PROTECTED) != 0
        fun isStatic(access: Int) = (access and ACC_STATIC) != 0
        fun isVolatile(access: Int) = (access and ACC_VOLATILE) != 0
        fun isTransient(access: Int) = (access and ACC_TRANSIENT) != 0
        fun isFinal(access: Int) = (access and ACC_FINAL) != 0
        fun isInterface(access: Int) = (access and ACC_INTERFACE) != 0

    }

}