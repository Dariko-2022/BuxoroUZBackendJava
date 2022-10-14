package uz.dariko.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LinkType {

    GOOD_TO_KNOW("Bilish Foydali",1),
    TAGS("Tegler",2),
    USEFUL_LINKS("Foydali manzillar",3);

    private final String name;
    private final Integer code;
}
