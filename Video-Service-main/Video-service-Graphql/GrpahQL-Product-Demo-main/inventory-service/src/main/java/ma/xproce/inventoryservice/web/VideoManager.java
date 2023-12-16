package ma.xproce.inventoryservice.web;

import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoManager {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CreatorRepository creatorRepository;


    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    public Video saveVideo(Video video) {
        creatorRepository.save(video.getCreator());
        return videoRepository.save(video);
    }

    public Video findById(Long id) {
        return videoRepository.findById(id).get();
    }

    public Video updateVideo(Video video) {
        return videoRepository.save(video);

    }
}