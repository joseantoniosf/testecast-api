package com.example.testecast.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testecast.api.model.DocumentoLeft;
import com.example.testecast.api.model.DocumentoRight;
import com.example.testecast.api.repository.DocumentoLeftRepository;
import com.example.testecast.api.repository.DocumentoRightRepository;

@RestController
@RequestMapping("/v1/diff")
public class DocumentoResource {

	@Autowired
	private DocumentoLeftRepository documentoLeftRepository;
	
	@Autowired
	private DocumentoRightRepository documentoRightRepository;
	
	@GetMapping("{codigo}/left")
	public List<DocumentoLeft> listarLeft() {
		return documentoLeftRepository.findAll();
	}

	@GetMapping("{codigo}/right")
	public List<DocumentoRight> listarRight() {
		return documentoRightRepository.findAll();
	}

}
