<%@ Page Title="" Language="C#" MasterPageFile="~/Home.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="CapaPresentacion.Home1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderDialog" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
    <style>
        .header{
            
            background: #13b7de;
            height: 50px;
            color: white;
            padding-top: 1%;
          font-size: 20px;
          margin-bottom:6%; 
          
            
        }
      #map {
        height: 100%;
        width: 100%;
        float: left;
      
        
      }
        #datos {
        height: 80%;
        width: 20%;
        float: right;
        padding: 2% 2% 2% 1%;
        background: #344646;
        border:  1px solid red;
        border-radius: 2%;
            overflow-y: scroll;
            
 
        
      }
      .itemGps{
          background-color: #444141;
          color: white;
          display: inline-block;
         width: 100%;
          position: relative;
          margin-bottom: 1%;
              overflow: hidden;
              height: 100px;
      }
      .itemGps:hover{
          background-color: #46e4e4;  
          border-radius: 5%;
          height: 110px;
      }
       .dato1{
          float: right;
          width: 70%;
          
      }
       .foto{
          float: left;
          vertical-align: central;
          padding: 1%;
          width: 28%;
          position: absolute;
          height: 100%;
        
      }
      .imgTest{
          
          border-radius: 100%;
      }
      .description{
          position: absolute;
          display: inherit;
              
      }
      .buscar{
    border-bottom: 2px solid #14bfe6;
    margin-bottom: 2%;
    padding: 2%;
    
          
      }
      .textBuscar{
          width: 100%;
      }
    </style>
    <div ng-app="myApp" >

<%-- <div class="header">
  <center >Lista de Gps  de Usuarios Registrados </center>   
  <img  class="imgTest" src="http://androtalk.es/wp-content/uploads/2014/05/google-maps-logo.png" width="50"  height="50">
      </div>    
  --%>
  
  <div class="todo">

      
      <div  ng-controller="mapCntrl" id="map">
           <ng-map center="[-1.68506378,-78.63885724]" style="height: 500px;width:100%;" zoom="4">
               <div ng-repeat="p in lista_clientGps">
       <!--            <marker icon="{{customIcon}}"  position="{{p.latitude}}, {{p.longitude}}" ></marker>-->
                   <marker  title="{{p.dispositivo.descripcion}}"  position="{{p.dispositivo.latitud}}, {{p.dispositivo.longitud}}" >
                      
                   </marker>
       
               </div>
           </ng-map>
                   
       </div>
         
    <%--  <div ng-controller="mapCntrl" id="datos"> 
          <div class="buscar">
              <input class="textBuscar form-control" type="text" placeholder="Buscar">
          </div>     
          <div ng-repeat="x in lista_clientGps" class="itemGps">
            
              <div class=" foto">
                      <img class="imgTest" src="http://www.zayedhotel.com/addons/default/themes/yoona/img/user.jpg" width="50px" height="50px"  />
                      <span class="description"> {{ x.dispositivo.descripcion}}</span>
              </div>
                  <div class=" dato1">
                  <p>{{ x.dispositivo.latitud }}</p>
                  <p>{{ x.dispositivo.longitud }}</p>
                  </div>
          </div>     
      </div>--%>
  </div>   
    </div>
    <script>
         var app = angular.module('myApp', ['ngMap']);
                //otro controlador
                  app.controller('mapCntrl', function ($scope, $http) {
                      //recupera datos 
                      $http.get("http://apprioturalexyungan.azurewebsites.net/misSerivicios.asmx/optenerAsignacion")
                    
                    .then(function (response) {
                        $scope.lista_clientGps = response.data.listDatos;
                       
                       });
                  // pone los  puntos 
                  
                      $scope.points = [
                          { "name": "Canberra", "latitude": -35.282614, "longitude": 149.127775 },
                          { "name": "Melbourne", "latitude": -37.815482, "longitude": 144.983460 },
                          { "name": "Sydney", "latitude": -33.869614, "longitude": 151.187451 }
                      ];
                  
                  
//                      $scope.customIcon = {
//                          "scaledSize": [32, 32],
//                          "url": "http://simpleicon.com/wp-content/uploads/map-marker-2.png"
//                      };
                  
                  });
                
    </script>
</asp:Content>
