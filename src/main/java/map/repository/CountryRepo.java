package map.repository;

import map.entity.Country;
import map.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CountryRepo extends JpaRepository<Country, Long> {
    Country getByTitle(String title);

    boolean existsByTitle(String name);

    Country getByRegions(Region byId);

    boolean existsByTitleAndRegionsTitle(String trim, String trim1);
}
