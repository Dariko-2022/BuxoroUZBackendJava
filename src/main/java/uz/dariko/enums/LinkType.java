package uz.dariko.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LinkType {

    GOOD_TO_KNOW("Bilish Foydali",1),
    TAGS("Tegler",2),
    USEFUL_LINKS("Foydali manzillar",3),
    SOCIAL_MEDIA("Ijtimoiy tarmoqlar",4),
    SOCIAL_MEDIA_HOKIM("Hokimning ijtimoiy tarmoqlari",5);



    private final String name;
    private final Integer code;
}
