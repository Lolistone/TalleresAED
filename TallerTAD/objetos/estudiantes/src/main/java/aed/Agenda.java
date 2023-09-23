package aed;

import java.util.Vector;

public class Agenda {

    private Fecha _fechaActual;
    Vector<Recordatorio> _recordatorios = new Vector<>();

    public Agenda(Fecha fechaActual) {
        _fechaActual = new Fecha(fechaActual.dia(), fechaActual.mes()); // No quiero aliasing.
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        _recordatorios.add(recordatorio);
    }

    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        
        sBuffer.append(_fechaActual.toString() + "\n");
        sBuffer.append("=====" + "\n");

        for (Recordatorio rec : _recordatorios) {
            if (_fechaActual.equals( rec.fecha() )) {
                sBuffer.append(rec.toString() + "\n");
            }
        }

        return sBuffer.toString();
    }

    public void incrementarDia() {
        _fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return _fechaActual;
    }

}
