/*
 * PrenominaReporteTextoPlano.java
 * Creado el 28/jun/2017 11:44:57 AM
 *
 */

package siayf.rh.reportes.nomina.prenomina;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import siayf.rh.reportes.util.ArchivoUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PrenominaReporteTextoPlano {

    private static final Logger LOGGER = Logger.getLogger(PrenominaReporteTextoPlano.class.getName());
    private static final Integer LINEAS_POR_HOJA = 66;
    private final PrenominaReporteTextoPlanoTools tools = new PrenominaReporteTextoPlanoTools();

    public byte[] generar(ProductoNominaDto productoNomina) {
        byte[] bytes = null;

        try {
            Path pathReporteTemporal = Files.createTempFile("prenomina", ".txt");
            Integer lineasTotales = 0;
            Integer lineasHojaActual;
            Integer numeroHoja = 0;

            if (!ArchivoUtil.SEPARADOR_DE_ARCHIVO_WINDOWS.equals(ArchivoUtil.SEPARADOR_DE_ARCHIVO)) {
                System.setProperty("line.separator", ArchivoUtil.SEPARADOR_DE_ARCHIVO_WINDOWS);
            }

            try(BufferedWriter out = Files.newBufferedWriter(pathReporteTemporal, ArchivoUtil.MS_DOS_LATIN_CHARSET)) {
                Map<String, BigDecimal> percepcionesGeneral = new HashMap<>();
                Map<String, BigDecimal> deduccionesGeneral = new HashMap<>();
                int totalEmpleadosGeneral = 1;
                boolean esPension = false;

                for(ProgramaDto programa : productoNomina) {
                    Map<String, BigDecimal> percepcionesPrograma = new HashMap<>();
                    Map<String, BigDecimal> deduccionesPrograma = new HashMap<>();
                    esPension = "PENSIÓN ALIMENTICIA".equals(programa.getPrograma());

                    int totalEmpleadosPrograma = 1;
                    for (UnidadResponsableDto unidadResponsable : programa) {
                        int totalEmpleadosUnidadResponsable = 1;

                        // Crea el encabezado por unidad responsable
                        numeroHoja += 1;
                        String encabezadoUnidadResponsable = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable());
                        out.write(encabezadoUnidadResponsable);
                        lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezadoUnidadResponsable);
                        Map<String, BigDecimal> percepcionesUnidadResponsable = new HashMap<>();
                        Map<String, BigDecimal> deduccionesUnidadResponsable = new HashMap<>();

                        for (NominaEmpleadoDto nominaEmpleado : unidadResponsable) {
                            List<DeduccionDto> deducciones;
                            List<PercepcionDto> percepciones;

                            if (nominaEmpleado.getPercepciones() != null) {
                                percepciones = new ArrayList<>();
                                percepciones.addAll(nominaEmpleado.getPercepciones().values());

                                for (PercepcionDto percepcion : percepciones) {
                                    String clave = percepcion.getClave();
                                    BigDecimal monto = percepcion.getMonto();

                                    if (percepcionesUnidadResponsable.containsKey(clave)) {
                                        BigDecimal total = percepcionesUnidadResponsable.get(clave);
                                        total = total.add(monto);
                                        percepcionesUnidadResponsable.put(clave, total);
                                    } else {
                                        percepcionesUnidadResponsable.put(clave, monto);
                                    }

                                    if (percepcionesPrograma.containsKey(clave)) {
                                        BigDecimal total = percepcionesPrograma.get(clave);
                                        total = total.add(monto);
                                        percepcionesPrograma.put(clave, total);
                                    } else {
                                        percepcionesPrograma.put(clave, monto);
                                    }

                                    if (percepcionesGeneral.containsKey(clave)) {
                                        BigDecimal total = percepcionesGeneral.get(clave);
                                        total = total.add(monto);
                                        percepcionesGeneral.put(clave, total);
                                    } else {
                                        percepcionesGeneral.put(clave, monto);
                                    }
                                }
                            } else {
                                percepciones = null;
                            }

                            if (nominaEmpleado.getDeducciones() != null) {
                                deducciones = new ArrayList<>();
                                deducciones.addAll(nominaEmpleado.getDeducciones().values());

                                for (DeduccionDto deduccion : deducciones) {
                                    String clave = deduccion.getClave();
                                    BigDecimal monto = deduccion.getMonto();

                                    if (deduccionesUnidadResponsable.containsKey(clave)) {
                                        BigDecimal total = deduccionesUnidadResponsable.get(clave);
                                        total = total.add(monto);
                                        deduccionesUnidadResponsable.put(clave, total);
                                    } else {
                                        deduccionesUnidadResponsable.put(clave, monto);
                                    }

                                    if (deduccionesPrograma.containsKey(clave)) {
                                        BigDecimal total = deduccionesPrograma.get(clave);
                                        total = total.add(monto);
                                        deduccionesPrograma.put(clave, total);
                                    } else {
                                        deduccionesPrograma.put(clave, monto);
                                    }

                                    if (deduccionesGeneral.containsKey(clave)) {
                                        BigDecimal total = deduccionesGeneral.get(clave);
                                        total = total.add(monto);
                                        deduccionesGeneral.put(clave, total);
                                    } else {
                                        deduccionesGeneral.put(clave, monto);
                                    }
                                }
                            } else {
                                deducciones = null;
                            }

                            lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;

                            String detalle = tools.getDetalle(totalEmpleadosUnidadResponsable, nominaEmpleado.getRfc(), nominaEmpleado.getNombre(), programa.getInicioPeriodo(), programa.getFinPeriodo(), percepciones, deducciones);
                            int lineasRequeridas = PrenominaReporteTextoPlanoTools.contadorDeLineas(detalle);

                            if ((lineasRequeridas + lineasHojaActual) >= LINEAS_POR_HOJA) {
                                numeroHoja += 1;
                                int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                                out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                                lineasTotales += lineasRestantes;

                                String encabezado = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable());
                                int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezado);
                                out.write(encabezado);
                                lineasTotales += lineasEncabezado;
                            }

                            out.write(detalle);
                            lineasTotales += lineasRequeridas;
                            totalEmpleadosUnidadResponsable++;
                            totalEmpleadosPrograma++;
                            totalEmpleadosGeneral++;
                        }

                        // Crea los totales por unidad responsable
                        lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
                        String totalesUnidadResponsable = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_POR_UNIDAD, percepcionesUnidadResponsable, deduccionesUnidadResponsable, totalEmpleadosUnidadResponsable - 1, esPension);
                        int lineasRequeridas = lineasHojaActual + PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesUnidadResponsable);

                        if ((lineasRequeridas + lineasHojaActual) >= LINEAS_POR_HOJA) {
                            numeroHoja += 1;
                            int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                            out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                            lineasTotales += lineasRestantes;

                            String encabezado = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable());
                            int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezado);
                            out.write(encabezado);
                            lineasTotales += lineasEncabezado;
                        }

                        out.write(totalesUnidadResponsable);
                        lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesUnidadResponsable);
                        lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
                        int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                        out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                        lineasTotales += lineasRestantes;
                    }

                    // Nueva página con un resumen del programa
                    numeroHoja += 1;
                    String encabezadoPrograma = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago());
                    int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezadoPrograma);
                    out.write(encabezadoPrograma);
                    lineasTotales += lineasEncabezado;

                    String totalesPrograma = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_POR_PROGRAMA, percepcionesPrograma, deduccionesPrograma, totalEmpleadosPrograma - 1, esPension);
                    out.write(totalesPrograma);
                    lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesPrograma);

                    lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
                    int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                    out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                    lineasTotales += lineasRestantes;
                }

                // Nueva página con un resumen del programa
                numeroHoja += 1;
                String encabezadoPrograma = tools.getEncabezado(numeroHoja, "-", productoNomina.getQuincena(), productoNomina.getFechaPago());
                int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezadoPrograma);
                out.write(encabezadoPrograma);
                lineasTotales += lineasEncabezado;

                String totalesPrograma = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_GENERAL, percepcionesGeneral, deduccionesGeneral, totalEmpleadosGeneral - 1, esPension);
                out.write(totalesPrograma);
                lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesPrograma);
                BigDecimal granTotal = getTotal(percepcionesGeneral, deduccionesGeneral);
                String firmas = tools.getFirmas(granTotal, productoNomina.getNombreElaboro(), productoNomina.getCargoElaboro(), productoNomina.getNombreReviso(), productoNomina.getCargoReviso(), productoNomina.getNombreAutorizo(), productoNomina.getCargoAutorizo());
                out.write(firmas);
            }

            bytes = Files.readAllBytes(pathReporteTemporal);
            Files.delete(pathReporteTemporal);
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }

        System.setProperty("line.separator", ArchivoUtil.SEPARADOR_DE_ARCHIVO);

        return bytes;
    }

    private BigDecimal getTotal(Map<String, BigDecimal> percepciones, Map<String, BigDecimal> deducciones) {
        BigDecimal totalDeducciones = BigDecimal.ZERO;
        BigDecimal totalPercepciones = BigDecimal.ZERO;

        for (Map.Entry<String, BigDecimal> percepcion : percepciones.entrySet()) {
            BigDecimal monto = percepcion.getValue();

            totalPercepciones = totalPercepciones.add(monto);
        }

        for (Map.Entry<String, BigDecimal> deduccion : deducciones.entrySet()) {
            BigDecimal monto = deduccion.getValue();

            totalDeducciones = totalDeducciones.add(monto);
        }

        return totalPercepciones.subtract(totalDeducciones);
    }
}
