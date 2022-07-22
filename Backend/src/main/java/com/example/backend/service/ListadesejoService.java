package com.example.backend.service;


import com.example.backend.dto.Productdto;
import com.example.backend.model.Listadesejo;
import com.example.backend.model.User;
import com.example.backend.repository.ListadesejoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListadesejoService {

    @Autowired
    ListadesejoRepository listadesejoRepository;

    @Autowired
    ProductService productService;

    public void createlistadesejo(Listadesejo listadesejo) {
        listadesejoRepository.save(listadesejo);
    }

    public List<Productdto> getWishListForUser(User user) {
   final List<Listadesejo> listadesejos = listadesejoRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<Productdto> productDtos = new ArrayList<>();
        for (Listadesejo listadesejo: listadesejos) {
            productDtos.add(productService.getProductdto(listadesejo.getProduct()));
        }

        return productDtos;
    }
}
