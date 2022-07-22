package com.example.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "wishlist")
public class Listadesejo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Listadesejo() {
    }

    public Listadesejo(User user, Product product) {
        this.user = user;
        this.product = product;
        this.createdDate = new Date();
    }

    public Listadesejo(Integer id, User user, Date createdDate, Product product) {
        this.id = id;
        this.user = user;
        this.createdDate = createdDate;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createDate) {
        this.createdDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
