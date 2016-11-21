package com.itube.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itube.domain.ReplyVO;
import com.itube.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

  @Inject
  private ReplyService service;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> register(@RequestBody ReplyVO vo) {

    ResponseEntity<String> entity = null;
    try {
      service.addReply(vo);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/all/{pid}", method = RequestMethod.GET)
  public ResponseEntity<List<ReplyVO>> list(@PathVariable("pid") Integer pid) {

    ResponseEntity<List<ReplyVO>> entity = null;
    try {
      entity = new ResponseEntity<>(service.listReply(pid), HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return entity;
  }

  @RequestMapping(value = "/{rid}", method = { RequestMethod.PUT, RequestMethod.PATCH })
  public ResponseEntity<String> update(@PathVariable("rid") Integer rid, @RequestBody ReplyVO vo) {

    ResponseEntity<String> entity = null;
    try {
      vo.setRid(rid);
      service.modifyReply(vo);

      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{rid}", method = RequestMethod.DELETE)
  public ResponseEntity<String> remove(@PathVariable("rid") Integer rid) {

    ResponseEntity<String> entity = null;
    try {
      service.removeReply(rid);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }  

}
