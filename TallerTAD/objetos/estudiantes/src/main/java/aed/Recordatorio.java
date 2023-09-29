package aed;

public class Recordatorio {

    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;

        // Quiero evitar el aliasing
        _fecha = new Fecha(fecha.dia(), fecha.mes()); 
        _horario = new Horario(horario.hora(), horario.minutos());
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        Fecha _copia = new Fecha(_fecha);
        return _copia;
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        
        sBuffer.append(_mensaje + " ");
        sBuffer.append("@ ");
        sBuffer.append(_fecha.toString() + " ");
        sBuffer.append(_horario.toString());
        
        return sBuffer.toString();
    }

}
