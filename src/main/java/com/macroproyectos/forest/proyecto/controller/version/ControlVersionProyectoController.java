package com.macroproyectos.forest.proyecto.controller.version;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.macroproyectos.forest.mantenimiento.integration.util.Parameters;
import com.macroproyectos.forest.mantenimiento.integration.util.TagSwagger;
import com.macroproyectos.forest.seguridad.CommonsController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;

/**
 * Clase que expone servicio rest para control de version de despliegue del proyecto
 * Esta clase no se debe modificar
 * 
 * @author paola.moreno
 *
 */
@Api(value = "Control versi\u00f3n", tags = { TagSwagger.CONTROL_VERSION })
@Controller
@SuppressWarnings("rawtypes")
public class ControlVersionProyectoController extends CommonsController {

	@ApiOperation(value = "Consulta datos de version del despliegue", notes = "Consulta datos de version del despliegue", tags = {
			TagSwagger.CONTROL_VERSION }, response = String.class,  extensions = @Extension(properties = { @ExtensionProperty(name = "x-auth-type", value = "None") }))
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Consulta datos version despliegue"),
			@ApiResponse(code = 400, message = "Error consultando datos de version del despliegue, ver log", response = String.class) })
	@RequestMapping(value = Parameters.CONTROL_VERSION_DESPLIEGUE, produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity consultarControlVersionDespliegue(@RequestHeader HttpHeaders headers) {
		InputStream fileStream = this.getClass().getClassLoader().getResourceAsStream("version.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
		String result = br.lines().collect(Collectors.joining(""));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}