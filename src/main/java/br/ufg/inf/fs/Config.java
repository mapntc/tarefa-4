package br.ufg.inf.fs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufg.inf.fs.entities.Hospedagem;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.entities.Hotel;
import br.ufg.inf.fs.entities.Quarto;
import br.ufg.inf.fs.entities.Role;
import br.ufg.inf.fs.entities.Usuario;
import br.ufg.inf.fs.enums.CategoriaQuarto;
import br.ufg.inf.fs.repositories.HospedagemRepository;
import br.ufg.inf.fs.repositories.HospedeRepository;
import br.ufg.inf.fs.repositories.HotelRepository;
import br.ufg.inf.fs.repositories.QuartoRepository;
import br.ufg.inf.fs.repositories.RoleRepository;
import br.ufg.inf.fs.repositories.UsuarioRepository;

@Configuration
@Profile("dev")
public class Config  implements CommandLineRunner{

	@Autowired
	private HotelRepository hoteRepository;
	
	@Autowired
	private QuartoRepository quartoRepository;
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private HospedagemRepository hospedagemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		/*
		 * INSERIR NO MEU BANCO DE DADOS INFORMAÇÕES INICIAIS...
		 * */
		
		Hotel h1 = new Hotel(null, "Calderão Furado", "Beco Diagonal", 3);
		Hotel h2 = new Hotel(null, "Bates Hotel", "White Pine Bay", 2);
		Hotel h3 = new Hotel(null, "Hotel Overlook", "Colorado", 4);
		Hotel h4 = new Hotel(null, "Continental Hotel", "Ney York City", 5);
		
		h1 = hoteRepository.save(h1);
		h2 = hoteRepository.save(h2);
		h3 = hoteRepository.save(h3);
		h4 = hoteRepository.save(h4);
		
		Quarto q1 = quartoRepository.save(new Quarto(null, h1, CategoriaQuarto.APARTAMENTO, 4, 1010, 200.0));
		Quarto q2 = quartoRepository.save(new Quarto(null, h2, CategoriaQuarto.SIMPLES, 1, 7, 100.0));
		Quarto q3 = quartoRepository.save(new Quarto(null, h3, CategoriaQuarto.PADRAO, 2, 306, 150.0));
		Quarto q4 = quartoRepository.save(new Quarto(null, h4, CategoriaQuarto.LUXO, 3, 1313, 500.0));
		
		// Seed no banco de dados
		Hospede hosp1 = hospedeRepository.save(new Hospede("Elias", LocalDate.of(2001, 3, 9), "12345678911"));
		Hospede hosp2 = hospedeRepository.save(new Hospede("Casagrande", LocalDate.of(2001, 9, 27), "12345678912"));
		Hospede hosp3 = hospedeRepository.save(new Hospede("Galvao", LocalDate.of(2001, 5, 9), "12345678913"));
		Hospede hosp4 = hospedeRepository.save(new Hospede("Tino", LocalDate.of(2001, 7, 19), "12345678914"));
		
		Hospedagem hos1 = hospedagemRepository.save(new Hospedagem(q1, hosp1, LocalDate.of(2021, 7, 20), LocalDate.of(2021, 7, 22)));
		Hospedagem hos2 = hospedagemRepository.save(new Hospedagem(q2, hosp2, LocalDate.of(2021, 7, 20), LocalDate.of(2021, 7, 23)));
		Hospedagem hos3 = hospedagemRepository.save(new Hospedagem(q3, hosp3, LocalDate.of(2021, 7, 20), LocalDate.of(2021, 7, 24)));
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.save(new Role("ROLE_ADMIN")));
		roles.add(roleRepository.save(new Role("ROLE_USER")));
		roles.add(roleRepository.save(new Role("ROLE_GUEST")));
		
		Usuario user1 = new Usuario("elias", "Elias Nepomuceno", new BCryptPasswordEncoder().encode("123456"), roles);
		
		usuarioRepository.save(user1);
	}

}
