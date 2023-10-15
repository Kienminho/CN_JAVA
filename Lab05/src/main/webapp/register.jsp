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
    <meta charset="UTF-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css    ">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        p.item {
            padding: 16px;
            text-align: center;
            border-radius: 8px;
            color: white;
            background-color: green;
        }
    </style>
</head>

<body class="bg-secondary">

<h3 class="text-center my-5 text-light">Account Registration</h3>

<!-- buộc toàn bộ dòng và cột phải bỏ trong class container -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-5">
            <div class="border p-3 rounded bg-light">
                <form id="form-register">
                    <div class="form-group">
                        <label for="name">Fullname</label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-user"></i>
                  </span>
                            </div>
                            <input id="name" name="username" type="text" class="form-control" placeholder="Your Name">
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-user"></i>
                  </span>
                                </div>
                                <input id="email" name="email" type="email" class="form-control" placeholder="Email">
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-lock"></i>
                  </span>
                                </div>
                                <input id="password" name="password" type="password" class="form-control" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password-confirm">Confirm Password</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-lock"></i>
                  </span>
                                </div>
                                <input id="password-confirm" type="password" class="form-control" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="container-message d-none alert alert-danger alert-dismissible fade show">
                                <p>Password must have at least 6 characters!</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success px-5" type="button" onclick="hanleRegister()">Register</button>
                        </div>
                        <div class="form-group">
                            <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/login">Login now!</a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
<script>

    const containerMessage  = $(".container-message");
    function hanleRegister() {
        const formRegister = document.getElementById("form-register");  //lấy form đăng kí
        const formData = new FormData(formRegister);
        const password = $("#password").val()
        const rePassword = $("#password-confirm").val();
        if($("#name").val() === "") {
            displayMessage(containerMessage, "Vui lòng không để trống tên.");

        }
        else if(password === "" || rePassword ==="") {
            displayMessage(containerMessage, "Vui lòng không để trống mật khẩu.");
        }

        else if($("#email").val()=== "" || !isValidEmail($("#email").val())) {
            displayMessage(containerMessage, "Email sai định dạng.");

        }
        else if(!checkPassowrd(password, rePassword)) {
            displayMessage(containerMessage, "Mật khẩu không khớp, vui lòng nhập lại")
        }
        else {
            fetch("${pageContext.request.contextPath}/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams(formData).toString(),
            })
                .then(res => res.json())
                .then(data => {
                    if(data.statusCode === 200) {
                        window.location = data.message;
                    }
                    else
                        displayMessage(containerMessage, data.message);
                })
                .catch(err => console.log(err))
        }





    }

    //kiểm tra mật khẩu
    function checkPassowrd(password, rePasssword) {
        var isCorrect
        (password===(rePasssword)) ? isCorrect = true : isCorrect =  false;
        return isCorrect;
    }
    //hiển thị thông báo lỗi
    function displayMessage(containerMessage, message) {
        containerMessage.children("p").text(message);
        containerMessage.removeClass("d-none");
        setTimeout(() => {
            containerMessage.addClass("d-none");
        }, 3000)
    }


    // kiểm tra định dạng email
    function isValidEmail(email) {
        // Biểu thức chính quy cho kiểm tra định dạng email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        // Kiểm tra chuỗi theo biểu thức chính quy
        return emailRegex.test(email);
    }

</script>

</html>
