package ru.testproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.testproject.Service.UserService;
import ru.testproject.model.User;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Анатолий on 08.06.2016.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/")
public class RestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody @Valid User user, BindingResult result){
        if (result.hasErrors()){
            StringBuilder sb=new StringBuilder();
            result.getFieldErrors().forEach(n -> sb.append(n.getField()).append(": ").append(n.getDefaultMessage()));
            return new ResponseEntity<String>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
        userService.saveUser(user);
        return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(n -> sb.append(n.getField()).append(": ").append(n.getDefaultMessage()));
            return new ResponseEntity<String>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            userService.saveUser(user);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }
}
