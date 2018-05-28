<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body id="loginpage">
    <form id="/login" class="formee" action="/login" method="post">
        <div class="container_16 clearfix">
            <div class="push_5 grid_6"></div>
            <div class="clear"></div>
            <div class="widget push_5 grid_6" id="login">
                <div class="widget_title">
                    <h2>TravelFoot</h2>
                </div>
                <div id="clomeyes" class="widget_body">
                    <div class="widget_content">
                        <label class="block" for="email">Email:</label>
                        <input type="text" id="id" name="userEmail" maxlength="30" tabindex="1" class="large"/>
                        <label class="block" for="pw">비밀번호:</label>
                        <input type="password" id="pw" name="pw" maxlength="30" tabindex="2" class="large" />
                        <div style="margin-top:10px">
                            <input type="submit" id="btn_login" value="로그인" class="btn right large">
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script type="text/javascript">
        $("#btn_login").on("click", function(e) {
            $("#form_login").submit();
        });
    </script>
</body>
</html> 