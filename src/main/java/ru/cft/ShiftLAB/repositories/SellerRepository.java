package ru.cft.ShiftLAB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.ShiftLAB.models.Seller;
import java.util.List;

@Repository(value = "sellerRepository")
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
