package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_hospedagem")
public class Hospedagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHospedagem;
	
	@ManyToOne
	private Quarto idQuarto;
	
	@ManyToOne
	private Hospede idHospede;
	private LocalDate dtCheckin;
	private LocalDate dtCheckout;

	@Deprecated
	public Hospedagem() {
	}

	public Hospedagem(Quarto idQuarto, Hospede idHospede, LocalDate dtCheckin, LocalDate dtCheckout) {
		this.idQuarto = idQuarto;
		this.idHospede = idHospede;
		this.dtCheckin = dtCheckin;
		this.dtCheckout = dtCheckout;
	}

	public Integer getIdHospedagem() {
		return idHospedagem;
	}

	public void setIdHospedagem(Integer idHospedagem) {
		this.idHospedagem = idHospedagem;
	}

	public Quarto getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(Quarto idQuarto) {
		this.idQuarto = idQuarto;
	}

	public Hospede getIdHospede() {
		return idHospede;
	}

	public void setIdHospede(Hospede idHospede) {
		this.idHospede = idHospede;
	}

	public LocalDate getDtCheckin() {
		return dtCheckin;
	}

	public void setDtCheckin(LocalDate dtCheckin) {
		this.dtCheckin = dtCheckin;
	}

	public LocalDate getDtCheckout() {
		return dtCheckout;
	}

	public void setDtCheckout(LocalDate dtCheckout) {
		this.dtCheckout = dtCheckout;
	}

	@Override
	public String toString() {
		return "Hospedagem [idHospedagem=" + idHospedagem + ", idQuarto=" + idQuarto.getIdQuarto() + ", idHospede=" + idHospede.getIdHospede()
				+ ", dtCheckin=" + dtCheckin + ", dtCheckout=" + dtCheckout + "]";
	}
}
