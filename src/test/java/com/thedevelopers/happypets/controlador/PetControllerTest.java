package com.thedevelopers.happypets.controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.servicios.IPetService;

public class PetControllerTest {
	  @Autowired

	    private PetController petRestController;

	    @MockBean

	    protected IPetService clinicService;

	    private MockMvc mockMvc;

	    private List<Pet> pets;

	    @Before

	    public void initPets(){

	    	this.mockMvc = MockMvcBuilders.standaloneSetup(petRestController)

	    			.setControllerAdvice(new Exception())

	    			.build();

	    	pets = new ArrayList<Pet>();

	    	Person george= new Person();

	    	george.setPrimerNombre("");
			george.setEmail("pablo@gmail.com");
			george.setContrasena("12353");
			george.setApellido("conteras");
			george.setFechaDeNacimiento(new Date(12 - 11 - 1990));
			george.setRun((long) 236422464);
			george.setNroTelefono("83882948");
			george.setCalle("23");
			george.setCiudad("chillan");
			george.setNroCalle((long) 23);


	    	Pet pet = new Pet();
	    	pet.setId((long)1);
	    	pet.setNombre("Lulu");
	    	pet.setAdoptante_email("marcela@gmail.com");
	    	pet.setPropietario_email(george.getEmail());
	    	pet.setFechaDeNacimiento(new Date(12-12-2017));
	    	pet.setEspecies("perro");
	    	pets.add(pet);
	    	pet=new Pet();
	    	pet.setId((long)2);
	    	pet.setNombre("pepito");
	    	pet.setAdoptante_email(george.getEmail());
	    	pet.setPropietario_email("marcos@gmail.com");
	    	pet.setFechaDeNacimiento(new Date(12-11-2010));
	    	pet.setEspecies("gato");
	    	pets.add(pet);

	    }
	    @Test
	    public void TestObtenerMascota()throws Exception {

	    	when(this.clinicService.buscarPetPorId((long)2)).thenReturn(pets.get(0));

	        this.mockMvc.perform(get("/api/pets/2")

	        	.accept(MediaType.APPLICATION_JSON_VALUE))

	            .andExpect(status().isOk())

	            .andExpect(content().contentType("application/json;charset=UTF-8"))

	            .andExpect(jsonPath("$.Id").value(2))

	            .andExpect(jsonPath("$.Nombre").value("pepito"));

	    }
@Test
	    public void testGetPetNotFound() throws Exception {

	    	when(this.clinicService.buscarPetPorId((long)-1)).thenReturn(null);

	        this.mockMvc.perform(get("/api/pets/-1")

	        	.accept(MediaType.APPLICATION_JSON))

	            .andExpect(status().isNotFound());

	    }
	@Test
	public void testGetAllPetsSuccess() throws Exception {

    	when(this.clinicService.buscarTodos()).thenReturn(pets);

        this.mockMvc.perform(get("/api/pets/")

        	.accept(MediaType.APPLICATION_JSON))

            .andExpect(status().isOk())

            .andExpect(content().contentType("application/json;charset=UTF-8"))

            .andExpect(jsonPath("$.[0].Id").value(1))

            .andExpect(jsonPath("$.[0].Nombre").value("Lulu"))

            .andExpect(jsonPath("$.[1].Id").value(2))

            .andExpect(jsonPath("$.[1].Nombre").value("Pepito"));

    }

}
