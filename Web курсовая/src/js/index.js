const $list = document.querySelector('.list');
const $field = document.querySelector('.text');

let persons = null;


function templateGenerator(list) {
    let template = '';
    if (list.length) { //если длина больше чем ноль, значит есть элемент;
        for (let i = 0; i < list.length; i++) {
            template += '<li>' + list[i].name + '</li>';
        }
    } else { //если ничего не найдено;
        template += '<li>Not Found!</li>';
    }
    $list.innerHTML = template;
}

fetch('http://localhost:8001/back')
    .then(function(responce) {
        return responce.json();
    })
    .then(function(data) {
        persons = data.results;
        templateGenerator(persons);
    })

$field.addEventListener('input', function() {
    let text = this.value.toLowerCase();
    let filterdPersons = persons.filter(function(el) {
        return ~el.name.toLowerCase().indexOf(text);

    });
    templateGenerator(filterdPersons);
})
