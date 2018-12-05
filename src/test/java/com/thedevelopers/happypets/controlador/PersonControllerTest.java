package com.thedevelopers.happypets.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.servicios.IPersonService;

public class PersonControllerTest {

	@Autowired

	private MockMvc mockMvc;

	@MockBean

	private IPersonService persons;

	private Person george;

	@Before

	public void setup() {

		george = new Person();

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


	}
	  @Test

	    public void testInitCreationForm() throws Exception {

	        mockMvc.perform(get("/owners/new"))

	            .andExpect(status().isOk())

	            .andExpect(model().attributeExists("persons"))

	            .andExpect(view().name("owners/createOrUpdateOwnerForm"));

	    }



	@Test

	public void testProcessCreationFormSuccess() throws Exception {

		mockMvc.perform(post("/Person/new")

				.param("PrimerNombre", "Joe")

				.param("Email", "fer@gmail.com")

				.param("Contrasena", "123 Caramel Street")

				.param("Apellido", "London")

				.param("FechaDeNacimiento", "13-11-1998")
				.param("Run", "18438483")
				.param("NroTelefono", "38448343")
				.param("Calle", "sur")
				.param("Ciudad", "Santiago")
				.param("NroCalle", "12"))

				.andExpect(status().is3xxRedirection());

	}
	 @Test

	    public void testProcessCreationFormHasErrors() throws Exception {

		  mockMvc.perform(post("/Person/new")
					.param("PrimerNombre", "Joe")
					.param("Email", "fer@gmail.com")
					.param("Apellido", "London")
					.param("FechaDeNacimiento", "13-11-1998")
					.param("Run", "18438483")
					.param("Calle", "sur")
					.param("Ciudad", "Santiago")
					.param("NroCalle", "12")

	            
	        )

	            .andExpect(status().isOk())

	            .andExpect(model().attributeHasErrors("persons"))

	            .andExpect(model().attributeHasFieldErrors("person", "Contrasena"))

	            .andExpect(model().attributeHasFieldErrors("person", "NroTelefono"))

	            .andExpect(view().name("owners/createOrUpdateOwnerForm"));

	    }

}
