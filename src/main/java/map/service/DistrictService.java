package map.service;

import map.dto.DistrictDto;
import map.entity.District;
import map.entity.Region;
import map.repository.DistrictRepo;
import map.repository.RegionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictService {
    private final DistrictRepo repo;
    private final RegionRepo regionRepo;

    public DistrictService(DistrictRepo repo, RegionRepo regionRepo) {
        this.repo = repo;
        this.regionRepo = regionRepo;
    }

    public List<District> getAll() {
        return repo.findAll();
    }

    public District get(Long id) {
        if (!repo.existsById(id)) return new District("There haven't got with selected id !");
        return repo.getById(id);
    }

    public String add(DistrictDto dto) {
        if (dto.getTitle().trim().equals("")) return "the district name can't be empty";
        if (!regionRepo.existsByTitle(dto.getRegionTitle())) return "the Region isn't found";
        if (regionRepo.existsByTitleAndDistrictsTitle(dto.getRegionTitle().trim(), dto.getTitle().trim()))
            return "the district already exist";
        Region myRegion = regionRepo.getByTitle(dto.getRegionTitle().trim());
        List<District> districts = myRegion.getDistricts();
        repo.save(new District(dto.getTitle().trim()));
        districts.add(repo.getByTitle(dto.getTitle().trim()));
        myRegion.setDistricts(districts);
        regionRepo.save(myRegion);
        return "the district successfully saved";
    }

    public String edit(Long id, District district) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        if (district.getTitle().trim().equals("")) return "the district name can't be empty";
        District myDistrict = repo.getById(id);
        myDistrict.setTitle(district.getTitle());
        repo.save(myDistrict);
        return "the district successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        deleteDistrict(repo.getById(id));
        repo.deleteById(id);
        return "the country successfully deleted";
    }

    private void deleteDistrict(District byId) {
        List<District> myDistricts = new ArrayList<>();
        Region region = regionRepo.getByDistricts(byId);
        for (District district: region.getDistricts()) {
            if (district.getId() != byId.getId())
                myDistricts.add(district);
        }
        region.setDistricts(myDistricts);
        regionRepo.save(region);
    }
}