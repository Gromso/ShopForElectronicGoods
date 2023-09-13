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

    private static String generateUniqueNumbers() {
        Random random = new Random();
        Set<Integer> dodijeljeniBrojevi = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (dodijeljeniBrojevi.size() < 9) {
            int randomNum;
            do {
                randomNum = 1 + random.nextInt(9);
            } while (dodijeljeniBrojevi.contains(randomNum));
            sb.append(randomNum);
            if (dodijeljeniBrojevi.size() < 8) {
                sb.append(" ");
            }
            dodijeljeniBrojevi.add(randomNum);
        }
        return sb.toString();
    }

    public static String generateFileName(String originalFileName) {
        String sanitizedFileName = originalFileName.replaceAll("[^A-Za-z0-9./\\s_]", "");
        Instant now = Instant.now();
        String uniqueNumbers = generateUniqueNumbers();
        String  ee = uniqueNumbers.replace(" ", "");
        return now.getEpochSecond() + ee + sanitizedFileName.toLowerCase();


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
