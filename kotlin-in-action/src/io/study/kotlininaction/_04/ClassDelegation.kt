package io.study.kotlininaction._04

class DelegatingCollection<T>(innerList: Collection<T> = ArrayList()) : Collection<T> by innerList {}

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        this.objectsAdded++
        return this.
        innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        this.objectsAdded += elements.size
        return this.innerSet.addAll(elements)
    }
}