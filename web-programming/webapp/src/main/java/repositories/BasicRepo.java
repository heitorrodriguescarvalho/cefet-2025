package repositories;

import java.util.List;
import java.util.Optional;

import models.BasicModel;

public interface BasicRepo {
	BasicModel create(BasicModel model);

	Optional<BasicModel> findByHostname(String hostname);

	List<BasicModel> findAll();

	BasicModel update(BasicModel model);

	boolean deleteByHostname(String hostname);
}
