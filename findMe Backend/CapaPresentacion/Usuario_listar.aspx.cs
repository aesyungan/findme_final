using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CapaLogicaDeNegocio;
using CapaEntidades;
using System.Data;
using System.IO;

namespace CapaPresentacion
{
    public partial class Usuario_listar : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            cargarDatos();
        }

        public void cargarDatos() {
            try
            {



               lvwDatos.DataSource = UsuarioLN.getInstance().Listar();
             



                lvwDatos.DataBind();
            }
            catch (Exception ex)
            {

                Response.Write("<script>alert('" + ex.Message + "')</script>");
            }

        }

        private String cargarImagen() {
            string dir_save_Archivo = "";
            try
            {

                if (fileUploader1.HasFile)
                {
                    // Se verifica que la extensión sea de un formato válido
                    string ext = fileUploader1.PostedFile.FileName;
                    ext = ext.Substring(ext.LastIndexOf(".") + 1).ToLower();
                    string[] formatos = new string[] { "jpg", "jpeg", "bmp", "png", "gif" };
                    if (Array.IndexOf(formatos, ext) < 0)
                    {
                        Response.Write("<script>alert('Formato de imagen inválido.')</script>");
                    }
                    else { 
                        dir_save_Archivo = GuardarArchivo(fileUploader1.PostedFile);
                    }



                }
                else {
                    Response.Write("<script>alert('Seleccione un archivo del disco duro')</script>");
                   
                    }
                
            }
            catch (Exception ex)
            {
                Response.Write("<script>alert('" + ex.Message + "')</script>");
            }
            return dir_save_Archivo;
        }

        private String GuardarArchivo(HttpPostedFile file)
        {
           
            // Se carga la ruta física de la carpeta temp del sitio
            string ruta = Server.MapPath("~/images");
            string ruta_final = "images/";

            // Si el directorio no existe, crearlo
            if (!Directory.Exists(ruta)) 
                Directory.CreateDirectory(ruta);


            string archivo = String.Format("{0}\\{1}", ruta, file.FileName);

            // Verificar que el archivo no exista
            if (File.Exists(archivo))
                Response.Write("<script>alert('Ya existe una imagen con nombre "+ file.FileName + "')</script>");
          //  MensajeError(String.Format("Ya existe una imagen con nombre\"{0}\".", file.FileName));
            else
            {
                file.SaveAs(archivo);
                ruta_final += file.FileName.ToString();
            }
            return ruta_final;

        }

        protected void btnVer_Click(object sender, EventArgs e)
        {
            try
            {
             


               



                
            }
            catch (Exception ex)
            {

                Response.Write("<script>alert('" + ex.Message + "')</script>");
            }
          
        }

   


        protected void lvwDatos_SelectedIndexChanging(object sender, ListViewSelectEventArgs e)
        {

            
            lvwDatos.SelectedIndex = e.NewSelectedIndex;
//            ListViewDataItem dato = lvwDatos.SelectedValue as ListViewDataItem;
          

            string pid = lvwDatos.SelectedDataKey.Value.ToString();

            //string pid = ListView1.DataKeys[e.NewSelectedIndex].Value.ToString(); 

            //Label1.Text = "Selected usuario ID: " + pid;

            lvwDatos.DataBind();
            //Usuario dato = lvwDatos.Select as Usuario;
            //Response.Write("<script>alert('" + dato.id_usuario+"')</script>");
        }

        

        protected void lnkRemove_Command(object sender, CommandEventArgs e)
        {
           // Label1.Text = "You chose: " + e.CommandName + " Item " + e.CommandArgument;
            try
            {
                Usuario u = new Usuario();
                u.id_usuario = Convert.ToInt32(e.CommandArgument.ToString());
               int correcto= UsuarioLN.getInstance().Eliminar(u);
                if (correcto > 0)
                {
                    Response.Write("<script>alert(' Eliminacion Correcta')</script>");
                    cargarDatos();
                }
                else {
                    Response.Write("<script>alert('Error Al Eliminar')</script>");
                }
                

            }
            catch (Exception ex)
            {

                Response.Write("<script>alert('" + ex.Message + "')</script>");
            }

        }

        protected void btnAgregarNuevo_Click(object sender, EventArgs e)
        {
            
           

            if (txtContrasena.Text.Equals(txtContrasena2.Text))
            {
                try
                {
                    Usuario user = new Usuario();
                    user.rol.id_rol = 2;//estudiante
                    user.nombres = txtNombres.Text;
                    user.apellidos = txtApellidos.Text;
                    user.cedula = txtCedula.Text;
                    user.foto = "http://apprioturalexyungan.azurewebsites.net/"+cargarImagen();
                    user.usuario = txtUsuario.Text;
                    user.contrasena = txtContrasena.Text;
                    user.estado = 1;
                    user.edad = Convert.ToInt32(txtEdad.Text);
                    user.direccion = txtDireccion.Text;



                    int exito = UsuarioLN.getInstance().Ingreso(user);
                    if (exito > 0)
                    {
                        
                       // Response.Write("<script> alert('Ingreso Correcto')</script>");
                        Response.Redirect("Usuario_listar.aspx");

                    }
                    else
                    {
                        Response.Write("<script> alert('Error en El ingreso de Datos')</script>");
                    }

                }
                catch (Exception ex)
                {
                    Response.Write("<script>alert('" + ex.Message + "')</script>");

                }
            }
            else
            {
                Response.Write("<script> alert('Contraseñas No son Iguales')</script>");
            }
        }
    }
}