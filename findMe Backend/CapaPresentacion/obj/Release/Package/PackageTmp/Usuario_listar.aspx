<%@ Page Title="" Language="C#" MasterPageFile="~/Home.Master" AutoEventWireup="true" CodeBehind="Usuario_listar.aspx.cs" Inherits="CapaPresentacion.Usuario_listar" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        #focuss:hover {
            background-color:red;
        }
         #focuss {
                color:white;
            background-color:#222222 ;
        }
        #imgselect {
        border-radius:50%;
        }

        #contenidotodo {
               position: relative;
    background-color: #fff;
    display: inline-block;
 
    padding: 0px;
    
    margin: 0px;
        }
        #item {
                overflow: auto;
               padding: 2%;
    color: white;
    position: relative;
    margin: 10px;
    border-radius: 2%;
    background-color: #a4a99d;
    width: 200px;
    display: inline-block;
        vertical-align: text-top;
        }

        #item_select {
           overflow: auto;
               padding: 2%;
    color: white;
    position: relative;
    margin: 10px;
    border-radius: 2%;
    background-color: yellow;
    width: 200px;
    display: inline-block;
        vertical-align: text-top;
        }

            #item:hover {
             background-color:#f32e2e;
                 }

        #item_todo {
            padding: 1%;
           
            /*background-color:brown;*/
        }
           #item_todo_row {
            padding: 1%;
            
            /*background-color:maroon;*/
        }
            #item_todo_row_codigo {
                border-radius: 100%;
    background-color: #58584f;
    width: 80px;
    height: 80px;
    position: absolute;
    margin: -19%;
    text-align: center;
    font-size: x-large;
    padding-top: 17%;
    padding-left: 14%;
        
        }
        #item_botones {
          
            border:10px 0px 0px 0px;
               border-top: 2px dashed #888585;
                padding: 4%;

        }

        /*Mensajes de dialogo css*/
        .alertTodo {
        display:none;
            position: fixed;
    background-color: rgba(49, 47, 47,0.8);
    width: 100%;
    height: 100%;
    z-index: 2;
        padding: 14%;
        }
        .alertTodo_show {
        display:inline;
        }

             .contenidoForm {
            
                background-color: #ffffff;
    
    padding: 2%;
    box-shadow: 15px 12px 20px #908f8f,-15px -12px 20px #908f8f;
    border-radius: 3em;
            
            }

/* para maxinozavion de todo  */
             @media screen and (min-width: 769px) {
                 .alertTodo {
     
            position: fixed;
    background-color: rgba(49, 47, 47,0.8);
    width: 100%;
    height: 100%;
    z-index: 2;
        padding: 6% 13% 17% 36%;
        }
    }


        /*fin Mensajes de dialogo css*/

         
    </style>
  
</asp:Content>
<asp:Content ID="content3" ContentPlaceHolderID="ContentPlaceHolderDialog" runat="server">
  
      <!-- Dialogo-->
    <div class="alertTodo">
        <div class=" contenidoForm">

             <div class="form-group">   
            <table class="table table-striped">
                <div class="table header center-block">
                 <center>  <h2> <span class="glyphicon glyphicon-book fa-2x"></span><i id="txt_texto_of_edit"></i></panel></h2></center>
                </div>
              <asp:Label AssociatedControlId="fileUploader1" runat="server"
	    Text="Seleccionar una imagen:" />
      <asp:FileUpload id="fileUploader1" runat="server" />
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
                 <tr>
                    <td> 
                      
                      <a id="btn_cancelar" class="btn btn-danger btn-block"><span class="glyphicon glyphicon-remove-circle fa-2x"></span> </a>
                     
                    </td>
                    <td> 
                     
                           <asp:LinkButton ID="btnAgregarNuevo" CssClass="btn btn-success btn-block" runat="server" OnClick="btnAgregarNuevo_Click"><span class="glyphicon glyphicon-check fa-2x"></span></asp:LinkButton>
                              
                     
                       
                      
                    </td>
                </tr>
                
               
            </table>
           
          
            

               
           
              
       
       </div>


        </div>
    </div>
     <!-- edialogo fin -->
    
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">



    

    <center>
        <h1>Lista De Usuarios</h1>
       
        <a id="btn_ingreso" class="btn btn-success"><span class="glyphicon glyphicon-pencil fa-2x "></span> Ingreso</a>
   </center>
    <center>
    <script>
        $(document).ready(function () {
            //open dialog
            $('#btn_ingreso').click(function () {
                $('.alertTodo').addClass('alertTodo_show');
                $('#txt_texto_of_edit').html("Nuevo");
                
            })
            //close dialog
            $('#btn_cancelar').click(function () {
                $('.alertTodo').removeClass('alertTodo_show');

            })
        })

    </script>

    <div id="contenidotodo">                    
   <asp:ListView runat="server" ID="lvwDatos"  DataKeyNames="id_usuario"
   OnSelectedIndexChanging="lvwDatos_SelectedIndexChanging" >
      <%--   <LayoutTemplate>
        <table class="table table-striped" >
            <tr>
                <th>Id</th>
                <th>Foto</th>
                <th>Nombres</th>
                <th>Cedula</th>
                <th>Direccion</th>
                <th>Edad</th>
                <th>Otros</th>
            </tr> 
        <asp:PlaceHolder runat="server" ID="ItemPlaceholder"/> 
        </table>     
    </LayoutTemplate>--%>
       
    <ItemTemplate>
             
        <div id="item">

       
            <div id="item_todo">
                <div id="item_todo_row_codigo">
                     <%# Eval("id_usuario") %>
                </div>
                <div id="item_todo_row">
                    
                    <img src="<%# Eval("foto") %>" width="80" height="80" alt="User" id="imgselect" />
                 </div>

                 <div id="item_todo_row">
                     <%# Eval("nombres")%> <%# Eval("apellidos")%>
                  </div>
                   <div id="item_todo_row">
                     <%# Eval("cedula")%>
                   </div>
                   <div id="item_todo_row">Dirección:
                          <%# Eval("direccion")%>
                       </div>
                    <div id="item_todo_row">Edad:
                      <%# Eval("edad")%>
                   </div>
                 </div>
               <div id="item_botones"></div>
               <asp:LinkButton ID="lnkSelectRemove" Text="Ver" CommandName="Select" runat="server" CssClass="">
                </asp:LinkButton> 
             <asp:LinkButton ID="lnkRemove"  OnCommand="lnkRemove_Command"  CommandArgument='<%# Eval("id_usuario") %>'   Text="Eliminar"  runat="server" CssClass="btn btn-danger">
                </asp:LinkButton> 
              
             
              
            </div>
       
            
    </ItemTemplate>  

     <SelectedItemTemplate>
         
        <div id="item" style="background-color: #336699; color: White;">

       
            <div id="item_todo">
                <div id="item_todo_row_codigo">
                     <%# Eval("id_usuario") %>
                </div>
                <div id="item_todo_row">
                    
                    <img src="<%# Eval("foto") %>" width="80" height="80" alt="User" id="imgselect" />
                 </div>

                 <div id="item_todo_row">
                     <%# Eval("nombres")%> <%# Eval("apellidos")%>
                  </div>
                   <div id="item_todo_row">
                     <%# Eval("cedula")%>
                   </div>
                   <div id="item_todo_row">Dirección:
                          <%# Eval("direccion")%>
                       </div>
                    <div id="item_todo_row">Edad:
                      <%# Eval("edad")%>
                   </div>
                 </div>
               <div id="item_botones"></div>
              <asp:LinkButton ID="lnkSelectRemove" Text="Ver" CommandName="Select" runat="server" CssClass="">
               </asp:LinkButton>
             
            <asp:LinkButton ID="lnkRemove"  OnCommand="lnkRemove_Command"   CommandArgument='<%# Eval("id_usuario")  %>'  Text="Eliminar" runat="server" CssClass="btn btn-danger">
                </asp:LinkButton> 
              
            </div>
        
     </SelectedItemTemplate>
</asp:ListView> 
         
    </div>  
        
        </center>

 

    
                             
       
</asp:Content>
