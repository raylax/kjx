package org.inurl.kjx.index

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import org.inurl.kjx.parser.Annotation
import org.inurl.kjx.parser.Klass

class Index {

    private val classMap: Multimap<String, Klass> = HashMultimap.create(1024, 1)

    private val annotationClassMap: Multimap<String, Annotation> = HashMultimap.create(4096, 5)

    fun add(classes: List<Klass>) {
        for (klass in classes) {
            add(klass)
        }
    }

    private fun add(klass: Klass) {
        val name = klass.getName()
        classMap.put(name, klass)
        for (annotation in klass.annotations) {
            annotation.classRef = klass
            annotationClassMap.put(annotation.getType(), annotation)
        }
    }



}