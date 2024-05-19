package com.turkcell.accountService.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum Action {
    DEFAULT(1),DELETE(2), EDIT(3);
    private final int value;
}
