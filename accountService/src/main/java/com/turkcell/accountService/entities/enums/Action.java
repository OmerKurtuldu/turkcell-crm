package com.turkcell.accountService.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Action {
    DELETE(1), EDIT(2);
    private final int value;
}
