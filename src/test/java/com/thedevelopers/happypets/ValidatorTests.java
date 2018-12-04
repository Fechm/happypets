package com.thedevelopers.happypets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.thedevelopers.happypets.model.Person;

public class ValidatorTests {
	private Validator createValidator() {

		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();

		localValidatorFactoryBean.afterPropertiesSet();

		return localValidatorFactoryBean;

	}

	@Test

	public void shouldNotValidateWhenFirstNameEmpty() {

		LocaleContextHolder.setLocale(Locale.FRANCE);

		Person person = new Person();

		person.setPrimerNombre("");
		person.setEmail("pablo@gmail.com");
		person.setContrasena("12353");
		person.setApellido("conteras");
		person.setFechaDeNacimiento(new Date(12 - 11 - 1990));
		person.setRun((long) 236422464);
		person.setNroTelefono("83882948");
		person.setCalle("23");
		person.setCiudad("chillan");
		person.setNroCalle((long) 23);

		Validator validator = createValidator();

		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations.size()).isEqualTo(1);

		ConstraintViolation<Person> violation = constraintViolations.iterator().next();

		assertThat(violation.getPropertyPath().toString()).isEqualTo("firstName");

		assertThat(violation.getMessage()).isEqualTo("must not be empty");

	}

	@Test
	public void contraseñaVacia() {
		LocaleContextHolder.setLocale(Locale.FRANCE);

		Person person = new Person();

		person.setPrimerNombre("pablo");
		person.setEmail("pablo@gmail.com");
		person.setContrasena("12353");
		person.setApellido("conteras");
		person.setFechaDeNacimiento(new Date(12 - 11 - 1990));
		person.setRun((long) 236422464);
		person.setNroTelefono("83882948");
		person.setCalle("23");
		person.setCiudad("chillan");
		person.setNroCalle((long) 23);

		Validator validator = createValidator();

		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations.size()).isEqualTo(1);

		ConstraintViolation<Person> violation = constraintViolations.iterator().next();

		assertThat(violation.getPropertyPath().toString()).isEqualTo("contrasena");

		assertThat(violation.getMessage()).isEqualTo("must not be empty");

	}

}
