<%--
  Created by IntelliJ IDEA.
  User: kienl
  Date: 10/15/2023
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>
            <form id="form-login" class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" name="username" type="text" class="form-control" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group d-flex align-items-right">
                    <p class="mr-2"><input type="checkbox" name="saveUser"> Remember username & password</p>
                    <a class="ml-auto" href="${pageContext.request.contextPath}/auth/register">Register here!</a>
                </div>
                <p class="message text-danger d-none"></p>
                <div class="form-group">
                    <button type="button" onclick="func_Login()" class="btn btn-success px-5">Login</button>
                </div>

            </form>

        </div>
    </div>
</div>

</body>

<script>

    const formLogin = document.getElementById("form-login");
    function func_Login() {
        const formData = new FormData(formLogin);

        fetch("${pageContext.request.contextPath}/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(formData).toString(),
        })
            .then(res =>  res.json())
            .then(data => {
                if(data.statusCode != 200) {
                    const eMessage =$(".message");
                    eMessage.text(data.message);
                    eMessage.removeClass("d-none");
                    setTimeout(() => {
                        eMessage.addClass("d-none");
                    }, 3000)
                }
                else
                    window.location = data.message;

            })
            .catch(err => console.log(err))

    }

</script>
</html>

