package br.com.yurekesley.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.yurekesley.domain.User;
import br.com.yurekesley.repositories.UserJdbcRepository;
import br.com.yurekesley.repositories.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserJdbcRepository userJdbcRepository;

	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	@RequestMapping("/")
	public String home(Model model) {

		
		System.out.println(platformTransactionManager);
		
		List<User> findAll = userJdbcRepository.findAll();

		List<User> findAll2 = userRepo.findAll();

		model.addAttribute("users", findAll);
		return "index";
	}

}
