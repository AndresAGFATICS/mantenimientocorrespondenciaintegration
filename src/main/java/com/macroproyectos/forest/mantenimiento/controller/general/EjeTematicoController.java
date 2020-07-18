package com.macroproyectos.forest.mantenimiento.controller.general;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.macroproyectos.forest.mantenimiento.service.general.EjeTematicoService;
import com.macroproyectos.forest.mantenimiento.integration.util.Parameters;
import com.macroproyectos.forest.mantenimiento.integration.util.TagSwagger;
import com.macroproyectos.api.automatization.dto.Response;
import com.macroproyectos.forest.mantenimiento.dto.corr.EjeTematico;
import com.macroproyectos.forest.seguridad.CommonsController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase de servicios rest de Ejes tematicos
 * 
 * @author AGFATICS
 *
 */
@Api(value = "Eje Tem\u00e1tico", tags = { TagSwagger.EJE_TEMATICO })
@Controller
@SuppressWarnings("rawtypes")
public class EjeTematicoController extends CommonsController {

	@Autowired
	private EjeTematicoService service;

	/**
	 * Consulta los ejes tematicos
	 * 
	 * @param propertiesPage
	 *            Propiedades que recibe el servicio para la paginacion y numero de
	 *            registros a retornar: pageSize: Numero de registros a consultar
	 *            por pagina pageNumber: Numero de la pagina a consultar
	 * @param activo:
	 *            1 tipos de radicado activo, 0 inactivos
	 * @param descripcion:
	 *            Descripcion del eje tematico
	 * @param headers
	 * @return Lista de Tipos de radicados en formato JSON
	 */
	@ApiOperation(value = "Consulta los Ejes Tem\u00e1ticos", notes = "Consulta los Ejes Tem\u00e1ticos", tags = {
			TagSwagger.EJE_TEMATICO, }, response = EjeTematico.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ejes Tem\u00e1ticos"),
			@ApiResponse(code = 400, message = "Error consultando los ejes tem\u00e1ticos, ver log", response = String.class) })
	@RequestMapping(value = Parameters.EJE_TEMATICO, produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity consultarEjeTematico(
			@ApiParam(value = TagSwagger.PAGINACION, required = false) @PageableDefault(page = 0, size = Integer.MAX_VALUE) Pageable propertiesPage,
			@ApiParam(value = "activo: 1 activo, 0 inactivo, null todos", required = false) @RequestParam(required = false) Integer activo,
			@ApiParam(value = "descripcion: Descripci\u00f3n del eje Tem\u00e1tico", required = false) @RequestParam(required = false) String descripcion,
			@RequestHeader HttpHeaders headers) {
		try {
			Map<String, Object> parametrosEje = new HashMap<>();
			if (activo != null)
				parametrosEje.put("activo", activo);

			if (descripcion != null)
				parametrosEje.put("descripcion", descripcion);

			List<EjeTematico> ejes = null;

			if (propertiesPage != null && propertiesPage.getPageNumber() > 0)
				ejes = service.consultarEjeTematico(parametrosEje, propertiesPage.getPageSize(),
						propertiesPage.getPageNumber());
			else
				ejes = service.consultarEjeTematico(parametrosEje, null, null);

			return new ResponseEntity<>(Parameters.getGson().toJson(ejes), HttpStatus.OK);
		} catch (Exception e) {
			return Parameters.getErrorMessage(e);
		}
	}

	/**
	 * Consulta los ejes tematicos asociados a una dependencia
	 * 
	 * @param propertiesPage
	 *            Propiedades que recibe el servicio para la paginacion y numero de
	 *            registros a retornar: pageSize: Numero de registros a consultar
	 *            por pagina pageNumber: Numero de la pagina a consultar
	 * @param activo:
	 *            1 tipos de radicado activo, 0 inactivos
	 * @param idDependencia:
	 *            Identificador de la dependencia
	 * @param descripcion:
	 *            Descripcion del eje tematico
	 * @param headers
	 * @return Lista de Tipos de radicados en formato JSON
	 */
	@ApiOperation(value = "Consulta los Ejes Tem\u00e1ticos - Dependencia", notes = "Consulta los Ejes Tem\u00e1ticos - Dependencia", tags = {
			TagSwagger.EJE_TEMATICO, }, response = EjeTematico.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ejes Tem\u00e1ticos"),
			@ApiResponse(code = 400, message = "Error consultando los ejes tem\u00e1ticos, ver log", response = String.class) })
	@RequestMapping(value = Parameters.EJE_TEMATICO_DEPENDENCIA, produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity consultarEjeTematicoDependnecia(
			@ApiParam(value = TagSwagger.PAGINACION, required = false) @PageableDefault(page = 0, size = Integer.MAX_VALUE) Pageable propertiesPage,
			@ApiParam(value = "idDependencia: Identificador de la dependencia", required = true) @RequestParam(required = true) Integer idDependencia,
			@ApiParam(value = "activo: 1 activo, 0 inactivo, null todos", required = false) @RequestParam(required = false) Integer activo,
			@ApiParam(value = "descripcion: Descripci\u00f3n del eje Tem\u00e1tico", required = false) @RequestParam(required = false) String descripcion,
			@RequestHeader HttpHeaders headers) {
		try {
			Map<String, Object> parametrosEje = new HashMap<>();
			parametrosEje.put("idDependencia", idDependencia);

			if (activo != null)
				parametrosEje.put("activo", activo);

			if (descripcion != null)
				parametrosEje.put("descripcion", descripcion);

			List<EjeTematico> ejes = null;

			if (propertiesPage != null && propertiesPage.getPageNumber() > 0)
				ejes = service.consultarEjeTematico(parametrosEje, propertiesPage.getPageSize(),
						propertiesPage.getPageNumber());
			else
				ejes = service.consultarEjeTematico(parametrosEje, null, null);

			return new ResponseEntity<>(Parameters.getGson().toJson(ejes), HttpStatus.OK);
		} catch (Exception e) {
			return Parameters.getErrorMessage(e);
		}
	}
	
	
	/**
     * Crea o edita los ejes tematicos
     * 
     * @param jsonGrid
     *            JSON con la informacion enviada por el front-end
     * 
     * @return Objeto con el resultado de la operacion
     * @throws ProcessException
     *             Excepcion del proceso
     */
    @ApiOperation(value = "Crea y edita los eje tematicos", notes = "Crea y edita los eje tematicos", tags = {
            TagSwagger.EJE_TEMATICO }, response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Eje tematicos creados/editados"),
            @ApiResponse(code = 400, message = "Error creando/editando Eje tematicos , ver log", response = String.class) })
    @RequestMapping(value = Parameters.EJE_TEMATICO_GUARDAR, produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)

    public ResponseEntity guardarEjeTematicos(
            @ApiParam(value = "JSON envidado por el front-end con los valores a persistir", required = true) @RequestBody String jsonFrontEnd,
            @RequestHeader HttpHeaders headers) {

        try {

            Response response = service.guardaEjeTematicoBD(jsonFrontEnd, getUserInfo(headers));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return Parameters.getErrorMessage(e);
        }
    }

}
