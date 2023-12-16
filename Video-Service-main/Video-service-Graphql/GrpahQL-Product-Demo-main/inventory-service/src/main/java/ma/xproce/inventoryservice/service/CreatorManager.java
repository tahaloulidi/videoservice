package ma.xproce.inventoryservice.service;


import lombok.AllArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dtos.CreatorDTO;
import ma.xproce.inventoryservice.mappers.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreatorManager {
    CreatorRepository creatorRepository;
    ModelMapperConfig modelMapperConfig;
    public List<CreatorDTO> getCreatorList() {
        List<Creator> creators=creatorRepository.findAll();
        List<CreatorDTO> creatorsDTO = null;
        for (Creator creator:creators) {
            CreatorDTO creatorDTO= modelMapperConfig.fromCreator(creator);
            creatorsDTO.add(creatorDTO);
        }
        return creatorsDTO;
    }

    public CreatorDTO saveCreator(CreatorDTO creatorDTO) {
        return modelMapperConfig.fromCreator(creatorRepository.save(modelMapperConfig.fromCreatorDTO(creatorDTO)));
    }
}
