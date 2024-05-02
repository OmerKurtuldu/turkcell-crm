package com.turkcell.commonpackage.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderType {
    MALE(1), FEMALE(2), OTHER(3);
    private final int value;
}

