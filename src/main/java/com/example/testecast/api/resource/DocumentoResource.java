package com.example.testecast.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping("/left")
	public List<DocumentoLeft> listarLeft() {
		return documentoLeftRepository.findAll();
	}

	@GetMapping("/right")
	public List<DocumentoRight> listarRight() {
		return documentoRightRepository.findAll();
	}

	@PostMapping("{codigo}/left")
	public ResponseEntity<DocumentoLeft> criar(@PathVariable Long codigo, @RequestBody DocumentoLeft docLeft, HttpServletResponse response) {
		DocumentoLeft documentoLeft = new DocumentoLeft();
		documentoLeft.setCodigo(codigo);
		documentoLeft.setDocumento(docLeft.getDocumento());
		
		DocumentoLeft docLeftSalvo = documentoLeftRepository.save(documentoLeft);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		
		return ResponseEntity.created(uri).body(docLeftSalvo);
	}
	
	@GetMapping("/{codigo}/left")
	public ResponseEntity<DocumentoLeft> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<DocumentoLeft> docLeft = documentoLeftRepository.findById(codigo);
		return docLeft.isPresent() ?
				ResponseEntity.ok(docLeft.get()) : ResponseEntity.notFound().build();
	}
}
