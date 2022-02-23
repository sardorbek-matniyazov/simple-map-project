package map.repository;

import map.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    Address getByTitleAndNumber(String title, Long number);

    boolean existsByTitleAndNumber(String title, Long number);
}
