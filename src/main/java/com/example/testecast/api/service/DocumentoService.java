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
		DocumentoLeft  docLeft  = documentoLeftRepository.findById(codigo).orElse(null);
		DocumentoRight docRight = documentoRightRepository.findById(codigo).orElse(null);
		
		if (docLeft==null || docRight==null) {
			return null;
		}
		
		if (docLeft.equals(docRight)) {
			return "Documentos " + docLeft.getCodigo() + " idênticos";
		}
		else {
			if (docLeft.getDocumento().length() != docRight.getDocumento().length()) {
				return "Documentos " + docLeft.getCodigo() + " com tamanhos diferentes";
			}
			else {
				return String.valueOf( compareDuasStrings(docLeft.getDocumento(), docRight.getDocumento()) );
			}
		}
	}
	
	/**
	 * Compara duas Strings caracter por caracter.
	 * No conexto desta API, as validações anteriores não permitem que elas sejam nulas
	 * Seus tamanhos são iguais, então tanto faz por qual começar a comparar
	 * 
	 * @param s1
	 * @param s2
	 * @return -1 se forem iguais; Qualquer outro número será a posição em que são diferentes
	 */
	private int compareDuasStrings(String s1, String s2) {
		int pos = -1;
		
		for (int i=0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				pos = i +1;
				break;
			}
		}
		
		return pos;
	}

}
