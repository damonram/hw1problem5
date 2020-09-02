package edu.tcu.cs.hw1problem5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    private String id;
    private String name;
//    private String walletId;

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
}
