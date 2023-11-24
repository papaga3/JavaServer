package huy.java.server.security.userDetail;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import huy.java.server.user.repo.UserRepo;
import huy.java.server.role.entity.Role;
import huy.java.server.user.entity.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found for email " + username);
		}
		Iterator<Role> roleIterator = user.getRoles().iterator();
		while(roleIterator.hasNext()) {
			System.out.println(roleIterator.next().getName());
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
	}

}
