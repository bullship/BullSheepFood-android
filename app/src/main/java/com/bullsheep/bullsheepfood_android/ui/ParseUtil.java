package com.bullsheep.bullsheepfood_android.ui;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtil {
    private static final String TAG = "ParseUtil";

    private static final List<String> PROTEIN_PATTERNS = Arrays.asList("protein");
    private static final List<String> CARBOHYDRATE_PATTERNS = Arrays.asList("total carbohydrate");
    private static final List<String> FAT_PATTERNS = Arrays.asList("total fats");

    public static String parseProtein(String text) {
        String regex = ".*protein[s]?.*[0-9]+";
        return parse(text, regex);
    }

    public static String parseCarb(String text) {
        String regex = ".*(?:carb|carbohydrate)[s]?.*[0-9]+";
        return parse(text, regex);
    }

    public static String parseFat(String text) {
        String regex = ".*fat[s]?.*[0-9]+";
        return parse(text, regex);
    }

    public static String parseCalories(String text) {
        String regex = ".*calories?.*[0-9]+";
        return parse(text, regex);
    }

    private static String parse(String text, String regex) {
        text = text.toLowerCase();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            Log.i(TAG,"Start index: " + matcher.start());
            Log.i(TAG," End index: " + matcher.end());
            Log.i(TAG," Found: " + matcher.group());
            String count = matcher.group().replaceAll("\\D+", "");
            return count;
        }
        return "";
    }

}
