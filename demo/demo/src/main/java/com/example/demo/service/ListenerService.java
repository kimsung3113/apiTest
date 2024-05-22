package com.example.demo.service;

import com.example.demo.data.entity.Listener;

public interface ListenerService {

	  Listener getEntity(Long id);

	  void saveEntity(Listener listener);

	  void updateEntity(Listener listener);

	  void removeEntity(Listener listener);
	}
