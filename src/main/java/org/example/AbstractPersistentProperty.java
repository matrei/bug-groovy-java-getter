package org.example;

public abstract class AbstractPersistentProperty<T extends Property> implements PersistentProperty<T> {

    protected final String name;

    public AbstractPersistentProperty(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
