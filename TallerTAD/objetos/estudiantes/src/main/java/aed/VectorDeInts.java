package aed;

public class VectorDeInts implements SecuenciaDeInts {

    private static final int CAPACIDAD_INICIAL = 1;
    private int _longitud; 
    private int[] _array;

    public VectorDeInts() {
        _longitud = 0;
        _array = new int[CAPACIDAD_INICIAL];
    }

    public VectorDeInts(VectorDeInts vector) {
        VectorDeInts vectorConstructor = vector.copiar(); // Aca realizo _longitud veces _new por como lo defini
        _longitud = vectorConstructor.longitud();
        _array = new int[_longitud];
        for (int j = 0; j < _longitud; j++) {
            _array[j] = vector.obtener(j);
        }
    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAtras(int i) {
        if (_longitud == _array.length){ 
            int[] nuevoarray = new int[_longitud + 1];
            for (int j = 0; j < _longitud; j++) {
                nuevoarray[j] = _array[j];
            }
            nuevoarray[_longitud] = i;
            _array = nuevoarray;
        }
        else {
            _array[_longitud] = i;
        }
        _longitud++;
    }

    public int obtener(int i) {
        return _array[i];
    }

    public void quitarAtras() {
        int[] nuevoarray = new int[_longitud];
        for (int i = 0; i < _longitud; i++) {
            nuevoarray[i] = _array[i];
        }
        _longitud--;
        _array = nuevoarray;
    }

    public void modificarPosicion(int indice, int valor) {
        _array[indice] = valor;
    }

    public VectorDeInts copiar() {
        VectorDeInts copiaVector = new VectorDeInts();
        for (int i = 0; i < _longitud; i++) {
            copiaVector.agregarAtras(obtener(i)); // Es lento, crea un array c/ vez que agrega un elemento. 
        }                                         // Se solucionaria creando un array ya del tamaño correcto y asignandole ahi los valores.                                                
        return copiaVector;
    }

}
