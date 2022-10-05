package com.app.controller;

import com.app.exceptions.AddressException;
import com.app.login.LoginException;
import com.app.model.Address;
import com.app.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<Address> addAddressHandler(@Valid @RequestBody Address address ,@RequestParam String key) throws AddressException, LoginException {

        Address addAddresses = addressService.addAddress(address, key);


        return new ResponseEntity<Address>(addAddresses, HttpStatus.OK);

    }
    @PutMapping("/address")
    public ResponseEntity<Address> updateProductHandler(@RequestBody Address address) throws AddressException{

        Address updateAddress = addressService.updateAddress(address);

        return new ResponseEntity<Address>(updateAddress, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<Address> deleteAddressHandler(@PathVariable("addressId") Integer addressId) throws AddressException{

        Address deleteAddress = addressService.removeAddress(addressId);

        return new ResponseEntity<Address>(deleteAddress, HttpStatus.OK);

    }
    @GetMapping("/getalladdress")
    public ResponseEntity<List<Address>> getAllAddressHandler() throws AddressException{

        List<Address> allAddress = addressService.viewAllAddress();

        return new ResponseEntity<List<Address>>(allAddress, HttpStatus.OK);

    }

    @GetMapping("/getaddress/{addressId}")
    public ResponseEntity<Address> getAddressHandler(@PathVariable("addressId") Integer addressId) throws AddressException{

        Address existingAddress = addressService.viewAddress(addressId);

        return new ResponseEntity<Address>(existingAddress, HttpStatus.OK);

    }

}
