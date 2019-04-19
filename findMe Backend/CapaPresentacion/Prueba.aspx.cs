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
    public partial class Prueba : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)

        {
            try
            {
                migridDatos.DataSource = AsignacionLN.getInstance().Listar();
                    migridDatos.DataBind();
               // Response.Write("<script>alert('" + DispositivoLN.getInstance().Listar().Count + "')</script>");
            }
            catch (Exception ex)
            {

                Response.Write("<script>alert('" + ex.Message + "')</script>");
            }
          
        }
    }
}