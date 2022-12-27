package com.usermicroservice.usersservice.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.usermicroservice.usersservice.models.AuthenticationRequest;
import com.usermicroservice.usersservice.models.AuthenticationResponse;
import com.usermicroservice.usersservice.models.JwtUtil;
import com.usermicroservice.usersservice.models.User;
import com.usermicroservice.usersservice.models.User1;
import com.usermicroservice.usersservice.repo.UserRepository;
import com.usermicroservice.usersservice.service.MyUserDetailsService;
import com.usermicroservice.usersservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	public UserController(@RequestBody UserService userService) {
	    super();
	    this.userService = userService;
	}

	public UserController() {
	    super();
	}
	
	@PostMapping("/adddoctor")
	public User addNewDoctorUser(@RequestBody User user)
	{
	    return userService.addNewDoctorUser(user);
	}
	
	@PostMapping("/addadmin")
	public User addNewAdminUser(@RequestBody User user)
	{
	    return userService.addNewAdminUser(user);
	}
	
	@PostMapping("/addingdelivaryuser")
	public User addNewDelivaryUser(@RequestBody User user)
	{
	    return userService.addNewDelivaryUser(user);
	}
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers()
	{
	    return  userService.getAllUsers();
	}

	
	@GetMapping("/getbyphonenumber/{mobileNumber}")
	public User getByPhoneNumber(@PathVariable Long mobileNumber)
	{
	    return  userService.findByMobileNo(mobileNumber);
	}
	
	@PutMapping("/updateuser/{userId}")
	public User updateUser(@PathVariable int userId,@RequestBody User user)
	{
	    return  userService.updateUser(user);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public void deleteUser(@PathVariable int userId)
	{
	     userService.deleteUser(userId);
	}
	
	@GetMapping("/getbyusername/{userName}")
	public User getByUserName(@PathVariable String userName)
	{
	    return  userService.getByUserName(userName);
	}
	
	
	@PostMapping("/reg")
    private ResponseEntity<?> subscribe(@RequestBody AuthenticationRequest request)
    {
        System.out.println("Nagalakshmi");
        String username = request.getUsername();
        String password = request.getPassword();


        User1 model = new User1();
        model.setUsername(username);
        model.setPassword(password);

 

        try {
            repo.save(model);
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error while subsribing the user with username " + username));
        }
            return ResponseEntity.ok(new AuthenticationResponse("client subscribed with username " + username));
    }

    @RequestMapping(value="/authenticate", method=RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest  authenticationRequest) throws Exception {
        try
        {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

 

        final String jwt = jwtTokenUtil.generateToken(userDetails);

 

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
	


}
