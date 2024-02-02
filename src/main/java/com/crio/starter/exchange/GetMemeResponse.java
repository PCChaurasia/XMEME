package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import com.crio.starter.data.GreetingsEntity;

@Data
@AllArgsConstructor
public class GetMemeResponse {

    List<GreetingsEntity> memeList;
    
}
