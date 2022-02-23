package map.repository;

import map.entity.Address;
import map.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {

    District getByTitle(String trim);

    boolean existsByTitle(String districtTitle);

    boolean existsByTitleAndAddress(String trim, Address byTitleAndNumber);

    District getByAddress(Address byId);
}
