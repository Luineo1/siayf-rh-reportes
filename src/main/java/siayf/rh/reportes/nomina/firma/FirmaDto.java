/*
 * FirmaDto.java
 * Creado el 08/sep/2017 12:34:48 PM
 * 
 */

package siayf.rh.reportes.nomina.firma;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class FirmaDto implements Serializable, Iterable<UnidadResponsableDto> {

    private static final long serialVersionUID = 7690585164049594702L;

    private final Integer idProductoNomina;
    private String quincena;
    private final Date fechaPago;
    private final Map<String, UnidadResponsableDto> unidadesResponsables;
    private final String nombreElaboro;
    private final String cargoElaboro;
    private final String nombreReviso;
    private final String cargoReviso;
    private final String nombreAutorizo;
    private final String cargoAutorizo;

    public FirmaDto(Integer idProductoNomina, Date fechaPago, Map<String, UnidadResponsableDto> unidadesResponsables, String nombreElaboro, String cargoElaboro, String nombreReviso, String cargoReviso, String nombreAutorizo, String cargoAutorizo) {
        this.idProductoNomina = idProductoNomina;
        this.fechaPago = fechaPago;
        this.unidadesResponsables = unidadesResponsables;
        this.nombreElaboro = nombreElaboro;
        this.cargoElaboro = cargoElaboro;
        this.nombreReviso = nombreReviso;
        this.cargoReviso = cargoReviso;
        this.nombreAutorizo = nombreAutorizo;
        this.cargoAutorizo = cargoAutorizo;
    }

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public String getQuincena() {
        if (quincena == null && fechaPago != null) {
            String dia = new SimpleDateFormat("dd").format(fechaPago);
            quincena = Integer.valueOf(dia) < 16 ? "1A" : "2A";
        }

        return quincena;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public Map<String, UnidadResponsableDto> getUnidadesResponsables() {
        return unidadesResponsables;
    }

    public String getNombreElaboro() {
        return nombreElaboro;
    }

    public String getCargoElaboro() {
        return cargoElaboro;
    }

    public String getNombreReviso() {
        return nombreReviso;
    }

    public String getCargoReviso() {
        return cargoReviso;
    }

    public String getNombreAutorizo() {
        return nombreAutorizo;
    }

    public String getCargoAutorizo() {
        return cargoAutorizo;
    }

    @Override
    public Iterator<UnidadResponsableDto> iterator() {
        return unidadesResponsables.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idProductoNomina);
        hash = 59 * hash + Objects.hashCode(this.quincena);
        hash = 59 * hash + Objects.hashCode(this.fechaPago);
        hash = 59 * hash + Objects.hashCode(this.unidadesResponsables);
        hash = 59 * hash + Objects.hashCode(this.nombreElaboro);
        hash = 59 * hash + Objects.hashCode(this.cargoElaboro);
        hash = 59 * hash + Objects.hashCode(this.nombreReviso);
        hash = 59 * hash + Objects.hashCode(this.cargoReviso);
        hash = 59 * hash + Objects.hashCode(this.nombreAutorizo);
        hash = 59 * hash + Objects.hashCode(this.cargoAutorizo);
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
        final FirmaDto other = (FirmaDto) obj;
        if (!Objects.equals(this.quincena, other.quincena)) {
            return false;
        }
        if (!Objects.equals(this.nombreElaboro, other.nombreElaboro)) {
            return false;
        }
        if (!Objects.equals(this.cargoElaboro, other.cargoElaboro)) {
            return false;
        }
        if (!Objects.equals(this.nombreReviso, other.nombreReviso)) {
            return false;
        }
        if (!Objects.equals(this.cargoReviso, other.cargoReviso)) {
            return false;
        }
        if (!Objects.equals(this.nombreAutorizo, other.nombreAutorizo)) {
            return false;
        }
        if (!Objects.equals(this.cargoAutorizo, other.cargoAutorizo)) {
            return false;
        }
        if (!Objects.equals(this.idProductoNomina, other.idProductoNomina)) {
            return false;
        }
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        return Objects.equals(this.unidadesResponsables, other.unidadesResponsables);
    }

    @Override
    public String toString() {
        return "FirmaDTO {"
                + "idProductoNomina : " + idProductoNomina
                + ", quincena : " + quincena
                + ", fechaPago : " + fechaPago
                + ", programas : [" + unidadesResponsables
                + "], nombreElaboro : " + nombreElaboro
                + ", cargoElaboro : " + cargoElaboro
                + ", nombreReviso : " + nombreReviso
                + ", cargoReviso : " + cargoReviso
                + ", nombreAutorizo : " + nombreAutorizo
                + ", cargoAutorizo : " + cargoAutorizo
                + '}';
    }
    
    public static final class Builder {

        private Integer idProductoNomina;
        private Date fechaPago;
        private Map<String, UnidadResponsableDto> unidadesResponsables;
        private String nombreElaboro;
        private String cargoElaboro;
        private String nombreReviso;
        private String cargoReviso;
        private String nombreAutorizo;
        private String cargoAutorizo;

        public Builder(Integer idProductoNomina, Date fechaPago, Map<String, UnidadResponsableDto> unidadesResponsables) {
            this.idProductoNomina = idProductoNomina;
            this.fechaPago = fechaPago;
            this.unidadesResponsables = unidadesResponsables;
            nombreElaboro = null;
            cargoElaboro = null;
            nombreReviso = null;
            cargoReviso = null;
            nombreAutorizo = null;
            cargoAutorizo = null;
        }

        public Builder setIdProductoNomina(Integer idProductoNomina) {
            this.idProductoNomina = idProductoNomina;
            return this;
        }

        public Builder setFechaPago(Date fechaPago) {
            this.fechaPago = fechaPago;
            return this;
        }

        public Builder setUnidadesResponsables(Map<String, UnidadResponsableDto> unidadesResponsables) {
            this.unidadesResponsables = unidadesResponsables;
            return this;
        }

        public Builder setNombreElaboro(String nombreElaboro) {
            this.nombreElaboro = nombreElaboro;
            return this;
        }

        public Builder setCargoElaboro(String cargoElaboro) {
            this.cargoElaboro = cargoElaboro;
            return this;
        }

        public Builder setNombreReviso(String nombreReviso) {
            this.nombreReviso = nombreReviso;
            return this;
        }

        public Builder setCargoReviso(String cargoReviso) {
            this.cargoReviso = cargoReviso;
            return this;
        }

        public Builder setNombreAutorizo(String nombreAutorizo) {
            this.nombreAutorizo = nombreAutorizo;
            return this;
        }

        public Builder setCargoAutorizo(String cargoAutorizo) {
            this.cargoAutorizo = cargoAutorizo;
            return this;
        }

        public FirmaDto construirFirmaDTO() {
            return new FirmaDto(idProductoNomina, fechaPago, unidadesResponsables, nombreElaboro, cargoElaboro, nombreReviso, cargoReviso, nombreAutorizo, cargoAutorizo);                
        }
    }

}
