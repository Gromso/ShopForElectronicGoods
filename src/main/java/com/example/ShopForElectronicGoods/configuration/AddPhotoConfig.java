package com.example.ShopForElectronicGoods.configuration;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;


public class AddPhotoConfig {


    private static int[] generateUniqueNumbers() {
        int[] nizBrojeva = new int[9];
        Random random = new Random();

        Set<Integer> dodijeljeniBrojevi = new HashSet<>();
        for (int i = 0; i < nizBrojeva.length; i++) {
            int randomNum;
            do {
                randomNum = 1 + random.nextInt(9);
            } while (dodijeljeniBrojevi.contains(randomNum));
            nizBrojeva[i] = randomNum;
            dodijeljeniBrojevi.add(randomNum);
        }
        return nizBrojeva;
    }

    public static String generateFileName(String OriginalFileName) {
        String fileReplaceName= OriginalFileName.replaceAll("[^A-Za-z0-9./\\s-]", "");
        Instant now = Instant.now();
        int[] nizBrojeva = generateUniqueNumbers();
        return now.getEpochSecond() + Arrays.toString(nizBrojeva) +  fileReplaceName.toLowerCase();
    }


    public static void uploadThumbnail(Path originalFilePath, Path thumbnailFilePath) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalFilePath.toFile());
        // Odaberite željenu širinu i visinu za thumbnail
        int thumbnailWidth = 100; // Širina thumbnail slike
        int thumbnailHeight = 100; // Visina thumbnail slike

        Thumbnails.of(originalImage)
                .size(thumbnailWidth, thumbnailHeight)
                .toFile(thumbnailFilePath.toFile());
    }

}
