package com.excilys.computerdb.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.dao.impl.UserDaoImpl;
import com.excilys.computerdb.model.User;
import com.excilys.computerdb.model.UserRole;

public class UserService implements UserDetailsService {

  @Autowired
  private UserDaoImpl userDao;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(final String username)
      throws UsernameNotFoundException {

    User user = userDao.findByUserName(username);
    List<GrantedAuthority> authorities =
        buildUserAuthority(user.getUserRole());

    return buildUserForAuthentication(user, authorities);

  }

  /**
   * Converts com.excilys.computerdb.model.User to
   * org.springframework.security.core.userdetails.User.
   * @param user : the user to convert.
   * @param authorities : list of the user's authorities.
   * @return : the converted user.
   */
  private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
      List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(),
        user.isEnabled(), true, true, true, authorities);
  }

  /**
   * Builds the user's authorities with his roles.
   * @param userRoles : the user's roles to convert.
   * @return : the built user's authorities.
   */
  private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    // Build user's authorities
    for (UserRole userRole : userRoles) {
      setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
    }

    List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

    return result;
  }
}