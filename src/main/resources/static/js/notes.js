function getNotesPromise(subject, value) {
    let parameters = [];
    if (subject) {
        parameters.push(`subject=${subject}`);
    }

    if (value) {
        parameters.push(`value=${value}`);
    }

    let url = "/api/notes";
    if (parameters.length > 0) {
        url += "?" + parameters.join("&");
    }
    return fetch(url);
}


function showNotesPieChart(subject) {
    getNotesPromise(subject, null)
        .then(response => response.json())
        .then(notes => renderPieChart(notes)
    );
}

function getNotesForGrade(notes, grade) {
    return notes.filter(note => note.value === grade).length;
}

function renderPieChart(notes) {
    const configPie = {
        type: 'pie',
        data: {
            datasets: [{
                data: [
                    getNotesForGrade(notes, 2),
                    getNotesForGrade(notes, 3),
                    getNotesForGrade(notes, 4),
                    getNotesForGrade(notes, 5),
                    getNotesForGrade(notes,6),
                ],
                backgroundColor: [
                    'red',
                    'orange',
                    'yellow',
                    'green',
                    'blue',
                ],
                label: 'Notes'
            }],
            labels: [
                "2",
                "3",
                "4",
                "5",
                "6"
            ]
        },
        options: {
            responsive: true
        }
    };

    if (!window.myPie) {
        const ctxPie = document.getElementById("chart-pie").getContext("2d");
        window.myPie = new Chart(ctxPie, configPie);
    } else {
        window.myPie.data = configPie.data;
        window.myPie.update();
    }
}

showNotesPieChart(null);


const subjectSelect = document.getElementById("subject-select");
subjectSelect.onchange = (event) => {
    let newSubject = event.target.value;
    if (newSubject === "ALL") {
        // reset filtering
        newSubject = null;
    }
    showNotesPieChart(newSubject);
}