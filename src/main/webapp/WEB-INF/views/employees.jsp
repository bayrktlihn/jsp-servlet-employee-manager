<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <title>Hello, world!</title>

    <script type="text/javascript">


        function removeAllChecked() {
            const values = allCheckedCheckboxs().map(item => +item.value);


            const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    location.reload();
                }
            }


            const requestBodyObject = {
                toBeDeletedEmployeeIds: values
            }

            xhr.open("DELETE", "${pageContext.request.contextPath}/employees")
            xhr.send(JSON.stringify(requestBodyObject));
        }


        function allCheckboxItems() {
            const rows = document.querySelectorAll("table tbody tr");

            const checkboxs = [];

            rows.forEach(row => {
                checkboxs.push(row.querySelector("input[type='checkbox']"));
            });

            return checkboxs;
        }

        function allCheckedCheckboxs() {
            return allCheckboxItems().filter(item => item.checked);
        }


        function clickAllCheck(element) {
            allCheckboxItems().forEach(checkbox => {
                checkbox.checked = element.checked;
            });

        }

        function deleteEmployee(element) {
            const id = +element.getAttribute('data-id');

            const xmlHttpRequest = new XMLHttpRequest();

            xmlHttpRequest.onreadystatechange = function () {
                if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
                    location.reload();
                }
            }

            xmlHttpRequest.open("DELETE", "${pageContext.request.contextPath}/employees/" + id);
            xmlHttpRequest.send()
        }

        function saveEmployee() {
            const employeeForm = document.querySelector("#employeeForm");

            const id = employeeForm.querySelector("input[name='id']").value;
            const name = employeeForm.querySelector("input[name='name']").value;
            const email = employeeForm.querySelector("input[name='email']").value;
            const phone = employeeForm.querySelector("input[name='phone']").value;
            const address = employeeForm.querySelector("textarea[name='address']").value;

            const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    location.reload();
                }
            }


            const requestBodyObject = {
                id: !!id ? +id : null,
                name,
                email,
                phone,
                address
            }


            const method = !id ? "POST" : "PUT";


            xhr.open(method, "${pageContext.request.contextPath}/employees")
            xhr.send(JSON.stringify(requestBodyObject));

        }


    </script>

</head>
<body>

<div class="container">

    <div style="display: flex; flex-direction: row; align-items: center; flex-wrap: wrap">
        <h1 style="flex: auto">Manage Employees</h1>
        <div style="flex-direction: row">
            <input type="button" value="Delete" class="btn btn-danger" onclick="removeAllChecked()">
            <input data-action="new-employee" type="button" value="Add New Employee"
                   class="btn btn-success employee-action-button"
                   data-toggle="modal"
                   data-target="#staticBackdrop">
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"><input onclick="clickAllCheck(this)" type="checkbox"></th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Phone</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">

            <tr>
                <td><input name="selectedEmployee" type="checkbox" value="${employee.id}"></td>
                <td><span class="employee-name">${employee.name}</span></td>
                <td><span class="employee-email">${employee.email}</span></td>
                <td><span class="employee-address">${employee.address}</span></td>
                <td><span class="employee-phone">${employee.phone}</span></td>
                <td>
                    <button data-action="update-employee" type="button" value="Add New Employee"
                            class="btn btn-success btn-sm employee-action-button"
                            data-toggle="modal"
                            data-target="#staticBackdrop">Edit
                    </button>
                    <button data-id="${employee.id}" class="btn btn-danger btn-sm" onclick="deleteEmployee(this)">
                        Delete
                    </button>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>


    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Employee</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <form id="employeeForm">
                        <input type="hidden" id="id" name="id">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" name="name" class="form-control" id="name">
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control" id="email">
                        </div>

                        <div class="form-group">
                            <label for="address">Address</label>
                            <textarea class="form-control" id="address" rows="3" name="address"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>


                    </form>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="saveEmployee()">Save</button>
                </div>
            </div>
        </div>
    </div>


</div>


<script type="text/javascript">
    const employeeActionButtons = document.querySelectorAll(".employee-action-button");

    employeeActionButtons.forEach(employeeActionButton => {
        employeeActionButton.addEventListener('click', (event) => {


            const name = document.querySelector("#name");
            const email = document.querySelector("#email");
            const address = document.querySelector("#address");
            const phone = document.querySelector("#phone");
            const id = document.querySelector("#id");

            const dataAction = employeeActionButton.getAttribute("data-action");
            if (dataAction === 'new-employee') {
                name.value = '';
                email.value = '';
                address.value = '';
                phone.value = '';
                id.value = '';

            } else if (dataAction === 'update-employee') {
                const rowElement = employeeActionButton.parentElement.parentElement;

                name.value = rowElement.querySelector(".employee-name").innerText;
                email.value = rowElement.querySelector(".employee-email").innerText;
                address.value = rowElement.querySelector(".employee-address").innerText;
                phone.value = rowElement.querySelector(".employee-phone").innerText;
                id.value = rowElement.querySelector("input[name='selectedEmployee']").value;
            }
        })
    })


</script>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
-->
</body>
</html>