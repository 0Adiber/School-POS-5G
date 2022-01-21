const _url = '/customer';
const elements = {};
let info;

window.onload = () => {
    info = document.getElementById('info');
    [ 'customerId', 'firstname', 'lastname', 'gender', 'active', 'email', 'since', ].forEach(n => elements[n] = document.getElementById(n));
};

function log (msg) {
    info.innerHTML += `[${new Date().toISOString()}]> <span style="color:rgba(0,0,0,.7);">${msg}</span><br/>`;
}

function v (e) {
    return e.type === 'checkbox' ? e.checked : e.value;
}

function h (m, b) {
    return {
        method: m,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(b),
    };
}

function getCustomer (id) {
    id = id || +elements.customerId.value;
    log(`getCustomer(${id})`);
    fetch(`${_url}/${id}`)
        .then(res => {
            log(`Status: ${res.status}`);
            return res.json();
        })
        .then(res => {
            Object.keys(res).forEach(k => typeof res[k] === 'boolean' ? (elements[k].checked = res[k]) : (elements[k].value = res[k]) );
        })
        .catch(err => console.error(err));
}

function postCustomer (customer) {
    if (!customer) {
        customer = {};
        Object.keys(elements).forEach(k => customer[k] = v(elements[k]));
    }
    log(`createCustomer(${customer})`);
    fetch(`${_url}`, h('post', customer))
        .then(res => {
            log(`Status: ${res.status}`);
            log(`URL: ${res.headers.get('Location')}`);
        })
        .catch(err => console.error(err));
}

function putCustomer (customer) {
    if (!customer) {
        customer = {};
        Object.keys(elements).forEach(k => v(elements[k]) ? customer[k] = v(elements[k]) : null);
    }
    fetch(`${_url}`, h('put', customer))
        .then(res => log(`Status: ${res.status}`))
        .catch(err => console.log(err));
}

function patchCustomer(customer) {
    if (!customer) {
        customer = {};
        Object.keys(elements).forEach(k => v(elements[k]) ? customer[k] = v(elements[k]) : null);
    }
    fetch(`${_url}/${customer.customerId}`, h('PATCH', customer))
        .then(res => log(`Status: ${res.status}`))
        .catch(err => console.error(err));
}

function deleteCustomer (id) {
    id = id || +elements.customerId.value;
    fetch(`${_url}/${id}`, h('delete'))
        .then(res => log(`Status: ${res.status}`))
        .catch(err => console.log(err));
}