package ru.cft.ShiftLAB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cft.ShiftLAB.controllers.dto.BestSellerRequest;
import ru.cft.ShiftLAB.controllers.dto.DurationRequest;
import ru.cft.ShiftLAB.models.Seller;

import java.time.LocalDateTime;
import java.util.List;

@Repository(value = "sellerRepository")
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "select * " +
            "from seller " +
            "where id = (SELECT id " +
            "            FROM (SELECT seller as id, SUM(amount) " +
            "                  FROM transaction " +
            "                  WHERE ?1 < transactiondate AND ?2 > transactiondate " +
            "                  GROUP BY seller " +
            "                  ORDER BY sum DESC " +
            "                  LIMIT 1 " +
            "                 ) as tbl " +
            "          )", nativeQuery = true)
    public Seller bestSeller(LocalDateTime startTime, LocalDateTime endTime);

    @Query(value = "SELECT *\n" +
            "FROM seller\n" +
            "WHERE id IN (SELECT id FROM\n" +
            "(SELECT seller as id, SUM(amount)\n" +
            "FROM transaction\n" +
            "WHERE ?1 < transactiondate AND ?2 > transactiondate\n" +
            "GROUP BY seller\n" +
            "HAVING SUM(amount) < ?3\n" +
            "ORDER BY sum DESC) as tbl)", nativeQuery = true)
    public List<Seller> lowSumSellers(LocalDateTime startTime, LocalDateTime endTime, int boundaryAmount);
}
