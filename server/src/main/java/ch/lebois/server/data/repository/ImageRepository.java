package ch.lebois.server.data.repository;

import ch.lebois.server.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageById(Long id);

}
