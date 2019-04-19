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
    public partial class Usuario_ingreso : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnAddUser_Click(object sender, EventArgs e)
        {

            Usuario user = new Usuario();
            user.rol.id_rol = 2;//estudiante
            user.nombres = txtNombres.Text;
            user.apellidos = txtApellidos.Text;
            user.cedula = txtCedula.Text;
            user.foto = "images/user.jpg";
            user.usuario = txtUsuario.Text;
            user.contrasena = txtContrasena.Text;
            user.estado = 1;
            user.edad = Convert.ToInt32(txtEdad.Text);
            user.direccion = txtDireccion.Text;

            if (user.contrasena.Equals(txtContrasena2.Text))
            {
                try
                {
                   int exito= UsuarioLN.getInstance().Ingreso(user);
                    if (exito > 0)
                    {
                        Response.Write("<script> alert('Ingreso Correcto')</script>");
                    }
                    else {
                        Response.Write("<script> alert('Error en El ingreso de Datos')</script>");
                    }

                }
                catch (Exception ex)
                {
                    Response.Write("<script>alert('" + ex.Message + "')</script>");

                }
            }
            else {
                Response.Write("<script> alert('Contraseñas No son Iguales')</script>");
            }


        }
    }
}