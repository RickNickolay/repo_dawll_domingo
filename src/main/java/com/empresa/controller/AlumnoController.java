package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {
	@Autowired
	private AlumnoService service;
	@GetMapping
	public ResponseEntity<List<Alumno>> listaAlummno(){
		log.info(">>>> Lista <<<<");
		List<Alumno> lstAlumno = service.listaAlumno();
		return ResponseEntity.ok(lstAlumno);
	}
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){
		log.info(">>>> Actualiza " + obj.getIdAlumno() + " <<<<");
		Optional<Alumno> optAlumno = service.obtienePorId(obj.getIdAlumno());
		if(optAlumno.isPresent()) {
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			if(objSalida != null) {
				return ResponseEntity.ok(objSalida);
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		}
		else {
			log.info("No existe el ID");
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		log.info(">>>> Registra " + obj.getIdAlumno() + " <<<<");
		Alumno objSalida = service.insertaActualizaAlumno(obj);
		if(objSalida != null) {
			return ResponseEntity.ok(objSalida);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
