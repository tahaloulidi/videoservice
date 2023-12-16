package ma.xproce.inventoryservice.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreatorDTO {
    private String name;
    private String email;
    @OneToMany(mappedBy = "creator")
    private List<VideoDTO> videos;
}