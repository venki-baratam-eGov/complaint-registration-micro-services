package org.location.web.controller;

import java.util.List;

import org.location.persistence.entity.Boundary;
import org.location.persistence.service.BoundaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boundary")
public class BoundaryController {

	private BoundaryService boundaryService;

	public BoundaryController(BoundaryService boundaryService) {
		this.boundaryService = boundaryService;
	}

	@GetMapping("getByLngAndLat")
	public Boundary getboundary(@RequestParam(name = "longitude",required=true) Double longitude,
			@RequestParam(name = "latitude",required=true) Double latitude) {
		return boundaryService.getByLongitudeAndLatitude(longitude, latitude);
	}

	@GetMapping("_search")
	@ResponseBody
	public ResponseEntity<?> search() {
		return getSuccessResponseForSearch(boundaryService.getAll());
	}

	public ResponseEntity<?> getSuccessResponseForSearch(List<Boundary> boundarys) {
		return new ResponseEntity<List<Boundary>>(boundarys, HttpStatus.OK);
	}

}