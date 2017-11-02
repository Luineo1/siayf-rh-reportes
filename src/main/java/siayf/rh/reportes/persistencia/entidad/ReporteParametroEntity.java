/**
 *
 */
package siayf.rh.reportes.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Eduardo Chuc Mex
 */
@Entity
@Table(name = "reportes_parametros")
public class ReporteParametroEntity implements Serializable {

    private static final long serialVersionUID = 3500014707930785010L;

    @Id
    @Column(name = "id_reporte_parametro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporteParametro;

    @Column(name = "clave")
    private String clave;

    @Column(name = "valor")
    private String valor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_referencia")
    private BitacoraReporteEntity bitacoraReporte;

    public Integer getIdReporteParametro() {
        return idReporteParametro;
    }

    public void setIdReporteParametro(Integer idReporteParametro) {
        this.idReporteParametro = idReporteParametro;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public BitacoraReporteEntity getBitacoraReporte() {
        return bitacoraReporte;
    }

    public void setBitacoraReporte(BitacoraReporteEntity bitacoraReporte) {
        this.bitacoraReporte = bitacoraReporte;
    }

}
