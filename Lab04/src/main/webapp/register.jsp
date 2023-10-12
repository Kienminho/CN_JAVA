<%--
  Created by IntelliJ IDEA.
  User: kienl
  Date: 10/12/2023
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <title>Register</title>
    <style>
        .table-container {
            height: 300px;
            width: 45%; /* Đặt chiều rộng mong muốn của container */
            margin: 10px;
            display: inline-block;
        }
        input, select, textarea {
            box-sizing: border-box;
        }
        input[type="text"], select {
            height: 24px;
        }
        td {
            padding: 4px;
        }

        table, td, tr {
            border: 1px solid black;
            border-collapse: collapse;
        }
        .table-info {
            display: none;
        }
        .field {
            color: #8c03c2;
        }
        #error-message {
            margin-top: 6px;
            color: red;
            display: none;
        }
        .output {
            color: green;
        }
        #registrationForm {
            margin-bottom: 12px;
        }

    </style>
</head>
<body>
<div  class="table-container">
    <form id="registrationForm" method="post" action="/registerServlet">
        <table>
            <tr>
                <td>Your Name</td>
                <td>
                    <input name="name" type="text" style="width: 200px" placeholder="Full Name">
                </td>
            </tr>
            <tr>
                <td>Email Address</td>
                <td><input name="email" type="text" style="width: 200px" placeholder="Email"></td>
            </tr>
            <tr>
                <td>Birthday</td>
                <td><input name="birthday" type="date" style="width: 200px"></td>
            </tr>
            <tr>
                <td>Birthtime</td>
                <td><input name="birthtime" type="time" style="width: 200px"></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <input type="radio" value="male" name="gender"> Male
                    <input type="radio" value="female" name="gender"> Female
                </td>
            </tr>
            <tr>
                <td>From</td>
                <td>
                    <select name="country" style="width: 200px">
                        <option>Select a country</option>
                        <optgroup label="Asia">
                            <option>Vietnam</option>
                            <option>Laos</option>
                            <option>Cambodia</option>
                            <option>Singapore</option>
                        </optgroup>
                        <optgroup label="Europe">
                            <option>France</option>
                            <option>Belgium</option>
                            <option>Italy</option>
                            <option>Finland</option>
                            <option>Ireland</option>
                        </optgroup>
                    </select>
                </td>
            </tr>
            <tr>
                <td style="vertical-align: text-top">Favorite IDE</td>
                <td>
                    <div><input type="checkbox" name="favorite_ide[]" value="Visual Studio Code"> Visual Studio Code</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Sublime Text"> Sublime Text</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Eclipse"> Eclipse</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Atom"> Atom</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Intelij Idea"> Intelij Idea</div>
                </td>
            </tr>
            <tr>
                <td>TOEIC Score</td>
                <td><input name="toeic" type="range" style="width: 200px" min="5" max="990" step="5"></td>
            </tr>
            <tr>
                <td style="vertical-align: text-top">Message</td>
                <td>
                    <textarea name="message" rows="5" style="width: 200px"></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="button" onclick="submitForm()">Register</button>
                    <button type="button" onclick="resetForm()">Again</button>
                </td>
            </tr>
        </table>
    </form>
    <p id="error-message"></p>
</div>



<div id="table-info" class="table-container table-info">
        <table>
            <tr>
                <td class="field">Họ tên</td>
                <td class="output" id="nameCell"></td>
            </tr>
            <tr>
                <td class="field">Email</td>
                <td class="output" id="emailCell"></td>
            </tr>
            <tr>
                <td class="field">Ngày sinh</td>
                <td class="output" id="birthdayCell"></td>
            </tr>
            <tr>
                <td class="field">Giờ sinh</td>
                <td class="output" id="birthtimeCell"></td>
            </tr>
            <tr>
                <td class="field">Giới tính</td>
                <td class="output" id="genderCell"></td>
            </tr>
            <tr>
                <td class="field">Quốc gia</td>
                <td class="output" id="countryCell"></td>
            </tr>
            <tr>
                <td class="field">IDE yêu thích</td>
                <td class="output" id="favoriteIDECell"></td>
            </tr>
            <tr>
                <td class="field">Điểm TOEIC</td>
                <td class="output" id="toeicCell"></td>
            </tr>
            <tr>
                <td class="field">Giới thiệu bản thân</td>
                <td class="output" id="messageCell"></td>
            </tr>
        </table>
    </div>
</body>

<script>

    function submitForm() {
        // Collect form data
        const formData = new FormData(document.getElementById('registrationForm'));




        // Send data to the server using Fetch
        fetch("http://localhost:8080/registerServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(formData).toString(),
        })
            .then(response => response.json())
            .then(data => {
                if(data.code ===200) {
                    document.getElementById('table-info').style.display = "inline-block";
                    $("#nameCell").text(data.user.name);
                    $("#emailCell").text(data.user.email);
                    $("#birthdayCell").text(data.user.birthday);
                    $("#birthtimeCell").text(data.user.birthtime);
                    $("#genderCell").text(data.user.gender);

                    //hiển thị ide yêu thích
                    data.user.favoriteIDE.map(ide => $('#favoriteIDECell').append('<li>' + ide + '</li>'));
                    $("#countryCell").text(data.user.country);
                    $("#toeicCell").text(data.user.toeic);
                    $("#messageCell").text(data.user.message);
                }
                else {
                    console.log(217)
                    $("#error-message").css("display", "inline");
                    $("#error-message").text("Đăng kí thất bại, vui lòng kiểm tra lại.");
                }
            })
            .catch(error => console.error("Error:", error));
    }

    //xoá dữ liệu ở các trường trong form
    function resetForm() {
        document.getElementById('registrationForm').reset();
        document.getElementById('table-info').style.display = "none";
        document.getElementById('error-message').style.display = "none";
    }
</script>
</html>
