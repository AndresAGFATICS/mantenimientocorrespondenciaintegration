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

import com.macroproyectos.forest.mantenimiento.service.general.GrupoSeguridadService;
import com.macroproyectos.forest.mantenimiento.integration.util.Parameters;
import com.macroproyectos.forest.mantenimiento.integration.util.TagSwagger;
import com.google.common.reflect.TypeToken;
import com.macroproyectos.api.automatization.dto.Response;
import com.macroproyectos.forest.mantenimiento.dto.corr.EjeTematico;
import com.macroproyectos.forest.mantenimiento.dto.corr.GrupoSeguridad;
import com.macroproyectos.forest.seguridad.CommonsController;
import com.macroproyectos.forest.sistema.log.MPLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Clase de servicios rest de Grupo Seguridad
 * 
 * @author AGFATICS
 *
 */
@Api(value = "Grupo Seguridad", tags = { TagSwagger.GRUPO_SEGURIDAD })
@Controller
@SuppressWarnings("rawtypes")
public class GrupoSeguridadController extends CommonsController {

	@Autowired
	private GrupoSeguridadService service;
	
	
	/**
	 * Consulta los Grupos de Seguridad
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
	 * @return Lista de Grupos de Seguridad en formato JSON
	 */
	@ApiOperation(value = "Consulta los Grupos de Seguridad", notes = "Consulta los Grupos de Seguridad", tags = {
			TagSwagger.GRUPO_SEGURIDAD, }, response = GrupoSeguridad.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Grupos de Seguridad"),
			@ApiResponse(code = 400, message = "Error consultando los Grupos de Seguridad, ver log", response = String.class) })
	@RequestMapping(value = Parameters.GRUPO_SEGURIDAD, produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity consultarGrupoSeguridad(
			@ApiParam(value = TagSwagger.PAGINACION, required = false) @PageableDefault(page = 0, size = Integer.MAX_VALUE) Pageable propertiesPage,
			@ApiParam(value = "id del Grupo de Seguridad", required = false) @RequestParam(required = false) Integer id,
			@ApiParam(value = "activo: 1 activo, 0 inactivo, null todos", required = false) @RequestParam(required = false) Integer activo,
			@ApiParam(value = "descripcion: Descripci\u00f3n del Grupo de Seguridad", required = false) @RequestParam(required = false) String descripcion,
			@RequestHeader HttpHeaders headers) {
		try {
			Map<String, Object> parametrosGrupoSeguridad = new HashMap<>();
			if (id != null)
				parametrosGrupoSeguridad.put("id", id);
			
			if (activo != null)
				parametrosGrupoSeguridad.put("activo", activo);

			if (descripcion != null)
				parametrosGrupoSeguridad.put("descripcion", descripcion);

			List<GrupoSeguridad> grupoSeguridad = null;

			if (propertiesPage != null && propertiesPage.getPageNumber() > 0)
				grupoSeguridad = service.consultarGrupoSeguridad(parametrosGrupoSeguridad, propertiesPage.getPageSize(),
						propertiesPage.getPageNumber());
			else
				grupoSeguridad = service.consultarGrupoSeguridad(parametrosGrupoSeguridad, null, null);

			return new ResponseEntity<>(Parameters.getGson().toJson(grupoSeguridad), HttpStatus.OK);
		} catch (Exception e) {
			return Parameters.getErrorMessage(e);
		}
	}
	
	
	/**
     * Actualiza los Grupos de Seguridad
     *
     * @param jsonFrontEnd
     *            Json enviado por el front-end para actualizar los Grupos de Seguridad
     *
     * @return resultado de proceso ok, o mensaje de error
     */
    @ApiOperation(value = "Actualizar los Grupos de Seguridad", notes = "Actualiza los Grupos de Seguridad", tags = {
            TagSwagger.GRUPO_SEGURIDAD }, response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Actualizar los Grupos de Seguridad"),
            @ApiResponse(code = 400, message = "Error al actualizar los Grupos de Seguridad", response = String.class) })
    @RequestMapping(value = Parameters.GRUPO_SEGURIDAD_GUARDAR, produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)

    public ResponseEntity actualizarGrupoSeguridad(
            @ApiParam(value = "JSON con los valores de los Grupos de Seguridad enviado por el front-end", required = true) @RequestBody String parametersJSON,
            @RequestHeader HttpHeaders headers) {

        try {
            MPLogger.log.info(" >>>>>>>>>>>>>>>>>>JSON ;::::: Enviado desde La FORMA {}", parametersJSON);
            Map<String, Object> mapJson = Parameters.getGson().fromJson(parametersJSON,
                    new TypeToken<Map<String, Object>>() {}.getType());

            return new ResponseEntity<>(
            		service.actualizarGrupoSeguridad(mapJson), HttpStatus.OK);
        } catch (Exception e) {
            MPLogger.log.info(" Se genero Error en el controllerrrrrrrrrr ::: : {}", e);
            return Parameters.getErrorMessage(e);
        }
    }
    
    

	/**
     * Crea o edita los Grupos de Seguridad
     * 
     * @param jsonGrid
     *            JSON con la informacion enviada por el front-end
     * 
     * @return Objeto con el resultado de la operacion
     * @throws ProcessException
     *             Excepcion del proceso
     */
    @ApiOperation(value = "Crea y edita los Grupos de Seguridad", notes = "Crea y edita los Grupos de Seguridad", tags = {
            TagSwagger.GRUPO_SEGURIDAD }, response = Response.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Grupos de Seguridad creados/editados"),
            @ApiResponse(code = 400, message = "Error creando/editando Grupos de Seguridad , ver log", response = String.class) })
    @RequestMapping(value = Parameters.GRUPO_SEGURIDAD_GUARDAR_GRID, produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)

    public ResponseEntity guardarGrupoSeguridadGrid(
            @ApiParam(value = "JSON envidado por el front-end con los valores a persistir", required = true) @RequestBody String jsonFrontEnd,
            @RequestHeader HttpHeaders headers) {

        try {

            Response response = service.guardaGrupoSeguridadBD(jsonFrontEnd, getUserInfo(headers));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return Parameters.getErrorMessage(e);
        }
    }
    
    
    

}
