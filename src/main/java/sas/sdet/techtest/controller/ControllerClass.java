package sas.sdet.techtest.controller;


import org.springframework.http.ResponseEntity;
import sas.sdet.techtest.domain.Order;
import sas.sdet.techtest.domain.User;
import sas.sdet.techtest.repository.RepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerClass {
	
	@Autowired
	RepositoryClass repositoryClass;

	/**
	 * Old Implementation
	 * @param name
	 * @return
	 */
//	@RequestMapping(path="/user/{name}")
//	@ResponseBody
//	public User getUserDexterity(@PathVariable String name){
//		return repositoryClass.loadUser(name);
//	}

	/**
	 * New Improved implementation
	 * @param name
	 * @return
	 */
	@RequestMapping(path = "/user/{name}")
	@ResponseBody
	public ResponseEntity<User> getUserDexterity(@PathVariable String name) {
		User user = repositoryClass.loadUser(name);

		if (user == null) {
			return ResponseEntity.notFound().build(); // Return 404 response for non-existent user
		}

		return ResponseEntity.ok(user);
	}

	
	@RequestMapping(path="/order", method=RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestParam String user, String item) throws Exception {
		try {
			Order order = repositoryClass.order(user, item);
			if(order == null) {
				throw new Exception();
			}
			return "OK";
		} catch (Exception Ko) {
			return "KO";
		}
	}

}