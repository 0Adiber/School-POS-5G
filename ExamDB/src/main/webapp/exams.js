window.onload = () => {
    loadClasses();
    loadSubjects();
}

var studentPage = "";
var subjects = [];

var examProps = ['examId', 'dateOfExam', 'duration', 'subject']

function loadSubjects() {
    fetch(`subject/`)
        .then(res => res.json())
        .then(res => {
            subjects = [{}, ...res];
        })
}

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

            for(const s of res['content']) {
                const row = document.createElement('tr');

                row.addEventListener('click', () => {
                    loadExams(s['studentId']);
                });

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

function loadExams(studentId) {
    fetch(`student/${studentId}`)
        .then(res => res.json())
        .then(res => {

            const table = document.getElementById('exam-list-rows');
            table.innerHTML = "";

            for(const s of res) {
                const row = document.createElement('tr');

                examProps.forEach(i => {
                    const data = document.createElement('td');
                    data.value = s[i];
                    if(i != 'subject')
                        data.innerHTML = s[i];
                    else
                        data.innerHTML = s[i]['shortname'];
                    row.appendChild(data);
                })
                const delBt = createEl("button", "delete");
                delBt.onclick = (e) => deleteExam(s['examId'], studentId);
                row.appendChild(delBt)
                table.appendChild(row);
            }

            const newExam = createEl("tr", "", {"class": "add-exam"});
            newExam.appendChild(tdWithInput({"type": "number", "name": "examId"}))
            newExam.appendChild(tdWithInput({"type": "date", "name": "dateOfExam"}))
            newExam.appendChild(tdWithInput({"type": "number", "name": "duration"}))
            const subjectSelect = createEl("select", "",{"id": "subject"});
            newExam.appendChild(subjectSelect);

            for(const sub of subjects) {
                subjectSelect.appendChild(createEl("option", sub['shortname'], {value: sub['subjectId']}))
            }

            const btn = createEl("button", "Add / Update", {"data-id": studentId});
            btn.onclick = (e) => {
                e.preventDefault();
                e.stopPropagation();
                const inputs = e.target.parentElement.querySelectorAll(`input`);
                const newExam = {};
                for (const i of inputs) {
                    if(i.value != '')
                        newExam[i.name] = i.value;
                }
                addExam(newExam, studentId, document.getElementById('subject').value);
            };
            newExam.appendChild(btn);
            table.appendChild(newExam);

        })
}

function deleteExam(examId, studentId) {
    fetch(`exam/${examId}`, {method: 'DELETE'})
        .then(() => {
           loadExams(studentId);
        });
}

function addExam(exam, studentId, subjectId) {
    if(exam['duration'] && exam['duration'] < 0) {
        window.alert("Duration must be >0")
        return;
    }

    if(subjectId)
        exam['subject'] = subjects.find((i) => i.subjectId == subjectId);

    if(exam['examId']) {
        fetch(`exam/${exam['examId']}`, {method: 'PATCH', body: JSON.stringify(exam), headers: {'Content-Type': 'application/json'}})
            .then(() => loadExams(studentId));
    } else {
        if(!exam['dateOfExam'] || !exam['duration'] || !exam['subject']) {
            window.alert("Please insert all needed values");
            return;
        }
        fetch(`exam/?studentId=${studentId}`, {
            method: 'POST',
            body: JSON.stringify(exam),
            headers: { 'Content-Type': 'application/json' }
        })
            .then(() => loadExams(studentId));
    }
}

function tdWithInput(attrs = {}) {
    const td = createEl("td");
    td.appendChild(createEl("input", "", attrs));
    return td;
}

function createEl(tag, text = "", attrs = {}) {
    const el = document.createElement(tag);
    for (const key in attrs) {
        el.setAttribute(key, attrs[key]);
    }
    el.innerText = text;
    return el;
}