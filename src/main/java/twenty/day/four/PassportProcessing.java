package twenty.day.four;

import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;

class PassportProcessing {

    private static final int MINIMAL_REQUIRED_FIELDS = 7;
    private static final int MAX_FIELDS = 8;
    private static final String CID = "cid";
    private static final List<String> EYE_COLORS = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    public static final int PID_LENGTH = 9;

    long countValidPassports(List<Map<String, String>> passports) {
        return passports.stream()
                .filter(this::validatePassport)
                .count();
    }

    boolean validatePassport(Map<String, String> passportProperties) {
        if (MapUtils.isEmpty(passportProperties) || passportProperties.size() < MINIMAL_REQUIRED_FIELDS) {
            return false;
        } else if (passportProperties.size() == MAX_FIELDS) {
            return true;
        } else if (passportProperties.size() == MINIMAL_REQUIRED_FIELDS) {
            return passportProperties.keySet().stream().noneMatch(key -> key.equals(CID));
        }
        return false;
    }

    long countValidPassportsExtended(List<Map<String, String>> passports) {
        return passports.stream()
                .filter(this::validatePassportExtended)
                .count();
    }

    boolean validatePassportExtended(Map<String, String> passportProperties) {
        if (MapUtils.isEmpty(passportProperties) || passportProperties.size() < MINIMAL_REQUIRED_FIELDS) {
            return false;
        } else if (passportProperties.size() == MINIMAL_REQUIRED_FIELDS
                && passportProperties.keySet().stream().anyMatch(key -> key.equals(CID))) {
            return false;
        } else {
            return passportProperties.entrySet()
                    .stream()
                    .allMatch(this::validateProperty);
        }
    }

    boolean validateProperty(Map.Entry<String, String> stringStringEntry) {
        String value = stringStringEntry.getValue();
        switch (stringStringEntry.getKey()) {
            case "byr":
                return (Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002);
            case "iyr":
                return (Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020);
            case "eyr":
                return (Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030);
            case "hgt":
                String unit = value.substring(value.length() - 2);
                if ("in".equals(unit)) {
                    final int height = Integer.parseInt(value.substring(0, value.length() - 2));
                    return (height >= 59 && height <= 76);
                } else if ("cm".equals(unit)) {
                    final int height = Integer.parseInt(value.substring(0, value.length() - 2));
                    return (height >= 150 && height <= 193);
                } else {
                    return false;
                }
            case "hcl":
                return ("#".equals(String.valueOf(value.charAt(0)))
                        && value.substring(1).matches("^[a-f0-9]+$")
                        && value.substring(1).length() == 6);
            case "ecl":
                return EYE_COLORS.stream().anyMatch(value::contains);
            case "pid":
                return PID_LENGTH == value.length() && value.matches("^[0-9]+$");
            case "cid":
                return true;
            default:
                return false;
        }
    }
}
