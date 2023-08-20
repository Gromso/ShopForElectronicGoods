package com.example.ShopForElectronicGoods.configuration;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;

import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPhotoConfig {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

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
        Pattern pattern = Pattern.compile("\\.(jpg|png|jpeg)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(OriginalFileName);
        if (!matcher.find()) {
            throw new ApiRequestException("Invalid file extension");
        }
        String fileExtension = matcher.group(1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new ApiRequestException("File extension not allowed");
        }
        String fileReplaceName = OriginalFileName.replaceAll("[^A-Za-z0-9./\\s-]", "");
        Instant now = Instant.now();
        int[] nizBrojeva = generateUniqueNumbers();
        return now.getEpochSecond() + Arrays.toString(nizBrojeva) +  fileReplaceName;
    }

}
