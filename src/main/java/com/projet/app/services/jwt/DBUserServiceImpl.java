//Charge les détails de l'utilisateur à partir de la base de données pour l'authentification.
package com.projet.app.services.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.app.models.DBUser;
import com.projet.app.repository.DBUserRepository;

@Service
public class DBUserServiceImpl implements UserDetailsService{//une interface centrale de Spring Security utilisée pour récupérer les détails de l'utilisateur 
	
	private final DBUserRepository dbUserRepository;//utilisé pour interagir avec la base de données.
	// Vérification de l'existence d'un utilisateur par email 
	// Récupération d'un utilisateur par email pour l'authentification
	
	
	 
	    public DBUserServiceImpl(@Autowired DBUserRepository dbUserRepository) {
	        this.dbUserRepository = dbUserRepository;
	    }



		   
		 @Override
		 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		     // Write logic to fetch user from DB
		     DBUser user = dbUserRepository.findByEmail(email)
		             .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));
		     
		     // Check the role from user profile and assign the correct authorities
		     String role = user.getProfile().getRole();  // Assuming 'profile' is where the role is stored
		     
		     // Map the role to GrantedAuthority
		     return new org.springframework.security.core.userdetails.User(
		             user.getEmail(),
		             user.getPassword(),
		             java.util.Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
		     );
		 }

}
