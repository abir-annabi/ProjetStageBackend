//Gère l'inscription d'un nouvel utilisateur en utilisant le service AuthService.
package com.projet.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.SignUpRequest;
import com.projet.app.models.DBUser;
import com.projet.app.services.AuthService;

@RestController
public class SignupController {
	
	private final AuthService authService; 
	
	@Autowired
	public SignupController(AuthService authService) {
		this.authService=authService;
	}
	@GetMapping("/signup") // Vérifiez si vous utilisez @GetMapping ou @PostMapping
    public String signup() {
        return "Signup endpoint is working!";
    }
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signupRequest ){
		DBUser createdUser = authService.createUser(signupRequest);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            //Si l'utilisateur a été créé avec succès, renvoie une réponse HTTP avec le statut 201 Created et l'utilisateur créé dans le corps de la réponse.
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
            //l'utilisateur n'a pas pu être créé (par exemple, si l'email existe déjà), renvoie une réponse HTTP avec le statut 400 Bad Request et un message d'erreur
        }
    }
	
}
