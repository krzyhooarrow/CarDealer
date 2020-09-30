package ScrapperService.repository_layer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

    private static final Pattern integerValue = Pattern.compile("^([0-9]+)");
    private static final Pattern floatValue = Pattern.compile("^([0-9]+)\\.([0-9]+)");


    public static Integer matchInteger(String text){
    final Matcher matcher = integerValue.matcher(text.replace(" ",""));
    return matcher.find()? Integer.parseInt(matcher.group(0)):0;
    }

    public static Float matchFloat(String text){
        final Matcher matcher = floatValue.matcher(text.replace(" ",""));
        return matcher.find()? Float.parseFloat(matcher.group(0)):0;
    }

    public static String matchByPattern(String text,String pattern){
        final Matcher matcher = Pattern.compile(pattern).matcher(text);
        return matcher.find()? matcher.group(0):null;
    }
}
