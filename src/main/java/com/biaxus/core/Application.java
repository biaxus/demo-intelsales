package com.biaxus.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import com.biaxus.core.config.error.pages.EmbeddedServletContainerCustomizerImpl;
import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.AuthorityId;
import com.biaxus.core.domain.AuthorityMode;
import com.biaxus.core.domain.AuthorityType;
import com.biaxus.core.domain.Cycle;
import com.biaxus.core.domain.Enterprise;
import com.biaxus.core.domain.EnterpriseRole;
import com.biaxus.core.domain.EnterpriseRoleAuthority;
import com.biaxus.core.domain.EnterpriseRoleAuthorityId;
import com.biaxus.core.domain.EnterpriseRoleId;
import com.biaxus.core.domain.Role;
import com.biaxus.core.domain.RoleAuthority;
import com.biaxus.core.domain.RoleAuthorityId;
import com.biaxus.core.domain.User;
import com.biaxus.core.domain.UserAuthority;
import com.biaxus.core.domain.UserAuthorityId;
import com.biaxus.core.domain.repository.AuthorityRepository;
import com.biaxus.core.domain.repository.CycleRepository;
import com.biaxus.core.domain.repository.EnterpriseRepository;
import com.biaxus.core.domain.repository.EnterpriseRoleRepository;
import com.biaxus.core.domain.repository.RoleAuthorityRepository;
import com.biaxus.core.domain.repository.RoleEnterpriseAuthorityRepository;
import com.biaxus.core.domain.repository.RoleRepository;
import com.biaxus.core.domain.repository.UserAuthorityRepository;
import com.biaxus.core.domain.repository.UserRepository;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		// System.out.println("Let's inspect the beans provided by
		// SpringBoot:");
		// String[] beanNames = ctx.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames) {
		// System.out.println(beanName);
		// }

	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		Hibernate4Module module = new Hibernate4Module();
		module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
		builder.indentOutput(true).modules(module, new ParameterNamesModule());
		return builder;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		rbms.setBasename("i18n/messages");
		return rbms;
	}

	@Bean
	public ThemeSource themeSource() {
		ResourceBundleThemeSource rbms = new ResourceBundleThemeSource();
		rbms.setBasenamePrefix("theme/");
		return rbms;
	}

	@Bean
	public ThemeResolver themeResolver() {
		CookieThemeResolver ctr = new CookieThemeResolver();
		ctr.setDefaultThemeName("skin-blue-light");
		return ctr;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizerImpl();
	}

	@Bean
	// @formatter:off
	public CommandLineRunner demo(EnterpriseRepository repository, 
								  CycleRepository cRepository, 
								  UserRepository userRepository,
								  AuthorityRepository authorityRepository,
								  RoleRepository roleRepository,
								  EnterpriseRoleRepository enterpriseRoleRepository,
								  RoleAuthorityRepository roleAuthorityRepository,
								  UserAuthorityRepository userAuthorityRepository,
								  RoleEnterpriseAuthorityRepository roleEnterpriseAuthorityRepository,
								  PasswordEncoder passwordEncoder) {
	// @formatter:on
		return (args) -> {
			// List<Authority> authorityList = new ArrayList<>();
			// String[] dashboard = new String[]{"DASHBOARD"};
			// AuthorityId aid = new AuthorityId();
			// aid.setKey(dashboard[0]);
			// aid.setType(AuthorityType.VIEW);
			// Authority _a = authorityRepository.findOne(aid);
			//
			// authorityList.add(_a);
			//
			// Role role = new Role();
			// role.setId("USER_DASHBOARD");
			// role.setDescription("");
			// roleRepository.save(role);
			//
			// int maxRoles = 1;
			// List<Authority> randomAuthorityList = new ArrayList<>();
			//
			// Enterprise e = repository.findOne(163L);
			//
			// User u = new User();
			// u.setUsername("demo");
			// u.setPassword(passwordEncoder.encode("demo"));
			// u.setEnabled(true);
			// u.setAccountNonExpired(true);
			// u.setAccountNonLocked(true);
			// u.setCredentialsNonExpired(true);
			// HashSet<Role> roleList = new HashSet<>();
			// roleList.add(role);
			// u.setRoleList(roleList);
			// u.setEnterprise(e);
			// userRepository.save(u);
			//
			// maxRoles = 1;
			// randomAuthorityList = new ArrayList<>();
			// for (int i = 0; i < maxRoles;) {
			// int index = i;
			// if (randomAuthorityList.contains(authorityList.get(index)))
			// continue; 
			// randomAuthorityList.add(authorityList.get(index));
			// i++;
			// }
			//
			// HashSet<UserAuthority> userAuthorityList = new HashSet<>();
			// for (Authority a : randomAuthorityList) {
			// UserAuthority ua = new UserAuthority();
			// UserAuthorityId uaid = new UserAuthorityId();
			// uaid.setAuthorityKey(a.getId().getKey());
			// uaid.setAuthorityType(a.getId().getType());
			// uaid.setUsername(u.getUsername());
			// ua.setId(uaid);
			// ua.setAuthority(a);
			// ua.setUser(u);
			// ua.setAuthorityMode(AuthorityMode.GRANT);
			// userAuthorityList.add(ua);
			// }
			// userAuthorityRepository.save(userAuthorityList);
		};
	}

}
