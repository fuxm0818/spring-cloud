package cloud.simple.service.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cloud.simple.service.service.UserService;
import cloud.simple.service.model.User;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> readUserInfo(){
//		List<User> list=userService.searchAll();
		List<User> list = new ArrayList<User>();
		User u1 = new User();
		u1.setUsername("u1");
		list.add(u1);
		User u2 = new User();
		u2.setUsername("u2");
		list.add(u2);
		return list;
	}
}
