package backend.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import backend.restaurant.domain.AppUser;
import backend.restaurant.domain.AppUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curuser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curuser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curuser.getRole()));
        return user;
    }

}