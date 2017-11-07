/*
 * ProgramaDto.java
 * Creado el 27/jun/2017 3:15:11 PM
 *
 */

package siayf.rh.reportes.nomina.firma;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class ProgramaDto implements Serializable, Comparable<ProgramaDto>, Iterable<FirmaEmpleadoDto> {

    private static final long serialVersionUID = 3213100192452199926L;

    private final Integer idPrograma;
    private final String programa;
    private final Date inicioPeriodo;
    private final Date finPeriodo;
    private final Map<String, FirmaEmpleadoDto> firmasEmpleados;

    public ProgramaDto(Integer idPrograma, String programa, Date inicioPeriodo, Date finPeriodo, Map<String, FirmaEmpleadoDto> firmasEmpleados) {
        this.idPrograma = idPrograma;
        this.programa = programa;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.firmasEmpleados = firmasEmpleados;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public Map<String, FirmaEmpleadoDto> getFirmasEmpleados() {
        return firmasEmpleados;
    }

    @Override
    public int compareTo(ProgramaDto o) {
        if (idPrograma == null && o.idPrograma == null) {
            return 0;
        }
        if (idPrograma == null) {
            return -1;
        }
        if (o.idPrograma == null) {
            return 1;
        }

        return idPrograma.compareTo(o.idPrograma);
    }

    @Override
    public Iterator<FirmaEmpleadoDto> iterator() {
        return firmasEmpleados.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.idPrograma);
        hash = 13 * hash + Objects.hashCode(this.programa);
        hash = 13 * hash + Objects.hashCode(this.inicioPeriodo);
        hash = 13 * hash + Objects.hashCode(this.finPeriodo);
        hash = 13 * hash + Objects.hashCode(this.firmasEmpleados);
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
        final ProgramaDto other = (ProgramaDto) obj;
        if (!Objects.equals(this.programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(this.idPrograma, other.idPrograma)) {
            return false;
        }
        if (!Objects.equals(this.inicioPeriodo, other.inicioPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.finPeriodo, other.finPeriodo)) {
            return false;
        }
        return Objects.equals(this.firmasEmpleados, other.firmasEmpleados);
    }

    @Override
    public String toString() {
        return "ProgramaDTO{" 
                + "idPrograma : " + idPrograma
                + ", programa : " + programa
                + ", inicioPeriodo : " + inicioPeriodo
                + ", finPeriodo : " + finPeriodo
                + ", firmasEmpleados : [" + firmasEmpleados
                + "]}";
    }

    public static final class Builder {

        private Integer idPrograma;
        private String programa;
        private Date inicioPeriodo;
        private Date finPeriodo;
        private Map<String, FirmaEmpleadoDto> firmasEmpleados;

        public Builder(Integer idPrograma, String programa, Date inicioPeriodo, Date finPeriodo) {
            this.idPrograma =  idPrograma;
            this.programa = programa;
            this.inicioPeriodo = inicioPeriodo;
            this.finPeriodo = finPeriodo;
            firmasEmpleados = null;
        }

        public Builder setIdPrograma(Integer idPrograma) {
            this.idPrograma = idPrograma;
            return this;
        }

        public Builder setPrograma(String programa) {
            this.programa = programa;
            return this;
        }

        public Builder setInicioPeriodo(Date inicioPeriodo) {
            this.inicioPeriodo = inicioPeriodo;
            return this;
        }

        public Builder setFinPeriodo(Date finPeriodo) {
            this.finPeriodo = finPeriodo;
            return this;
        }

        public Builder setFirmasEmpleados(Map<String, FirmaEmpleadoDto> firmasEmpleados) {
            this.firmasEmpleados = firmasEmpleados;
            return this;
        }

        public ProgramaDto construirProgramaDTO() {
            return new ProgramaDto(idPrograma, programa, inicioPeriodo, finPeriodo, firmasEmpleados);
        }
    }

}
