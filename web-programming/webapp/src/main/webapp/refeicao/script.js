// -----------------------------
// CRUD Refeições (frontend)
// Controller: /<contexto>/refeicao/api
// -----------------------------

// Descobre o context path automaticamente.
// Ex.: /webapp/index.html  ->  /webapp
const _pathParts = window.location.pathname.split('/').filter(Boolean);
// Se estiver em contexto root (ex.: /index.html), não deve assumir que o primeiro "segmento" é o contexto.
const _first = _pathParts[0] || '';
const _contextPath = (
  _pathParts.length > 1 || (_first && !_first.includes('.'))
) ? `/${_first}` : '';

// Base absoluta para evitar problemas com subpastas.
const API_BASE = `${window.location.origin}${_contextPath}/refeicao/api`;

function escapeHtml(str) {
  return String(str)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#39;');
}

async function readResponseBody(response) {
  const contentType = (response.headers.get('content-type') || '').toLowerCase();
  const text = await response.text();

  if (!text) return null;

  // Preferir JSON quando o header indicar.
  if (contentType.includes('application/json')) {
    try {
      return JSON.parse(text);
    } catch {
      return text;
    }
  }

  // Muitos exemplos didáticos esquecem o content-type; tenta parsear mesmo assim.
  try {
    return JSON.parse(text);
  } catch {
    return text;
  }
}

function showResponse(elementId, body, statusCode) {
  const element = document.getElementById(elementId);
  const ok = statusCode >= 200 && statusCode < 300;
  const statusClass = ok ? 'success' : 'error';
  const statusBadge = ok ? 'status-success' : 'status-error';

  const printable = (typeof body === 'string')
    ? body
    : JSON.stringify(body, null, 2);

  element.className = `response-display ${statusClass}`;
  element.innerHTML = `
        <span class="status-code ${statusBadge}">${statusCode}</span>
        <span>${new Date().toLocaleTimeString()}</span>
        <hr style="margin: 10px 0; border: none; border-top: 1px solid #ccc;">
        <pre>${escapeHtml(printable)}</pre>
    `;
  element.style.display = 'block';

  updateLog(`[${statusCode}] ${elementId}`, body);
}

function updateLog(action, _response) {
  const logDisplay = document.getElementById('log-display');
  const timestamp = new Date().toLocaleTimeString();
  const logEntry = `<strong>[${timestamp}]</strong> ${escapeHtml(action)}<br>`;
  logDisplay.innerHTML = logEntry + logDisplay.innerHTML;
}

function getValue(id) {
  const el = document.getElementById(id);
  return (el?.value ?? '').trim();
}

function requireValue(id, label) {
  const v = getValue(id);
  if (!v) {
    alert(`Preencha o campo: ${label}`);
    throw new Error(`Campo obrigatório vazio: ${id}`);
  }
  return v;
}

function clearInput(...ids) {
  let last = null;
  ids.forEach(id => {
    const element = document.getElementById(id);
    if (element) {
      element.value = '';
      last = element;
    }
  });
  last?.focus();
}

// -----------------------------
// GET
// -----------------------------

async function fetchAll() {
  updateLog(`GET ${API_BASE} (all)`, null);
  try {
    const response = await fetch(API_BASE);
    const body = await readResponseBody(response);
    showResponse('getall-response', body, response.status);
  } catch (error) {
    showResponse('getall-response', { error: error.message }, 500);
  }
}

async function fetchByIdCartaoDataRefeicao() {
  try {
    const idCartaoEstudante = requireValue('getByBoth-idCartaoEstudante', 'idCartaoEstudante');
    const dataRefeicao = requireValue('getByBoth-dataRefeicao', 'dataRefeicao (YYYY-MM-DD)');

    const url = new URL(API_BASE);
    url.searchParams.set('idCartaoEstudante', idCartaoEstudante);
    url.searchParams.set('dataRefeicao', dataRefeicao);

    updateLog(`GET ${url.pathname}${url.search}`, null);
    const response = await fetch(url.toString());
    const body = await readResponseBody(response);
    showResponse('getByBoth-response', body, response.status);

  } catch (error) {
    // requireValue já alertou; mas se foi outra falha, mostra erro.
    if (error?.message?.startsWith('Campo obrigatório')) return;
    showResponse('getByBoth-response', { error: error.message }, 500);
  }
}

async function fetchByIdCartao() {
  try {
    const idCartaoEstudante = requireValue('getByCartao-idCartaoEstudante', 'idCartaoEstudante');

    const url = new URL(API_BASE);
    url.searchParams.set('idCartaoEstudante', idCartaoEstudante);

    updateLog(`GET ${url.pathname}${url.search}`, null);
    const response = await fetch(url.toString());
    const body = await readResponseBody(response);
    showResponse('getByCartao-response', body, response.status);

  } catch (error) {
    if (error?.message?.startsWith('Campo obrigatório')) return;
    showResponse('getByCartao-response', { error: error.message }, 500);
  }
}

async function fetchByDataRefeicao() {
  try {
    const dataRefeicao = requireValue('getByData-dataRefeicao', 'dataRefeicao (YYYY-MM-DD)');

    const url = new URL(API_BASE);
    url.searchParams.set('dataRefeicao', dataRefeicao);

    updateLog(`GET ${url.pathname}${url.search}`, null);
    const response = await fetch(url.toString());
    const body = await readResponseBody(response);
    showResponse('getByData-response', body, response.status);

  } catch (error) {
    if (error?.message?.startsWith('Campo obrigatório')) return;
    showResponse('getByData-response', { error: error.message }, 500);
  }
}

// -----------------------------
// POST
// -----------------------------

async function createRecord() {
  try {
    const idCartaoEstudante = requireValue('post-idCartaoEstudante', 'idCartaoEstudante');
    const dataRefeicao = requireValue('post-dataRefeicao', 'dataRefeicao (YYYY-MM-DD)');

    updateLog(`POST ${API_BASE} (create)`, null);

    const params = new URLSearchParams({
      idCartaoEstudante,
      dataRefeicao,
    });

    const response = await fetch(API_BASE, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      },
      body: params.toString(),
    });

    const body = await readResponseBody(response);
    showResponse('post-response', body, response.status);

    if (response.ok) {
      clearInput('post-idCartaoEstudante', 'post-dataRefeicao');
    }

  } catch (error) {
    if (error?.message?.startsWith('Campo obrigatório')) return;
    showResponse('post-response', { error: error.message }, 500);
  }
}

// -----------------------------
// PUT
// -----------------------------

async function updateRecord() {
  try {
    const oldIdCartaoEstudante = requireValue('put-oldIdCartaoEstudante', 'oldIdCartaoEstudante');
    const oldDataRefeicao = requireValue('put-oldDataRefeicao', 'oldDataRefeicao (YYYY-MM-DD)');
    const newIdCartaoEstudante = requireValue('put-newIdCartaoEstudante', 'newIdCartaoEstudante');
    const newDataRefeicao = requireValue('put-newDataRefeicao', 'newDataRefeicao (YYYY-MM-DD)');

    // Importante: em Tomcat é comum PUT com body form-urlencoded não preencher getParameter().
    // Por isso enviamos os parâmetros na query string.
    const url = new URL(API_BASE);
    url.searchParams.set('oldIdCartaoEstudante', oldIdCartaoEstudante);
    url.searchParams.set('oldDataRefeicao', oldDataRefeicao);
    url.searchParams.set('newIdCartaoEstudante', newIdCartaoEstudante);
    url.searchParams.set('newDataRefeicao', newDataRefeicao);

    updateLog(`PUT ${url.pathname}${url.search}`, null);

    const response = await fetch(url.toString(), {
      method: 'PUT'
    });

    const body = await readResponseBody(response);
    showResponse('put-response', body, response.status);

    if (response.ok) {
      clearInput('put-oldIdCartaoEstudante', 'put-oldDataRefeicao', 'put-newIdCartaoEstudante', 'put-newDataRefeicao');
    }

  } catch (error) {
    if (error?.message?.startsWith('Campo obrigatório')) return;
    showResponse('put-response', { error: error.message }, 500);
  }
}

// -----------------------------
// DELETE
// -----------------------------

async function deleteRecord() {
  try {
    const idCartaoEstudante = requireValue('delete-idCartaoEstudante', 'idCartaoEstudante');
    const dataRefeicao = requireValue('delete-dataRefeicao', 'dataRefeicao (YYYY-MM-DD)');

    // Mesmo motivo do PUT: usar query string.
    const url = new URL(API_BASE);
    url.searchParams.set('idCartaoEstudante', idCartaoEstudante);
    url.searchParams.set('dataRefeicao', dataRefeicao);

    updateLog(`DELETE ${url.pathname}${url.search}`, null);

    const response = await fetch(url.toString(), {
      method: 'DELETE'
    });

    const body = await readResponseBody(response);
    showResponse('delete-response', body, response.status);

    if (response.ok) {
      clearInput('delete-idCartaoEstudante', 'delete-dataRefeicao');
    }

  } catch (error) {
    if (error?.message?.startsWith('Campo obrigatório')) return;
    showResponse('delete-response', { error: error.message }, 500);
  }
}

// -----------------------------
// UX: Enter para submeter
// -----------------------------

document.addEventListener('keypress', (e) => {
  if (e.key !== 'Enter') return;

  const id = e.target?.id;
  if (!id) return;

  // GET
  if (id === 'getByBoth-dataRefeicao' || id === 'getByBoth-idCartaoEstudante') {
    fetchByIdCartaoDataRefeicao();
    return;
  }
  if (id === 'getByCartao-idCartaoEstudante') {
    fetchByIdCartao();
    return;
  }
  if (id === 'getByData-dataRefeicao') {
    fetchByDataRefeicao();
    return;
  }

  // POST
  if (id === 'post-dataRefeicao' || id === 'post-idCartaoEstudante') {
    createRecord();
    return;
  }

  // PUT
  if (id.startsWith('put-')) {
    updateRecord();
    return;
  }

  // DELETE
  if (id.startsWith('delete-')) {
    deleteRecord();
    return;
  }
});