package com.susanghan_guys.server.work.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {
    @JsonProperty("Visual")
    VISUAL,

    @JsonProperty("Film")
    FILM,

    @JsonProperty("Digital Contents")
    DIGICON,

    @JsonProperty("Experience")
    EXP,

    @JsonProperty("Outdoor Activation")
    OUTACT
}
