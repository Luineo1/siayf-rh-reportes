/*
 *
 */

package siayf.rh.reportes.persistencia.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Eduardo Chuc Mex
 *
 */
@Entity
@Table(name = "bitacoras_reportes")
public class BitacoraReporteEntity implements Serializable {

    private static final long serialVersionUID = 8742569036929609315L;

    @Id
    @Column(name = "id_referencia")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID idReferencia;

    @Column(name = "nombre_reporte", nullable = false)
    private String nombreReporte;

    @Column(name = "fecha_generacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaGeneracion;

    @Column(name = "hora_generacion", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaGeneracion;

    @ManyToOne(targetEntity = UsuarioEntity.class)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "bitacoraReporte", cascade = CascadeType.ALL)
    private Set<ReporteParametroEntity> reporteParametros;

    public UUID getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(UUID idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getHoraGeneracion() {
        return horaGeneracion;
    }

    public void setHoraGeneracion(Date horaGeneracion) {
        this.horaGeneracion = horaGeneracion;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Set<ReporteParametroEntity> getReporteParametros() {
        return reporteParametros;
    }

    public void setReporteParametros(Set<ReporteParametroEntity> reporteParametros) {
        this.reporteParametros = reporteParametros;
    }

}
