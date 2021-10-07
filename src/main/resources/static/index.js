const delay = ms => {
    return new Promise(r => setTimeout(() => r(), ms))
}

const url = 'http://127.0.0.1:4040/api'

async function renderAllUsersTable () {
    try {
        await delay(2000)
        const response = await fetch(url)
        const data = await response.json()
        console.log('Data:', data)

        let temp = ""
        let UserTableBody = $("#user_table_body")
        UserTableBody.empty();
        data.forEach((user) => {
            let string_roles = ""
            temp += "<tr>";
            temp += "<td>" + user.id + "</td>"
            temp += "<td>" + user.firstName + "</td>"
            temp += "<td>" + user.lastName + "</td>"
            temp += "<td>" + user.age + "</td>"
            temp += "<td>" + user.email + "</td>"

            user.roles.forEach(role=>{
                string_roles += role.name + " ";
            })
            console.log(string_roles)
            temp += "<td>" + string_roles + "</td>"

            // temp += "<td><button type='button' class='btn btn-info text-light' data-bs-toggle='modal' data-bs-target='#editModal' data-user-id='"+ el.id+ "'  data-bs-whatever='@edit'>Edit</button></td>";
            // temp += "<td><button type='button' class='btn btn-danger' data-bs-toggle='modal' data-bs-target='#deleteModal' data-user-id='"+ el.id+ "' data-bs-whatever='@delete'>Delete</button></td></tr>";

            // temp += "<td> <button type='button' class='btn btn-info text-light' data-bs-toggle='modal' data-bs-target=='#modalEdit'   /*data-user-id='"+ el.id+ "' data-bs-whatever='@edit'*/ > Edit </button> </td>"
            // temp += "<td> <button type='button' class='btn btn-info text-light' data-bs-toggle='modal' data-bs-target=='#modalDelete' /*ata-user-id='"+ el.id+ "' data-bs-whatever='@delete'*/ > Delete </button> </td>"

        })
        document.getElementById("user_table_body").innerHTML = temp;

    } catch
        (e) {
        console.error(e);
    }
}

renderAllUsersTable ()
