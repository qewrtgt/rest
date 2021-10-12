const url = 'api/users'
// const url = 'http://127.0.0.1:3030/api/users'
$(document).ready(function () {
    CreatPrincipalUserTable()
    restartAllUsers();
});

async function restartAllUsers() {
    let UserTableBody = $("#user_table_body")
    UserTableBody.empty();
    openTabById("usersTable")

    await fetch(url)
        .then((response) => {
            console.log(response)
            response.json().then(data => data.forEach(function (user) {
                let TableRow = createTableRow(user);
                UserTableBody.append(TableRow);
            }));
        }).catch(error => {
    });
}

function createTableRow(u) {
    let string_roles = "";
    // console.log(u)
    u.roles.forEach(role => {
        string_roles += role.name + " ";
    })
    return `<tr id="users_table_row">
            <td>${u.id}</td>
            <td>${u.firstName}</td>
            <td>${u.lastName}</td>
            <td>${u.age}</td>
            <td>${u.email}</td>
            <td>${string_roles}</td>
            <td>
            <a  href="${url}/${u.id}" class="btn btn-info editBut">Edit</a>
            </td>
            <td>
            <a  href="${url}/${u.id}" class="btn btn-danger delBut">Delete</a>
            </td>
        </tr>`;
}

document.addEventListener('click', function (event) {
    event.preventDefault()

    if ($(event.target).hasClass('delBut')) {
        let href = $(event.target).attr("href");
        delModalButton(href)
    }

    /*открыть форму*/
    if ($(event.target).hasClass('editBut')) {
        let href = $(event.target).attr("href");
        $("#exampleModalEdit").modal();

        $.get(href, function (user) {
            $(".editUser #idEd").val(user.id);
            $(".editUser #firstNameEd").val(user.firstName);
            $(".editUser #lastNameEd").val(user.lastName);
            $(".editUser #ageEd").val(user.age);
            $(".editUser #emailEd").val(user.email);
            $("editUser #passwordEd").val(user.password);
            $(".editUser #selectRoleEd").val(allRoles());
        });
    }

    /*отправить форму*/
    if ($(event.target).hasClass('editButton')) {
        let user = {
            id: $('#idEd').val(),
            firstName: $('#firstNameEd').val(),
            lastName: $('#lastNameEd').val(),
            age: $('#ageEd').val(),
            email: $('#emailEd').val(),
            password: $('#passwordEd').val(),
            roles: getRoleFromTable("#selectRoleEd")

        }
        editModalButton(user)
    }

    if ($(event.target).hasClass('newButton')) {
        let user = {
            firstName: $('#firstNameNew').val(),
            lastName: $('#lastNameNew').val(),
            age: $('#ageNew').val(),
            email: $('#emailNew').val(),
            password: $('#passwordNew').val(),
            roles: getRoleFromTable("#selectRoleNew")
        }
        newModalButton(user)
        console.log("new User" + user);

    }

    if ($(event.target).hasClass('logout')) {
        logout();
    }
});

function newModalButton(user) {
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }).then(function (response) {
        $('input').val('');
    }).then(()=> restartAllUsers());
}

function editModalButton(user) {
    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }).then(function (response) {
        $('input').val('');
        $('#exampleModalEdit').modal('hide');
    }).then(() => restartAllUsers());
}

function delModalButton(href) {
    fetch(href, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }).then(() => restartAllUsers());
}

function allRoles() {

    fetch(url+ "/allroles")
        .then((response) => {
            response.json().then(data => {
                let roles = []
                data.forEach(function (role) {
                    roles.push(role.name)
                    //console.log(roles)
                })
                console.log(roles)
                return roles;
            });
        }).catch(error => {

    });
}

function getRoleFromTable(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({id: $(this).val(), role: $(this).attr("name"), authority: $(this).attr("name")})
    });
    return data;
}


function CreatPrincipalUserTable() {
    let principalTableBody = $("#principal_table_body")

    fetch(url + "/principal")
        .then((response) => {
            response.json().then((function (user) {
                let TableRow = createTableRowUser(user);
                principalTableBody.append(TableRow);
            }));
        }).catch(error => {
    });
}

function createTableRowUser(user) {
    let string_roles = "";
    // console.log(user)
    user.roles.forEach(role => {
        string_roles += role.name + " ";
    })
    $(".navbar #emailPrincipal").val(user.email);
    $(".navbar #rolePrincipal").val(string_roles);


    return `<tr id="principal_table_row">
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${string_roles}</td>
        </tr>`;
}

function openTabById(tab) {
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
}

function logout() {
    document.location.replace("/logout");
}