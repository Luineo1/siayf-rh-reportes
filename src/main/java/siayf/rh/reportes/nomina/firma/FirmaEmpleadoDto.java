/*
 * FirmaDTO.java
 * Creado el 08/sep/2017 12:29:01 PM
 * 
 */

package siayf.rh.reportes.nomina.firma;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class FirmaEmpleadoDto implements Serializable {

    private static final long serialVersionUID = -6648191785606871680L;

    private final String filiacion;
    private final String nombre;
    private final String numeroCheque;
    private final BigDecimal importe;

    public FirmaEmpleadoDto(String filiacion, String nombre, String numeroCheque, BigDecimal importe) {
        this.filiacion = filiacion;
        this.nombre = nombre;
        this.numeroCheque = numeroCheque;
        this.importe = importe;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.filiacion);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.numeroCheque);
        hash = 97 * hash + Objects.hashCode(this.importe);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FirmaEmpleadoDto other = (FirmaEmpleadoDto) obj;
        if (!Objects.equals(this.filiacion, other.filiacion)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.numeroCheque, other.numeroCheque)) {
            return false;
        }
        return Objects.equals(this.importe, other.importe);
    }

    @Override
    public String toString() {
        return "FirmaEmpleadoDTO{"
                + "filiacion : " + filiacion
                + ", nombre : " + nombre
                + ", numeroCheque : " + numeroCheque
                + ", importe : " + importe
                + '}';
    }
    
    public static final class Builder {

        private String filiacion;
        private String nombre;
        private String numeroCheque;
        private BigDecimal importe;

        public Builder() {
            filiacion = null;
            nombre = null;
            numeroCheque = null;
            importe = null;
        }

        public Builder setFiliacion(String filiacion) {
            this.filiacion = filiacion;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setNumeroCheque(String numeroCheque) {
            this.numeroCheque = numeroCheque;
            return this;
        }

        public Builder setImporte(BigDecimal importe) {
            this.importe = importe;
            return this;
        }

        public FirmaEmpleadoDto construirFirmaEmpleadoDTO () {
            return new FirmaEmpleadoDto(filiacion, nombre, numeroCheque, importe);
        }
    }
}
