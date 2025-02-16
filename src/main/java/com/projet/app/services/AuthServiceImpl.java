//Implémentation du service de création d'utilisateur, incluant le hachage du mot de passe.
package com.projet.app.services;

import java.util.List;

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
	public DBUser createUser(SignUpRequest signupRequest) {
	    if (dbUserRepository.existsByEmail(signupRequest.getEmail())) {
	        return null;
	    }
	    DBUser user = new DBUser();
	    user.setName(signupRequest.getName());
	    user.setEmail(signupRequest.getEmail());
	    
	        // Gérer le cas où le numéro de téléphone est invalide
	    	user.setPhoneNumber(signupRequest.getPhoneNumber()); // Ajouter le numéro de téléphone

	    

	    
	    Profile profile = signupRequest.getProfile();
	    if (profile == null || profile.getRole() == null) {
	        profile = new Profile("user");
	    }
	    user.setProfile(profile);

	    String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
	    user.setPassword(hashPassword);

	    dbUserRepository.save(user);
	    return user;
	}

	
	@Override
	public List<DBUser> getAllUsers() {
	    return dbUserRepository.findAll();
	}


    // Lire un utilisateur par ID
    public DBUser getUserById(Long id) {
        return dbUserRepository.findById(id).orElse(null);
    }

    // Mettre à jour un utilisateur
    public DBUser updateUser(Long id, DBUser updatedUser) {
        DBUser existingUser = dbUserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setStructure(updatedUser.getStructure());
            existingUser.setProfile(updatedUser.getProfile());
            return dbUserRepository.save(existingUser);
        }
        return null;
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        dbUserRepository.deleteById(id);
    }
	
}
