<%@ Page Title="" Language="C#" MasterPageFile="~/Home.Master" AutoEventWireup="true" CodeBehind="Cerrar.aspx.cs" Inherits="CapaPresentacion.Cerrar" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderDialog" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
   <center>
     <h1><span class="fa fa-spinner fa-4x"></span>Cerrando Session </h1>
       <script>
           $(document).ready(function () {

               alert("Hola");
           });
       </script>

   </center>
</asp:Content>
