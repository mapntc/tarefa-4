package br.ufg.inf.fs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

	List<Hotel> findByNmHotel(String nmHotel);

	List<Hotel> findByQtdEstrelas(Integer qtdEstrelas);

}
