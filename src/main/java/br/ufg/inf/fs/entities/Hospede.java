package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_hospede")
public class Hospede implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHospede;
	private String nmHospede;
	private LocalDate dtNascimento;
	private Integer cpf;

	@Deprecated // usado apenas para API de Reflection
	public Hospede() {
	}

	public Hospede(String nmHospede, LocalDate dtNascimento, Integer cpf) {
		this.nmHospede = nmHospede;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
	}

	public Integer getIdHospede() {
		return idHospede;
	}

	public void setIdHospede(Integer idHospede) {
		this.idHospede = idHospede;
	}

	public String getNmHospede() {
		return nmHospede;
	}

	public void setNmHospede(String nmHospede) {
		this.nmHospede = nmHospede;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Hospede [idHospede=" + idHospede + ", nmHospede=" + nmHospede + ", dtNascimento=" + dtNascimento
				+ ", cpf=" + cpf + "]";
	}
}
