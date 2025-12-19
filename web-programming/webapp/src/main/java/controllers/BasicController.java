package controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BasicModel;
import repositories.BasicRepo;
import repositories.BasicRepoMemory;

public class BasicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BasicRepo repo = new BasicRepoMemory();

	// GET /basic?hostname=HOST -> single; GET /basic -> list all
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String hostname = request.getParameter("hostname");
		if (hostname == null || hostname.isEmpty()) {
			List<BasicModel> all = repo.findAll();
			StringBuilder json = new StringBuilder("{\"status\":\"success\",\"data\":[");
			for (int i = 0; i < all.size(); i++) {
				BasicModel model = all.get(i);
				json.append("{\"hostname\":\"").append(model.getHostname()).append("\",\"username\":\"")
						.append(model.getUsername()).append("\"}");
				if (i < all.size() - 1) {
					json.append(",");
				}
			}
			json.append("]}");
			response.getWriter().print(json.toString());
			return;
		}

		repo.findByHostname(hostname).ifPresentOrElse(model -> {
			try {
				String json = "{\"status\":\"success\",\"data\":{\"hostname\":\"" + model.getHostname()
						+ "\",\"username\":\"" + model.getUsername() + "\"}}";
				response.getWriter().print(json);
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}, () -> {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			try {
				response.getWriter().print("{\"status\":\"error\",\"message\":\"not found\"}");
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		});
	}

	// POST /basic with hostname & username -> create
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String hostname = request.getParameter("hostname");
		String username = request.getParameter("username");
		if (hostname == null || username == null || hostname.isEmpty() || username.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.print("{\"status\":\"error\",\"message\":\"hostname and username are required\"}");
			return;
		}

		BasicModel created = repo.create(new BasicModel(hostname, username));
		response.setStatus(HttpServletResponse.SC_CREATED);
		String json = "{\"status\":\"created\",\"data\":{\"hostname\":\"" + created.getHostname()
				+ "\",\"username\":\"" + created.getUsername() + "\"}}";
		response.getWriter().print(json);
	}

	// PUT /basic with hostname & username -> update existing
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		Map<String, String[]> parametersMap = ensureParameters(request);
		String hostname = first(parametersMap, "hostname");
		String username = first(parametersMap, "username");
		if (hostname == null || username == null || hostname.isEmpty() || username.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.print("{\"status\":\"error\",\"message\":\"hostname and username are required\"}");
			return;
		}

		try {
			BasicModel updated = repo.update(new BasicModel(hostname, username));
			String json = "{\"status\":\"updated\",\"data\":{\"hostname\":\"" + updated.getHostname()
					+ "\",\"username\":\"" + updated.getUsername() + "\"}}";
			response.getWriter().print(json);
		} catch (IllegalArgumentException ex) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print("{\"status\":\"error\",\"message\":\"" + ex.getMessage() + "\"}");
		}
	}

	// DELETE /basic?hostname=HOST -> delete by hostname
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String hostname = request.getParameter("hostname");
		if (hostname == null || hostname.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("{\"status\":\"error\",\"message\":\"hostname is required\"}");
			return;
		}

		boolean deleted = repo.deleteByHostname(hostname);
		if (!deleted) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter()
					.print("{\"status\":\"error\",\"message\":\"not found: " + hostname + "\"}");
			return;
		}

		response.getWriter().print("{\"status\":\"deleted\",\"message\":\"" + hostname + "\"}");
	}

	private Map<String, String[]> ensureParameters(HttpServletRequest request) throws IOException {
		Map<String, String[]> params = request.getParameterMap();
		if (!params.isEmpty()) {
			return params;
		}

		String contentType = request.getContentType();
		if (contentType == null || !contentType.toLowerCase().startsWith("application/x-www-form-urlencoded")) {
			return params;
		}

		String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		Map<String, List<String>> accumulator = new HashMap<>();
		for (String pair : body.split("&")) {
			if (pair.isEmpty()) {
				continue;
			}
			int idx = pair.indexOf('=');
			String rawKey = idx >= 0 ? pair.substring(0, idx) : pair;
			String rawValue = idx >= 0 ? pair.substring(idx + 1) : "";
			String key = URLDecoder.decode(rawKey, StandardCharsets.UTF_8);
			String value = URLDecoder.decode(rawValue, StandardCharsets.UTF_8);
			accumulator.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
		}

		Map<String, String[]> parsed = new HashMap<>();
		accumulator.forEach((k, v) -> parsed.put(k, v.toArray(new String[0])));
		return parsed;
	}

	private String first(Map<String, String[]> params, String key) {
		String[] values = params.get(key);
		return (values == null || values.length == 0) ? null : values[0];
	}
}