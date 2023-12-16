package org.example;

public abstract class Association<T extends Property> extends AbstractPersistentProperty<T> {
    public Association(String name) {
        super(name);
    }
}
