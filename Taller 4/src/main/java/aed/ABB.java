package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int _cardinal;
    private T _minAnterior;
    private T _maxAnterior;
    private T _min;
    private T _max;

    private class Nodo {
        private Nodo _padre;
        private Nodo _izq;
        private Nodo _der;
        private T _valor;

        public Nodo(T v){
            _padre = null;
            _izq = null;
            _der = null;
            _valor = v;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        return _min;
    }

    public T maximo(){
        return _max;
    }

    public void insertar(T elem){

        if (_cardinal != 0) {
            if (elem.compareTo(_max) > 0) {
                _maxAnterior = _max;
                _max = elem;
            }
            else if (elem.compareTo(_min) < 0) {
                _minAnterior = _min;
                _min = elem;
            }
        }
        else {
            _max = elem;
            _min = elem;
        }
            
        Nodo nuevoNodo = new Nodo(elem);
        if (_raiz == null) {
            _raiz = nuevoNodo;
            _cardinal += 1;
        }
        else {
            if (!(pertenece(elem))) {
                insertarRecursivo(_raiz, nuevoNodo);
            }
        }
    }            


    private void insertarRecursivo(Nodo actual, Nodo buscado) {
        
        if (buscado._valor.compareTo(actual._valor) > 0) {
            if (actual._der == null) {
                actual._der = buscado;
                buscado._padre = actual;
                _cardinal++;
            }
            else {
                insertarRecursivo(actual._der, buscado);
            }
        }

        else if (buscado._valor.compareTo(actual._valor) < 0) {
            if (actual._izq == null) {
                actual._izq = buscado;
                buscado._padre = actual;
                _cardinal++;
            }
            else {
                insertarRecursivo(actual._izq, buscado);
            }
        }
    }

    public boolean pertenece(T elem) {
        if (_raiz == null) {
            return false;
        }
        else {
            Nodo desde = _raiz;
            if (elem.compareTo(desde._valor) == 0) {
                return true;
            }
            else {
                while (desde != null) {
                    if (elem.compareTo(desde._valor) > 0) {
                        desde = desde._der;
                    }
                    else if (elem.compareTo(desde._valor) < 0) {
                        desde = desde._izq;
                    }

                    if (desde != null) {
                        if (elem.compareTo(desde._valor) == 0){
                            return true;
                        }
                    }
                }
                return false;
            }
        }    
    }

    

    public void eliminar(T elem){
        Nodo nodo = buscarNodo(_raiz, elem);
        if (nodo._der == null && nodo._izq == null) {
            reemplazar(nodo, null);
        }
        else if (nodo._der != null && nodo._izq == null) {
            reemplazar(nodo, nodo._der);
        }
        else if (nodo._der == null && nodo._izq != null) {
            reemplazar(nodo, nodo._izq);
        }
        else {
            T pred = predecesor(elem);
            eliminar(pred);
            nodo._valor = pred;
            _cardinal++; // Feo..
        }            
        _cardinal--;

        if (nodo._valor == _max) {
            _max = _maxAnterior;
        }
        else if (nodo._valor == _max){
            _min = _minAnterior;
        }
    }

    private Nodo buscarNodo(Nodo desde, T elem) {
        if (desde._valor.compareTo(elem) == 0) {
            return desde;
        }
        else if (desde._valor.compareTo(elem) > 0) {
            return buscarNodo(desde._izq, elem);
        }
        else if (desde._valor.compareTo(elem) < 0){
            return buscarNodo(desde._der, elem);
        }
            return null;
    }

    private void reemplazar(Nodo nodo, Nodo reemp) {
        if (nodo._padre != null) {
            if (nodo._padre._valor.compareTo(nodo._valor) < 0) {
                nodo._padre._der = reemp;
                if (reemp != null) {
                    reemp._padre = nodo._padre;   
                }
                nodo = null;
            }
            else {
                nodo._padre._izq = reemp;
                if (reemp != null) {
                    reemp._padre = nodo._padre;   
                }
                nodo = null;                 
            }
            }
        else {
            if (nodo._der != null) {
                _raiz = nodo._der;
                nodo = null;
            }
            else if (nodo._izq != null){
                _raiz = nodo._izq;
                nodo = null;                
            }
            else {
                _raiz = null;
            }
            if (reemp != null) {
                reemp._padre = null;
            }
        }  
    }

    private T predecesor(T elem) {
        Nodo nodo = buscarNodo(_raiz, elem);
        Nodo pred = nodo._izq;
        while (pred._der != null) {
            pred = pred._der;
        }
        return pred._valor;
    }


    // Version más eficiente del ToString usando StringBuffer y recursión. 
    public String toString(){
        StringBuffer sb = new StringBuffer("{");
        sb.append(inorder(_raiz));
        sb.append("}");
        return sb.toString();
    }

    public StringBuffer inorder(Nodo actual) {
        StringBuffer sb = new StringBuffer("");
        if (actual != null) {
            sb.append(inorder(actual._izq));
            if (actual._izq != null) {
                sb.append(",");                        
            }
            sb.append(actual._valor);
            if (actual._der != null) {
                sb.append(",");
            }
            sb.append(inorder(actual._der));
        }
        return sb;  
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            boolean res = _actual._der != null || _actual._padre != null;
            return res;
        }
    
        public T siguiente() {
            Nodo posibleSucesor;

            if (_actual._der != null) {
                posibleSucesor = _actual._der;
                while (posibleSucesor._izq != null) {
                    posibleSucesor = posibleSucesor._izq;
                }
            }

            else {
                if (_actual._valor.compareTo(_actual._padre._valor) < 0)
                    posibleSucesor = _actual._padre;
                else {
                    posibleSucesor = _actual;
                    if (_actual._valor != _max) {
                        while (posibleSucesor._valor.compareTo(posibleSucesor._padre._valor) > 0) {
                            posibleSucesor = posibleSucesor._padre;
                        }
                        posibleSucesor = posibleSucesor._padre;
                    }
                }
            }

            T res = _actual._valor;
            _actual = posibleSucesor;

            return res;
        }
    }

    public Iterador<T> iterador() {
        ABB_Iterador it = new ABB_Iterador();
        it._actual = buscarNodo(_raiz, _min);
        return it;
    }

}
