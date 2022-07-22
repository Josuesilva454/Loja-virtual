package com.example.backend.repository;

import com.example.backend.model.Listadesejo;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public  interface ListadesejoRepository extends JpaRepository<Listadesejo,  Integer > {

     List<Listadesejo> findAllByUserOrderByCreatedDateDesc(User user);

}
