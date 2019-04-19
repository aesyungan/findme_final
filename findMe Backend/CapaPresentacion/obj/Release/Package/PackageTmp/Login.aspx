<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="CapaPresentacion.Login"  %>

<!DOCTYPE html>

<html class="bg-black" xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <script type="text/javascript" src="css/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

   <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/font-awesome-4.7.0/css/font-awesome.css"/>
    <link  type="text/css" rel="stylesheet" href="css/AdminLTE.css"/>
    <style>
        .fondo {
        background-color:red;
        }
    </style>
</head>
<body class="bg-black fondo">
    <div class="form-box" >
        <div class="header bg-blue-gradient">Iniciar Sesion</div>
             <form id="form1" runat="server">
    
                 <div class="body bg-gray">
                    <div class="form-group">
                     <asp:TextBox ID="txtUsuario"  runat="server" CssClass="form-control" placeholder="User name"></asp:TextBox>
                   </div>
                     <div class="form-group">
                         
                         <asp:TextBox ID="txtPassword"  runat="server"  CssClass="form-control" placeholder="User Password" TextMode="Password" ></asp:TextBox>
                   </div>
                     
                  </div>
                 <div class="footer bg-blue-gradient">
                     <div class="form-group">
                         <asp:Button ID="btnIngreso" runat="server" Text="Entrar"  CssClass="btn btn-success btn-block" OnClick="btnIngreso_Click" />
                     </div>
                 </div>

           </form>
       
    </div>
   
</body>
</html>
