package edu.tcu.cs.hw1problem5.domain;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
>>>>>>> master
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    private String id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="owner")
    @JsonIgnore
    private List<Product> productsOwned = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Wallet wallet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="owner")
    @JsonIgnore
    private List<Product> productsOwned = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    private Wallet wallet;


    public User() {
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product){
        //set owner
        product.setOwner(this);
        //add this product to this user
        this.productsOwned.add(product);
    }
    public void removeProduct(Product product){
        //remove owner
        product.setOwner(null);
        //remove this product from products
        this.productsOwned.remove(product);
    }
    public void addWallet(Wallet wallet){
        wallet.setOwner(this);
        this.setWallet(wallet);
    }
    public void removeWallet(Wallet wallet){
        wallet.setOwner(null);
        this.setWallet(null);
    }

    public List<Product> getProductsOwned() {
        return productsOwned;
    }

    public void setProductsOwned(List<Product> productsOwned) {
        this.productsOwned = productsOwned;
    }
}
