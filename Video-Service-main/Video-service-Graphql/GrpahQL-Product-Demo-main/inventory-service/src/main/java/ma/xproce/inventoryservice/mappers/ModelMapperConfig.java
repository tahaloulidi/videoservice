package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dtos.CreatorDTO;
import ma.xproce.inventoryservice.dtos.VideoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {
    private final ModelMapper modelMapper=new ModelMapper();
    public CreatorDTO fromCreator(Creator creator){
        return this.modelMapper.map(creator, CreatorDTO.class);
   }
    public Creator fromCreatorDTO(CreatorDTO creatorDTO){
        return this.modelMapper.map(creatorDTO, Creator.class);
   }

    public VideoDTO fromVideo(Video video){
        return this.modelMapper.map(video, VideoDTO.class);
   }
    public Video fromVideoDTO(VideoDTO videoDTO){
        return this.modelMapper.map(videoDTO, Video.class);
   }
}
