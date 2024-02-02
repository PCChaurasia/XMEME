package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "greetings")
@NoArgsConstructor
public class GreetingsEntity {

  @Id
  String id;

  @NotNull
  String name;
  
  @NotNull
  String url;
  
  @NotNull
  String caption;

}
