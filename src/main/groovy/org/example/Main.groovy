package org.example

import groovy.transform.CompileStatic

@CompileStatic
class Attribute extends Property {}

@CompileStatic
abstract class ToOne<T extends Property> extends Association<T> {
    ToOne(String name) { super(name) }
}

@CompileStatic
abstract class ToMany<T extends Property> extends Association<T> {
    ToMany(String name) { super(name) }
}

@CompileStatic
abstract class OneToOne<T extends Property> extends ToOne<T> {
    OneToOne(String name) { super(name) }
}

@CompileStatic
abstract class OneToMany<T extends Property> extends ToMany<T> {
    OneToMany(String name) { super(name) }
}

@CompileStatic
abstract class ManyToMany<T extends Property> extends ToMany<T> {
    ManyToMany(String name) { super(name) }
}

@CompileStatic
static void main(String[] args) {

    def oneToOne = new OneToOne<Attribute>('foo') {}
    def oneToMany = new OneToMany<Attribute>('bar') {}

    for (association in [oneToOne, oneToMany]) {
        if (association instanceof ToOne) {
            // This line compiles fine
            def propertyName = association.name
            println "to-one -> $propertyName"
        } else if ((association instanceof OneToMany) || (association instanceof ManyToMany)) {
            def workaround = association.getName() // <-- this is a workaround
            // This line compiles in 3.0.19 but not in 3.0.20-SNAPSHOT
            def propertyName = association.name
            println "to-many -> $propertyName"
        }
    }
}