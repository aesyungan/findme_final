using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CapaEntidades;
using CapaLogicaDeNegocio;

namespace CapaPresentacion
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            //si ya existe session se redirige
            if (Session["id_usuario"] != null)
            {
                Response.Redirect("Home.aspx");
            }
        }

        protected void btnIngreso_Click(object sender, EventArgs e)
        {
            //string user = txtUsuario.Text;
            //string password = txtUsuario.Text;
            //if (user.Equals("alex") && password.Equals("alex"))
            //{
            //    Response.Write("<script>alert('Ingreso Correcto')</script>");
            //}
            //else {
            //    Response.Write("<script>alert('Ingreso Incorrecto')</script>");
            //}

            Usuario usuario = new Usuario();
            usuario.usuario = txtUsuario.Text;
            
            usuario.contrasena = txtPassword.Text;

            try
            {
               Usuario user= UsuarioLN.getInstance().VerificarUsuario(usuario);

                if (user != null) 
                {
                    //Session.Clear();
                  
                    Session["id_usuario"] = user.id_usuario;
                    Session["id_rol"] = user.rol.id_rol;
                    Session["nombres"] = user.nombres;
                    Session["apellidos"] = user.apellidos;
                    Session["cedula"] = user.cedula;
                    Session["foto"] = user.foto;
                    Session["usuario"] = user.usuario;
                    Session["contrasena"] = user.contrasena;
                    Session["estado"] = user.estado;
                    Session["edad"] = user.edad;
                    Session["direccion"] = user.direccion;


                    Response.Redirect("Home.aspx");
                
                    //Response.Write("<script>alert('Bienvenido " + user.nombres + " " + user.apellidos + " ')</script>");
                  
                }else{
                    Response.Write("<script>alert('Ingreso Incorrecto')</script>");
                }
            }
            catch (Exception ex)
            {
                
                Response.Write("<script>alert('"+ex.Message+"')</script>");

            }
        }
    }
}