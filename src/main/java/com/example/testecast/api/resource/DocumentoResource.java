package com.example.testecast.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.example.testecast.api.service.DocumentoService;

@RestController
@RequestMapping("/v1/diff")
public class DocumentoResource {

	@Autowired
	private DocumentoLeftRepository documentoLeftRepository;
	
	@Autowired
	private DocumentoRightRepository documentoRightRepository;
	
	@Autowired
	private DocumentoService documentoService;
	
	// --------------------------------------------------------------------------
	// ----------DOCUMENTO LEFT -------------------------------------------------
	// --------------------------------------------------------------------------
	@GetMapping("/left")
	public List<DocumentoLeft> listarLeft() {
		return documentoLeftRepository.findAll();
	}
	
	@GetMapping("/{codigo}/left")
	public ResponseEntity<DocumentoLeft> buscarLeftPeloCodigo(@PathVariable Long codigo) {
		Optional<DocumentoLeft> docLeft = documentoLeftRepository.findById(codigo);
		return docLeft.isPresent() ?
				ResponseEntity.ok(docLeft.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping("{codigo}/left")
	public ResponseEntity<DocumentoLeft> criar(@PathVariable Long codigo, @Valid @RequestBody DocumentoLeft docLeft, HttpServletResponse response) {
		DocumentoLeft docLeftSalvo = documentoService.salvar(codigo, docLeft);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		
		return ResponseEntity.created(uri).body(docLeftSalvo);
	}
	
	
	// --------------------------------------------------------------------------
	// ----------DOCUMENTO RIGHT ------------------------------------------------
	// --------------------------------------------------------------------------
	@GetMapping("/right")
	public List<DocumentoRight> listarRight() {
		return documentoRightRepository.findAll();
	}
	
	@GetMapping("/{codigo}/right")
	public ResponseEntity<DocumentoRight> buscarRightPeloCodigo(@PathVariable Long codigo) {
		Optional<DocumentoRight> docRight = documentoRightRepository.findById(codigo);
		return docRight.isPresent() ?
				ResponseEntity.ok(docRight.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping("{codigo}/right")
	public ResponseEntity<DocumentoRight> criar(@PathVariable Long codigo, @Valid @RequestBody DocumentoRight docRight, HttpServletResponse response) {
		DocumentoRight docRightSalvo = documentoService.salvar(codigo, docRight);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		
		return ResponseEntity.created(uri).body(docRightSalvo);
	}
	
	
	// --------------------------------------------------------------------------
	// ----------RESULTADO DA DIFERENÇA ENTRE LEFT E RIGHT ----------------------
	// --------------------------------------------------------------------------
	@GetMapping("/{codigo}")
	public String diffEntreLeftERight(@PathVariable Long codigo) {
		return documentoService.resultadoDiffLeftRight(codigo);
	}

}
