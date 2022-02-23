package map.repository;

import map.entity.District;
import map.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepo extends JpaRepository<Region, Long> {

    Region getByTitle(String trim);

    boolean existsByTitle(String regionTitle);

    boolean existsByTitleAndDistrictsTitle(String trim, String trim1);

    Region getByDistricts(District byId);
}
