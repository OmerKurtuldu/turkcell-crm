package com.turkcell.CustomerService.core.business.abstracts;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

public interface MessageService {
    String getMessage(String key);
    String getMessageWithArgs(String key, Object... args);
}


