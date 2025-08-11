package com.susanghan_guys.server.work.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Brand {
    @JsonProperty("빼빼로")
    BBABBARO,

    @JsonProperty("탐스")
    TAMS,

    @JsonProperty("크러시")
    KRUSH,

    @JsonProperty("에어리즘")
    AIRISM,

    @JsonProperty("롯데월드")
    LOTTE_WORLD,

    @JsonProperty("롯데자이언츠")
    LOTTE_GIANTS,

    @JsonProperty("롯데리아")
    LOTTERIA,

    @JsonProperty("넥센타이어")
    NEXEN_TIRE,

    @JsonProperty("SBI 저축은행 사이다뱅크")
    SBI_BANK
}
