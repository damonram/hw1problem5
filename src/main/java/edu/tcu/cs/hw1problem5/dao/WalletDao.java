package edu.tcu.cs.hw1problem5.dao;

import edu.tcu.cs.hw1problem5.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, String> {
}
