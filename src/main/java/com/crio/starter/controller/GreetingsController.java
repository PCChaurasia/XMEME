package com.crio.starter.controller;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.GetMemeResponse;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.service.GreetingsService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  @Autowired
  private  GreetingsService mservice;


  @GetMapping("/memes/")
  public List<GreetingsEntity> GetAllMemes() {

    GetMemeResponse memeListobj = mservice.getMemes();

    return memeListobj.getMemeList();
  }



  @GetMapping("/memes/{id}")
  public ResponseEntity<GreetingsEntity> GetMemebyId(@PathVariable String id){

    Optional<GreetingsEntity> memeInfo = mservice.getMeme(id);

    if(memeInfo.isEmpty())
    {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    else
    {
      return new ResponseEntity<>(memeInfo.get(), HttpStatus.CREATED);
    }
  }

  
  @PostMapping("/memes/")
  public ResponseEntity<PostMemeResponse> StoreMeme(@Valid @RequestBody GreetingsEntity meme){

    boolean duplicate = mservice.addMeme(meme);

    PostMemeResponse response =  new PostMemeResponse(meme.getId());

    if(duplicate) 
    {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } 
    else 
    {
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
  }

  

}
