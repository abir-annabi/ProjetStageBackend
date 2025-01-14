//Implémentation du service de création d'utilisateur, incluant le hachage du mot de passe.
package com.projet.app.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.app.dto.SignUpRequest;
import com.projet.app.models.DBUser;
import com.projet.app.models.Profile;
import com.projet.app.repository.DBUserRepository;
@Service
public class AuthServiceImpl implements AuthService{
	
	
	private final DBUserRepository dbUserRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthServiceImpl(DBUserRepository dbUserRepository,PasswordEncoder passwordEncoder) {
		this.dbUserRepository = dbUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public DBUser createUser(SignUpRequest signupRequest) {//signuprequest contient les infos d'utilisateur a creer
        //Check if customer already exist
        if (dbUserRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }
        DBUser user = new DBUser();
        
        
        
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        
        
        dbUserRepository.save(user); // Hibernate gérera les deux tables

        BeanUtils.copyProperties(signupRequest,user);//copier les propriétés de l'objet signupRequest vers l'objet user.

        Profile profile = signupRequest.getProfile(); // Rôle transmis via SignUpRequest
        if (profile == null || profile.getRole() == null) {
            profile = new Profile("user"); // Rôle par défaut
        }
        user.setProfile(profile);
        
        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);
        dbUserRepository.save(user);
        return user;
	
	}
	
}
