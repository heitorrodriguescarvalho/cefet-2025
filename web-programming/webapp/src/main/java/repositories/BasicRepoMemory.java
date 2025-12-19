package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.BasicModel;

public class BasicRepoMemory implements BasicRepo {
	private final List<BasicModel> store = new ArrayList<>();

	public BasicRepoMemory() {
		// Initial dummy data
		store.add(new BasicModel("host1", "user1"));		
	}

	@Override
	public BasicModel create(BasicModel model) {
		deleteByHostname(model.getHostname());
		store.add(model);
		return model;
	}

	@Override
	public Optional<BasicModel> findByHostname(String hostname) {
		for (BasicModel model : store) {
			if (model.getHostname().equals(hostname)) {
				return Optional.of(model);
			}
		}
		return Optional.empty();
	}

	@Override
	public List<BasicModel> findAll() {
		return new ArrayList<>(store);
	}

	@Override
	public BasicModel update(BasicModel model) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getHostname().equals(model.getHostname())) {
				store.set(i, model);
				return model;
			}
		}
		throw new IllegalArgumentException("Model not found for hostname: " + model.getHostname());
	}

	@Override
	public boolean deleteByHostname(String hostname) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getHostname().equals(hostname)) {
				store.remove(i);
				return true;
			}
		}
		return false;
	}
}
