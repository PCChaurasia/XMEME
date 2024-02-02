package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMemeRequest {

    private String id;

    private String name;

    private String url;

    private String caption;
    
}
