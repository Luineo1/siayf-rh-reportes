/*
 * AlmacenReportesExcel.java
 * Creado el 23/sep/2016 7:17:59 PM
 * 
 */

package siayf.rh.reportes.core.excel;

import java.util.HashMap;
import java.util.Map;

import siayf.rh.reportes.api.AlmacenReportes;

/**
 *
 * @author Freddy Barrera
 */
public class AlmacenReportesExcel implements AlmacenReportes<ExcelReporte> {

    private static final Map<String, ExcelReporte> REPORTES;

    // 1. El reporte no usa plantillas para su generación.
    static {
        ExcelReporte acumulados = new ExcelReporte(
                "acumulados--plantilla.xlsx", "plantillas/acumulados/");

        ExcelReporte comisionadoLicencia = new ExcelReporte(
                "Comisionado_Licencia.xlsx", "plantillas/comisionadoLicencia/");

        ExcelReporte consentradoAltaBaja = new ExcelReporte(
                "Concentrado_Altas_Bajas.xlsx",
                "plantillas/consentradoAltaBaja/");

        ExcelReporte seguroPopular = new ExcelReporte(
                "plantilla--seguro-popular.xlsx", "plantillas/siif/");

        ExcelReporte seguroPopularReporte = new ExcelReporte(
                "plantilla--seguro-popular.xlsx", "plantillas/siif/");

        ExcelReporte proyeccionesPresupuestalesContratoPorMes 
                = new ExcelReporte("Contrato_Estatal_Federal.xlsx",
                        "plantillas/contrato/");

        ExcelReporte proyeccionesPresupuestalesContrato = new ExcelReporte(
                "Contrato_Estatal_Federal.xlsx", "plantillas/contrato/");

        ExcelReporte detalleEmpleado = new ExcelReporte(
                "Detalle_Empleado.xlsx", "plantillas/empleado/");

        ExcelReporte productoNominaReporte = new ExcelReporte(
                "Producto_Nomina.xlsx", "plantillas/nommina/");
        
        ExcelReporte prooductoNominaEstatusReporte = new ExcelReporte(
        		"Producto_Nomina.xlsx", "plantillas/nommina/");

        ExcelReporte productoNominaSuplenciaReporte = new ExcelReporte(
                "Producto_Nomina.xlsx", "plantillas/nommina/");

        ExcelReporte historialPagoReporte = new ExcelReporte(
                "Historial_Pago.xlsx", "plantillas/nommina/");

        ExcelReporte relacionPersonalSuplenteReporte = new ExcelReporte(
                "Relacion_Personal_Suplente.xlsx", "plantillas/suplencia/");
        
        ExcelReporte productoNominaFederalProgramasReporte = new ExcelReporte(
                "Producto_Nomina_Programas.xlsx", "plantillas/nommina/");
        ExcelReporte productoNominaProgramasReporte = new ExcelReporte(
                "Producto_Nomina_Programas.xlsx", "plantillas/nommina/");

        ExcelReporte dispercionReporte = new ExcelReporte(null, null); // 1.
        ExcelReporte pagoGeneralReporte = new ExcelReporte(null, null); // 1.
        ExcelReporte distribucionPresupuestal = new ExcelReporte(null, null); // 1.
        ExcelReporte productoNominaFederalReporteReporte = new ExcelReporte(null, null); // 1.

        REPORTES = new HashMap<>();
        REPORTES.put("acumulados", acumulados);
        REPORTES.put("comisionado_licencia", comisionadoLicencia);
        REPORTES.put("consentrado_alta_baja", consentradoAltaBaja);
        REPORTES.put("seguro_popular", seguroPopular);
        REPORTES.put("seguro_popular_reporte", seguroPopularReporte);
        REPORTES.put("contrato_estatal_federal", proyeccionesPresupuestalesContratoPorMes);
        REPORTES.put("contrato_estatal_federal_proyeccion", proyeccionesPresupuestalesContrato);
        REPORTES.put("detalle_empleado", detalleEmpleado);
        REPORTES.put("producto_nomina", productoNominaReporte);
        REPORTES.put("producto_nomina_suplencia", productoNominaSuplenciaReporte);
        REPORTES.put("producto_nomina_estatus",prooductoNominaEstatusReporte);
        REPORTES.put("historial_pago", historialPagoReporte);
        REPORTES.put("relacion_personal_suplente", relacionPersonalSuplenteReporte);
        REPORTES.put("dispersion_nomina", dispercionReporte);
        REPORTES.put("pago_general", pagoGeneralReporte);
        REPORTES.put("reporte_distribucion_presupuestal", distribucionPresupuestal);
        REPORTES.put("producto_nomina_federales", productoNominaFederalReporteReporte);
        REPORTES.put("producto_nomina_programas", productoNominaProgramasReporte);
    }

    @Override
    public ExcelReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
	public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
