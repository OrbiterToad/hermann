package ch.wetwer.server.data.repository;

import ch.wetwer.server.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageById(Long id);

}
