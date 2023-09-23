package aed;

public class Horario {

    private int _hora;
    private int _minutos;

    public Horario(int hora, int minutos) {
        _hora = hora;
        _minutos = minutos;
    }

    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minutos;
    }

    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        
        sBuffer.append(_hora);
        sBuffer.append(":");
        sBuffer.append(_minutos);
        
        return sBuffer.toString();
    }

    @Override
    public boolean equals(Object otro) {

        boolean otraEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();
        
        if (otraEsNull || claseDistinta) {
            return false;
        }

        Horario otroHorario =  (Horario) otro;

        boolean res = (_minutos == otroHorario.minutos()) && (_hora == otroHorario.hora());

        return res;
    }

}
