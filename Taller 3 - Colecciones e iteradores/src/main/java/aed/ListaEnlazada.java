package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo fst;
    private Nodo lst;
    private int size;

    private class Nodo {
        T val;
        Nodo prev;
        Nodo pxmo;
        public Nodo(T v) {
            val = v;
            prev = null;
            pxmo = null;
        }
    }

    public ListaEnlazada() {
        fst = null;
        lst = null;
        size = 0;
    }

    public int longitud() {
        return size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (fst != null) {
            fst.prev = nuevo;
        }
        nuevo.pxmo = fst;
        fst = nuevo;
        if (size == 0) {
            lst = nuevo;
        }
        size++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (lst != null) {
            lst.pxmo = nuevo;
        }
        nuevo.prev = lst;
        lst = nuevo;
        if (size == 0) {
            fst = nuevo;
        }
        size++;
    }

    public T obtener(int i) {
        Nodo actual;
        if (i > size/2) {
            actual = lst;
            for (int j = size - 1; j != i; j--) {
                actual = actual.prev;
            }
        }
        else {
            actual = fst;
            for (int j = 0; j != i; j++) {
                actual = actual.pxmo;
            }
        }
        return actual.val;
    }

    public void eliminar(int i) {

        if (i == 0) {
            fst = fst.pxmo;
            // Cuando tengo un solo elemento (y lo elimino) fst apunta a null
            if (fst != null) {
                fst.prev = null;
            }
            else {
                lst = null;
            }
        }

        else {

            Nodo actual = fst;
            for (int j = 0; j < i; j++) {
                actual = actual.pxmo;
            }
            actual.prev.pxmo = actual.pxmo;

            // Si estoy en la ultima posición, previo.pxmo es null.
            if (actual.pxmo != null) {
                actual.pxmo.prev = actual.prev;
            }
            else {
                lst = actual.prev;
            }
        }
        size--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual;
        if (indice > size/2) {
            actual = lst;
            for (int j = size - 1; j != indice; j--) {
                actual = actual.prev;
            }
        }
        else {
            actual = fst;
            for (int j = 0; j != indice; j++) {
                actual = actual.pxmo;
            }
        }
        actual.val = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<T>();
        Nodo actual= fst;
        for (int i = 0; i < size; i++) {
            copia.agregarAtras(actual.val);
            actual = actual.pxmo;
        }
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        ListaEnlazada<T> copia = lista.copiar();
        fst = copia.fst;
        lst = copia.lst;
        size = copia.longitud();
    }
    
    @Override
    public String toString() {

        Vector<T> elem = new Vector<T>(size);
        for (int i = 0; i < size; i++) {
            elem.add(i, obtener(i));
        }

        return elem.toString();
    }

    private class ListaIterador implements Iterador<T> {
        int pos;

        public ListaIterador() {
            pos = 0;
        }

        public boolean haySiguiente() {
            boolean res = (pos < size);
            return res;
        }
        
        public boolean hayAnterior() {
	        boolean res = (pos > 0 && size > 0);
            return res;
        }

        public T siguiente() {
	        int i = pos;
            pos++;
            return obtener(i);
        }
        
        public T anterior() {
            pos--;
	        int i = pos;
            return obtener(i);
        }
    }

    public Iterador<T> iterador() {
	    ListaIterador it = new ListaIterador();
        return it;
    }

}
