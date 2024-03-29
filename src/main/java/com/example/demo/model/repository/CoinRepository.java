package com.example.demo.model.repository;

import com.example.demo.model.entity.CoinPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<CoinPO, Integer> {

    List<CoinPO> findAllByCurrencyIn(List<String> currencyList);

    boolean existsByCurrency(String currency);

    CoinPO findByCurrency(String currency);

    @Transactional
    Integer deleteByCurrency(String currency);
}
