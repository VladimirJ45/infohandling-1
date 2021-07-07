package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Component;

public interface Parser {
    Component parse(String text);
}
