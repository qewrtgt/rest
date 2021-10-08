$(document).ready(setTimeout(function () {
    console.log(" DOCUMENT READY ADMIN = 1,5 sec")
    getAllUsers()
}, 1500))

function getAllUsers() {

    const url = 'http://localhost:8080/api/users';
    const container = $('#tbody');
    console.log(url + " getAllUsers = ADMIN")

    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {

        let html = '';

        response.forEach((item) => {
            let trHtml =
                `<tr data-id="${item.id}">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.lastname}</td>
                    <td>${item.age}</td>
                    <td>${item.email}</td>
                    <td>${item.roles}</td>
                    <td><a id="buttonEditUser" role="button" onclick="getEditModal(${item.id})" class="btn btn-primary btn-sm" data-target="#buttonEditUser">Edit</a></td>
                    <td><a id="buttonDeleteUser" role="button" onclick="getDeleteModal(${item.id})" class="btn btn-danger btn-sm" >Delete</a></td>
                </tr>`;
            html += trHtml;
        })
        container.html(html);
    })
}