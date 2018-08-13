package ch.lebois.server.service;

import ch.lebois.server.data.entity.Client;
import ch.lebois.server.data.entity.Image;
import ch.lebois.server.data.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Service
public class ImageService {

    private ImageRepository imageRepository;

    private ArrayList<Byte> bytes = new ArrayList<>();

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void addBytes(String newByte) {
        for (String by : newByte.split("\n")) {
            this.bytes.add(Byte.valueOf(by));
        }
    }

    public void clearBytes() {
        this.bytes = new ArrayList<>();
    }

    public String createImg(Client client) {

        byte[] byteArray = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            byteArray[i] = bytes.get(i);
        }

        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(byteArray));
            File f = new File(client.getName() + "_" + new Date().getTime() + ".jpg");
            ImageIO.write(img, "jpg", f);
            bytes = new ArrayList<>();
            log.info("saved file " + f.getAbsolutePath());
            Image image = new Image();
            image.setClient(client);
            image.setPath(f.getAbsolutePath());
            imageRepository.save(image);
            return f.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
