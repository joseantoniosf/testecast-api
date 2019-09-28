package com.example.testecast.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testecast.api.model.DocumentoLeft;
import com.example.testecast.api.model.DocumentoRight;
import com.example.testecast.api.repository.DocumentoLeftRepository;
import com.example.testecast.api.repository.DocumentoRightRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoLeftRepository documentoLeftRepository;
	
	@Autowired
	private DocumentoRightRepository documentoRightRepository;
		
	public DocumentoLeft salvar(Long codigo, DocumentoLeft docLeft) {
		DocumentoLeft documentoLeft = new DocumentoLeft();
		documentoLeft.setCodigo(codigo);
		documentoLeft.setDocumento(docLeft.getDocumento());
		
		return documentoLeftRepository.save(documentoLeft);
	}

	public DocumentoRight salvar(Long codigo, DocumentoRight docRight) {
		DocumentoRight documentoRight = new DocumentoRight();
		documentoRight.setCodigo(codigo);
		documentoRight.setDocumento(docRight.getDocumento());
		
		return documentoRightRepository.save(documentoRight);
	}
	
	public String resultadoDiffLeftRight(Long codigo) {
		DocumentoLeft  docLeft  = documentoLeftRepository.findById(codigo).orElse(new DocumentoLeft());
		DocumentoRight docRight = documentoRightRepository.findById(codigo).orElse(new DocumentoRight());
		
		if (docLeft.equals(docRight)) {
			return "Documentos " + docLeft.getCodigo() + " idênticos";
		}
		else {
			if (docLeft.getDocumento().length() != docRight.getDocumento().length()) {
				return "Documentos " + docLeft.getCodigo() + " com tamanhos diferentes";
			}
			else {
				return "Falta fazer o Diff dos dois documentos";//TODO: Fazer o diff dos dois documentos
			}
		}
	}

}
