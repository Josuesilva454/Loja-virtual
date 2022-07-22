package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.Productdto;
import com.example.backend.model.Listadesejo;
import com.example.backend.model.Product;
import com.example.backend.model.User;
import com.example.backend.service.AuthenticationService;
import com.example.backend.service.ListadesejoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Listadesejo")
public class ListadesejoController {

    @Autowired
    ListadesejoService listadesejoService;
    @Autowired
    AuthenticationService authenticationService;

    // salvar o produto dentro da lista;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);

        //encontre o usuário
        User user = authenticationService.getUser(token);

        // salvar o item dentro da lista de desejo
        Listadesejo listadesejo = new Listadesejo(user, product);
        listadesejoService.createlistadesejo(listadesejo);

        // retorna a lista de desejo para um item do usúario
        ApiResponse apiResponse = new ApiResponse(true, "Adicionado à Lista de Desejos");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
    // get all wishlist item for a user

    @GetMapping("/{token}")
    public ResponseEntity<List<Productdto>> getWishList(@PathVariable("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);

        List<Productdto> productDtos = listadesejoService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
