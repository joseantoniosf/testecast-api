package com.example.testecast.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="documento_right")
public class DocumentoRight {

	@Id
	private Long codigo;
	
	@NotNull
	@Size(min=5, max=2000000)
	private String documento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

	public boolean equals(DocumentoLeft obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
		DocumentoLeft other = obj;//(DocumentoLeft)
		if (codigo == null) {
			if (other.getCodigo() != null)
				return false;
		} else if (!codigo.equals(other.getCodigo()))
			return false;
		if (documento == null) {
			if (other.getDocumento() != null)
				return false;
		} else if (!documento.equals(other.getDocumento()))
			return false;
		return true;
	}
	
}
