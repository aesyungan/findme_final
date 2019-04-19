using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using CapaEntidades;
using CapaLogicaDeNegocio;
using System.Web.Script.Services;
using System.Web.Script.Serialization;

namespace CapaPresentacion
{
    /// <summary>
    /// Descripción breve de misSerivicios
    /// </summary>
    [WebService(Namespace = "http://apprioturalexyungan.azurewebsites.net/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
   // [System.ComponentModel.ToolboxItem(false)]
    [System.Web.Script.Services.ScriptService]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class misSerivicios : System.Web.Services.WebService
    {

    
        public static void converyirToJson(object obj)
        {
            System.Web.HttpContext.Current.Response.Write(new JavaScriptSerializer().Serialize(obj));
           // return new JavaScriptSerializer().Serialize(obj);

        }

        [WebMethod]
        public List<Usuario> optenerUsuarios()
        {
            return UsuarioLN.getInstance().Listar();
        }

        //servicios web para android en JSON
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void OptenerUsuariosInJson()
        {
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "correcto";
            datos.success = 1;
            datos.listDatos = UsuarioLN.getInstance().Listar();
            converyirToJson(datos);
            

        }
        //optener asignscion


        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void optenerAsignacion()
        {
            DatosJson<Asignacion> datos = new DatosJson<Asignacion>();

            datos.listDatos = AsignacionLN.getInstance().Listar();
            if (datos.listDatos.Count > 0)
            {
                datos.message = "correcto";
                datos.success = 1;

            }
            else
            {
                datos.message = "Error no hay datos";
                datos.success = 0;
            }
            converyirToJson(datos);


        }
        //optener asignscion id usuario


        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void optenerAsignacionIdUsuario(int id_usuario)
        {
            DatosJson<Asignacion> datos = new DatosJson<Asignacion>();
            List<Asignacion> lstRes = new List<Asignacion>();
           
            foreach (Asignacion item in AsignacionLN.getInstance().Listar())
            {
                if (item.usuario_.id_usuario==id_usuario)
                {
                    lstRes.Add(item);
                }
            }

            datos.listDatos = lstRes;

            if (datos.listDatos.Count > 0)
            {
                datos.message = "correcto hay datos";
                datos.success = 1;

            }
            else
            {
                datos.message = "Error no hay datos";
                datos.success = 0;
            }
            converyirToJson(datos);


        }

        #region dispositivo 
        //optener dispositivos
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void optenerDispositivos()
        {
            DatosJson<Dispositivo> datos = new DatosJson<Dispositivo>();
            
            datos.listDatos = DispositivoLN.getInstance().Listar();
            if (datos.listDatos.Count > 0)
            {
                datos.message = "correcto";
                datos.success = 1;

            }
            else {
                datos.message = "Error no hay datos";
                datos.success = 0;
            }
            converyirToJson(datos);


        }

        //insertar
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void insertarDispositivo(string descripcion , string imei,double latitud, double longitud, double altura)
        {
            Dispositivo item = new Dispositivo();
            item.descripcion = descripcion;
            item.imei = imei;
            item.latitud = latitud;
            item.longitud = longitud;
            item.altura = altura;
            DatosJson<Dispositivo> datos = new DatosJson<Dispositivo>();

            datos.success = DispositivoLN.getInstance().Ingreso(item);
            if (datos.success > 0)
            {
                datos.message = "correcto";
               

            }
            else
            {
                datos.message = "Error en la creacion";
                
            }
            converyirToJson(datos);


        }
        //update
        //servicios web para android en JSON  por id Rol
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void updateDispositivo(int id_dispositivo,string descripcion, string imei, string fecha_update_registro , double latitud, double longitud, double altura)
        {
            Dispositivo item = new Dispositivo();
            item.id_dispositivo = id_dispositivo;
            item.descripcion = descripcion;
            item.imei = imei;
            item.fecha_update_registro = Convert.ToDateTime(fecha_update_registro);
            item.latitud = latitud;
            item.longitud = longitud;
            item.altura = altura;
            DatosJson<Dispositivo> datos = new DatosJson<Dispositivo>();
            datos.listDatos = new List<Dispositivo>();
            datos.success = DispositivoLN.getInstance().Update(item);
            if (datos.success > 0)
            {
                datos.message = "correcto";


            }
            else
            {
                datos.message = "Error en la actulizacion";

            }
            converyirToJson(datos);


        }
        //delete
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void deleteDispositivo(int id_dispositivo)
        {
            Dispositivo item = new Dispositivo();
            item.id_dispositivo = id_dispositivo;
       
            DatosJson<Dispositivo> datos = new DatosJson<Dispositivo>();

            datos.success = DispositivoLN.getInstance().Delete(item);
            if (datos.success > 0)
            {
                datos.message = "correcto";


            }
            else
            {
                datos.message = "Error en la eliminacion";

            }
            converyirToJson(datos);


        }


        #endregion
        //---------------------------------------------------------------------USUARIO---------------------------
        //servicios web para android en JSON  por id Rol
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void OptenerUsuarioIdRolJson(int id_rol)
        {
            List<Usuario> lstUser = new List<Usuario>();
            foreach (Usuario item in UsuarioLN.getInstance().Listar())
            {
                if (item.rol.id_rol==id_rol)
                {
                    lstUser.Add(item);
                }
            }
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.listDatos = lstUser;
            if (lstUser.Count() > 0)
            {
                datos.message = "correcto";
                datos.success = 1;
            }
            else {
                datos.message = "No existe datos";
                datos.success = 0;

            }
            converyirToJson(datos);
            

        }


        //servicios web para android en JSON  por id Rol
        
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void VerificarUsuario(string usuario ,string password)
        {
            List<Asignacion> lstUser = new List<Asignacion>();
            Usuario user = new Usuario();
            user.usuario = usuario;
            user.contrasena = password;
            List<Asignacion> usuarioRes = AsignacionLN.getInstance().Logueo(user);
            if (usuarioRes.Count >0)
            {
                lstUser=usuarioRes;
            }
            
            DatosJson<Asignacion> datos = new DatosJson<Asignacion>();
            datos.listDatos = lstUser;
            if (lstUser.Count() > 0)
            {
                datos.message = "successful";
                datos.success = 1;
            }
            else
            {
                datos.message = "Fail Login..!";
                datos.success = 0;

            }
            converyirToJson(datos);


        }
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void insertarUsuario(int id_rol,string nombres,string apellidos,string cedula,string foto,string usuario ,string contrasena,int estado,int edad,string direccion)
        {
            Usuario item = new Usuario();
            item.rol.id_rol = id_rol;
            item.nombres = nombres;
            item.apellidos = apellidos;
            item.cedula = cedula;
            item.foto = foto;
            item.usuario = usuario;
            item.contrasena = contrasena;
            item.estado = estado;
            item.edad = edad;
            item.direccion = direccion;
            
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al insertar";
            datos.success = 0;
            if (UsuarioLN.getInstance().Ingreso(item)>=1)
            {
                datos.success = 1;
                datos.message = "ingreso Correcto";
            }
            
            datos.listDatos = null;
            converyirToJson(datos);


        }

        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void insertarUsuarioDispositivoTodo(int id_rol, string nombres, string apellidos, string cedula, string foto, string usuario, string contrasena, int estado, int edad, string direccion, string descripcion, string imei)
        {
            Usuario item = new Usuario();
            item.rol.id_rol = id_rol;
            item.nombres = nombres;
            item.apellidos = apellidos;
            item.cedula = cedula;
            item.foto = foto;
            item.usuario = usuario;
            item.contrasena = contrasena;
            item.estado = estado;
            item.edad = edad;
            item.direccion = direccion;

            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al insertar";
            datos.success = 0;
            if (UsuarioLN.getInstance().Ingreso(item) >= 1)
            {
                Dispositivo newDispositivo = new Dispositivo();
                newDispositivo.descripcion = descripcion;
                newDispositivo.imei = imei;
                newDispositivo.latitud = 0;
                newDispositivo.longitud = 0;
                newDispositivo.altura = 0;
                if (DispositivoLN.getInstance().Ingreso(newDispositivo) >= 1)
                {
                    Dispositivo newDispo = DispositivoLN.getInstance().BuscarPorImei(newDispositivo.imei);
                    Usuario us = UsuarioLN.getInstance().buscarUsuario_UserName(item.usuario);
                    if (newDispositivo != null && us != null)
                    {
                        Asignacion newasig = new Asignacion();
                        newasig.dispositivo = newDispo;
                        newasig.usuario_ = us;
                        newasig.propietario = 1;// para 
                        if (AsignacionLN.getInstance().Ingreso(newasig) >= 1)
                        {
                            datos.success = 1;
                            datos.message = "ingreso Correcto";
                        }
                        else {
                            datos.message = "error al insertar asignacion";
                        }
                    }
                    else {
                        datos.message = "error al recuperar datos";
                    }

                }
                else {
                    datos.message = "error al insertar dispoitivo";
                }  

                
            }

            datos.listDatos = null;
            converyirToJson(datos);


        }

        //update
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void updateUsuario(int id_usuario,int id_rol, string nombres, string apellidos, string cedula, string foto, string usuario, string contrasena, int estado, int edad, string direccion)
        {
            Usuario item = new Usuario();
            item.id_usuario = id_usuario;
            item.rol.id_rol = id_rol;
            item.nombres = nombres;
            item.apellidos = apellidos;
            item.cedula = cedula;
            item.foto = foto;
            item.usuario = usuario;
            item.contrasena = contrasena;
            item.estado = estado;
            item.edad = edad;
            item.direccion = direccion;

            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al actualizar";
            datos.success = 0;
            if (UsuarioLN.getInstance().Update(item) >= 1)
            {
                datos.success = 1;
                datos.message = "Correcto";
            }

            datos.listDatos = null;
            converyirToJson(datos);


        }
        //delete
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void deleteUsuario(int id_usuario)
        {
            Usuario item = new Usuario();
            item.id_usuario = id_usuario;
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al eliminar";
            datos.success = 0;
            if (UsuarioLN.getInstance().Eliminar(item) >= 1)
            {
                datos.success = 1;
                datos.message = "Correcto";
            }

            datos.listDatos = null;
            converyirToJson(datos);


        }
        //-----asignacionnnnn--------------------------------------------------
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void insertarAsignacion(int id_usuario,int id_dispositivo,int propietario)
        {
            Asignacion item = new Asignacion();
            item.usuario_.id_usuario = id_usuario;
            item.dispositivo.id_dispositivo = id_dispositivo;
            item.propietario = propietario;
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al insertar";
            datos.success = 0;
            if (AsignacionLN.getInstance().Ingreso(item) >= 1)
            {
                datos.success = 1;
                datos.message = "Correcto";
            }

            datos.listDatos = null;
            converyirToJson(datos);


        }
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void updateAsignacion(int id_asinacion,int id_usuario, int id_dispositivo, int propietario)
        {
            Asignacion item = new Asignacion();
            item.id_asignacion = id_asinacion;
            item.usuario_.id_usuario = id_usuario;
            item.dispositivo.id_dispositivo = id_dispositivo;
            item.propietario = propietario;
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al update";
            datos.success = 0;
            if (AsignacionLN.getInstance().Update(item) >= 1)
            {
                datos.success = 1;
                datos.message = "Correcto";
            }

            datos.listDatos = null;
            converyirToJson(datos);


        }
        [WebMethod]
        [System.Web.Script.Services.ScriptMethod(ResponseFormat = System.Web.Script.Services.ResponseFormat.Json)]
        public void deleteAsignacion(int id_asinacion)
        {
            Asignacion item = new Asignacion();
            item.id_asignacion = id_asinacion;
          
            DatosJson<Usuario> datos = new DatosJson<Usuario>();
            datos.message = "error al eliminar";
            datos.success = 0;
            if (AsignacionLN.getInstance().Remove(item) >= 1)
            {
                datos.success = 1;
                datos.message = "Correcto";
            }

            datos.listDatos = null;
            converyirToJson(datos);


        }
    }
}
