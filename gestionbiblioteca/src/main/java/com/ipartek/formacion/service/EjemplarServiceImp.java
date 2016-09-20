package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dao.interfaces.EjemplarDAO;
import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.service.interfaces.EjemplarService;

@Service
public class EjemplarServiceImp implements EjemplarService {
	@Autowired
	EjemplarDAO eDao;

	@Override
	public List<Ejemplar> getAll() {
		List<Ejemplar> ejemplares = null;
		ejemplares = eDao.getAll();
		return ejemplares;
	}

	@Override
	public Ejemplar getById(int id) {

		return eDao.getById(id);
	}

	@Override
	public Ejemplar update(Ejemplar ejemplar) {

		return eDao.update(ejemplar);
	}

	@Override
	public void delete(int id) {
		eDao.delete(id);
	}

	@Override
	public Ejemplar create(Ejemplar ejemplar) {

		return eDao.create(ejemplar);
	}

}
