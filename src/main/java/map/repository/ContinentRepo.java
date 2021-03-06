package map.repository;

import map.entity.Continent;
import map.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContinentRepo  extends JpaRepository<Continent, Long> {

    boolean existsByTitle(String title);

    Optional<Continent> findByTitle(String title);

    Continent getByTitle(String title);

    boolean existsByTitleAndCountryTitle(String title, String countryTitle);

    boolean existsByTitleAndCountry(String title, Country country);

    Continent getByCountry(Country country);
}
