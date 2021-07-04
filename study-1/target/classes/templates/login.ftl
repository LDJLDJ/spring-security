<html xmlns="http://www.w3.org/1999/html">
    <head>
        <title>登录界面</title>
    </head>
    <body>
        <h1>
            授权登录系统
        </h1>
        <#--username 和 password 这两个名称不能改变，因为springsecurity就是通过这两个字段获取的
            如果不一样，就要用拦截器去拦截，把对应的账号改为yserbane，比如前端传来的是account，那么就在request里面设置属性username = account-->
        <form action="/user/login" method="post">
            <span>用户名</span><input type="text" name="username" /></br>
            <span>密码</span><input type="password" name="password" /></br>
            <input type="submit" value="登录"/>
        </form>

    </body>

</html>