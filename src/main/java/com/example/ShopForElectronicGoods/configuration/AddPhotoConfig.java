package com.example.ShopForElectronicGoods.configuration;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
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

    public static void uploadOriginalFile(Path originalFilePath , MultipartFile file ) throws IOException {
        if (!Files.exists(originalFilePath.getParent())) {
            Files.createDirectories(originalFilePath.getParent());
        }
        Files.write(originalFilePath, file.getBytes());
    }


    public static void uploadSmallFile(Path originalFilePath, Path thumbnailFilePath) throws IOException {
        if (!Files.exists(thumbnailFilePath.getParent())) {
            Files.createDirectories(thumbnailFilePath.getParent());
        }
        BufferedImage originalImage = ImageIO.read(originalFilePath.toFile());
        Thumbnails.of(originalImage)
                .width(320)
                .height(240)
                .addFilter(new Canvas(320, 240, Positions.CENTER, Color.WHITE))
                .toFile(thumbnailFilePath.toFile());
    }

    public static void uploadThumbnailFile(Path originalFilePath, Path thumbnailFilePath) throws IOException {
        if (!Files.exists(thumbnailFilePath.getParent())) {
            Files.createDirectories(thumbnailFilePath.getParent());
        }
        BufferedImage originalImage = ImageIO.read(originalFilePath.toFile());
        Thumbnails.of(originalImage)
                .height(120)
                .width(100)
                .addFilter(new Canvas(120, 100, Positions.CENTER, Color.WHITE))
                .toFile(thumbnailFilePath.toFile());
    }

}
