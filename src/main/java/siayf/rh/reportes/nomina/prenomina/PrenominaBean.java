/*
 * PrenominaBean.java
 * Creado el 11/jul/2017 6:53:59 PM
 *
 */

package siayf.rh.reportes.nomina.prenomina;

import siayf.rh.reportes.persistencia.consulta.PrenominaQuery;
import javax.ejb.Stateless;
import javax.inject.Inject;
import siayf.rh.reportes.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class PrenominaBean implements Prenomina {

    @Inject
    private PrenominaQuery prenominaReporteService;

    @Override
    public byte[] generarReporte(Integer idProductoNomina) {
        if (ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto no debe ser nulo o menor que uno.");
        }

        ProductoNominaDto productoNomina = prenominaReporteService.obtenerProductoNomina(idProductoNomina);
        PrenominaReporteTextoPlano prenominaReporteTextoPlano = new PrenominaReporteTextoPlano();
        return prenominaReporteTextoPlano.generar(productoNomina);
    }

}
