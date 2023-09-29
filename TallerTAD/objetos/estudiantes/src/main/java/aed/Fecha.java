package aed;

public class Fecha {

    private int _dia;
    private int _mes;

    public Fecha(int dia, int mes) {
        _dia = dia;
        _mes = mes;
    }

    public Fecha(Fecha fecha) {
        _dia = fecha.dia();
        _mes = fecha.mes();
    }

    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }

    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        
        sBuffer.append(_dia);
        sBuffer.append("/");
        sBuffer.append(_mes);
        
        return sBuffer.toString();
    }

    @Override // Sobreescribo pues el método equals ya está definido.

    public boolean equals(Object otra) {

        // equals puede recibir cualquier objeto.
        boolean otraEsNull = (otra == null);
        boolean claseDistinta = otra.getClass() != this.getClass();
        
        if (otraEsNull || claseDistinta) {
            return false;
        }

        // casteo para poder usar los metodos de Fecha.
        Fecha otraFecha = (Fecha) otra;

        boolean res = (_dia == otraFecha.dia()) && (_mes == otraFecha.mes());

        return res;
    }

    public void incrementarDia() {
        if (_dia == diasEnMes(_mes)) {
            _dia = 1;
            if (_mes == 12) {
                _mes = 1;
            }
            else {
                _mes++;
            }
        }
        else {
            _dia++;
        } 
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
