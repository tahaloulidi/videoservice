package ma.xproce.inventoryservice.web;

import lombok.AllArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.dtos.CreatorDTO;
import ma.xproce.inventoryservice.dtos.VideoDTO;
import ma.xproce.inventoryservice.mappers.ModelMapperConfig;
import ma.xproce.inventoryservice.service.CreatorManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
public class VideoGraphQlController {

    private CreatorRepository creatorRepository;

   private ModelMapperConfig modelMapperConfig;
   private CreatorManager creatorManager;
   private VideoManager videoManager;

    @QueryMapping
    public List<CreatorDTO> creatorList(){
        return creatorManager.getCreatorList();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }

  /*  @MutationMapping
    public Video saveVideo(@Argument Video video){
        return videoRepository.save(video) ;
    }*/

    @MutationMapping
    public CreatorDTO saveCreator(@Argument CreatorDTO creatorDTO){
        return creatorManager.saveCreator(creatorDTO);
    }
    @SubscriptionMapping("notifyVideoChange")
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();
                    CreatorDTO creatorDTO = CreatorDTO.builder().name("x" +
                                    random.nextInt())
                            .email("x@gmail.com").build();
                    CreatorDTO creatorDTO1 = creatorManager.saveCreator(creatorDTO);
                    Video video = videoManager.findById(1L);
                    VideoDTO videoDTO= modelMapperConfig.fromVideo(video);
                    videoDTO.setCreator(creatorDTO1);
                    videoManager.updateVideo(modelMapperConfig.fromVideoDTO(videoDTO));
                    return video;
                }));
    }
}
