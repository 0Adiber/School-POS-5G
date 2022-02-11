window.onload = () => {
    loadClasses();
}

var studentPage = "";

function loadClasses() {
    fetch(`classname/`)
        .then(res => res.json())
        .then(res => {
            const select = document.getElementById("classnames");
            for(const c of res) {
                const opt = document.createElement('option');
                opt.value = c['classId'];
                opt.innerHTML = c['classname']
                select.appendChild(opt);
            }
        })
}

function loadStudents(page = 0) {
    const classId = document.getElementById('classnames').value;
    if(studentPage) {
        page = studentPage['pageable']['pageNumber'] + page;
        if (page > studentPage['totalPages']-1 || page < 0)
            return;
    }
    fetch(`classname/${classId}?page=${page}`)
        .then(res => res.json())
        .then(res => {
            studentPage = res;
            document.getElementById('student-list-current').innerHTML = res['pageable']['pageNumber']+1;
            document.getElementById('student-list-total').innerHTML = res['totalPages'];

            const table = document.getElementById('student-list-rows');
            table.innerHTML = "";

            console.log(res['content'])

            for(const s of res['content']) {
                const row = document.createElement('tr');
                Object.keys(s).forEach(i => {
                    const data = document.createElement('td');
                    data.value = s[i];
                    data.innerHTML = s[i];
                    row.appendChild(data);
                })
                table.appendChild(row);
            }

        })
}