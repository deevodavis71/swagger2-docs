package com.sjd.swaggertest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sjd.swaggertest.views.Views;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResourceDTO {

    @JsonView({Views.Read.class, Views.Write.class})
    private String global;

    @JsonView({Views.Read.class})
    private Integer onRead;

    @JsonView({Views.Write.class})
    private Integer onWrite;

    @JsonView({Views.Read.class})
    private String noAnnotation;

    @JsonView({Views.Read_Minimal.class})
    private String minimal;
}
