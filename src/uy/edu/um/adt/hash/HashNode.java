package uy.edu.um.adt.hash;

import java.util.Objects;

public class HashNode<K,T> {
    private K key;
    private T data;
    private boolean borrado = false;

    public HashNode(K key, T data) {
        this.key = key;
        this.data = data;

    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;

    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashNode<?, ?> hashNode = (HashNode<?, ?>) o;
        return Objects.equals(key, hashNode.key) && Objects.equals(data, hashNode.data);
    }
}