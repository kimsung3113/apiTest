package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.entity.Listener;
import com.example.demo.data.repository.ListenerRepository;
import com.example.demo.service.ListenerService;

@Service
public class ListenerServiceImpl implements ListenerService {

  private ListenerRepository listenerRepository;

  @Autowired
  public ListenerServiceImpl(ListenerRepository listenerRepository) {
    this.listenerRepository = listenerRepository;
  }

  @Override
  public Listener getEntity(Long id) {
    return listenerRepository.findById(id).get();
  }

  @Override
  public void saveEntity(Listener listener) {
    listenerRepository.save(listener);
  }

  @Override
  public void updateEntity(Listener listener) {
    Listener foundListener = listenerRepository.findById(listener.getId()).get();
    foundListener.setName(listener.getName());

    listenerRepository.save(foundListener);
  }

  @Override
  public void removeEntity(Listener listener) {
    listenerRepository.delete(listener);
  }
}
