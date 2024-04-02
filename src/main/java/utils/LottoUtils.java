package utils;

import domains.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoUtils {
    public static List<LottoNumber> parsePickedNumbers(List<Integer> pickedNumbers) {
        return pickedNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
