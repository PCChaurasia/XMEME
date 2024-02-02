package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.GetMemeResponse;
import com.crio.starter.repository.GreetingsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;



@Service
public class GreetingsService {

  @Autowired
  private  GreetingsRepository Repository;

  @Autowired
  private MongoTemplate mongoTemplate;

 public boolean addMeme(GreetingsEntity memeDto){

  Query query = new Query();

  query.addCriteria(Criteria.where("name").is(memeDto.getName()));
  query.addCriteria(Criteria.where("url").is(memeDto.getUrl()));
  query.addCriteria(Criteria.where("caption").is(memeDto.getCaption()));

  boolean alreadyExists = mongoTemplate.exists(query, "greetings");


  if(alreadyExists)
  {
    return true;
  }
  else
  {
    Repository.save(memeDto);
    return false;
  }
  
}

  public GetMemeResponse getMemes() {
    List<GreetingsEntity> memeFetch = Repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    
    int size = memeFetch.size();
    if(size == 0)
    {
      return new GetMemeResponse(memeFetch);
    }
    else if(size<100)
    {
      return new GetMemeResponse(memeFetch);
    }
    else
    {
      return new GetMemeResponse(memeFetch.stream().limit(100).collect(Collectors.toList()));
    }

    
  }

  public Optional<GreetingsEntity> getMeme(String memeid){

    return Repository.findById(memeid);

  }


 
}
