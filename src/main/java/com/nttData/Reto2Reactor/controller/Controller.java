package com.nttData.Reto2Reactor.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/index")
public class Controller {

	// Base de datos
	private List<Person> listaUsuarios = new ArrayList<>();
	
	private Flux<Person> flux;

	public Controller() {
		listaUsuarios.add(new Person("Adri", "adri", "1234", 23));
		listaUsuarios.add(new Person("Ivan", "ivan", "1234", 24));
		listaUsuarios.add(new Person("Ana", "ana", "1234", 25));
	}

	// Para hacer la búsqueda
	// http://localhost:8080/login?user=adri&password=1234
	@GetMapping(path = "/login", produces = "text/event-stream")
	public Flux<Person> login(@RequestParam(value = "user") String user,
			@RequestParam(value = "password") String pass) {
		var res = listaUsuarios.stream().filter(p -> (p.getUser().equals(user) && p.getPassword().equals(pass)))
				.collect(Collectors.toList());
		if (!res.isEmpty()) {
			 flux = Flux.fromIterable(listaUsuarios);

			return flux;
		}

		return null;

	}

	/**
	 * Delete (aun no está terminado)
	 * 
	 * @param user
	 * @param pass
	 * @param delete
	 * @return
	 */
	@GetMapping(path = "/delete", produces = "text/event-stream")
	public Flux delete(@RequestParam(value = "user") String user, @RequestParam(value = "password") String pass,
			@RequestParam(value = "delete") String delete) {
		var res = listaUsuarios.stream().filter(p -> (p.getUser().equals(user) && p.getPassword().equals(pass)))
				.collect(Collectors.toList());
		if (!res.isEmpty()) {
			var del = listaUsuarios.stream().filter(p -> p.getUser().equals(delete)).collect(Collectors.toList());
			listaUsuarios.remove(del.get(0));
			System.err.println();
			Flux<Person> flux = Flux.fromIterable(listaUsuarios);

			return flux;
		}

		return null;

	}

	@GetMapping(path = "/addUser")
	public void addUser(@RequestParam String name, @RequestParam String user, @RequestParam String password,
			@RequestParam Integer age) {
		listaUsuarios.add(new Person(name, user, password, age));
		Flux.concat(flux, Flux.just(new Person(name, user, password, age)));
	}
	
	public void delteUser(@RequestParam String name) {
		for (Person person : listaUsuarios) {
			//if(person.getUser()==name) 
		}
	}

}
