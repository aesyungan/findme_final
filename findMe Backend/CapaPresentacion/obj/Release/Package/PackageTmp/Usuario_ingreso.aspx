<%@ Page Title="" Language="C#" MasterPageFile="~/Home.Master" AutoEventWireup="true" CodeBehind="Usuario_ingreso.aspx.cs" Inherits="CapaPresentacion.Usuario_ingreso" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderDialog" runat="server">

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <center>
      <h1>  Ingreso De Usuario</h1>

   </center>
        <div class="form-group">   
            <table class="table table-striped">
                <tr>
                    <td> 
                      <div class="form-group">
                      <label for="email">Nombres:</label>
                      <asp:TextBox ID="txtNombres" runat="server" CssClass="form-control" placeholder="Nombres"></asp:TextBox>
                      </div>
                    </td>
                    <td> 
                      <div class="form-group">
                      <label for="email">Apellidos:</label>
                    
                          <asp:TextBox ID="txtApellidos" runat="server" CssClass="form-control" placeholder="Apellidos"></asp:TextBox>
                      </div>
                    </td>
                </tr>

                <tr>
                    <td> 
                      <div class="form-group">
                      <label for="email">Cedula:</label>
                      <asp:TextBox ID="txtCedula" runat="server" CssClass="form-control" placeholder="Cedula"></asp:TextBox>
                      </div>
                    </td>
                    <td> 
                      <div class="form-group">
                      <label for="email">Edad:</label>
                    
                          <asp:TextBox ID="txtEdad" runat="server" CssClass="form-control" placeholder="Edad"></asp:TextBox>
                      </div>
                    </td>
                </tr>

                <tr>
                    <td> 
                      <div class="form-group">
                      <label for="email">Direccion:</label>
                      <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control" placeholder="Direccion"></asp:TextBox>
                      </div>
                    </td>
                    <td> 
                      <div class="form-group">
                      <label for="email">Usuario:</label>
                    
                          <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control" placeholder="User Name"></asp:TextBox>
                      </div>
                    </td>
                </tr>
                <tr>
                    <td> 
                      <div class="form-group">
                      <label for="email">Contraseña:</label>
                      <asp:TextBox ID="txtContrasena" runat="server" CssClass="form-control" placeholder="Contraseña"></asp:TextBox>
                      </div>
                    </td>
                    <td> 
                      <div class="form-group">
                      <label for="email">Contraseña:</label>
                    
                          <asp:TextBox ID="txtContrasena2" runat="server" CssClass="form-control" placeholder="Contraseña"></asp:TextBox>
                      </div>
                    </td>
                </tr>
                
               
            </table>
            <asp:Button ID="btnAddUser" runat="server" Text="Add"  CssClass="btn btn-success btn-block" OnClick="btnAddUser_Click" /> 
               
           
              
       
       </div>
   
</asp:Content>
