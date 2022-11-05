package com.online.shop.system.user.service.application.rest;

import com.online.shop.system.user.service.domain.dto.create.CreateAddress;
import com.online.shop.system.user.service.domain.dto.create.CreateUser;
import com.online.shop.system.user.service.domain.dto.create.UpdateAddress;
import com.online.shop.system.user.service.domain.dto.create.response.AddressIDResponse;
import com.online.shop.system.user.service.domain.dto.create.response.Data;
import com.online.shop.system.user.service.domain.dto.create.response.UserAddressResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserIDResponse;
import com.online.shop.system.user.service.domain.ports.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    public final UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<Data<UserIDResponse>> createUser(@RequestBody CreateUser createUser){
        return new ResponseEntity<>(new Data<>(userApplicationService.createUser(createUser)), HttpStatus.CREATED);
    }

    @PostMapping("/address")
    public ResponseEntity<Data<UserAddressResponse>> createAddress(@RequestBody CreateAddress createAddress){
        return new ResponseEntity<>(new Data<>(userApplicationService.addAddress(createAddress)), HttpStatus.CREATED);
    }

    @PatchMapping("/address")
    public ResponseEntity<Data<UserAddressResponse>> updateAddress(@RequestBody UpdateAddress updateAddress){
        return new ResponseEntity<>(new Data<>(userApplicationService.updateAddress(updateAddress)), HttpStatus.OK);
    }

    @DeleteMapping("/address/{addressID}")
    public ResponseEntity<Data<AddressIDResponse>> deleteAddress(@PathVariable("addressID") Integer addressID){
        return new ResponseEntity<>(new Data<>(userApplicationService.deleteAddress(addressID)), HttpStatus.OK);
    }


}
