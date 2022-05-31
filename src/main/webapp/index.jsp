<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="./styles.css">
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <form action="login" method="post" name="login">
        <div class="my-form">
            <div class="form-group text-center">
                <h1>Authorization</h1>
            </div>
            <div class="form-group">
                <label class="control-label required">Login</label>
                <input type="text" name="login" id="login" class="form-control" required
                       placeholder="Enter the login *">
            </div>
            <div class="form-group">
                <label class="control-label required">Password</label>
                <input type="password" name="password" id="password" class="form-control" required
                       placeholder="Enter the password *">
            </div>
            <h6 class="error">${sessionScope.login_error}</h6>
            <button type="submit" class="btn btn-block btn-primary tx-tfm login-button">Sign in</button>
        </div>
    </form>
</div>
</body>
</html>
