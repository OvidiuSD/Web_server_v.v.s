package web.server.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Version {
    HTTP_1_1("HTTP/1.1", 1, 1);

    public final String LITERAL;
    public final int MAJOR;
    public final int MINOR;

    Version(String LITERAL, int MAJOR, int MINOR){
        this.LITERAL = LITERAL;
        this.MAJOR = MAJOR;
        this.MINOR = MINOR;
    }

    private static final Pattern httpVersionRegexPattern = Pattern.compile("^HTTP/(?<major>\\d+).(?<minor>\\d)");

    public static Version getBestCompatibleVersion(String literalVersion) throws  BadParsException {
        Matcher matcher = httpVersionRegexPattern.matcher(literalVersion);
        if(!matcher.find() || matcher.groupCount() !=2){
            throw new BadParsException();
        }
        int major = Integer.parseInt(matcher.group("major"));
        int minor = Integer.parseInt(matcher.group("minor"));

        Version tempBestCompatibleVersion = null;
        for(Version version : Version.values()){
            if(version.LITERAL.equals(literalVersion)){
                return version;
            } else{
                if(version.MAJOR == major){
                    if(version.MINOR < minor){
                        tempBestCompatibleVersion = version;
                    }
                }
            }
        }
        return tempBestCompatibleVersion;
    }
}
