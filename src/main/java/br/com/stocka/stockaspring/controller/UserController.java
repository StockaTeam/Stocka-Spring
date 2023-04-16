package br.com.stocka.stockaspring.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stocka.stockaspring.dto.UserDto;
import br.com.stocka.stockaspring.model.UserModel;
import br.com.stocka.stockaspring.service.UserServiceImpl;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    
    final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    // @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid UserDto userDto){  
        
        Optional<UserModel> userModelOptional = userServiceImpl.findByUsername(userDto.getUsername());

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            String userEnteredPasswordWithotEncrypted = userDto.getPassword();
            String encryptedPasswordFromDb = userModelOptional.get().getPassword();

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  
            boolean isPasswordMatches = bcrypt.matches(userEnteredPasswordWithotEncrypted, encryptedPasswordFromDb);
            
            if (isPasswordMatches) {
                return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get().getUserId());
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body("User or Password wrong.");
            }
        }
            
    }

    // @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if(userServiceImpl.existsByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Username " + userDto.getUsername() + " is already in use!");
        }
        
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.save(userModel));
    }

    // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.findAll());
    }

    // @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") Long id){
        Optional<UserModel> userModelOptional = userServiceImpl.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
        Optional<UserModel> userModelOptional = userServiceImpl.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userServiceImpl.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    // @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid UserDto userDto){
        Optional<UserModel> userModelOptional = userServiceImpl.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserId(userModelOptional.get().getUserId());
        // userModel.setRegistrationDate(userModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.save(userModel));
    }
}
