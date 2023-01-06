<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>TransactionsTMS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
        <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans'>
        <link href="../lib/css/tms/log.css" rel="stylesheet" type="text/css"/>
        <link href="../lib/css/tms/float.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <%

            HttpSession ownsession = request.getSession(false);
            if (session != null) {
                session.setAttribute("user", ownsession);
            }
            session.setAttribute("password", ownsession);

        %>


        <div class="cont">
            <form class="form-horizontal form-material" id="loginform"name="login" action="Login" method="POST">
                <div class="demo">

                    <div class="login">
                        <div class="login__check"></div>
                        <div class="login__form">
                            <div class="login__row">
                                <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
                                <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
                                </svg>
                                <input type="text" name="user" type="text" required="required" onfocus="this.select();" class="login__input name" placeholder="Usuario"/>
                            </div>
                            <div class="login__row">
                                <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
                                <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
                                </svg>
                                <input type="password"name="password"type="password" required="required"  onfocus="this.select();"  class="login__input pass" placeholder="Password"/>
                            </div>

                            <input type="hidden" value="" id="hidCliente" name="hidCliente"/>
                            <button type="submit"  class="login__submit">Entrar</button>
                            <input type="hidden"  name="pag" value=CuentasMobil"/> 

                            <p class="login__signup">¿Problemas con su cuenta?<a></a></p>
                        </div>
                    </div>

                </div>
            </form>
            <div id="container-floating">
                <div class="nd4 nds" data-toggle="tooltip" data-placement="left" data-original-title="ir a TMS"><a  href="<%=request.getContextPath()%>/index.jsp"><p class="letter"><i class="fa fa-truck"></i></a></p>
                </div>
                <div class="nd3 nds" data-placement="left"> <a  href="<%=request.getContextPath()%>/MobPod"><p class="letter"> <i class="icon-power"></i></p></a></div>
                <div class="nd1 nds" data-toggle="tooltip" data-placement="left" data-original-title="Soporte"><img class="reminder">
                    <a  href="<%=request.getContextPath()%>/soporte.jsp"> <p class="letter">S</p></a>
                </div>
            </div>
        </div>

        <script>
            loadLogin('<%=request.getContextPath()%>');
        </script> 
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="../lib/js/tms/index.js" type="text/javascript"></script>
    </body>
</html>
