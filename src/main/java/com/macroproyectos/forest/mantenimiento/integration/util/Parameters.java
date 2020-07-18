package com.macroproyectos.forest.mantenimiento.integration.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.macroproyectos.api.automatization.util.LocalDateAdapter;

import java.time.LocalDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase que define los parametros requeridos en la automatizacion
 * 
 * @author kenneth.sanchez
 *
 */
public final class Parameters {

	/**
	 * Constructor sin parametros requerido por Sonar
	 */
	private Parameters() {
	}

	public static final String EJE_TEMATICO = "/ejeTematico";
	public static final String EJE_TEMATICO_DEPENDENCIA = "/ejeTematico/dependencia";
	public static final String EJE_TEMATICO_GUARDAR = "/ejeTematico/guardar";
	
	
	public static final String GRUPO_SEGURIDAD = "/grupoSeguridad";
	public static final String GRUPO_SEGURIDAD_GUARDAR = "/grupoSeguridad/guardar";
	public static final String GRUPO_SEGURIDAD_GUARDAR_GRID = "/grupoSeguridad/guardar_grid";

	// Lista de paths utilizados en la automatizacíón

	// Path de control de version - no modificar
	public static final String CONTROL_VERSION_DESPLIEGUE = "/controlVersion";
	
	
	// Lista de paths utilizados en la automatización
	public static final String PTH_EJEMPLO_CONSULTA = "/ejemploConsulta";
	public static final String PTH_EJEMPLO_GUARDAR = "/ejemploGuardar";
	public static final String PTH_EJEMPLO_ACTUALIZAR = "/ejemploActualizar";
	public static final String PTH_EJEMPLO_ELIMINAR = "/ejemploEliminar";

	/**
	 * Metodo que retorna el mensaje de error que se debe retornar por todos los
	 * servicios REST
	 * 
	 * @param e
	 *            Excepcion que contiene el error
	 * @return Mensaje de error
	 */
	@SuppressWarnings("rawtypes")
	public static ResponseEntity getErrorMessage(Exception e) {
		ResponseEntity resp = null;
		String cause = e.getCause() != null ? e.getCause().toString().replace("\n", "-").replace("\r", "-") : "";
		String cause2 = e.toString() != null ? e.toString().replace("\n", "-").replace("\r", "-") : "";

		resp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("{\"type\":\"" + e.getClass().getSimpleName() + "\",\"message\":\"" + e.getMessage()
						+ "\",\"cause\":\"" + cause + "\",\"string\":\"" + cause2 + "\"}");

		return resp;
	}
	
	/**
	 * Metodo para realizar la conversion de los datos a formato JSON
     * @return the gson
     */
    public static Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

}