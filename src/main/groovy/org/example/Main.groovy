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
abstract class ManyToOne<T extends Property> extends ToOne<T> {
    ManyToOne(String name) { super(name) }
}

@CompileStatic
abstract class MappingFactory<T extends Property> {
    ToOne createOneToOne(String name) {
        return new OneToOne<T>(name) {}
    }
    ToMany createOneToMany(String name) {
        return new OneToMany<T>(name) {}
    }
}

@CompileStatic
class DefaultMappingFactory<T extends Property> extends MappingFactory<T> {}

@CompileStatic
static void main(String[] args) {
    def mappingFactory = new DefaultMappingFactory<Attribute>()
    def oneToOne = mappingFactory.createOneToOne('foo')
    def oneToMany = mappingFactory.createOneToMany('bar')
    def manyToMany = mappingFactory.createOneToMany('baz')
    def associations = [oneToOne, oneToMany, manyToMany] as List<Association>

    for (association in associations) {
        if (association instanceof ToOne) {
            // This line compile fine
            def propertyName = association.name
            println "to-one -> $propertyName"
        } else if ((association instanceof OneToMany) || (association instanceof ManyToMany)) {
            // This line compiles in 3.0.19 but not in 3.0.20-SNAPSHOT
            def propertyName = association.name
            println "to-many -> $propertyName"
        }
    }
}