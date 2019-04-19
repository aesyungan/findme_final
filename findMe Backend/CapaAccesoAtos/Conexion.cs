using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaAccesoAtos
{
    public class Conexion
    {

        #region "Patron Singleton"
        private static Conexion conexion=null;
        private Conexion()
        {

        }

        public static Conexion getInstance() {
            if (conexion == null)
            {
                conexion = new Conexion();
            }
            return conexion;
        }
        #endregion


        public SqlConnection ConeccionDB() {
            SqlConnection conexion = new SqlConnection();
            conexion.ConnectionString = SettingsApp.Default.cadena_conexion;
            return conexion;
        }
    }
}
