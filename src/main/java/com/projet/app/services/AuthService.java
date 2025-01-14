//nterface définissant la méthode pour créer un utilisateur.
package com.projet.app.services;

import com.projet.app.dto.SignUpRequest;
import com.projet.app.models.DBUser;

public interface AuthService {
	DBUser createUser(SignUpRequest signupRequest);
}
